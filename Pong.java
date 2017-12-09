import java.awt.Color;
import javax.swing.JFrame;
import java.io.IOException;
class Pong extends JFrame {
    public final static int WIDTH = 500, HEIGHT = 500;
    public Panel panel;
    public Pong() throws IOException {
        setSize(WIDTH, HEIGHT);
        setTitle("Pong");
        setBackground(Color.WHITE);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        panel = new Panel(this);
        add(panel);
		setVisible(true);
    }
    public Panel getPanel() {
        return panel;
    }
    public static void main(String[] args) throws IOException {
        new Pong();
    }
}