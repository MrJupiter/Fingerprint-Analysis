package panels;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;

import beans.BddDirectoryBean;
import beans.ImageBean;

public class HackathonPanel extends JPanel implements PanelsInterface {

	private static final long serialVersionUID = 1L;

	private JTextField pathTextField;
	private ImageBean mccCurveRocCurveImage;
	private ImageBean userAlgorithRocCurveImage;
	private JButton browseButton;
	private JButton saveImageButton;
	private JButton startButton;
	private JLabel title;
	private BddDirectoryBean bddDirectoryBean;

	public HackathonPanel() {
		setLayout(null);
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		panel_1.setBackground(Color.WHITE);
		panel_1.setBounds(24, 65, 995, 53);
		add(panel_1);
		panel_1.setLayout(null);

		title = new JLabel("Hackathon");
		title.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 20));
		title.setHorizontalAlignment(SwingConstants.CENTER);
		title.setBounds(301, 13, 382, 34);
		title.setLocation(304, 12);
		add(title);

		browseButton = new JButton("Browse");
		browseButton.setBounds(886, 12, 97, 25);
		browseButton.setBackground(new Color(141, 61, 150));
		browseButton.setForeground(new Color(255, 255, 255));
		panel_1.add(browseButton);

		JLabel lblNewLabel = new JLabel("The absolute path to the BDD:");
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

		JLabel label = new JLabel("MCC ROC CURVE");
		label.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 15));
		label.setBounds(128, 31, 155, 16);
		panel_2.add(label);

		JLabel label_1 = new JLabel("YOUR ALGORITHM ROC CURVE");
		label_1.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 15));
		label_1.setBounds(682, 31, 226, 16);
		panel_2.add(label_1);

		JPanel panel_4 = new JPanel();
		panel_4.setBorder(new LineBorder(new Color(0, 0, 255), 2));
		panel_4.setBounds(11, 60, 404, 422);
		panel_2.add(panel_4);
		panel_4.setLayout(null);

		mccCurveRocCurveImage = new ImageBean();
		mccCurveRocCurveImage.setBounds(12, 13, 380, 396);
		panel_4.add(mccCurveRocCurveImage);
		mccCurveRocCurveImage.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));

		JPanel panel_3 = new JPanel();
		panel_3.setLayout(null);
		panel_3.setBorder(new LineBorder(new Color(0, 0, 255), 2));
		panel_3.setBounds(574, 60, 404, 422);
		panel_2.add(panel_3);

		userAlgorithRocCurveImage = new ImageBean();
		userAlgorithRocCurveImage.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		userAlgorithRocCurveImage.setBounds(12, 13, 380, 396);
		panel_3.add(userAlgorithRocCurveImage);

		startButton = new JButton("Start");
		startButton.setFont(new Font("Times New Roman", Font.BOLD, 13));
		startButton.setBounds(427, 246, 135, 25);
		startButton.setBackground(new Color(141, 61, 150));
		startButton.setForeground(new Color(255, 255, 255));
		panel_2.add(startButton);

		saveImageButton = new JButton("Save");
		saveImageButton.setFont(new Font("Times New Roman", Font.BOLD, 13));
		saveImageButton.setBounds(427, 457, 135, 25);
		saveImageButton.setBackground(new Color(141, 61, 150));
		saveImageButton.setForeground(new Color(255, 255, 255));
		panel_2.add(saveImageButton);

		bddDirectoryBean = new BddDirectoryBean();

	}

	public void setListeners(JFrame frame) {
		browseButton.addActionListener(new ActionListener() {@Override
			public void actionPerformed(ActionEvent e) {
				JFileChooser repository = new JFileChooser();
				try {
					repository.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
					repository.showOpenDialog(frame);
					repository.setBounds(600, 400, 600, 420);
					pathTextField.setText(repository.getSelectedFile().getAbsolutePath());
					bddDirectoryBean.setDirectoryAbsolutePathString(repository.getSelectedFile().getAbsolutePath());
				} catch(Exception ex) {
					System.out.println("Error Message: " + ex.getMessage());
				}
			}
		});

		pathTextField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				super.keyPressed(e);
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					try {
						bddDirectoryBean.setDirectoryAbsolutePathString(pathTextField.getText());
					} catch(Exception ex) {
						System.out.println("Error Message: " + ex.getMessage());
					}
				}
			}
		});

		saveImageButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if ((BufferedImage) mccCurveRocCurveImage.getCurrentImage() != null || (BufferedImage) userAlgorithRocCurveImage.getCurrentImage() != null) {
					JFileChooser repository = new JFileChooser();
					repository.setApproveButtonText("Save");
					try {
						repository.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
						repository.showOpenDialog(frame);
						repository.setBounds(600, 400, 600, 420);
						File file = new File(repository.getSelectedFile().getAbsolutePath());
						String fileName = file.getName();
						
						boolean mccBoolean = (BufferedImage) mccCurveRocCurveImage.getCurrentImage() != null;
						boolean userAlgoBoolean = (BufferedImage) userAlgorithRocCurveImage.getCurrentImage() != null;
						
						BufferedImage mccCurveRocCurveBufferedImage = (mccBoolean==true)?drawTextOnImage("MCC ROC CURVE", (BufferedImage) mccCurveRocCurveImage.getCurrentImage(), 10):null;
						BufferedImage userAlgorithRocCurveBufferedImage = (userAlgoBoolean==true)?drawTextOnImage("User Algorithm ROC CURVE", (BufferedImage) userAlgorithRocCurveImage.getCurrentImage(), 10):null;

						int mccCurveRocCurveImageWidth = (mccCurveRocCurveBufferedImage != null) ? mccCurveRocCurveBufferedImage.getWidth() : 0;
						int userAlgoCurveRocCurveImageWidth = (userAlgorithRocCurveBufferedImage != null) ? userAlgorithRocCurveBufferedImage.getWidth() : 0;

						int mccCurveRocCurveImageHeight = (mccCurveRocCurveBufferedImage != null) ? mccCurveRocCurveBufferedImage.getHeight() : 0;
						int userAlgoCurveRocCurveImageHeight = (userAlgorithRocCurveBufferedImage != null) ? userAlgorithRocCurveBufferedImage.getHeight() : 0;

						int width = (mccCurveRocCurveImageWidth > userAlgoCurveRocCurveImageWidth) ? mccCurveRocCurveImageWidth: userAlgoCurveRocCurveImageWidth;
						int height = mccCurveRocCurveImageHeight + userAlgoCurveRocCurveImageHeight;

						BufferedImage newImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
						Graphics2D g2 = newImage.createGraphics();
						Color oldColor = g2.getColor();
						g2.setPaint(Color.WHITE);
						g2.fillRect(0, 0, width, height);
						g2.setColor(oldColor);

						if (mccCurveRocCurveBufferedImage != null) 
							g2.drawImage(mccCurveRocCurveBufferedImage, null, 0, 0);

						if (userAlgorithRocCurveBufferedImage != null) 
							g2.drawImage(userAlgorithRocCurveBufferedImage, null, 0, mccCurveRocCurveImageHeight);

						g2.dispose();
						ImageIO.write(newImage, fileName.substring(fileName.lastIndexOf(".") + 1), file);
					} catch(Exception ex) {
						System.out.println("Error Message: " + ex.getMessage());
					}
				}
			}
		});

		startButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (!pathTextField.getText().equals("")) {
						File trashDir = new File("trash");
						if (!trashDir.exists()) {
							try {
								trashDir.mkdir();
							}
							catch(SecurityException se) {}
						}
						bddDirectoryBean.startTheHackathon();
						mccCurveRocCurveImage.setCurrentImage(bddDirectoryBean.getMccRocCurve());
						userAlgorithRocCurveImage.setCurrentImage(bddDirectoryBean.getUserAlgorithmRocCurve());
					}
				} catch(Exception ex) {
					ex.printStackTrace();
				}
			}
		});
	}

	private BufferedImage drawTextOnImage(String text, BufferedImage image, int space) {
		BufferedImage bi = new BufferedImage(image.getWidth(), image.getHeight() + space, BufferedImage.TRANSLUCENT);
		Graphics2D g2d = (Graphics2D) bi.createGraphics();
		g2d.addRenderingHints(new RenderingHints(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY));
		g2d.addRenderingHints(new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON));
		g2d.addRenderingHints(new RenderingHints(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON));

		g2d.drawImage(image, 0, 0, null);

		g2d.setColor(Color.BLACK);
		g2d.setFont(new Font("Calibri", Font.BOLD, 20));
		FontMetrics fm = g2d.getFontMetrics();
		int textWidth = fm.stringWidth(text);

		g2d.drawString(text, (bi.getWidth() / 2) - textWidth / 2, bi.getHeight());

		g2d.dispose();
		return bi;
	}

}