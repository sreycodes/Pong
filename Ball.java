import java.awt.Graphics;
import java.awt.Rectangle;
import javax.swing.Timer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
public class Ball implements ActionListener {
    public static final int WIDTH = 30, HEIGHT = 30;
    public Pong game;
    public int x, y;
    public int xa = 2, ya = 2;
    public Timer collided;
    public boolean justCollided = false;
    public int difficulty = 0;
    public Ball(Pong game) {
        this.game = game;
        x = 250;
        y = 250;
        collided = new Timer(100,this);
        collided.start();
    }
    public void update() {
        if(game.getPanel().playing)
        {
            if ((x < 0) || (x > game.getWidth() - WIDTH)) {
                try {
                    game.getPanel().playing = false;
                    game.getPanel().save();
                    game.getPanel().load();
                }
                catch (Exception E) {}
            }
            else if (y < 0 || y > game.getHeight() - HEIGHT*2)
                ya = -ya;
            x += xa;
            y += ya;
            if(!justCollided)
            {
                checkCollision();
            }
        }
    }
    public void checkCollision() {
        if (game.getPanel().getRacket('l').getBounds().intersects(getBounds()) || game.getPanel().getRacket('r').getBounds().intersects(getBounds()))
        {
            xa = -xa;
            game.getPanel().score++;
            justCollided = true;
        }
    }
    public void actionPerformed(ActionEvent e)
    {
       if(game.getPanel().playing)
       {
           if(difficulty == 50)
           {
               if(xa>0)
                    xa++;
               else 
                    xa--;
               difficulty = 0;
           }
           if(justCollided)
                justCollided = false;
           difficulty++;
       }
    }
    public Rectangle getBounds() {
        return new Rectangle(x, y, WIDTH, HEIGHT);
    }
    public void paint(Graphics g) {
        if(game.getPanel().playing)
        {
            g.fillOval(x, y, WIDTH, HEIGHT);
        }
        else
        {
            g.drawString("Press Space to play",game.getWidth()/2 - 50,game.getHeight()/2);
            if(game.getPanel().not>0)
                g.drawString("Highscore: " + game.getPanel().highscore,game.getWidth()/2 - 50,game.getHeight()/2 + 50);
        }
    }
}