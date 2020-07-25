import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.security.Key;

public class Gameplay extends JPanel implements Runnable , KeyListener
{
    public static final int WIDTH = 600;
    public static final int HEIGHT = 600;
    public static final int SCALE = 1;

    private BufferedImage image;
    private Graphics2D g;
    private Thread thread;
    private boolean running;
    private int FPS = 8;
    private long milTime = 1000 / FPS;
    private boolean key_input;
    private boolean paused;


    private Snake s;
    private Map map;


    public Gameplay()
    {
        super();

        setPreferredSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
        setFocusable(true);
        requestFocus();

        this.key_input = false;
        this.paused = false;
        this.s = new Snake();
        this.map = new Map();
    }






    public void addNotify()
    {
        super.addNotify();
        if(thread == null)
        {
            thread = new Thread(this);
            addKeyListener(this);
            thread.start();
        }

    }


    private void init()
    {
        image = new BufferedImage(
                WIDTH, HEIGHT,
                BufferedImage.TYPE_INT_RGB
        );
        g = (Graphics2D) image.getGraphics();
        running = true;


    }

    public void run()
    {
        init();

        long start;
        long elapsed;
        long wait;


        //game loop
        while(running)
        {
            start = System.nanoTime();

            update();
            draw();
            drawToScreen();

            elapsed = System.nanoTime() - start;
            wait = milTime - elapsed / 1000000;
            if(wait < 0) wait = 5;

            try
            {
                Thread.sleep(wait);
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
    }

    private void update()
    {
        key_input = false;

        if(paused)
            return;
        if(s.alive)
        {
            s.update(map.entities);
            map.update();
        }
        else
        {
            s = new Snake();
            map = new Map();
        }
    }


    private void draw()
    {


        map.draw(g);
        s.draw(g);
        if(paused)
        {
            g.setColor(new Color(199, 199, 199));
            try
            {
                Font font = new Font("Comic Sans", Font.BOLD, 45);
                g.setFont(font);
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
            g.drawString("PAUSED",197,290);
        }
    }


    private void drawToScreen()
    {
        Graphics g2 = getGraphics();
        g2.drawImage(image, 0, 0,
                WIDTH * SCALE, HEIGHT * SCALE,
                null);
        g2.dispose();
    }


    public void keyTyped(KeyEvent key)
    {

    }


    public void keyPressed(KeyEvent key)
    {
        int keyCode = key.getKeyCode();


        if(key_input)
            return;

        if(keyCode == KeyEvent.VK_ESCAPE)
        {
            paused = !paused;
            return;
        }

        if(keyCode == KeyEvent.VK_DOWN && s.dir != 'C')
        {
            s.dir = 'B';
            key_input = true;
            return;
        }

        if(keyCode == KeyEvent.VK_UP && s.dir != 'B')
        {
            s.dir = 'C';
            key_input = true;
            return;
        }

        if(keyCode == KeyEvent.VK_RIGHT && s.dir != 'E')
        {
            s.dir = 'D';
            key_input = true;
            return;
        }

        if(keyCode == KeyEvent.VK_LEFT && s.dir != 'D')
        {
            s.dir = 'E';
            key_input = true;
            //return;
        }
    }


    public void keyReleased(KeyEvent key)
    {

    }
}