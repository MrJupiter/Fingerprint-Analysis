package panels;

/**
 * 
 * @author Abdelmalik GHOUBIR
 * @date 17 June 2019
 * 
 */

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.FileWriter;

import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;

import beans.ImageBean;
import beans.TemplateTextBean;

public class MinutiaeExtractionPanel extends JPanel implements PanelsInterface {

	private static final long serialVersionUID = 1L;

	private JTextField pathTextField;
	private ImageBean originalImagePanel;
	private TemplateTextBean templateTextBean;
	private JButton browseButton;
	private JButton convertToTemplateButton;
	private JButton saveMinutaeTemplateButton;
	private JLabel title;
	private JButton jColorChooser;

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

		browseButton = new JButton("Browse");
		browseButton.setBounds(886, 12, 97, 25);
		browseButton.setBackground(new Color(141, 61, 150));
		browseButton.setForeground(new Color(255, 255, 255));
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
		convertToTemplateButton.setBounds(427, 247, 135, 25);
		convertToTemplateButton.setBackground(new Color(141, 61, 150));
		convertToTemplateButton.setForeground(new Color(255, 255, 255));
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

		jColorChooser = new JButton("Minutiae color");
		jColorChooser.setBounds(427, 215, 135, 25);
		jColorChooser.setBackground(new Color(141, 61, 150));
		jColorChooser.setForeground(new Color(255, 255, 255));
		panel_2.add(jColorChooser);

		saveMinutaeTemplateButton = new JButton("Save");
		saveMinutaeTemplateButton.setFont(new Font("Times New Roman", Font.BOLD, 13));
		saveMinutaeTemplateButton.setBounds(427, 457, 135, 25);
		saveMinutaeTemplateButton.setBackground(new Color(141, 61, 150));
		saveMinutaeTemplateButton.setForeground(new Color(255, 255, 255));
		panel_2.add(saveMinutaeTemplateButton);
	}

	public void setListeners(JFrame frame) {
		browseButton.addActionListener(new ActionListener() {@Override
			public void actionPerformed(ActionEvent e) {
				JFileChooser repository = new JFileChooser();
				try {
					repository.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
					repository.showOpenDialog(frame);
					repository.setBounds(600, 400, 600, 420);
					pathTextField.setText(repository.getSelectedFile().getAbsolutePath());
					originalImagePanel.setCurrentImage(pathTextField.getText());
				} catch(Exception ex) {
					System.out.println("Error Message: " + ex.getMessage());
				}
			}
		});

		pathTextField.addKeyListener(new KeyAdapter() {@Override
			public void keyPressed(KeyEvent e) {
				super.keyPressed(e);
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					try {
						originalImagePanel.setCurrentImage(pathTextField.getText());
					} catch(Exception ex) {
						System.out.println("Error Message: " + ex.getMessage());
					}
				}
			}
		});

		convertToTemplateButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (originalImagePanel.getCurrentImage() != null) templateTextBean.convertToTemplate(pathTextField.getText(), "rubbish", originalImagePanel);
					TemplateTextBean.clearRubbish("rubbish");
				} catch(Exception e2) {
					e2.printStackTrace();
				}
			}
		});

		saveMinutaeTemplateButton.addActionListener(new ActionListener() {@Override
			public void actionPerformed(ActionEvent e) {
				if (!templateTextBean.getText().equals("")) {
					JFileChooser repository = new JFileChooser();
					repository.setApproveButtonText("Save");
					try {
						repository.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
						repository.showOpenDialog(frame);
						repository.setBounds(600, 400, 600, 420);
						FileWriter fw = new FileWriter(repository.getSelectedFile().getAbsolutePath());
						fw.write(templateTextBean.getText());
						fw.close();
					} catch(Exception ex) {
						System.out.println("Error Message: " + ex.getMessage());
					}
				}
			}
		});

		jColorChooser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!pathTextField.getText().equals("")) {
					Color initialcolor = Color.RED;
					Color color = JColorChooser.showDialog(new JPanel(), "Select a color", initialcolor);
					originalImagePanel.setCircleColor(color);
					try {
						TemplateTextBean image2template = new TemplateTextBean();
						image2template.convertToTemplate(pathTextField.getText(), "rubbish", originalImagePanel);
						TemplateTextBean.clearRubbish("rubbish");
					} catch(Exception e2) {
						e2.printStackTrace();
					}
				}
			}
		});
	}

}