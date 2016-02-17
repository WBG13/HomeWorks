import org.json.JSONException;
import org.json.JSONObject;

import java.io.*;
import java.net.URL;
import java.nio.charset.Charset;


public class ReadJsonFromURL {
private String readAll(Reader rd) throws IOException {
        StringBuilder sb = new StringBuilder();
        int cp;
        while ((cp = rd.read()) != -1) {
            sb.append((char) cp);
        }
        return sb.toString();
    }

    public JSONObject readJsonFromUrl(String url) throws IOException, JSONException {
        InputStream inpStream = new URL(url).openStream();
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(inpStream, Charset.forName("UTF-8")));
            String jsonText = readAll(br);
            JSONObject json = new JSONObject(jsonText);
            return json;
        } finally {
            inpStream.close();
        }
    }

    public static void main(String[] args) throws IOException, JSONException {
        ReadJsonFromURL rJS = new ReadJsonFromURL();
        JSONObject json = rJS.readJsonFromUrl("http://jsonplaceholder.typicode.com/posts/1");
        System.out.println(json.get("id"));
        System.out.println(json.get("userId"));
        System.out.println(json.get("title"));
        System.out.println(json.get("body"));
    }
}
