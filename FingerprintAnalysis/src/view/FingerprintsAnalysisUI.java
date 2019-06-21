package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;

import imageProcessing.ImageConvertor;
import imageProcessing.ImageProcessing;
import panels.FingerprintAuthenticationPanel;
import panels.ImageProcessingPanel;
import panels.MinutiaeExtractionPanel;
import utilies.StringNumeric;
import utilies.Terminal;
import utilies.Thin;

import javax.imageio.ImageIO;
import javax.swing.JColorChooser;
import javax.swing.JFileChooser;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileWriter;

import javax.swing.event.ChangeListener;

import beans.TemplateTextBean;

import javax.swing.event.ChangeEvent;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;
import java.awt.event.InputEvent;
import java.awt.Font;

public class FingerprintsAnalysisUI {

	private JFrame frame;
	private ImageConvertor imageConvertor;
	private CardLayout cardLayout = new CardLayout();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FingerprintsAnalysisUI window = new FingerprintsAnalysisUI();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public FingerprintsAnalysisUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame("Image Processing");
		frame.setResizable(false);
		frame.setBounds(100, 100, 1051, 726);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new GridLayout(1, 0, 0, 0));
		
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		frame.setLocation(dim.width/2-frame.getSize().width/2, dim.height/2-frame.getSize().height/2);
		
		imageConvertor = new ImageProcessing();
		
		JPanel centralPanel = new JPanel();
		frame.getContentPane().add(centralPanel);
        centralPanel.setLayout(cardLayout);
        
        ImageProcessingPanel imageProcessingPanel = new ImageProcessingPanel();		
        centralPanel.add(imageProcessingPanel, "imageProcessingPanel");

        MinutiaeExtractionPanel minutiaeExtractionPanel = new MinutiaeExtractionPanel();
        centralPanel.add(minutiaeExtractionPanel, "minutaeExtractionPanel");
        
        FingerprintAuthenticationPanel fingerprintAuthenticationPanel = new FingerprintAuthenticationPanel();
        centralPanel.add(fingerprintAuthenticationPanel, "fingerprintAuthenticationPanel");
         
        cardLayout.show(centralPanel, "imageProcessingPanel");
        JMenuBar menuBar = new JMenuBar();
        frame.setJMenuBar(menuBar);
        
        JMenuItem imageProcessingMenuItem = new JMenuItem("Image Processing");
        imageProcessingMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_I, InputEvent.CTRL_MASK));
        imageProcessingMenuItem.setFont(new Font("Segoe UI", Font.BOLD, 15));
        imageProcessingMenuItem.setBackground(new Color(0,255,255));
        menuBar.add(imageProcessingMenuItem);

        JMenuItem minutiaeExtractionMenuItem = new JMenuItem("Minutiae Extraction");
        minutiaeExtractionMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_M, InputEvent.CTRL_MASK));
        menuBar.add(minutiaeExtractionMenuItem);

        JMenuItem fingerprintAuthentificationMenuItem = new JMenuItem("Fingerprint Authentication");
        fingerprintAuthentificationMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F, InputEvent.CTRL_MASK));
        menuBar.add(fingerprintAuthentificationMenuItem);
        
        // Listeners:
		
        // Menu items Listeners:
        imageProcessingMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(centralPanel, "imageProcessingPanel");
                frame.setTitle("Image Processing");
                imageProcessingMenuItem.setFont(new Font("Segoe UI", Font.BOLD, 15));
                fingerprintAuthentificationMenuItem.setFont(new Font("Segoe UI", Font.PLAIN, 15));
                minutiaeExtractionMenuItem.setFont(new Font("Segoe UI", Font.PLAIN, 15));

                imageProcessingMenuItem.setBackground(new Color(0,255,255));
                fingerprintAuthentificationMenuItem.setBackground(new Color(240,240,240));
                minutiaeExtractionMenuItem.setBackground(new Color(240,240,240));
            }
        });
        
        fingerprintAuthentificationMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(centralPanel, "fingerprintAuthenticationPanel");
                frame.setTitle("Fingerprint Authentication");
                fingerprintAuthentificationMenuItem.setFont(new Font("Segoe UI", Font.BOLD, 15));
                imageProcessingMenuItem.setFont(new Font("Segoe UI", Font.PLAIN, 15));
                minutiaeExtractionMenuItem.setFont(new Font("Segoe UI", Font.PLAIN, 15));
                
                fingerprintAuthentificationMenuItem.setBackground(new Color(0,255,255));
                imageProcessingMenuItem.setBackground(new Color(240,240,240));
                minutiaeExtractionMenuItem.setBackground(new Color(240,240,240));
            }
        });
        
        minutiaeExtractionMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(centralPanel, "minutaeExtractionPanel");
                frame.setTitle("Minutiae Extraction");
                minutiaeExtractionMenuItem.setFont(new Font("Segoe UI", Font.BOLD, 15));
                imageProcessingMenuItem.setFont(new Font("Segoe UI", Font.PLAIN, 15));
                fingerprintAuthentificationMenuItem.setFont(new Font("Segoe UI", Font.PLAIN, 15));
                
                minutiaeExtractionMenuItem.setBackground(new Color(0,255,255));
                imageProcessingMenuItem.setBackground(new Color(240,240,240));
                fingerprintAuthentificationMenuItem.setBackground(new Color(240,240,240));
            }
        });
        
        // Image Processing Panel Listeners
        imageProcessingPanel.getBrowseButton().addActionListener(new ActionListener() {
			@Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser repository = new JFileChooser();
                try {
                    repository.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
                    repository.showOpenDialog(frame);
                    repository.setBounds(600, 400, 600, 420);
                    imageProcessingPanel.setPathTextFieldText(repository.getSelectedFile().getAbsolutePath());
                	imageProcessingPanel.getOriginalImagePanel().setCurrentImage(imageProcessingPanel.getPathTextFieldText());
                	imageConvertor.setPathInputImage(imageProcessingPanel.getPathTextFieldText());
                }catch (Exception ex){
                    System.out.println("Error Message: " + ex.getMessage());
                }
            }
		});
		
		imageProcessingPanel.getPathTextField().addKeyListener(new KeyAdapter() {
			@Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);
                if(e.getKeyCode() == KeyEvent.VK_ENTER){
                    try {
                    	
                    	imageProcessingPanel.getOriginalImagePanel().setCurrentImage(imageProcessingPanel.getPathTextFieldText());
                    	imageConvertor.setPathInputImage(imageProcessingPanel.getPathTextFieldText());
                    }catch(Exception ex){
                        System.out.println("Error Message: " + ex.getMessage());
                    }
                }
            }
		});
		
		imageProcessingPanel.getBtnToGray().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					imageProcessingPanel.setBufferedConvertedImage(imageConvertor.convertToGrayscale());
					imageProcessingPanel.getConvertedImagePanel().setCurrentImage(imageProcessingPanel.getBufferedConvertedImage());
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
		});
		
		imageProcessingPanel.getSaveImageButton().addActionListener(new ActionListener() {
			@Override
            public void actionPerformed(ActionEvent e) {
				if(imageProcessingPanel.getBufferedConvertedImage() != null) {
	                JFileChooser repository = new JFileChooser();
	                repository.setApproveButtonText("Save");
	                try {
	                    repository.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
	                    repository.showOpenDialog(frame);
	                    repository.setBounds(600, 400, 600, 420);
	                    File file = new File(repository.getSelectedFile().getAbsolutePath());
	                    String fileName = file.getName();
                    	ImageIO.write(imageProcessingPanel.getBufferedConvertedImage(), fileName.substring(fileName.lastIndexOf(".") + 1), file);
	                }catch (Exception ex){
	                    System.out.println("Error Message: " + ex.getMessage());
	                }
				}
            }
		});
		
		imageProcessingPanel.getThresholdSpinner().addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				try {
					imageProcessingPanel.setBufferedConvertedImage(imageConvertor.convertToBinary(Integer.parseInt(imageProcessingPanel.getThresholdSpinner().getValue().toString())));
					imageProcessingPanel.getConvertedImagePanel().setCurrentImage(imageProcessingPanel.getBufferedConvertedImage());
				}catch(Exception ex) {
					ex.printStackTrace();
				}
			}
		});

		imageProcessingPanel.getBtnToBinary().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					imageProcessingPanel.setBufferedConvertedImage(imageConvertor.convertToBinary(Integer.parseInt(imageProcessingPanel.getThresholdSpinner().getValue().toString())));
					imageProcessingPanel.getConvertedImagePanel().setCurrentImage(imageProcessingPanel.getBufferedConvertedImage());
				}catch(Exception ex) {
					ex.printStackTrace();
				}
			}
		});
	
		imageProcessingPanel.getThinButton().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					Thin thin = new Thin(imageProcessingPanel.getPathTextFieldText(), Integer.parseInt(imageProcessingPanel.getThresholdSpinner().getValue().toString()));
					imageProcessingPanel.setBufferedConvertedImage(thin.thinImage());
					imageProcessingPanel.getConvertedImagePanel().setCurrentImage(imageProcessingPanel.getBufferedConvertedImage());
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
		});
        
        // Minutiae Extraction Panel Listeners
        minutiaeExtractionPanel.getBrowseButton().addActionListener(new ActionListener() {
			@Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser repository = new JFileChooser();
                try {
                    repository.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
                    repository.showOpenDialog(frame);
                    repository.setBounds(600, 400, 600, 420);
                    minutiaeExtractionPanel.getPathTextField().setText(repository.getSelectedFile().getAbsolutePath());
                    minutiaeExtractionPanel.getOriginalImagePanel().setCurrentImage(minutiaeExtractionPanel.getPathTextField().getText());
                }catch (Exception ex){
                    System.out.println("Error Message: " + ex.getMessage());
                }
            }
		});
		
        minutiaeExtractionPanel.getPathTextField().addKeyListener(new KeyAdapter() {
			@Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);
                if(e.getKeyCode() == KeyEvent.VK_ENTER){
                    try {
    					minutiaeExtractionPanel.getOriginalImagePanel().setCurrentImage(minutiaeExtractionPanel.getPathTextField().getText());
                    }catch(Exception ex){
                        System.out.println("Error Message: " + ex.getMessage());
                    }
                }
            }
		});
		
		minutiaeExtractionPanel.getConvertToTemplateButton().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if(minutiaeExtractionPanel.getOriginalImagePanel().getCurrentImage() != null)
						minutiaeExtractionPanel.getTemplateTextBean().convertToTemplate(minutiaeExtractionPanel.getPathTextField().getText(), "rubbish", minutiaeExtractionPanel.getOriginalImagePanel());
						TemplateTextBean.clearRubbish("rubbish");
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
		});
		
		minutiaeExtractionPanel.getSaveMinutaeTemplateButton().addActionListener(new ActionListener() {
			@Override
            public void actionPerformed(ActionEvent e) {
				if(!minutiaeExtractionPanel.getTemplateTextBean().getText().equals("")) {
					JFileChooser repository = new JFileChooser();
		            repository.setApproveButtonText("Save");
		            try {
		               repository.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
		               repository.showOpenDialog(frame);
		               repository.setBounds(600, 400, 600, 420);
		               FileWriter fw = new FileWriter(repository.getSelectedFile().getAbsolutePath());
		               fw.write(minutiaeExtractionPanel.getTemplateTextBean().getText());
		               fw.close();
		            }catch (Exception ex){
		                System.out.println("Error Message: " + ex.getMessage());
		            }
				}
            }
		});
		
		minutiaeExtractionPanel.getjColorChooser().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
        		if(!minutiaeExtractionPanel.getPathTextField().getText().equals("")) {
					Color initialcolor=Color.RED;    
					Color color=JColorChooser.showDialog(minutiaeExtractionPanel,"Select a color",initialcolor);   
					minutiaeExtractionPanel.getOriginalImagePanel().setCircleColor(color);
					try {
                    		TemplateTextBean image2template = new TemplateTextBean();
                    		image2template.convertToTemplate(minutiaeExtractionPanel.getPathTextField().getText(), "rubbish", minutiaeExtractionPanel.getOriginalImagePanel());
    						TemplateTextBean.clearRubbish("rubbish");
					} catch (Exception e2) {
						e2.printStackTrace();
					}
				}
			}
		});
		
        // Fingerprint Authentication Panel Listeners
		fingerprintAuthenticationPanel.getBrowseButton1().addActionListener(new ActionListener() {
			@Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser repository = new JFileChooser();
                try {
                    repository.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
                    repository.showOpenDialog(frame);
                    repository.setBounds(600, 400, 600, 420);
                    fingerprintAuthenticationPanel.setPathTextField1(repository.getSelectedFile().getAbsolutePath());
                    fingerprintAuthenticationPanel.getOriginalImagePanel1().setCurrentImage(fingerprintAuthenticationPanel.getPathTextField1().getText());
                }catch (Exception ex){
                    System.out.println("Error Message: " + ex.getMessage());
                }
            }
		});
		
		fingerprintAuthenticationPanel.getPathTextField1().addKeyListener(new KeyAdapter() {
			@Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);
                if(e.getKeyCode() == KeyEvent.VK_ENTER){
                    try {
                        fingerprintAuthenticationPanel.getOriginalImagePanel1().setCurrentImage(fingerprintAuthenticationPanel.getPathTextField1().getText());
                    }catch(Exception ex){
                        System.out.println("Error Message: " + ex.getMessage());
                    }
                }
            }
		});
		
		fingerprintAuthenticationPanel.getBrowseButton2().addActionListener(new ActionListener() {
			@Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser repository = new JFileChooser();
                try {
                    repository.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
                    repository.showOpenDialog(frame);
                    repository.setBounds(600, 400, 600, 420);
                    fingerprintAuthenticationPanel.setPathTextField2(repository.getSelectedFile().getAbsolutePath());
                    fingerprintAuthenticationPanel.getOriginalImagePanel2().setCurrentImage(fingerprintAuthenticationPanel.getPathTextField2().getText());
                }catch (Exception ex){
                    System.out.println("Error Message: " + ex.getMessage());
                }
            }
		});
		
		fingerprintAuthenticationPanel.getPathTextField2().addKeyListener(new KeyAdapter() {
			@Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);
                if(e.getKeyCode() == KeyEvent.VK_ENTER){
                    try {
                        fingerprintAuthenticationPanel.getOriginalImagePanel2().setCurrentImage(fingerprintAuthenticationPanel.getPathTextField2().getText());
                    }catch(Exception ex){
                        System.out.println("Error Message: " + ex.getMessage());
                    }
                }
            }
		});
	
		fingerprintAuthenticationPanel.getAuthentificateButton().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!fingerprintAuthenticationPanel.getThresholdValueTextField().getText().equals("") && !fingerprintAuthenticationPanel.getPathTextField1().getText().equals("") && !fingerprintAuthenticationPanel.getPathTextField2().getText().equals("")) {
					try {
						String thresholdString = fingerprintAuthenticationPanel.getThresholdValueTextField().getText();
                		if(StringNumeric.isNumeric(thresholdString) && !thresholdString.equals("") && !fingerprintAuthenticationPanel.getPathTextField1().getText().equals("") && !fingerprintAuthenticationPanel.getPathTextField2().getText().equals("")) {
                    		TemplateTextBean image2template = new TemplateTextBean();
                    		image2template.convertToTemplate(fingerprintAuthenticationPanel.getPathTextField1().getText(), "rubbish1", fingerprintAuthenticationPanel.getOriginalImagePanel1());
                    		image2template.convertToTemplate(fingerprintAuthenticationPanel.getPathTextField2().getText(), "rubbish2", fingerprintAuthenticationPanel.getOriginalImagePanel2());
    						String score = fingerprintAuthenticationPanel.getScoreLabel().getScoreResult("./rubbish1.txt", "./rubbish2.txt");
    						float threshold = Float.parseFloat(thresholdString);
    						fingerprintAuthenticationPanel.getAuthorisationColorPanel().setBackground((Double.parseDouble(score)>threshold)?new Color(0,255,0):new Color(255,0,0));
    						fingerprintAuthenticationPanel.getScoreLabel().setForeground((Double.parseDouble(score)>threshold)?new Color(0,0,0):new Color(255,255,255));
    						TemplateTextBean.clearRubbish("rubbish1");
    						TemplateTextBean.clearRubbish("rubbish2");
                    	}
					} catch (Exception e2) {
						e2.printStackTrace();
					}
				}
			}
		});
		
		fingerprintAuthenticationPanel.getThresholdValueTextField().addKeyListener(new KeyAdapter() {
			@Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);
                if(e.getKeyCode() == KeyEvent.VK_ENTER){
                	try {
                		String thresholdString = fingerprintAuthenticationPanel.getThresholdValueTextField().getText();
                		if(StringNumeric.isNumeric(thresholdString) && !thresholdString.equals("") && !fingerprintAuthenticationPanel.getPathTextField1().getText().equals("") && !fingerprintAuthenticationPanel.getPathTextField2().getText().equals("")) {
                    		TemplateTextBean image2template = new TemplateTextBean();
                    		image2template.convertToTemplate(fingerprintAuthenticationPanel.getPathTextField1().getText(), "rubbish1", fingerprintAuthenticationPanel.getOriginalImagePanel1());
                    		image2template.convertToTemplate(fingerprintAuthenticationPanel.getPathTextField2().getText(), "rubbish2", fingerprintAuthenticationPanel.getOriginalImagePanel2());
    						String score = fingerprintAuthenticationPanel.getScoreLabel().getScoreResult("./rubbish1.txt", "./rubbish2.txt");
    						float threshold = Float.parseFloat(thresholdString);
    						fingerprintAuthenticationPanel.getAuthorisationColorPanel().setBackground((Double.parseDouble(score)>threshold)?new Color(0,255,0):new Color(255,0,0));
    						fingerprintAuthenticationPanel.getScoreLabel().setForeground((Double.parseDouble(score)>threshold)?new Color(0,0,0):new Color(255,255,255));
    						TemplateTextBean.clearRubbish("rubbish1");
    						TemplateTextBean.clearRubbish("rubbish2");
                    	}
                    }catch(Exception ex){
                        System.out.println("Error Message: " + ex.getMessage());
                    }
                }
            }
		});
		
		fingerprintAuthenticationPanel.getjColorChooser().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String thresholdString = fingerprintAuthenticationPanel.getThresholdValueTextField().getText();
        		if(StringNumeric.isNumeric(thresholdString) && !thresholdString.equals("") && !fingerprintAuthenticationPanel.getPathTextField1().getText().equals("") && !fingerprintAuthenticationPanel.getPathTextField2().getText().equals("")) {
					Color initialcolor=Color.RED;    
					Color color=JColorChooser.showDialog(fingerprintAuthenticationPanel,"Select a color",initialcolor);   
					fingerprintAuthenticationPanel.getOriginalImagePanel1().setCircleColor(color);
					fingerprintAuthenticationPanel.getOriginalImagePanel2().setCircleColor(color);
					try {
                    		TemplateTextBean image2template = new TemplateTextBean();
                    		image2template.convertToTemplate(fingerprintAuthenticationPanel.getPathTextField1().getText(), "rubbish1", fingerprintAuthenticationPanel.getOriginalImagePanel1());
                    		image2template.convertToTemplate(fingerprintAuthenticationPanel.getPathTextField2().getText(), "rubbish2", fingerprintAuthenticationPanel.getOriginalImagePanel2());
    						String score = fingerprintAuthenticationPanel.getScoreLabel().getScoreResult("./rubbish1.txt", "./rubbish2.txt");
    						float threshold = Float.parseFloat(thresholdString);
    						fingerprintAuthenticationPanel.getAuthorisationColorPanel().setBackground((Double.parseDouble(score)>threshold)?new Color(0,255,0):new Color(255,0,0));
    						fingerprintAuthenticationPanel.getScoreLabel().setForeground((Double.parseDouble(score)>threshold)?new Color(0,0,0):new Color(255,255,255));
    						TemplateTextBean.clearRubbish("rubbish1");
    						TemplateTextBean.clearRubbish("rubbish2");
					} catch (Exception e2) {
						e2.printStackTrace();
					}
				}
			}
		});
		
		Terminal.executeCommand("rm *xyt *txt *brw *dm *hcm *lcm *lfm *min *qm *png *jpeg *jpg *gif");
	}
}