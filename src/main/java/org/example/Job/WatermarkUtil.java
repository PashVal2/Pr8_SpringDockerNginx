package org.example.Job;

import org.springframework.stereotype.Component;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.Buffer;

@Component
public class WatermarkUtil {
    public void addWatermark(String imagePath, String newImagePath) throws IOException {
        BufferedImage image = ImageIO.read(new File(imagePath));
        BufferedImage watermark = ImageIO.read(new File("src/main/resources/static/graph/watermark.png"));

        Graphics2D graphics = (Graphics2D) image.getGraphics();
        AlphaComposite alphaComposite = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.3f); // прозрачность
        graphics.setComposite(alphaComposite);

        int x = image.getWidth() - watermark.getWidth() - 10;
        int y = image.getHeight() - watermark.getHeight() - 10;

        graphics.drawImage(watermark, x, y, null);

        graphics.dispose();

        ImageIO.write(image, "PNG", new File(newImagePath));
    }
}
