import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;

import static java.lang.Thread.sleep;


public class PortListener implements Runnable {

    ArrayList transmission = new ArrayList<>();
    BufferedReader in;

    public PortListener(BufferedReader in) {
        this.in = in;
    }

    @Override
    public void run() {
        transmission.add("");
        while (true) {
            try {
                for (int i = 0; i < transmission.size(); i++) {
                    transmission.set(i, (in.readLine() + "\n") + transmission.get(i));
                }
            } catch (IOException e) {
                try {
                    java.util.List<String> disc = new ArrayList<>();
                    disc.add("Server not responding.");
                    ClientGUI.setResults((ArrayList) disc);
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
                break;
            }
            try {
                sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            ClientGUI.setResults(transmission);
        }
    }
}
