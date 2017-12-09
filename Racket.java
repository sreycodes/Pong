import java.awt.Graphics;
import java.awt.Rectangle;
import javax.swing.Timer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class Racket implements ActionListener{
    public int WIDTH = 2, HEIGHT = 60;
    public Pong game;
    public int up, down;
    public int x;
    public int y, ya;
    public int speed = 2;
    public Timer increaseSpeed;
    public Racket(Pong game, int up, int down, int x) {
        this.game = game;
        this.x = x;
        y = game.getHeight() / 2;
        this.up = up;
        this.down = down;
        increaseSpeed = new Timer(5000,this);
        increaseSpeed.start();
    }
    public void update() {
        if (y > 0 && y < game.getHeight() - HEIGHT - 30)
            y += ya;
        else if (y <= 0)
        {
           y += speed;
        }
        else if (y >= game.getHeight() - HEIGHT - 30)
        {
            y -= speed;
        }
    }
    public void actionPerformed(ActionEvent e)
    {
        if(game.getPanel().playing)
            speed++;
    }
    public void pressed(int keyCode) {
        if (keyCode == up)
            ya = -speed;
        else if (keyCode == down)
            ya = speed;
    }
    public void released(int keyCode) {
        if (keyCode == up || keyCode == down)
            ya = 0;
    }
    public Rectangle getBounds() {
        return new Rectangle(x, y, WIDTH, HEIGHT);
    }
    public void paint(Graphics g) {
        g.fillRect(x, y, WIDTH, HEIGHT);
    }
}