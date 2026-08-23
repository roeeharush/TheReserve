package ecosystem.gui;
import ecosystem.core.Environment;
import ecosystem.decorators.EntityDecorator;
import ecosystem.entities.AbstractEntity;
import ecosystem.entities.LivingEntity;
import ecosystem.entities.animals.Deer;
import ecosystem.entities.animals.Lion;
import ecosystem.entities.animals.Rabbit;
import ecosystem.entities.plants.Flower;
import ecosystem.entities.plants.OakTree;
import javax.swing.*;
import java.awt.*;


/**
 * מחלקה שמייצגת פאנל תצוגה גרפי עבור הסטטיסטיקה של העולם בזמן אמת
 * הפאנל עוקב אחר השינויים בסביבה ומציג את כמויות החיות והצמחים את מספר התורות שעברו ואת סך כל האנרגיה הקיימת במערכת
 */
public class StatsPanel extends JPanel implements WorldObserver {
    private final Environment environment;
    private JLabel lionCount;
    private JLabel deerCount;
    private JLabel rabbitCount;
    private JLabel flowerCount;
    private JLabel oakTreeCount;
    private JLabel tickCount;
    private JLabel totalEnergy;


    /**
     * בונה פאנל סטטיסטיקה חדש ומחבר אותו לעולם
     * הבנאי קובע את פריסת התוויות בטור אחד מעצב מסגרת כותרת מתאימה מגדיר צבע רקע אפור בהיר ומאזן את הפאנל כמאזין לשינויים בעולם
     * @param environment סביבת העולם שממנה נאספים הנתונים הסטטיסטיים של הישויות
     */
    public StatsPanel(Environment environment) {
        this.environment = environment;
        setLayout(new GridLayout(0, 1));
        setBorder(BorderFactory.createTitledBorder("Statistics"));
        setBackground(new Color(225, 225, 225));
        initLabels();
        environment.addObserver(this);
    }


    /**
     * מייצרת את כל תוויות הטקסט הריקות של פאנל הסטטיסטיקה ומכניסה אותן לתצוגה
     * התוויות המאותחלות מיועדות להצגת מוני האוכלוסייה של האריות הצבאים הארנבים הפרחים והאלונים וכן להצגת מוני התורות וסך האנרגיה
     */
    private void initLabels() {
        lionCount = new JLabel();
        add(lionCount);
        deerCount = new JLabel();
        add(deerCount);
        rabbitCount = new JLabel();
        add(rabbitCount);
        flowerCount = new JLabel();
        add(flowerCount);
        oakTreeCount = new JLabel();
        add(oakTreeCount);
        tickCount = new JLabel();
        add(tickCount);
        totalEnergy = new JLabel();
        add(totalEnergy);
    }


    /**
     * מתודת עדכון שמופעלת אוטומטית בכל פעם שחל שינוי בעולם האקולוגי
     * המתודה סורקת את כל הישויות במפה ומבצעת פירוק דינמי של מעטפות דקורטורים כדי להגיע לישויות המקוריות ובכך מבטיחה ספירה מדויקת של מוני האוכלוסיות וחישוב עקבי של סך כל האנרגיה במערכת גם עבור יצורים מואצים או מורעלים
     */
    @Override
    public void onWorldChanged() {
        SwingUtilities.invokeLater(() -> {
            int lions = 0;
            int deers = 0;
            int rabbit = 0;
            int flower = 0;
            int oaktree = 0;
            double energy = 0;

            for (AbstractEntity entity : environment.getEntities()) {
                Object effective = entity;
                if (entity instanceof EntityDecorator decorator)
                    effective = decorator.getDecoratedEntity();

                if (effective instanceof LivingEntity living)
                    energy += living.getEnergy();
                if (effective instanceof Lion)
                    lions++;
                if (effective instanceof Deer)
                    deers++;
                if (effective instanceof Rabbit)
                    rabbit++;
                if (effective instanceof Flower)
                    flower++;
                if (effective instanceof OakTree)
                    oaktree++;
            }

            lionCount.setText("LION:" + lions);
            deerCount.setText("DEER:" + deers);
            rabbitCount.setText("RABBIT:" + rabbit);
            flowerCount.setText("FLOWER:" + flower);
            oakTreeCount.setText("OAKTREE:" + oaktree);
            tickCount.setText("⏱:" + environment.getTicks());
            totalEnergy.setText("⚡:" + energy);
        });
    }
}