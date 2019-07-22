package view;

/**
 * 
 * @author Abdelmalik GHOUBIR
 * @date 17 June 2019
 * 
 */

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;

import panels.FingerprintAuthenticationPanel;
import panels.HackathonPanel;
import panels.ImageProcessingPanel;
import panels.MinutiaeExtractionPanel;
import utilies.Terminal;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.Font;

public class FingerprintsAnalysisUI {

	private JFrame frame;
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
				
		JPanel centralPanel = new JPanel();
		frame.getContentPane().add(centralPanel);
        centralPanel.setLayout(cardLayout);
        
        ImageProcessingPanel imageProcessingPanel = new ImageProcessingPanel();		
        centralPanel.add(imageProcessingPanel, "imageProcessingPanel");

        MinutiaeExtractionPanel minutiaeExtractionPanel = new MinutiaeExtractionPanel();
        centralPanel.add(minutiaeExtractionPanel, "minutaeExtractionPanel");
        
        FingerprintAuthenticationPanel fingerprintAuthenticationPanel = new FingerprintAuthenticationPanel();
        centralPanel.add(fingerprintAuthenticationPanel, "fingerprintAuthenticationPanel");
         
        HackathonPanel hackathonPanel = new HackathonPanel();
        centralPanel.add(hackathonPanel, "hackathonPanel");
        
        cardLayout.show(centralPanel, "imageProcessingPanel");
        JMenuBar menuBar = new JMenuBar();
        frame.setJMenuBar(menuBar);
        
