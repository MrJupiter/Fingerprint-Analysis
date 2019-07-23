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
import utilies.FileConvertor;
import utilies.Terminal;

public class BddDirectoryBean extends JPanel implements Serializable {

	private static final long serialVersionUID = 1L;
	private String _directoryAbsolutePathString;
	BufferedImage mccRocCurve;
	BufferedImage userAlgorithmRocCurve;

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
		UserAlgorithm userAlgorithm = new UserAlgorithm();
		userAlgorithm.generateFile("userAlgoRocCurve.txt", "trash//userALgoScores.txt");

		Terminal.executeCommand("./exe/Evaluation/Eva.Biometrics.Console.Greyc.Evaluation.exe trash//scoreTempBis.txt MccRocCuve.txt EvaluationConfiguration.json");
		if ((new File("MccRocCuve_Roc.pdf")).exists()) mccRocCurve = ExtractImageFromPdf.PDF2BufferedImage("MccRocCuve_Roc.pdf");

		Terminal.executeCommand("./exe/Evaluation/Eva.Biometrics.Console.Greyc.Evaluation.exe trash//userALgoScores.txt userAlgoRocCurve.txt EvaluationConfiguration.json");
		if ((new File("userAlgoRocCurve_Roc.pdf")).exists()) userAlgorithmRocCurve = ExtractImageFromPdf.PDF2BufferedImage("userAlgoRocCurve_Roc.pdf");

		Terminal.executeCommand("rm *.pdf *txt");
	}

	private void executeMindtctSDK() throws Exception {
		String executableMindtctString = "./exe\\mindtct.exe";
		File[] listOfFiles = new File(_directoryAbsolutePathString).listFiles();
		for (File file: listOfFiles) {
			if (file.isFile()) {
				if (file.getName().endsWith(".png") || file.getName().endsWith(".jpg") || file.getName().endsWith(".jpeg")) {
					File fileBis = file;
					if (ImageIO.read(file).getColorModel().getPixelSize() != 8) {
						ImageConvertor imageConvertor = new ImageProcessing();
						imageConvertor.setPathInputImage(file.getAbsolutePath());
						BufferedImage convertedImage = imageConvertor.convertToGrayscale();
						fileBis = new File("trash\\" + getFileNameWithoutExtension(file) + ".png");
						ImageIO.write(convertedImage, "png", fileBis);
					}
					Terminal.executeCommand(executableMindtctString + " -m1 " + fileBis.getAbsolutePath() + " " + getFileNameWithoutExtension(fileBis));
					Terminal.executeCommand("rm *brw *dm *hcm *lcm *lfm *min *qm");
					System.out.println("Generating .\\trash\\" + getFileNameWithoutExtension(fileBis) + ".txt");
					FileConvertor.convertIt(fileBis.getAbsolutePath(), getFileNameWithoutExtension(fileBis));
					Terminal.executeCommand("rm *xyt *png");
				}
			}
		}
	}

	private void executeMCCSDK() throws Exception {
		String executableMccMatcher = "./exe\\MCCSdkV2.0\\SourceCode\\C#\\bin\\Debug\\MccMatcher.exe";
		String MccPaperMatchParameters = "./exe\\MCCSdkV2.0\\Executables\\MccPaperMatchParameters.xml";
		File[] listOfFiles = new File("trash").listFiles();
		for (File file1: listOfFiles) {
			if (file1.isFile()) {
				if (file1.getName().endsWith(".txt")) {
					for (File file2: listOfFiles) {
						if (file2.isFile()) {
							if (file2.getName().endsWith(".txt")) {
								System.out.println("Comparison score between .\\trash\\" + getFileNameWithoutExtension(file1) + ".txt and " + getFileNameWithoutExtension(file2) + ".txt");
								Terminal.executeCommand(executableMccMatcher + " " + file1.getAbsolutePath() + " " + file2.getAbsolutePath() + " " + MccPaperMatchParameters + " trash/scoreTemp.txt");
							}
						}
					}
				}
			}
		}
	}

	private String getFileNameWithoutExtension(File file) throws Exception {
		String fileName = "";
		if (file != null && file.exists()) fileName = file.getName().replaceFirst("[.][^.]+$", "");
		return fileName;
	}

	private void adaptIt() throws Exception {
		FileWriter fw = new FileWriter("trash//scoreTempBis.txt");
		Scanner scanner = new Scanner(new File("trash//scoreTemp.txt"));
		String regex = "(\\s)+";
		String[] header = scanner.nextLine().split(regex);

		fw.write(getFileNameWithoutExtension(new File(header[0])) + ".txt " + getFileNameWithoutExtension(new File(header[1])) + ".txt ");
		String[] idenAndAcq1 = getFileNameWithoutExtension(new File(header[0])).split("_");
		String[] idenAndAcq2 = getFileNameWithoutExtension(new File(header[1])).split("_");
		fw.write(idenAndAcq1[0].equals(idenAndAcq2[0]) ? "Intra": "Inter");
		fw.write(" " + header[3] + "\n");

		while (scanner.hasNext()) {
			String[] row = scanner.nextLine().split(regex);
			fw.write(getFileNameWithoutExtension(new File(row[0])) + ".txt " + getFileNameWithoutExtension(new File(row[1])) + ".txt ");
			String[] idenAndAcq3 = getFileNameWithoutExtension(new File(row[0])).split("_");
			String[] idenAndAcq4 = getFileNameWithoutExtension(new File(row[1])).split("_");
			fw.write(idenAndAcq3[0].equals(idenAndAcq4[0]) ? "Intra": "Inter");
			fw.write(" " + row[3] + "\n");
		}
		scanner.close();
		fw.close();
	}

}