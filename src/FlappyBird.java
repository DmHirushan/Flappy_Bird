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

    //Bird
    int birdX = boardWidth/8;
    int birdY = boardHeight/2;
    int birdWidth = 34;
    int birdHeight = 24;

    class Bird{
        int x = birdX;
        int y= birdY;
        int width = birdWidth;
        int height = birdHeight;
        Image image;

        Bird(Image image){
            this.image = image;
        }
    }

    //game logic
    Bird bird;

    FlappyBird(){
        setPreferredSize(new Dimension(boardWidth, boardHeight));
//        setBackground(Color.BLUE);

        //load images
        backgroundImage = new ImageIcon(Objects.requireNonNull(getClass().getResource("./assets/flappybirdbg.png"))).getImage();
        flappyBirdImage = new ImageIcon(Objects.requireNonNull(getClass().getResource("assets/flappybird.png"))).getImage();
        topPipeImage = new ImageIcon(Objects.requireNonNull(getClass().getResource("assets/toppipe.png"))).getImage();
        bottomPipeImage = new ImageIcon(Objects.requireNonNull(getClass().getResource("assets/bottompipe.png"))).getImage();

        bird = new Bird(flappyBirdImage);
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        draw(g);
    }

    private void draw(Graphics g) {
        g.drawImage(backgroundImage, 0, 0, boardWidth, boardHeight, null);
    }
}
