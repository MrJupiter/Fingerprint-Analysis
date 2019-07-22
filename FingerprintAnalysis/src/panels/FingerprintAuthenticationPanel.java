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

import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;

import beans.ImageBean;
import beans.ScoreResultBean;
import beans.TemplateTextBean;
import utilies.StringNumeric;

public class FingerprintAuthenticationPanel extends JPanel implements PanelsInterface{

	private static final long serialVersionUID = 1L;
	
	private JTextField pathTextField1;
	private JTextField pathTextField2;
	private ImageBean originalImagePanel1;
	private ImageBean originalImagePanel2;
	private JButton browseButton1;
	private JButton browseButton2;
	private JButton authentificateButton;
	private ScoreResultBean scoreLabel;
	private JPanel authorisationColorPanel;
	private JTextField thresholdValueTextField;
	private JButton jColorChooser;
	
	public FingerprintAuthenticationPanel() {
		setLayout(null);
		JLabel title = new JLabel("Fingerprint Authentication");
		title.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 18));
		title.setHorizontalAlignment(SwingConstants.CENTER);
		title.setBounds(301, 3, 382, 34);
        add(title);
        
        JPanel panel_1 = new JPanel();
		panel_1.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		panel_1.setBackground(Color.WHITE);
		panel_1.setBounds(24, 35, 995, 53);
		add(panel_1);
		panel_1.setLayout(null);
        
		JPanel panel2 = new JPanel();
		panel2.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		panel2.setBackground(Color.WHITE);
		panel2.setBounds(24, 85, 995, 53);
		add(panel2);
		panel2.setLayout(null);
		
		browseButton1 = new JButton("Browse");
		browseButton1.setBounds(886, 12, 97, 25);
		browseButton1.setBackground(new Color(141, 61, 150));
		browseButton1.setForeground(new Color(255, 255, 255));
		panel_1.add(browseButton1);
		
		browseButton2 = new JButton("Browse");
		browseButton2.setBounds(886, 12, 97, 25);
		browseButton2.setBackground(new Color(141, 61, 150));
		browseButton2.setForeground(new Color(255, 255, 255));
		panel2.add(browseButton2);

		JLabel lblNewLabel = new JLabel("The first image:");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel.setBounds(12, 16, 191, 16);
		panel_1.add(lblNewLabel);
		
		JLabel lblNewLabel2 = new JLabel("The second image:");
		lblNewLabel2.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel2.setBounds(12, 16, 191, 16);
		panel2.add(lblNewLabel2);
		
		pathTextField1 = new JTextField();
		pathTextField1.setBounds(107, 13, 770, 22);
		panel_1.add(pathTextField1);
		pathTextField1.setColumns(10);
		
		pathTextField2 = new JTextField();
		pathTextField2.setBounds(125, 13, 752, 22);
		panel2.add(pathTextField2);
		pathTextField2.setColumns(10);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_2.setBackground(Color.WHITE);
		panel_2.setBounds(24, 140, 995, 497);
		add(panel_2);
		panel_2.setLayout(null);
		
		JLabel label = new JLabel("Fingerprint N°1");
		label.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 15));
		label.setBounds(148, 31, 105, 16);
		panel_2.add(label);
		
		jColorChooser = new JButton("Minutiae color");
		jColorChooser.setBounds(427, 130, 135, 25);
		jColorChooser.setBackground(new Color(141, 61, 150));
		jColorChooser.setForeground(new Color(255, 255, 255));
		panel_2.add(jColorChooser);
		
		JLabel lblThreshold = new JLabel("Threshold:");
		lblThreshold.setBounds(462, 160, 72, 18);
		panel_2.add(lblThreshold);
		
		thresholdValueTextField = new JTextField();
		thresholdValueTextField.setBounds(457, 180, 72, 26);
		thresholdValueTextField.setHorizontalAlignment(JTextField.CENTER);
		panel_2.add(thresholdValueTextField);
		
		authentificateButton = new JButton("Authenticate");
		authentificateButton.setFont(new Font("Times New Roman", Font.BOLD, 13));
		authentificateButton.setBounds(427, 227, 135, 25);
		authentificateButton.setBackground(new Color(141, 61, 150));
		authentificateButton.setForeground(new Color(255, 255, 255));
		panel_2.add(authentificateButton);
		
		authorisationColorPanel = new JPanel();
		authorisationColorPanel.setBounds(427, 270, 135, 25);
		panel_2.add(authorisationColorPanel);
		
		scoreLabel = new ScoreResultBean();
		scoreLabel.setBounds(450, 270, 106, 16);
		authorisationColorPanel.add(scoreLabel);

		JLabel label_1 = new JLabel("Fingerprint N°2");
		label_1.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 15));
		label_1.setBounds(722, 31, 126, 16);
		panel_2.add(label_1);
		
		JPanel panel_4 = new JPanel();
		panel_4.setBorder(new LineBorder(new Color(0, 0, 255), 2));
		panel_4.setBounds(11, 60, 404, 422);
		panel_2.add(panel_4);
		panel_4.setLayout(null);
		
		originalImagePanel1 = new ImageBean();
		originalImagePanel1.setBounds(12, 13, 380, 396);
		panel_4.add(originalImagePanel1);
		originalImagePanel1.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		
		JPanel panel_3 = new JPanel();
		panel_3.setLayout(null);
		panel_3.setBorder(new LineBorder(new Color(0, 0, 255), 2));
		panel_3.setBounds(574, 60, 404, 422);
		panel_2.add(panel_3);
		
		originalImagePanel2 = new ImageBean();
		originalImagePanel2.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		originalImagePanel2.setBounds(12, 13, 380, 396);
		panel_3.add(originalImagePanel2);
	}
	
	public void setListeners(JFrame frame) {
		browseButton1.addActionListener(new ActionListener() {
			@Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser repository = new JFileChooser();
                try {
                    repository.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
                    repository.showOpenDialog(frame);
                    repository.setBounds(600, 400, 600, 420);
                    pathTextField1.setText(repository.getSelectedFile().getAbsolutePath());
                    originalImagePanel1.setCurrentImage(pathTextField1.getText());
                }catch (Exception ex){
                    System.out.println("Error Message: " + ex.getMessage());
                }
            }
		});
		
		pathTextField1.addKeyListener(new KeyAdapter() {
			@Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);
                if(e.getKeyCode() == KeyEvent.VK_ENTER){
                    try {
                        originalImagePanel1.setCurrentImage(pathTextField1.getText());
                    }catch(Exception ex){
                        System.out.println("Error Message: " + ex.getMessage());
                    }
                }
            }
		});
		
		browseButton2.addActionListener(new ActionListener() {
			@Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser repository = new JFileChooser();
                try {
                    repository.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
                    repository.showOpenDialog(frame);
                    repository.setBounds(600, 400, 600, 420);
                    pathTextField2.setText(repository.getSelectedFile().getAbsolutePath());
                    originalImagePanel2.setCurrentImage(pathTextField2.getText());
                }catch (Exception ex){
                    System.out.println("Error Message: " + ex.getMessage());
                }
            }
		});
		
		pathTextField2.addKeyListener(new KeyAdapter() {
			@Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);
                if(e.getKeyCode() == KeyEvent.VK_ENTER){
                    try {
                        originalImagePanel2.setCurrentImage(pathTextField2.getText());
                    }catch(Exception ex){
                        System.out.println("Error Message: " + ex.getMessage());
                    }
                }
            }
		});
	
		authentificateButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String thresholdString = thresholdValueTextField.getText();
				if(!thresholdString.equals("") && !pathTextField1.getText().equals("") && !pathTextField2.getText().equals("")) {
					try {
                		if(StringNumeric.isNumeric(thresholdString)) {
                    		TemplateTextBean image2template = new TemplateTextBean();
                    		image2template.convertToTemplate(pathTextField1.getText(), "rubbish1", originalImagePanel1);
                    		image2template.convertToTemplate(pathTextField2.getText(), "rubbish2", originalImagePanel2);
    						String score = scoreLabel.getScoreResult("./trash/rubbish1.txt", "./trash/rubbish2.txt");
    						float threshold = Float.parseFloat(thresholdString);
    						authorisationColorPanel.setBackground((Double.parseDouble(score)>threshold)?new Color(0,255,0):new Color(255,0,0));
    						scoreLabel.setForeground((Double.parseDouble(score)>threshold)?new Color(0,0,0):new Color(255,255,255));
    						TemplateTextBean.clearRubbish("rubbish1");
    						TemplateTextBean.clearRubbish("rubbish2");
                    	}
					} catch (Exception e2) {
						e2.printStackTrace();
					}
				}
			}
		});
		
		thresholdValueTextField.addKeyListener(new KeyAdapter() {
			@Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);
                if(e.getKeyCode() == KeyEvent.VK_ENTER){
                	try {
                		String thresholdString = thresholdValueTextField.getText();
                		if(StringNumeric.isNumeric(thresholdString) && !thresholdString.equals("") && !pathTextField1.getText().equals("") && !pathTextField2.getText().equals("")) {
                    		TemplateTextBean image2template = new TemplateTextBean();
                    		image2template.convertToTemplate(pathTextField1.getText(), "rubbish1", originalImagePanel1);
                    		image2template.convertToTemplate(pathTextField2.getText(), "rubbish2", originalImagePanel2);
    						String score = scoreLabel.getScoreResult("./trash/rubbish1.txt", "./trash/rubbish2.txt");
    						float threshold = Float.parseFloat(thresholdString);
    						authorisationColorPanel.setBackground((Double.parseDouble(score)>threshold)?new Color(0,255,0):new Color(255,0,0));
    						scoreLabel.setForeground((Double.parseDouble(score)>threshold)?new Color(0,0,0):new Color(255,255,255));
    						TemplateTextBean.clearRubbish("rubbish1");
    						TemplateTextBean.clearRubbish("rubbish2");
                    	}
                    }catch(Exception ex){
                        System.out.println("Error Message: " + ex.getMessage());
                    }
                }
            }
		});
		
		jColorChooser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String thresholdString = thresholdValueTextField.getText();
        		if(StringNumeric.isNumeric(thresholdString) && !thresholdString.equals("") && !pathTextField1.getText().equals("") && !pathTextField2.getText().equals("")) {
					Color initialcolor = Color.RED;    
					Color color = JColorChooser.showDialog(new JPanel(),"Select a color",initialcolor);   
					originalImagePanel1.setCircleColor(color);
					originalImagePanel2.setCircleColor(color);
					try {
                    		TemplateTextBean image2template = new TemplateTextBean();
                    		image2template.convertToTemplate(pathTextField1.getText(), "rubbish1", originalImagePanel1);
                    		image2template.convertToTemplate(pathTextField2.getText(), "rubbish2", originalImagePanel2);
    						String score = scoreLabel.getScoreResult("./trash/rubbish1.txt", "./trash/rubbish2.txt");
    						float threshold = Float.parseFloat(thresholdString);
    						authorisationColorPanel.setBackground((Double.parseDouble(score)>threshold)?new Color(0,255,0):new Color(255,0,0));
    						scoreLabel.setForeground((Double.parseDouble(score)>threshold)?new Color(0,0,0):new Color(255,255,255));
    						TemplateTextBean.clearRubbish("rubbish1");
    						TemplateTextBean.clearRubbish("rubbish2");
					} catch (Exception e2) {
						e2.printStackTrace();
					}
				}
			}
		});
		
	}

}

