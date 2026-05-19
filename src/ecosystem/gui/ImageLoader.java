package ecosystem.gui;

import javax.swing.*;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class ImageLoader {
    private static final Map<String, ImageIcon> images = new HashMap<>();

    public static boolean loadImage(){
        boolean flag = true;
        String [] entities  = { "Lion", "Deer", "Rabbit", "Flower","OakTree", "Water" ,"Rock" , "ground"};
        for (String e : entities){
            URL temp = ImageLoader.class.getResource("/Resources/" + e + ".jpeg");
            if(temp != null )
                images.put( e, new ImageIcon(temp));
            else
                flag = false;
        }
        return flag;
    }

    public static ImageIcon getImage(String name){
        return images.get(name);
    }

}
