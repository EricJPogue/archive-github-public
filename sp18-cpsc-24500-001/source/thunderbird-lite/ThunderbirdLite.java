import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ThunderbirdLite {
    public static void main(String[] args) throws IOException {
        System.out.println("ThunderbirdLite starting...");

        if (args.length != 1) {
            System.out.println("Error: This application requires one argument that is the name of a file.");
            System.exit(0);
        }

        BufferedReader myBR = new BufferedReader(new FileReader(args[0]));
        String line;
        while((line = myBR.readLine()) != null) {
            String[] elements = line.split("\"");
            if (elements.length > 1) {
                System.out.println(elements[1]);
            }
        }
    }
}