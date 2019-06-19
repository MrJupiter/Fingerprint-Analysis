package panels;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;

import beans.ImageBean;
import beans.TemplateTextBean;

public class MinutiaeExtractionPanel extends JPanel{
	
	private static final long serialVersionUID = 1L;

	private JTextField pathTextField;
	private ImageBean originalImagePanel;
	private TemplateTextBean templateTextBean;
	private JButton browseButton;
	private JButton convertToTemplateButton;
	private JButton saveMinutaeTemplateButton;
	private JLabel title;
	
	public MinutiaeExtractionPanel() {
		setLayout(null);
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		panel_1.setBackground(Color.WHITE);
		panel_1.setBounds(24, 65, 995, 53);
		add(panel_1);
		panel_1.setLayout(null);
		
		title = new JLabel("Minutiae Detector");
		title.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 18));
		title.setHorizontalAlignment(SwingConstants.CENTER);
		title.setBounds(301, 13, 382, 34);
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
		
		JLabel label = new JLabel("Image");
		label.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 15));
		label.setBounds(148, 31, 105, 16);
		panel_2.add(label);
		
		convertToTemplateButton = new JButton("To Template");
		convertToTemplateButton.setFont(new Font("Times New Roman", Font.BOLD, 13));
		convertToTemplateButton.setBounds(427, 227, 135, 35);
		panel_2.add(convertToTemplateButton);
		
		JLabel label_1 = new JLabel("Minutiae Template");
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
		
		templateTextBean = new TemplateTextBean();
		JScrollPane sampleScrollPane = new JScrollPane(templateTextBean, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		sampleScrollPane.setBounds(12, 13, 380, 396);
		panel_3.add(sampleScrollPane);

		saveMinutaeTemplateButton = new JButton("Save");
		saveMinutaeTemplateButton.setFont(new Font("Times New Roman", Font.BOLD, 13));
		saveMinutaeTemplateButton.setBounds(427, 457, 135, 25);
		panel_2.add(saveMinutaeTemplateButton);	
	}

	public JTextField getPathTextField() {
		return pathTextField;
	}

	public ImageBean getOriginalImagePanel() {
		return originalImagePanel;
	}

	public TemplateTextBean getTemplateTextBean() {
		return templateTextBean;
	}

	public JButton getBrowseButton() {
		return browseButton;
	}
	
	public JButton getConvertToTemplateButton() {
		return convertToTemplateButton;
	}
	
	public JButton getSaveMinutaeTemplateButton() {
		return saveMinutaeTemplateButton;
	}

	public JLabel getTitle() {
		return title;
	}
	
}
