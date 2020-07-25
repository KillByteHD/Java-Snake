import javax.swing.*;
import java.awt.*;

public class Game
{
    public static void main(String[] args)
    {
        JFrame o = new JFrame("Snek");
        o.setIconImage(Toolkit.getDefaultToolkit().getImage("Resources/snek.png"));
        o.setLocation(660,240);
        o.setContentPane(new Gameplay());
        o.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        o.setResizable(false);
        o.pack();
        o.setVisible(true);
    }
}
