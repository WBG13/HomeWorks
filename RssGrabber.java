import java.io.*;
import java.net.URL;

public class Grabber {
    public static void main(String[] args) throws IOException {
        System.out.println(readRss("https://news.yandex.ru/hardware.rss"));
    }

    public static String readRss(String urlAddress) throws IOException {
        URL rssURL;
        rssURL = new URL(urlAddress);
        BufferedReader in = new BufferedReader(new InputStreamReader(rssURL.openStream()));
        String sourceCode = "";
        String line;
        while ((line = in.readLine())!=null){
            if(line.contains("<title>")){
                int firstPos =  line.indexOf("<title>");
                String temp = line.substring(firstPos);
                temp = temp.replace("<title>", "");
                int lastPos = temp.indexOf("</title>");
                temp = temp.substring(0,lastPos);
                sourceCode += temp+"\n";
            }
        }
            in.close();
        return sourceCode;
    }
}
