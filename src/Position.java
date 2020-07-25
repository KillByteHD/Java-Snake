import java.util.Objects;

public class Position
{
    private int x;
    private int y;

    public Position()
    {
        this.x = 0;
        this.y = 0;
    }

    public Position(int x, int y)
    {
        this.x = x;
        this.y = y;
    }

    public Position(Position p)
    {
        this.x = p.getX();
        this.y = p.getY();
    }

    public int getX() {
        return this.x;
    }
    public int getY() { return this.y; }

    public void setX(int y) {
        this.y = y;
    }
    public void setY(int y) {
        this.y = y;
    }
    public void setPos(int x , int y)
    {
        this.x = x;
        this.y = y;
    }


    public double distance(Position p)
    {
        return Math.sqrt( Math.pow(this.x - p.x,2) + Math.pow(this.y - p.y,2));
    }


    public String toString()
    {
        return "(" + x + "," + y + ")";
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Position position = (Position) o;
        return x == position.x &&
                y == position.y;
    }

    public Position clone()
    {
        return new Position(this);
    }
}
