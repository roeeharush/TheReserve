package ecosystem.gui;
import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class ImageLoader {
    private static final Map<String, ImageIcon> images = new HashMap<>();

    public static void loadImage(){
        String[] entities = {"Lion", "Deer", "Rabbit", "Flower", "OakTree", "Water", "Rock", "ground"};
        for (String e : entities){
            try {
                ImageIcon icon = new ImageIcon("Resources/" + e + ".jpeg");
                Image scaled = icon.getImage().getScaledInstance(64, 64, Image.SCALE_SMOOTH);
                images.put(e,new ImageIcon(scaled));
            } catch (Exception ex) {
                System.err.println(" error in load image  " + e);
            }
        }
    }


    public static ImageIcon getImage(String name){
        return images.get(name);
    }

}
