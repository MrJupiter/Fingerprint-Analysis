package beans;

/**
 * 
 * @author Abdelmalik GHOUBIR
 * @date 17 June 2019
 * 
 */

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.Serializable;
import java.rmi.server.RMIClassLoader;
import java.util.Scanner;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;

import imageProcessing.ImageConvertor;
import imageProcessing.ImageProcessing;
import utilies.FileConvertor;
import utilies.Terminal;

public class TemplateTextBean extends JTextArea implements Serializable {

	private static final long serialVersionUID = 1L;

	public TemplateTextBean() {
		super();
		setEditable(false);
	}

	public void convertToTemplate(String pathImage, String resultFileNameWithoutExtension, ImageBean imageBean) throws Exception {
		setEditable(true);
		setText("");

		File file = new File(pathImage);
		File fileBis = file;
		if (ImageIO.read(file).getColorModel().getPixelSize() != 8) {
			ImageConvertor imageConvertor = new ImageProcessing();
			imageConvertor.setPathInputImage(file.getAbsolutePath());
			BufferedImage convertedImage = imageConvertor.convertToGrayscale();
			fileBis = new File("trash\\" + getFileNameWithoutExtension(file) + ".png");
			ImageIO.write(convertedImage, "png", fileBis);
		}

		if(ImageIO.read(file).getColorModel().getPixelSize() == 1)
	        JOptionPane.showMessageDialog(null, "Do not use binary images!", "Error", JOptionPane.INFORMATION_MESSAGE);
		else {
			String executableMindtctString = "./resources/exe\\mindtct.exe";
			Terminal.executeCommand(executableMindtctString + " -m1 " + fileBis.getAbsolutePath() + " " + resultFileNameWithoutExtension);
			Terminal.executeCommand("rm *brw *dm *hcm *lcm *lfm *min *qm");
			FileConvertor.convertIt(fileBis.getAbsolutePath(), resultFileNameWithoutExtension);
			if ((new File("trash//" + resultFileNameWithoutExtension + ".txt")).exists()) {
				BufferedReader reader = new BufferedReader(new FileReader("trash//" + resultFileNameWithoutExtension + ".txt"));
				String line = reader.readLine();
				while (line != null) {
					setText(getText() + line + "\n");
					line = reader.readLine();
				}
				reader.close();
			}
			drawMinutaes(resultFileNameWithoutExtension, imageBean);
		}
		setEditable(false);
	}

	public static void clearRubbish(String resultFileNameWithoutExtension) {
		Terminal.executeCommand("rm " + resultFileNameWithoutExtension + ".xyt trash//*");
	}

	private void drawMinutaes(String resultFileNameWithoutExtension, ImageBean imageBean) throws Exception {
		if ((new File(resultFileNameWithoutExtension + ".xyt")).exists()) {
			Scanner scanner = new Scanner(new File(resultFileNameWithoutExtension + ".xyt"));
			String regex = "(\\s)+";
			String[] header = scanner.nextLine().split(regex);

			BufferedImage image = ImageIO.read(new File(imageBean.getImageAbsolutePathString()));
			int height = image.getHeight();
			int width = image.getWidth();

			imageBean.drawMinutiae(imageBean.getGraphics(), imageBean.getWidth() * Integer.parseInt(header[0]) / width, imageBean.getHeight() * Integer.parseInt(header[1]) / height);
			while (scanner.hasNext()) {
				String[] row = scanner.nextLine().split(regex);
				imageBean.drawMinutiae(imageBean.getGraphics(), imageBean.getWidth() * Integer.parseInt(row[0]) / width, imageBean.getHeight() * Integer.parseInt(row[1]) / height);
			}
			scanner.close();
		}
	}
	
	private String getFileNameWithoutExtension(File file) throws Exception {
		String fileName = "";
		if (file != null && file.exists()) fileName = file.getName().replaceFirst("[.][^.]+$", "");
		return fileName;
	}

}