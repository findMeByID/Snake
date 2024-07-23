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

    /**
     * Directional variables
     */
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

    /**
     * Method responsible for moving Snake Entity.
     *  1 - Stores X Y coordinates of all sections in Snake Entity
     *  2 - Get current location of SnakeHead and calculate new X Y coordinates
     *  3 - Set new Head coordinates
     *  4 - Starting at 1st Section after Head, update X Y coordinates of all Sections to the coordinate
     * of Section in front of it using previously stored X Y coordinates (1)
     */
    private void moveSnake () {
        //Store all current positions of the snake sections
        int[] prevX = new int[snake.size()];
        int[] prevY = new int[snake.size()];
        for (int i = 0; i < snake.size(); i++) {
            Entity section = snake.get(i);
            prevX[i] = section.xPos;
            prevY[i] = section.yPos;
        }

        //Move SnakeHead Entity to new position
        Entity snakeHead = snake.getFirst();
        int newXHead = snakeHead.xPos + goX * gameBoard.tileSize;
        int newYHead = snakeHead.yPos + goY * gameBoard.tileSize;
        snakeHead.xPos = newXHead;
        snakeHead.yPos = newYHead;

        // Update the positions of the trailing sections
        for (int section = 1; section < snake.size(); section++) {
            Entity trailingSection = snake.get(section);
            trailingSection.xPos = prevX[section - 1];
            trailingSection.yPos = prevY[section - 1];
        }
    }

    /**
     * Method that checks if food is present in tile that Snakehead is moving into. Called by moveSnake.
     * If food is present, food tile is updated to new Location and true is returned.
     */
    private void isFoodPresent () {
        Entity snakeHead = snake.getFirst();

        if (snakeHead.xPos == food.xPos && snakeHead.yPos == food.yPos) {
            int[] newFoodLocation = Util.randomGridLocation(gameBoard.boardWidth, gameBoard.boardHeight, gameBoard.tileSize);
            food.xPos = newFoodLocation[0];
            food.yPos = newFoodLocation[1];

            Entity snakeTail = snake.getLast();
            Entity bodySection = new Entity ("bodySection", snakeTail.xPos, snakeTail.yPos);
            System.out.println(snakeTail.xPos + " " + snakeTail.yPos);
            snake.add(bodySection);

        }
    }


    /**
     * Invoked when an action occurs.
     *
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        moveSnake();
        isFoodPresent();
        gameBoard.repaint();
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
