import java.awt.Image;

import javax.swing.ImageIcon;

public class Images {
	
	public static ImageIcon scaleImage(int width, int height, ImageIcon icon){
		Image img = icon.getImage();
		Image img2 = img.getScaledInstance(width, height, java.awt.Image.SCALE_SMOOTH);
		return new ImageIcon(img2);
	}
	
}
