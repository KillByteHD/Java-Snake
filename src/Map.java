import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Map
{
    private int map_size;
    private int k;
    public List<Thing> entities;
    private long time_played;

    public Map()
    {
        this.map_size = 30;
        this.k = 20;
        this.entities = new ArrayList<>();
        this.time_played = 0;
        create_fruit();
    }

    public int getMap_size()
    {
        return map_size;
    }

    public void create_fruit()
    {
        this.entities.add(new Thing());
    }


    public void update()
    {
        if(time_played == 40 || this.entities.isEmpty())
        {
            create_fruit();
            time_played = 0;
        }
        else
            ++time_played;
    }

    public void draw(Graphics g)
    {
        //Background
        g.setColor(new Color(46, 68, 48));
        g.fillRect(0,0,600,600);

        //chess blocks
        for(int i = 0 ; i < map_size ; i++)
            for(int j = 0 ; j < map_size ; j++)
            {
                if((i+j) % 2 == 0)
                {
                    g.setColor(new Color(50, 89, 54));
                    g.fillRect(i*k ,j*k , k ,k);
                }
            }

        //draws all entities
        for(Thing t : entities)
            t.draw(g);

    }

}
