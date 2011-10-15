package gui;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class ImageFrame extends JPanel{
	private Image Image;
    public ImageFrame() {
    }

    public ImageFrame(String nombreImage) {
        if (nombreImage != null) {
            Image = new ImageIcon(
                           getClass().getResource(nombreImage)).getImage();
        }
    }

    public ImageFrame(Image ImageInitial) {
        if (ImageInitial != null) {
            Image = ImageInitial;
        }
    }

    public void setImage(String nombreImage) {
        if (nombreImage != null) {
            Image = new ImageIcon(
                   getClass().getResource(nombreImage)
                   ).getImage();
        } else {
            Image = null;
        }

        repaint();
    }

    public void setImage(Image newImage) {
        Image = newImage;

        repaint();
    }

    @Override
    public void paint(Graphics g) {
        if (Image != null) {
            g.drawImage(Image, 0, 0, getWidth(), getHeight(),
                              this);

            setOpaque(false);
        } else {
            setOpaque(true);
        }

        super.paint(g);
    }
}

