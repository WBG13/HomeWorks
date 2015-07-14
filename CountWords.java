import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

public class CountWords {
    public static void main(String[] args)
            throws FileNotFoundException, IOException {

        File file = new File("E:\\1.txt");
        BufferedReader br = new BufferedReader(
                new InputStreamReader(
                        new FileInputStream(file), "UTF-8"
                )
        );
        String line = null;
        while ((line = br.readLine()) != null) {
            System.out.println(line);

            String[] myString = line.split("\\s+");
            System.out.println("Words:" + myString.length);
        }
        br.close();
    }
}
