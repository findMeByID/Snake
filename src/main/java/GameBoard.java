import java.awt.*;
import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class GameBoard extends JPanel {

    /**
     * Game board Width
     */
    int boardWidth;

    /**
     * Game board Height
     */
    int boardHeight;

    /**
     * Tile size
     */
    int tileSize;

    /**
     * Snake entity, composed of different tiles that grow as the snake eats
     */
    List<Entity> snake = new ArrayList<>();

    /**
     * Food entity, the snake must eat to grow
     */
    Entity food;

    public GameBoard (int width, int height, int tileSize) {
        this.boardWidth = width;
        this.boardHeight = height;
        this.tileSize = tileSize;

        setPreferredSize(new Dimension(this.boardWidth, this.boardHeight));
        setBackground(Color.WHITE);

        //Start snakeHead and add to snake
        int[] snakeSpawn = Util.randomGridLocation(boardWidth, boardHeight, tileSize);
        Entity snakeHead = new Entity("snakeHead", snakeSpawn[0], snakeSpawn[1]);
        snake.add(snakeHead);

        //Start food
        int[] foodSpawn = Util.randomGridLocation(boardWidth, boardHeight, tileSize);
        food = new Entity("food", foodSpawn[0], foodSpawn[1]);

    }

    /**
     * Paints the game panel
     * @param g the <code>Graphics</code> object to protect
     */
    public void paintComponent (Graphics g) {
        super.paintComponent(g);
        draw(g);
    }

    /**
     * Draws the game panel
     * @param g the <code>Graphics</code> object to protect
     */
    private void draw (Graphics g) {
        //Draw the game grid
        gameGrid(g);

        //Draw the snake
        for (Entity entity : snake) {
            g.setColor(Color.GREEN);
            g.fillRect(entity.xPos, entity.yPos, tileSize, tileSize);
        }

        //Draw the food
        g.setColor(Color.RED);
        g.fillRect(food.xPos, food.yPos, tileSize, tileSize);

    }

    /**
     * Draw square grid on board
     * @param g
     */
    private void gameGrid (Graphics g) {
        for (int i = 0; i < boardWidth; i += tileSize) {
            g.drawLine(i, 0, i, boardHeight);
            g.drawLine(0, i, boardWidth, i);
        }
    }
}
