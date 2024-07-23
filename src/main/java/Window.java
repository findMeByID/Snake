import javax.swing.*;
public class Window {

    /**
     * Main start function
     */
    public static void main(String[] args) {
        //Define window size
        int width = 800;
        int height = 600;

        //Define tileSize
        int tileSize = 25;

        //Define game speed (ms)
        int gameSpeed = 100;

        JFrame frame = new JFrame("Snake");
        //Add the game panel to the frame
        frame.setVisible(true);
        //Set the size of the frame
        frame.setSize(width, height);
        //Set the location of the frame (center of screen)
        frame.setLocationRelativeTo(null);
        //Set the frame to not be resizable
        frame.setResizable(false);
        //Set the default close operation of the frame
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Add the game panel to the frame
        GameBoard gameBoard = new GameBoard(width, height, tileSize);
        GameLogic gameLogic = new GameLogic(gameBoard, gameSpeed);

        frame.addKeyListener(gameLogic);
        frame.add(gameBoard);

        //Make sure the frame fits the size of the panel without the top bar
        frame.pack();

        //Request focus for gameLogic Object
        gameLogic.requestFocus();
    }
}
