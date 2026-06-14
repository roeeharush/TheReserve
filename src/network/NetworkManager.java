package network;
import ecosystem.core.Environment;


import javax.swing.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.logging.Logger;


public class NetworkManager {
    private static final Logger logger = Logger.getLogger(NetworkManager.class.getName());
    private ServerSocket server;
    private final int port;
    private final Environment env;
    private final AtomicBoolean running;
    private Thread listenerThread;

    public NetworkManager(int port, Environment env) {
        this.port = port;
        this.env = env;
        this.running = new AtomicBoolean(true);
    }

    public void start() {
        try {
            server = new ServerSocket(port);

            listenerThread = new Thread(new Runnable() {
                @Override
                public void run() {
                    while (running.get()) {
                        try {
                            Socket client = server.accept();
                            handleClient(client);
                        } catch (IOException e) {
                            if (running.get()) {
                                logger.warning("שגיאה בקבלת חיבור: " + e.getMessage());
                            }
                        }
                    }
                }
            });

            listenerThread.setDaemon(true);
            listenerThread.start();

        } catch (IOException e) {
            logger.severe("נכשל בפתיחת ServerSocket: " + e.getMessage());
        }
    }

    private void handleClient(Socket client) {
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
            String message = in.readLine();

            if (message != null ) {
                NetworkCommand cmd = CommandParser.parse(message,env);
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        cmd.execute();
                    }
                });
            }
            client.close();

        } catch (IOException e) {
            logger.warning("error " + e.getMessage());
        }
    }

    public void stop() {
        running.set(false);
        try {
            if (server != null && !server.isClosed()) {
                server.close();
            }
        } catch (IOException e) {
            logger.warning("שגיאה בסגירת ServerSocket: " + e.getMessage());
        }
        if (listenerThread != null) {
            listenerThread.interrupt();
        }
    }




}
