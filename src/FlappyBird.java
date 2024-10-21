import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;

public class FlappyBird extends JPanel implements ActionListener, KeyListener {
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

    //Pipes
    int pipeX = boardWidth;
    int pipeY = 0;
    int pipeWidth = 64;
    int pipeHeight = 512;

    class Pipe {
        int x = pipeX;
        int y = pipeY;
        int width = pipeWidth;
        int height = pipeHeight;
        Image image;
        boolean passed = false;

        Pipe (Image image) {
            this.image = image;
        }
    }

    //game logic
    Bird bird;
    int velocityX = -4;
    int velocityY = 0;
    int gravity = 1;

    ArrayList<Pipe> pipes;
    Random random = new Random();

    Timer gameLoop;
    Timer placePipeTimer;


    FlappyBird(){
        setPreferredSize(new Dimension(boardWidth, boardHeight));
//        setBackground(Color.BLUE);
        setFocusable(true);
        addKeyListener(this);

        //load images
        backgroundImage = new ImageIcon(Objects.requireNonNull(getClass().getResource("./assets/flappybirdbg.png"))).getImage();
        flappyBirdImage = new ImageIcon(Objects.requireNonNull(getClass().getResource("assets/flappybird.png"))).getImage();
        topPipeImage = new ImageIcon(Objects.requireNonNull(getClass().getResource("assets/toppipe.png"))).getImage();
        bottomPipeImage = new ImageIcon(Objects.requireNonNull(getClass().getResource("assets/bottompipe.png"))).getImage();

        //bird
        bird = new Bird(flappyBirdImage);

        pipes = new ArrayList<>();

        //place pipes timer
        placePipeTimer = new Timer(1500, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                placePipes();
            }
        });
        placePipeTimer.start();

        //game timer
        gameLoop = new Timer(1000/60, this); //1000/60 = 16.6
        gameLoop.start();
    }

    public void placePipes(){
        int randomPipeY = (int) (pipeY - pipeHeight/4 - Math.random() * (pipeHeight / 2));
        int openSpace = boardHeight/4;

        Pipe topPipe = new Pipe(topPipeImage);
        topPipe.y = randomPipeY;
        pipes.add(topPipe);

        Pipe bottomPipe = new Pipe(bottomPipeImage);
        bottomPipe.y = topPipe.y + pipeHeight + openSpace;
        pipes.add(bottomPipe);

    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        draw(g);
    }

    private void draw(Graphics g) {
        System.out.println("draw ");
        g.drawImage(backgroundImage, 0, 0, boardWidth, boardHeight, null);
        //bird
        g.drawImage(bird.image, bird.x, bird.y, bird.width, bird.height, null);

        //pipes
        for (int i = 0; i < pipes.size(); i++) {
            Pipe pipe = pipes.get(i);
            g.drawImage(pipe.image, pipe.x, pipe.y, pipe.width, pipe.height, null);
        }
    }

    public void move(){
        //bird
        velocityY += gravity;
        bird.y += velocityY;
        bird.y = Math.max(bird.y, 0);

        //pipes
        for (int i = 0; i < pipes.size(); i++) {
            Pipe pipe = pipes.get(i);
            pipe.x += velocityX;
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        move();
        repaint();
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            velocityY = -9;
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
