import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.List;


public class GameLogic extends JPanel implements ActionListener, KeyListener {
    Timer loopTime;

    /**
     * Game board and game entities
     */
    GameBoard gameBoard;
    List<Entity> snake;
    Entity food;

    int goX;
    int goY;


    public GameLogic (GameBoard gameBoard, int delay) {
        //Define board that logic encompasses
        this.gameBoard = gameBoard;

        //Extract starter entities from board
        this.snake = gameBoard.snake;
        this.food = gameBoard.food;

        //Start game loop
        loopTime = new Timer(delay, this);
        loopTime.start();

        //Add Key Listener to object
        addKeyListener(this);
        setFocusable(true);
    }

    private void moveSnake () {
        Entity snakeHead = snake.get(0);
        int newXHead = snakeHead.xPos + goX * gameBoard.tileSize;
        int newYHead = snakeHead.yPos + goY * gameBoard.tileSize;

        Entity newSnakeHead = new Entity("snakeHead", newXHead, newYHead);
        snake.remove(0);
        snake.add(0,newSnakeHead);

        gameBoard.repaint();
        System.out.println("Repaint called");
    }

    /**
     * Invoked when an action occurs.
     *
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        moveSnake();
    }

    /**
     * Invoked when a key has been pressed.
     * See the class description for {@link KeyEvent} for a definition of
     * a key pressed event.
     *
     * @param e the event to be processed
     */
    @Override
    public void keyPressed(KeyEvent e) {
        int pressedKeyCode =  e.getKeyCode();
        switch (pressedKeyCode) {
            case KeyEvent.VK_UP :
                goX = 0;
                goY = -1;
                break;
            case KeyEvent.VK_DOWN :
                goX = 0;
                goY = 1;
                break;
            case KeyEvent.VK_LEFT :
                goX = -1;
                goY = 0;
                break;
            case KeyEvent.VK_RIGHT :
                goX = 1;
                goY = 0;
                break;
        }
    }

    /**
     * Invoked when a key has been released.
     * See the class description for {@link KeyEvent} for a definition of
     * a key released event.
     *
     * @param e the event to be processed
     */
    @Override
    public void keyReleased(KeyEvent e) {

    }

    /**
     * Invoked when a key has been typed.
     * See the class description for {@link KeyEvent} for a definition of
     * a key typed event.
     *
     * @param e the event to be processed
     */
    @Override
    public void keyTyped(KeyEvent e) {

    }
}
