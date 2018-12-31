import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOExceptions;

public class ReadFile {
    public static void main (String[] args) throws IOException {
        BufferedReader br = null;
        br = new BufferedReader(new FileReader("ReadFile.java"));
        String line;
        while ((line = br.readLine()) != null) {
            System.out.println(line);

        // Parse each line into 

        }
        br.close();
    }
}