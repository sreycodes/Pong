import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JPanel;
import javax.swing.Timer;
import java.io.PrintWriter;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.File;
public class Panel extends JPanel implements ActionListener, KeyListener {
    public Pong game;
    public Ball ball;
    public Racket left, right;
    public Powerups spawner;
    public int score;
    public boolean playing = false;
    public int not = 0;
    long ps = 0;
    public int highscore = 0;
    public Panel(Pong game) throws IOException {
        setBackground(Color.ORANGE);
        this.game = game;
        setFocusable(true);
        addKeyListener(this);
        ball = new Ball(game);
        left = new Racket(game, KeyEvent.VK_W, KeyEvent.VK_S,4);
        right = new Racket(game, KeyEvent.VK_UP, KeyEvent.VK_DOWN, game.getWidth()-9);
        spawner = new Powerups(game);
        Timer movement = new Timer(10, this);
        movement.start();
        not = load();
    }
    public void play() {
        if(!playing)
        {
            not++;
            ball.x = 250;
            ball.y = 250;
            ball.xa = 2;
            left.up = KeyEvent.VK_W;
            left.down = KeyEvent.VK_S;
            right.up = KeyEvent.VK_UP;
            right.down = KeyEvent.VK_DOWN;
            left.HEIGHT = right.HEIGHT = 60;
            left.y = right.y = game.getHeight()/2;
            ball.difficulty = 0;
            ball.justCollided = false;
            left.speed = right.speed = 2;
            score = 0;
            playing = true;
        }
    }
    public void save() throws IOException
    {
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("score.txt",true)));
        pw.println(score);
        pw.close();
    }
    public int load() throws IOException
    {
        if(new File("score.txt").exists()) {
            BufferedReader br = new BufferedReader(new FileReader("score.txt"));
            int n = 0;
            String s;
            System.out.println('\u000c');
            try {
                while((s = br.readLine())!=null)
                {
                    if(Integer.parseInt(s)>highscore)
                        highscore = Integer.parseInt(s);
                    System.out.println(s);
                    n++;
                }
                return n;
            }
            catch (Exception E) {}
        }
        return 0;
    }
    public Racket getRacket(char side) {
        if (side == 'l')
            return left;
        else
            return right;
    }
    public Ball getBall()
    {
        return ball;
    }
    public void increaseScore() {
        score++;
    }
    public int getScore() {
        return score;
    }
    public void update() {
        ball.update();
        left.update();
        right.update();
        if(ps==1000)
        {
            spawner.spawn();
            ps = 0;
        }
        ps++;
        spawner.checkCollision();
    }
    public void actionPerformed(ActionEvent e) {
        if(playing)
        {
            update();
            repaint();
        }
    }
    public void keyPressed(KeyEvent e) {
        if((e.getKeyCode() == KeyEvent.VK_SPACE)&&(!playing))
        {
            play();
        }
        else
        {
            left.pressed(e.getKeyCode());
            right.pressed(e.getKeyCode());
        }
    }
    public void keyReleased(KeyEvent e) {
        left.released(e.getKeyCode());
        right.released(e.getKeyCode());
    }
    public void keyTyped(KeyEvent e) {
        ;
    }
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setFont(new Font("TimesRoman", Font.PLAIN, 15)); 
        g.drawString("Score: " + this.getScore(), game.getWidth()/2 - 20, 20);
        ball.paint(g);
        left.paint(g);
        right.paint(g);
        spawner.paint(g);
    }
}