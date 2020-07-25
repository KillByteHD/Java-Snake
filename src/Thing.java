import java.awt.*;
import java.util.Random;

public class Thing
{
    public Position pos;
    private Color color;
    public int points;
    private int k;

    public Thing()
    {
        Random r = new Random();
        this.pos = new Position(r.nextInt(29) , r.nextInt(29));
        this.color = new Color(203, 29, 25);
        this.points = 100;
        this.k = 20;
    }

    public void draw(Graphics g)
    {

        g.setColor(color);
        //g.fillOval(k * pos.getX() ,k * pos.getY() , k , k );
        g.fillRect(k * pos.getX() ,k * pos.getY() , k , k );
    }



    public String toString()
    {
        return this.pos.toString();
    }
}
