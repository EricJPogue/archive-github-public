import java.net.*;
import java.io.*;

public class HttpRequest {
    public static void main(String[] args) throws Exception {
        System.out.println("HttpRequest starting...");
        URL myURL = new URL("https://sp18-cpsc-24500-001.azurewebsites.net/my-information.json");
        URLConnection myConnection = myURL.openConnection();
        BufferedReader in = new BufferedReader(new InputStreamReader(myConnection.getInputStream()));
        String inputLine;
        while ((inputLine = in.readLine()) != null) {
            System.out.println(inputLine);
        }
        in.close();
    }
}