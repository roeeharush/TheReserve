package network;
import ecosystem.core.Environment;
import javax.swing.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.logging.Logger;


/**
 * מחלקה האחראית על ניהול השרת שמאפשר קליטת ישויות חדשות מרשת חיצונית
 * המחלקה פותחת ServerSocket ברקע, מאזינה לחיבורים נכנסים, מפענחת את ההודעות המתקבלות ומפעילה אותן על המודל המקומי בצורה בטוחה מבחינת תהליכונים
 */
public class NetworkManager {
    private static final Logger logger = Logger.getLogger(NetworkManager.class.getName());
    private ServerSocket server;
    private final int port;
    private final Environment env;
    private final AtomicBoolean running;
    private Thread listenerThread;


    /**
     * בונה מנהל רשת חדש עבור פורט ועולם סימולציה מסוימים
     * @param port הפורט שעליו השרת יאזין לחיבורים נכנסים
     * @param env סביבת העולם שאליה יתווספו ישויות שיתקבלו מהרשת
     */
    public NetworkManager(int port, Environment env) {
        this.port = port;
        this.env = env;
        this.running = new AtomicBoolean(true);
    }


    /**
     * פותחת את השרת ומתחילה תהליכון האזנה עצמאי ברקע לחיבורי לקוחות נכנסים
     * כל חיבור מתקבל מטופל בנפרד, וכל שגיאה בלתי צפויה נלכדת ונרשמת כדי שהתהליכון לא ימות בשקט
     */
    public void start() {
        try {
            server = new ServerSocket();
            server.setReuseAddress(true);
            server.bind(new InetSocketAddress(port));
            listenerThread = new Thread(new Runnable() {
                @Override
                public void run() {
                    while (running.get()) {
                        try {
                            Socket client = server.accept();
                            handleClient(client);
                        } catch (IOException e) {
                            if (running.get())
                                logger.warning("Error accepting connection: " + e.getMessage());
                        } catch (Exception e) {
                            logger.severe("Unexpected error handling client: " + e.getMessage());
                        }
                    }
                }
            });

            listenerThread.setDaemon(true);
            listenerThread.start();
        } catch (IOException e) {
            logger.severe("Failed to open ServerSocket: " + e.getMessage());
        }
    }


    /**
     * מטפלת בחיבור לקוח בודד - קוראת הודעה אחת, מפענחת אותה לפקודה, ומריצה אותה על ה-Event Dispatch Thread כדי לשמור על בטיחות עדכוני הממשק הגרפי
     * @param client חיבור הלקוח שהתקבל מהשרת
     */
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
            logger.warning("Error " + e.getMessage());
        }
    }


    /**
     * עוצרת את השרת בצורה מסודרת - מכבה את דגל הריצה, סוגרת את השקע, ומעירה את תהליכון ההאזנה אם הוא חסום
     */
    public void stop() {
        running.set(false);
        try {
            if (server != null && !server.isClosed()) {
                server.close();
            }
        } catch (IOException e) {
            logger.warning("Error closing ServerSocket: " + e.getMessage());        }
        if (listenerThread != null) {
            listenerThread.interrupt();
        }
    }

}
