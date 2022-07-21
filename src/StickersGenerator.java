import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import java.io.File;
import java.io.InputStream;

import javax.imageio.ImageIO;  


public class StickersGenerator {

    public void create(InputStream inputStream, String fileName) throws Exception {

        // read image from static dir
        // InputStream inputStream = new FileInputStream(new File("input/movie1.jpg"));
        
        // read image from url
        // InputStream inputStream = new URL("https://m.media-amazon.com/images/M/MV5BMDFkYTc0MGEtZmNhMC00ZDIzLWFmNTEtODM1ZmRlYWMwMWFmXkEyXkFqcGdeQXVyMTMxODk2OTU@.jpg").openStream();

        // image will be read in the App.java 

        BufferedImage originalImage = ImageIO.read(inputStream);

        // create new img (blank) in memory with transparency and new size
        int width = originalImage.getWidth();
        int height = originalImage.getHeight();
        int newHeight = height + 200;

        BufferedImage newImage = new BufferedImage(width, newHeight, BufferedImage.TRANSLUCENT);

        // copy original img (movie picture) to the new translucid img (in memory)
        Graphics2D graphics = (Graphics2D) newImage.getGraphics();
        graphics.drawImage(originalImage, 0, 0, null);

        // style (set) font, font size, font color
        var font = new Font(Font.SANS_SERIF, Font.BOLD, 66);
        graphics.setColor(Color.MAGENTA);
        graphics.setFont(font);
        
        // write a text in the new img (movie picture + blank img)
        graphics.drawString("Nasa API", 150, newHeight - 100);
        
        // "write" (save) new img (sticker) in a file
        ImageIO.write(newImage, "png", new File("output/" + fileName));
        // ImageIO.write(newImage, "png", new File("output/sticker1.png"));
    }
    // test
    // public static void main(String[] args) throws Exception {
    //     var generator = new StickersGenerator();
    //     generator.create();        
    // }
    // App.java will call the StickersGenerator class
    
}
