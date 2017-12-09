import java.awt.Graphics;
import java.awt.Color;
import java.awt.Rectangle;
import javax.swing.Timer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class Powerups {
    public static final int WIDTH = 60, HEIGHT = 60;
    public Pong game;
    public int x, y;
    public String powerup;
    public boolean alreadySpawned = false;
    public Powerups(Pong game) {
        this.game = game;
    }
    public void spawn() {
        if((game.getPanel().playing)&&(!alreadySpawned))
        {
            double chooser = Math.random();
            x = (int)(Math.random()*200 + 100);
            y = (int)(Math.random()*200 + 100);
            if(chooser<.25)
            {
                powerup = "FLIP";
            }
            else if(chooser>.75)
            {
                powerup = "TP";
            }
            else if(chooser>.5)
            {
                powerup = "LONG";
            }
            else
            {
                powerup = "SHORT";
            }
            alreadySpawned = true;
        }
    }
    public void checkCollision() {
        if ((getBounds().intersects(game.getPanel().getBall().getBounds()))&&(alreadySpawned))
        {
            if(powerup == "FLIP")
            {
                int temp1 = game.getPanel().getRacket('l').up;
                game.getPanel().getRacket('l').up = game.getPanel().getRacket('l').down;
                game.getPanel().getRacket('l').down = temp1;
                int temp2 = game.getPanel().getRacket('r').up;
                game.getPanel().getRacket('r').up = game.getPanel().getRacket('r').down;
                game.getPanel().getRacket('r').down = temp2;
            }
            else if(powerup == "TP")
            {
                if(Math.random() > .5)
                    game.getPanel().getBall().xa = - (game.getPanel().getBall().xa);
                game.getPanel().getBall().x = (int)(Math.random()*350 + 50);
                game.getPanel().getBall().y = (int)(Math.random()*350 + 50);
            }
            else if(powerup == "LONG")
            {
                game.getPanel().getRacket('l').HEIGHT += 20;
                game.getPanel().getRacket('r').HEIGHT += 20;
            }
            else
            {
                game.getPanel().getRacket('l').HEIGHT -= 15;
                game.getPanel().getRacket('r').HEIGHT -= 15;
            }
            alreadySpawned = false;
        }
    }
    public Rectangle getBounds() {
        return new Rectangle(x, y, WIDTH, HEIGHT);
    }
    public void paint(Graphics g) {
        if((game.getPanel().playing)&&(alreadySpawned))
        {
            g.fillOval(x, y, WIDTH, HEIGHT);
            g.setColor(Color.WHITE);
            if(powerup == "FLIP")
                g.drawString(powerup,x+15,y+35);
            else if(powerup == "TP")
                 g.drawString(powerup,x+20,y+35);
            else if(powerup == "LONG")
                g.drawString(powerup,x+10,y+35);
            else
                g.drawString(powerup,x+5,y+40);
        }
    }
}