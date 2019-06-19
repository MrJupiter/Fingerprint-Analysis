package utilies;

import java.util.Scanner;
import java.awt.image.BufferedImage;
import java.io.*;
import javax.imageio.ImageIO;

public class FileConvertor {
	
    public static void convertIt(String imagePath, String resultFileNameWithoutExtension) throws Exception {
        FileWriter fw = new FileWriter(resultFileNameWithoutExtension + ".txt");

        // Getting the image dimensions: Height and Width
        BufferedImage image = ImageIO.read(new File(imagePath));
        fw.write(image.getWidth() + "\n");
        fw.write(image.getHeight() + "\n");
        fw.write("500\n");

        // Getting the number of extracted minutiaes
        BufferedReader br = new BufferedReader(new FileReader(resultFileNameWithoutExtension + ".xyt"));
        int linesNumber = 0;
        while (br.readLine() != null) linesNumber++;
        br.close();
        fw.write(linesNumber + "\n");

        //  Convertion of the last column to radian
        Scanner scanner = new Scanner(new File(resultFileNameWithoutExtension + ".xyt"));
        String regex = "(\\s)+";
        String[] header = scanner.nextLine().split(regex);

        fw.write(header[0] + " " + header[1] + " " + (Integer.parseInt(header[2]) * Math.PI / 180) + "\n");
        while (scanner.hasNext()) {
            String[] row = scanner.nextLine().split(regex);
            fw.write(row[0] + " " + row[1] + " " + (Integer.parseInt(row[2]) * Math.PI / 180) + "\n");
        }
        fw.close();
        scanner.close();
    }
}