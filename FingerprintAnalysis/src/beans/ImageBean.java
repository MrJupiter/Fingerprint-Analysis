package beans;

/**
 * 
 * @author Abdelmalik GHOUBIR
 * @date 17 June 2019
 * 
 */

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.File;
import java.io.Serializable;

import javax.swing.JPanel;

public class ImageBean extends JPanel implements Serializable {

	private static final long serialVersionUID = 1L;
	private String _imageAbsolutePathString;
	private Image _currentImage = null;
	private Color circleColor;

	public ImageBean() {
		super();
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		g.drawImage(_currentImage, 0, 0, getWidth(), getHeight(), this);
	}

	public void setCurrentImage(String imageAbsolutePathString) throws Exception {
		_imageAbsolutePathString = imageAbsolutePathString;
		File file = new File(_imageAbsolutePathString);
		if (file.isFile() && (file.getName().endsWith(".jpg") || file.getName().endsWith(".png") || file.getName().endsWith(".gif") || file.getName().endsWith(".jpeg"))) _currentImage = Toolkit.getDefaultToolkit().createImage(file.getAbsolutePath());
		repaint();
	}

	public void setCurrentImage(Image image) {
		_currentImage = image;
		repaint();
	}

	public Image getCurrentImage() {
		return _currentImage;
	}

	public String getImageAbsolutePathString() {
		return _imageAbsolutePathString;
	}

	public void drawMinutiae(Graphics g, int x, int y) throws Exception {
		g.setColor(circleColor);
		g.drawOval(x, y, 7, 7);
	}

	public void setCircleColor(Color circleColor) {
		this.circleColor = circleColor;
	}
}