        JMenuItem imageProcessingMenuItem = new JMenuItem("Image Processing");
        imageProcessingMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_I, InputEvent.CTRL_MASK));
        imageProcessingMenuItem.setFont(new Font("Segoe UI", Font.BOLD, 15));
        imageProcessingMenuItem.setBackground(new Color(141, 61, 150));
        imageProcessingMenuItem.setForeground(new Color(255, 255, 255));
        menuBar.add(imageProcessingMenuItem);

        JMenuItem minutiaeExtractionMenuItem = new JMenuItem("Minutiae Extraction");
        minutiaeExtractionMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_M, InputEvent.CTRL_MASK));
        menuBar.add(minutiaeExtractionMenuItem);
        
        JMenuItem fingerprintAuthentificationMenuItem = new JMenuItem("Fingerprint Authentication");
        fingerprintAuthentificationMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F, InputEvent.CTRL_MASK));
        menuBar.add(fingerprintAuthentificationMenuItem);
        
        JMenuItem hackathonMenuItem = new JMenuItem("Hackathon");
        hackathonMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_H, InputEvent.CTRL_MASK));
        menuBar.add(hackathonMenuItem);
        // Listeners:
		
        // Menu items Listeners:
        imageProcessingMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(centralPanel, "imageProcessingPanel");
                frame.setTitle("Image Processing");
                hackathonMenuItem.setFont(new Font("Segoe UI", Font.PLAIN, 15));
                imageProcessingMenuItem.setFont(new Font("Segoe UI", Font.BOLD, 15));
                fingerprintAuthentificationMenuItem.setFont(new Font("Segoe UI", Font.PLAIN, 15));
                minutiaeExtractionMenuItem.setFont(new Font("Segoe UI", Font.PLAIN, 15));

                imageProcessingMenuItem.setForeground(new Color(255, 255, 255));
                hackathonMenuItem.setForeground(new Color(0, 0, 0));
                fingerprintAuthentificationMenuItem.setForeground(new Color(0, 0, 0));
                minutiaeExtractionMenuItem.setForeground(new Color(0, 0, 0));

                imageProcessingMenuItem.setBackground(new Color(141, 61, 150));
                hackathonMenuItem.setBackground(new Color(240,240,240));
                fingerprintAuthentificationMenuItem.setBackground(new Color(240,240,240));
                minutiaeExtractionMenuItem.setBackground(new Color(240,240,240));
            }
        });
        
        fingerprintAuthentificationMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(centralPanel, "fingerprintAuthenticationPanel");
                frame.setTitle("Fingerprint Authentication");
                hackathonMenuItem.setFont(new Font("Segoe UI", Font.PLAIN, 15));
                fingerprintAuthentificationMenuItem.setFont(new Font("Segoe UI", Font.BOLD, 15));
                imageProcessingMenuItem.setFont(new Font("Segoe UI", Font.PLAIN, 15));
                minutiaeExtractionMenuItem.setFont(new Font("Segoe UI", Font.PLAIN, 15));

                fingerprintAuthentificationMenuItem.setForeground(new Color(255, 255, 255));
                hackathonMenuItem.setForeground(new Color(0, 0, 0));
                imageProcessingMenuItem.setForeground(new Color(0, 0, 0));
                minutiaeExtractionMenuItem.setForeground(new Color(0, 0, 0));
                
                fingerprintAuthentificationMenuItem.setBackground(new Color(141, 61, 150));
                hackathonMenuItem.setBackground(new Color(240,240,240));
                imageProcessingMenuItem.setBackground(new Color(240,240,240));
                minutiaeExtractionMenuItem.setBackground(new Color(240,240,240));
            }
        });
        
        minutiaeExtractionMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(centralPanel, "minutaeExtractionPanel");
                frame.setTitle("Minutiae Extraction");
                hackathonMenuItem.setFont(new Font("Segoe UI", Font.PLAIN, 15));
                minutiaeExtractionMenuItem.setFont(new Font("Segoe UI", Font.BOLD, 15));
                imageProcessingMenuItem.setFont(new Font("Segoe UI", Font.PLAIN, 15));
                fingerprintAuthentificationMenuItem.setFont(new Font("Segoe UI", Font.PLAIN, 15));

                minutiaeExtractionMenuItem.setForeground(new Color(255, 255, 255));
                hackathonMenuItem.setForeground(new Color(0, 0, 0));
                imageProcessingMenuItem.setForeground(new Color(0, 0, 0));
                fingerprintAuthentificationMenuItem.setForeground(new Color(0, 0, 0));
                
                minutiaeExtractionMenuItem.setBackground(new Color(141, 61, 150));
                hackathonMenuItem.setBackground(new Color(240,240,240));
                imageProcessingMenuItem.setBackground(new Color(240,240,240));
                fingerprintAuthentificationMenuItem.setBackground(new Color(240,240,240));
            }
        });
        
        hackathonMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(centralPanel, "hackathonPanel");
                frame.setTitle("Hackathon");
                hackathonMenuItem.setFont(new Font("Segoe UI", Font.BOLD, 15));
                minutiaeExtractionMenuItem.setFont(new Font("Segoe UI", Font.PLAIN, 15));
                imageProcessingMenuItem.setFont(new Font("Segoe UI", Font.PLAIN, 15));
                fingerprintAuthentificationMenuItem.setFont(new Font("Segoe UI", Font.PLAIN, 15));

                hackathonMenuItem.setForeground(new Color(255, 255, 255));
                minutiaeExtractionMenuItem.setForeground(new Color(0, 0, 0));
                imageProcessingMenuItem.setForeground(new Color(0, 0, 0));
                fingerprintAuthentificationMenuItem.setForeground(new Color(0, 0, 0));
                
                hackathonMenuItem.setBackground(new Color(141, 61, 150));
                minutiaeExtractionMenuItem.setBackground(new Color(240,240,240));
                imageProcessingMenuItem.setBackground(new Color(240,240,240));
                fingerprintAuthentificationMenuItem.setBackground(new Color(240,240,240));
            }
        });
        
        // Image Processing Panel Listeners
        imageProcessingPanel.setListeners(frame);
        // Minutiae Extraction Panel Listeners
        minutiaeExtractionPanel.setListeners(frame);
        // Fingerprint Authentication Panel Listeners
		fingerprintAuthenticationPanel.setListeners(frame);
        // HackathonPanel Listeners
		hackathonPanel.setListeners(frame);

		Terminal.executeCommand("rm *xyt *txt *brw *dm *hcm *lcm *lfm *min *qm *png *jpeg *jpg *gif");
	}

}