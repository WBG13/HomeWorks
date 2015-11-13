
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Scanner;

public class StartServer {

    java.util.List<String> transmission = new ArrayList<>();


    protected static Logger log = LoggerFactory.getLogger("ThreadedServer");
    private static final int PORT = 19000;
    private static int counter = 0;
    int limitOfConnections = 2; //TODO Set limit connections
    public boolean spellCheck = true; //TODO Filter banned words

    private List<ClientHandler> handlers = new ArrayList<>();

    public void startServer() throws Exception {
        log.info("Starting server...");
        ServerSocket serverSocket = new ServerSocket(PORT);
        while (true) {
            Socket socket = serverSocket.accept();
            if (checker()) {
                log.info(" Max number of client connections " + ++counter + ". Last connected user: "
                        + socket.getInetAddress().toString() + ":" + socket.getPort() + ". Limit " + limitOfConnections);
                ClientHandler handler = new ClientHandler(this, socket);
                handlers.add(handler);
                handler.start();
            } else {
                ClientHandler handler = new ClientHandler(this, socket);
                handler.send("Please try again later.\nThe maximum connection limit has been reached. ");
                System.out.println("Max numbers of connections, client " + socket.getInetAddress().toString() + ":" + socket.getPort() + "  has been disconnected");
            }
        }
    }

    public boolean checker() {
        return counter < limitOfConnections + 1;
    }

    class ClientHandler extends Thread {

        private StartServer server;
        private BufferedReader in;
        private PrintWriter out;

        public ClientHandler(StartServer server, Socket socket) throws Exception {
            this.server = server;
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream());
        }

        public void send(String message) {
            out.println(message);
            out.flush();
        }

        @Override
        public void run() {
            try {
                String line;
                while ((line = in.readLine()) != null) {
                    transmission.add(line + "\n");
                    log.info("Handlers [" + counter + "]<< " + line);
                    if (spellCheck) server.broadcast(changeBannedWords(line));
                    else server.broadcast(line);
                }
            } catch (IOException e) {
                log.error("Failed to read from socket, client has been disconnected. Max number of client connections " + --counter + ".");
            } finally {
                Closer.closeResource(in);
                Closer.closeResource(out);
            }
        }
    }

    public String changeBannedWords(String message) throws FileNotFoundException {
        java.util.List<String> outputTransmission = new ArrayList<>();
        String s;
        String p;
        Scanner is = new Scanner(message);
        while (is.hasNext()) {
            s = is.next();
            try {
                Scanner in = new Scanner(new File("src/ru.GeekBrains.Chat.PavelShu.Client.Server/BlockingWords"));
                while (in.hasNext()) {
                    p = in.next();
                    if (s.equals(p)) s = "%&#@";
                }
                outputTransmission.add(s);
            } catch (IOException e) {
                System.out.println("Error in block \"compareWithBannedWords\" , can't find list of banned words!!!\n");
                spellCheck = false;
                return message;
            }
        }
        return String.valueOf(outputTransmission);
    }

    public void broadcast(String msg) {
        log.info("Broadcast to all: " + msg);
        for (ClientHandler handler : handlers) {
            Calendar cal = Calendar.getInstance();
            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
            handler.send(sdf.format(cal.getTime()) + " " + msg);
        }
    }
}
