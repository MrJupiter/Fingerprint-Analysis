package panels;

/**
 * 
 * @author Abdelmalik GHOUBIR
 * @date 17 June 2019
 * 
 */

import java.awt.Color;
import java.awt.Font;
import java.awt.image.BufferedImage;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;

import beans.ImageBean;

public class ImageProcessingPanel extends JPanel{
	private JTextField pathTextField;
	private ImageBean originalImagePanel;
	private ImageBean convertedImagePanel;
	private BufferedImage bufferedConvertedImage;
	private JButton browseButton;
	private JButton btnToGray;
	private JButton saveImageButton;
	private JSpinner thresholdSpinner;
	private JButton btnToBinary;
	private JButton thinButton;
	private JLabel title;
	
	private static final long serialVersionUID = 1L;

	public ImageProcessingPanel() {
		setLayout(null);
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		panel_1.setBackground(Color.WHITE);
		panel_1.setBounds(24, 65, 995, 53);
		add(panel_1);
		panel_1.setLayout(null);
		
		title = new JLabel("Image Processing");
		title.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 20));
		title.setHorizontalAlignment(SwingConstants.CENTER);
		title.setBounds(301, 13, 382, 34);
		title.setLocation(304, 12);
        add(title);
        
		browseButton = new JButton("Browse...");
		browseButton.setBounds(886, 12, 97, 25);
		panel_1.add(browseButton);
		
		JLabel lblNewLabel = new JLabel("The absolute path to the image:");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel.setBounds(12, 16, 191, 16);
		panel_1.add(lblNewLabel);
		
		pathTextField = new JTextField();
		pathTextField.setBounds(202, 13, 672, 22);
		panel_1.add(pathTextField);
		pathTextField.setColumns(10);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_2.setBackground(Color.WHITE);
		panel_2.setBounds(24, 140, 995, 497);
		add(panel_2);
		panel_2.setLayout(null);
		
		JLabel label = new JLabel("Original Image");
		label.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 15));
		label.setBounds(148, 31, 105, 16);
		panel_2.add(label);
		
		btnToGray = new JButton("To Grayscale");
		btnToGray.setFont(new Font("Times New Roman", Font.BOLD, 13));
		btnToGray.setBounds(427, 177, 135, 25);
		panel_2.add(btnToGray);
		
		JLabel label_1 = new JLabel("Converted Image");
		label_1.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 15));
		label_1.setBounds(722, 31, 126, 16);
		panel_2.add(label_1);
		
		JPanel panel_4 = new JPanel();
		panel_4.setBorder(new LineBorder(new Color(0, 0, 255), 2));
		panel_4.setBounds(11, 60, 404, 422);
		panel_2.add(panel_4);
		panel_4.setLayout(null);
		
		originalImagePanel = new ImageBean();
		originalImagePanel.setBounds(12, 13, 380, 396);
		panel_4.add(originalImagePanel);
		originalImagePanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		
		JPanel panel_3 = new JPanel();
		panel_3.setLayout(null);
		panel_3.setBorder(new LineBorder(new Color(0, 0, 255), 2));
		panel_3.setBounds(574, 60, 404, 422);
		panel_2.add(panel_3);
		
		convertedImagePanel = new ImageBean();
		convertedImagePanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		convertedImagePanel.setBounds(12, 13, 380, 396);
		panel_3.add(convertedImagePanel);
		
		btnToBinary = new JButton("To Binary");
		btnToBinary.setFont(new Font("Times New Roman", Font.BOLD, 13));
		btnToBinary.setBounds(427, 246, 135, 25);
		panel_2.add(btnToBinary);
		
		saveImageButton = new JButton("Save");
		saveImageButton.setFont(new Font("Times New Roman", Font.BOLD, 13));
		saveImageButton.setBounds(427, 457, 135, 25);
		panel_2.add(saveImageButton);
		
		thresholdSpinner = new JSpinner();
		thresholdSpinner.setModel(new SpinnerNumberModel(new Integer(45), null, null, new Integer(1)));
		thresholdSpinner.setBounds(504, 215, 50, 22);
		panel_2.add(thresholdSpinner);
		
		JLabel lblThreshold = new JLabel("Threshold:");
		lblThreshold.setBounds(437, 217, 72, 18);
		panel_2.add(lblThreshold);
		
		thinButton = new JButton("Thin");
		thinButton.setFont(new Font("Times New Roman", Font.BOLD, 13));
		thinButton.setBounds(427, 304, 135, 25);
		panel_2.add(thinButton);
	}

	public JTextField getPathTextField() {
		return pathTextField;
	}

	public ImageBean getOriginalImagePanel() {
		return originalImagePanel;
	}

	public ImageBean getConvertedImagePanel() {
		return convertedImagePanel;
	}

	public BufferedImage getBufferedConvertedImage() {
		return bufferedConvertedImage;
	}

	public JButton getBrowseButton() {
		return browseButton;
	}

	public JButton getBtnToGray() {
		return btnToGray;
	}

	public JButton getSaveImageButton() {
		return saveImageButton;
	}

	public JSpinner getThresholdSpinner() {
		return thresholdSpinner;
	}

	public JButton getBtnToBinary() {
		return btnToBinary;
	}

	public JButton getThinButton() {
		return thinButton;
	}
	
	public void setPathTextFieldText(String text) {
		pathTextField.setText(text);
	}

	public String getPathTextFieldText() {
		return pathTextField.getText();
	}

	public void setBufferedConvertedImage(BufferedImage bufferedConvertedImage) {
		this.bufferedConvertedImage = bufferedConvertedImage;
	}
	
	public JLabel getTitle() {
		return title;
	}
}
