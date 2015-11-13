import java.io.*;
import java.net.Socket;

public class Connection {
    public PrintWriter out;

    Connection(String host, int port) throws Exception {
        Socket socket = new Socket(host, port);
        BufferedReader in = new BufferedReader(
                new InputStreamReader(
                        socket.getInputStream()));

        this.out = new PrintWriter(
                socket.getOutputStream(), true);
        Thread listener = new Thread(new PortListener(in));
        listener.start();
    }

    public void sendMsg(String userTextField, PrintWriter out) {
        String line = null;
        new ByteArrayInputStream(userTextField.getBytes());

        BufferedReader console = new BufferedReader(
                new InputStreamReader(new ByteArrayInputStream(userTextField.getBytes())));
        try {
            line = console.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        out.println(line);
    }
}
