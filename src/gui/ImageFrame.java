package gui;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JPanel;

public class ImageFrame extends JPanel{
	private Image Image;
    public ImageFrame() {
    }


    public ImageFrame(Image ImageInitial) {
        if (ImageInitial != null) {
            Image = ImageInitial;
        }
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

