import java.awt.*;
import java.io.PipedOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Snake
{
    private List<Position> positions;
    private int k;
    public char dir;
    public boolean alive;
    private long points;


    public Snake()
    {
        this.positions = new ArrayList<>();
        this.k = 20;
        this.dir = 'D';
        this.alive = true;
        this.points = 0;

        for(int i = 0 ; i < 4 ; i++)
            positions.add(new Position(i+1,1));
    }


    public void update(List<Thing> ent)
    {
        Position p = positions.get(positions.size()-1);
        Position next_pos;

        switch(dir)
        {
            case 'C':
                next_pos = new Position(p.getX() , p.getY() - 1);
                break;
            case 'B':
                next_pos = new Position(p.getX() , p.getY() + 1);
                break;
            case 'D':
                next_pos = new Position(p.getX() + 1 , p.getY());
                break;
            case 'E':
                next_pos = new Position(p.getX() - 1 , p.getY());
                break;
            default:
                next_pos = new Position(p.getX() + 1 , p.getY());
                break;
        }

        //colisions
         //border collisions
        if( ((p.getX() + 1 == 30 && dir == 'D') || (p.getY() + 1 == 30 && dir == 'B') || (p.getX() - 1 == -1 && dir == 'E') || (p.getY() - 1 == -1 && dir == 'C'))
                //tail collisions
                || positions.contains(next_pos) )
        {
            alive = false;
            return;
        }

        boolean torf = true;


        for(Thing i : ent)
        {
            if(i.pos.equals(next_pos))
            {
                int asd = ent.indexOf(i);
                this.points += i.points;
                torf = false;
                ent.remove(asd);
                break;
            }
        }

        //position inc
        if(torf)
            positions.remove(0);
        positions.add(next_pos);


    }


    public void draw(Graphics g)
    {
        int grad = 5;
        int yellow = 255 - grad*positions.size();
        if(yellow < 0)
            yellow = 0;
        for(Position p : positions)
        {

            g.setColor(new Color(255, yellow, 0));
            g.fillRect(k * p.getX(),
                    k * p.getY(),
                    k,
                    k );

            if(yellow < 255 - grad)
                yellow+=grad;
        }
    }
}
