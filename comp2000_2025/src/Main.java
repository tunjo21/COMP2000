import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Main extends JFrame {
    public static void main(String[] args) { new Main(); }

    private Main() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Canvas canvas = new Canvas();
        this.setContentPane(canvas);
        this.pack();
        this.setVisible(true);
    }

    class Canvas extends JPanel {
        Stage stage = new Stage();
        boolean showInstructions = true;

        public Canvas() {
            setPreferredSize(new Dimension(1024,720));
            addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    if (showInstructions) {
                        showInstructions = false;
                    } else {
                     stage.handleClick(e.getPoint());
                    }
                }
            });
        }

        @Override
        public void paint(Graphics g) {
            if (showInstructions) {
                drawInstructions(g);
            } else {
            stage.paint(g, getMousePosition());
            stage.update();
            }
            repaint();
        }

        private void drawInstructions (Graphics g) {
            g.setColor(Color.WHITE);
            g.fillRect(0, 0, getWidth(), getHeight());
            g.setColor(Color.BLACK);
            g.setFont(new Font("Arial", Font.BOLD, 30)); 
            g.drawString("Catch the Monster ×͜×", 50, 50);  
            g.setFont(new Font("Arial", Font.BOLD, 18));
            g.drawString("Instructions:", 50, 100);
            g.drawString("1. Click on the grid to move the nearest animal towards the monster (•ᴗ•)", 50, 130);
            g.drawString("2. If an animal reaches the monster, you score 10 points (ᵔᴗᵔ) ", 50, 160);
            g.drawString("3. The monster moves randomly every few frames ( • ᴗ - ) ", 50, 190);
            g.drawString("4. Try to catch the monster as many times as possible („• ֊ •„)", 50, 220);
            g.drawString("5. After 60 seconds, if your score is below 50, don't worry (╥.╥)" , 50, 250);
            g.drawString("   We got your back! Help is on the way (˵ ¬ᴗ¬˵)", 50, 280);
            g.drawString("6. Don't forget to say bye to them >-;;;€•>", 50, 310);

            g.setFont(new Font("Arial", Font.BOLD, 24));
            g.drawString("Click anywhere to start (^ᴗ^) ", 50, 350);

        }
    }
}

