import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class FlappyBird extends JPanel {
    int boardWidth = 360;
    int boardHeight = 640;

    //Images
    Image backgroundImage;
    Image flappyBirdImage;
    Image topPipeImage;
    Image bottomPipeImage;
    FlappyBird(){
        setPreferredSize(new Dimension(boardWidth, boardHeight));
//        setBackground(Color.BLUE);

        //load images
        backgroundImage = new ImageIcon(Objects.requireNonNull(getClass().getResource("./assets/flappybirdbg.png"))).getImage();
        flappyBirdImage = new ImageIcon(Objects.requireNonNull(getClass().getResource("assets/flappybird.png"))).getImage();
        topPipeImage = new ImageIcon(Objects.requireNonNull(getClass().getResource("assets/toppipe.png"))).getImage();
        bottomPipeImage = new ImageIcon(Objects.requireNonNull(getClass().getResource("assets/bottompipe.png"))).getImage();
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        draw(g);
    }

    private void draw(Graphics g) {
        g.drawImage(backgroundImage, 0, 0, boardWidth, boardHeight, null);
    }
}
