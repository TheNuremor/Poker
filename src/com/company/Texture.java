package com.company;


import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

public class Texture extends Canvas {

    URL resource = getClass().getResource("../../CardTextures/blank2.png");
    Image img;

    public Texture() {
        try {
            img = ImageIO.read(resource);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void paint(Graphics g) {
        Graphics2D g2D = (Graphics2D) g;
        AffineTransform transform = new AffineTransform();
        transform.scale(0.25, 0.25);
        g2D.drawImage(img, transform, null);
    }
}
