import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import javax.imageio.ImageIO;

public class Driver {

    static int cumulativeSum = 0;

    public static void main(String[] args) {
        BufferedImage image;
        int width;
        int height;
        BST tree = new BST();

        try {
            File input = new File("image.png");
            image = ImageIO.read(input);
            width = image.getWidth();
            height = image.getHeight();
            for (int i = 0; i < width; i++) {
                for (int j = 0; j < height; j++) {
                    Color c = new Color(image.getRGB(i, j));
                    int value = (int) c.getRed();
                    if (i == 0 && j == 0) {
                        tree.root = new BTNode(new Pixel(value));
                    } else {
                        BTNode temp = tree.search(value);
                        if (temp != null) {
                            temp.data.intensityCount++;
                        } else {
                            tree.add(new Pixel(value));
                        }
                    }
                }
            }

            getNewValues(tree.root, width * height);
            for (int i = 0; i < width; i++) {
                for (int k = 0; k < height; k++) {
                    Color c = new Color(image.getRGB(i, k));
                    int value = (int) c.getRed();
                    BTNode pix = tree.search(value);
                    Color newColor = new Color(pix.data.newIntensity, pix.data.newIntensity, pix.data.newIntensity);
                    image.setRGB(i, k, newColor.getRGB());
                }
            }

            File output = new File("new-image.png");
            ImageIO.write(image, "png", output);
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    /**
     * getNewValues() Finds the cumulative sum and new intensity for each pixel
     *
     * @param node     Used for recursion
     * @param pixelCnt The number of pixels in the image
     */
    public static void getNewValues(BTNode node, int pixelCnt) {
        if (node == null) {
            return;
        }
        // In order traversal
        getNewValues(node.left, pixelCnt);
        cumulativeSum += node.data.intensityCount;
        node.data.cumulativeSum = cumulativeSum;
        node.data.newIntensity = (int) Math.round(((cumulativeSum * 1.0 / pixelCnt * 255)));
        getNewValues(node.right, pixelCnt);
    }
}
