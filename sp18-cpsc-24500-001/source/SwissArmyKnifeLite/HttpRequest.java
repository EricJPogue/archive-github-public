import java.net.*;
import java.io.*;
import java.util.ArrayList;

public class HttpRequest {
    private String requestURL;
    protected ArrayList<String> urlContent;
    
    HttpRequest() {
        requestURL = "";
        urlContent = new ArrayList<String>();
    }

    public Boolean readURL(String urlIn) {
        requestURL = urlIn;
        Boolean returnValue = false; 
        try {

            URL myURL = new URL(requestURL);
            URLConnection myConnection = myURL.openConnection();
            BufferedReader in = new BufferedReader(new InputStreamReader(myConnection.getInputStream()));
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                urlContent.add(inputLine);
                returnValue = true;
            }
            in.close();
        }
        catch (Exception e) {
            returnValue = false;
            System.out.println("An exception occurred in HttpRequest!");
            System.out.println("URL: "+requestURL);
        }
        
        return returnValue;
    }

    public String toString() {
        String returnValue = "URL: "+requestURL;
        for (String s : urlContent) {
            returnValue = returnValue + s + "\n";
        } 
        return returnValue;
    }
}