package beans;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileWriter;
import java.io.Serializable;
import java.util.Scanner;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import imageProcessing.ImageConvertor;
import imageProcessing.ImageProcessing;
import userAlgorithm.UserAlgorithm;
import utilies.ExtractImageFromPdf;
import utilies.FileManipulator;
import utilies.Terminal;

public class BddDirectoryBean extends JPanel implements Serializable {

	private static final long serialVersionUID = 1L;
	private String _directoryAbsolutePathString;
	private BufferedImage mccRocCurve;
	private BufferedImage userAlgorithmRocCurve;

	public BddDirectoryBean() {
		super();
	}

	public void setDirectoryAbsolutePathString(String _directoryAbsolutePathString) {
		this._directoryAbsolutePathString = _directoryAbsolutePathString;
	}

	public BufferedImage getMccRocCurve() {
		return mccRocCurve;
	}

	public BufferedImage getUserAlgorithmRocCurve() {
		return userAlgorithmRocCurve;
	}

	public void startTheHackathon() throws Exception {
		executeMindtctSDK();
		executeMCCSDK();
		adaptIt();
		
		String rocCurveEvaluationExecutable = "./resources/exe/Evaluation/Eva.Biometrics.Console.Greyc.Evaluation.exe";
		String rocCurveEvaluationJSONConfig = "./resources/EvaluationConfiguration.json";
		
		Terminal.executeCommand(rocCurveEvaluationExecutable + " trash//MCCScoreTempBis.txt MccRocCuve.txt " + rocCurveEvaluationJSONConfig);
		if ((new File("MccRocCuve_Roc.pdf")).exists()) 
			mccRocCurve = ExtractImageFromPdf.PDF2BufferedImage("MccRocCuve_Roc.pdf");

		UserAlgorithm userAlgorithm = new UserAlgorithm();
		userAlgorithm.generateFile("trash//userALgoScores.txt");

		Terminal.executeCommand(rocCurveEvaluationExecutable + " trash//userALgoScores.txt userAlgoRocCurve.txt " + rocCurveEvaluationJSONConfig);
		if ((new File("userAlgoRocCurve_Roc.pdf")).exists()) 
			userAlgorithmRocCurve = ExtractImageFromPdf.PDF2BufferedImage("userAlgoRocCurve_Roc.pdf");

		Terminal.executeCommand("rm *.pdf *txt  trash//*");
	}

	private void executeMindtctSDK() throws Exception {
		String executableMindtctString = "./resources/exe\\mindtct.exe";
		File[] listOfFiles = new File(_directoryAbsolutePathString).listFiles();
		for (File file: listOfFiles) {
			if (file.isFile()) {
				if (file.getName().endsWith(".png") || file.getName().endsWith(".jpg") || file.getName().endsWith(".jpeg")) {
					File fileBis = file;
					if (ImageIO.read(file).getColorModel().getPixelSize() != 8) {
						ImageConvertor imageConvertor = new ImageProcessing();
						imageConvertor.setPathInputImage(file.getAbsolutePath());
						BufferedImage convertedImage = imageConvertor.convertToGrayscale();
						fileBis = new File("trash\\" + FileManipulator.getFileNameWithoutExtension(file) + ".png");
						ImageIO.write(convertedImage, "png", fileBis);
					}
					Terminal.executeCommand(executableMindtctString + " -m1 " + fileBis.getAbsolutePath() + " " + FileManipulator.getFileNameWithoutExtension(fileBis));
					System.out.println("Generating .\\trash\\" + FileManipulator.getFileNameWithoutExtension(fileBis) + ".txt");
					FileManipulator.convertIt(fileBis.getAbsolutePath(), FileManipulator.getFileNameWithoutExtension(fileBis));
					Terminal.executeCommand("rm *brw *dm *hcm *lcm *lfm *min *qm *xyt *png");
				}
			}
		}
	}

	private void executeMCCSDK() throws Exception {
		String executableMccMatcher = "./resources/exe\\MCCSdkV2.0\\SourceCode\\C#\\bin\\Debug\\MccMatcher.exe";
		String MccPaperMatchParameters = "./resources/exe\\MCCSdkV2.0\\Executables\\MccPaperMatchParameters.xml";
		File[] listOfFiles = new File("trash").listFiles();
		for (File file1: listOfFiles) {
			if (file1.isFile() && file1.getName().endsWith(".txt")) {
					for (File file2: listOfFiles) {
						if (file2.isFile() && file2.getName().endsWith(".txt")) {
								System.out.println("Comparison score between .\\trash\\" + FileManipulator.getFileNameWithoutExtension(file1) + ".txt and " + FileManipulator.getFileNameWithoutExtension(file2) + ".txt");
								Terminal.executeCommand(executableMccMatcher + " " + file1.getAbsolutePath() + " " + file2.getAbsolutePath() + " " + MccPaperMatchParameters + " trash/MCCScoreTemp.txt");
						}
					}
			}
		}
	}

	private void adaptIt() throws Exception {
		FileWriter fw = new FileWriter("trash//MCCScoreTempBis.txt");
		Scanner scanner = new Scanner(new File("trash//MCCScoreTemp.txt"));
		String regex = "(\\s)+";
		String[] header = scanner.nextLine().split(regex);

		fw.write(FileManipulator.getFileNameWithoutExtension(new File(header[0])) + ".txt " + FileManipulator.getFileNameWithoutExtension(new File(header[1])) + ".txt ");
		String[] idenAndAcq1 = FileManipulator.getFileNameWithoutExtension(new File(header[0])).split("_");
		String[] idenAndAcq2 = FileManipulator.getFileNameWithoutExtension(new File(header[1])).split("_");
		fw.write(idenAndAcq1[0].equals(idenAndAcq2[0]) ? "Intra": "Inter");
		fw.write(" " + header[3] + "\n");

		while (scanner.hasNext()) {
			String[] row = scanner.nextLine().split(regex);
			fw.write(FileManipulator.getFileNameWithoutExtension(new File(row[0])) + ".txt " + FileManipulator.getFileNameWithoutExtension(new File(row[1])) + ".txt ");
			String[] idenAndAcq3 = FileManipulator.getFileNameWithoutExtension(new File(row[0])).split("_");
			String[] idenAndAcq4 = FileManipulator.getFileNameWithoutExtension(new File(row[1])).split("_");
			fw.write(idenAndAcq3[0].equals(idenAndAcq4[0]) ? "Intra": "Inter");
			fw.write(" " + row[3] + "\n");
		}
		scanner.close();
		fw.close();
	}

}