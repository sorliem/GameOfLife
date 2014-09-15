package gameoflife;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import javax.swing.JFrame;
import javax.swing.Timer;

/**
 *
 * @author Miles Sorlie
 */
public class GameOfLife extends JFrame implements ActionListener {

    GamePanel panel;
    public boolean board[][];
    int panelWidth, panelHeight;
    private static final int DELAY = 750;
    Timer timer = new Timer(DELAY, this);

    public GameOfLife() throws FileNotFoundException {
        super("Game of Life");
        readBoard();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        panel = new GamePanel(this, panelWidth, panelHeight);
        getContentPane().add(panel);
        pack();
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
        printBoard();
        
        timer.start();
        while (true) {
            runGame();
        }
    }

    private void readBoard() throws FileNotFoundException {
        Scanner in = new Scanner(new File("start_board_2.in"));
        panelWidth = in.nextInt();
        panelHeight = in.nextInt();
        in.nextLine();
        board = new boolean[panelWidth][panelHeight];
        String line = in.nextLine();
        for (int i = 0; i < panelWidth; i++) {
            for (int j = 0; j < panelHeight; j++) {
                if (line.charAt(j) == 'x') {
                    board[i][j] = true;
                }
            }
            if (in.hasNextLine()) {
                line = in.nextLine();
            }
        }
    }
    
    private void printBoard() {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j]) 
                    System.out.print("x");
                else    
                    System.out.print(" ");
            }
            System.out.print("\n");
        }
        System.out.println("-----------------------------------------------"
                + "-------------------------------------");
    }

    /**
     * Any live cell with fewer than two live neighbours dies, as if caused by
     * under-population. Any live cell with two or three live neighbours lives
     * on to the next generation. Any live cell with more than three live
     * neighbours dies, as if by overcrowding. Any dead cell with exactly three
     * live neighbours becomes a live cell, as if by reproduction.
     */
    public void runGame() {
        boolean[][] prevBoard = board;
        for (int i = 0; i < prevBoard.length; i++) {
            for (int j = 0; j < prevBoard[0].length; j++) {
                int numLiveNeighbors = 0;
                for (int k = -1; k <= 1; k++) {
                    for (int l = -1; l <= 1; l++) {
                        if (k != 0 || l != 0) {
                            try {
                                if (prevBoard[i + k][j + l]) {
                                    numLiveNeighbors++;
                                }
                            } catch (IndexOutOfBoundsException e) {
                                //System.out.println("Out of array");
                            }
                        }
                    }
                }

                if (!prevBoard[i][j] && numLiveNeighbors == 3) {
                    board[i][j] = true;
                } else if (prevBoard[i][j] && numLiveNeighbors == 1) {
                    board[i][j] = false;
                } else if (prevBoard[i][j] && numLiveNeighbors == 2) {
                    board[i][j] = true;
                } else if (prevBoard[i][j] && numLiveNeighbors == 3) {
                    board[i][j] = true;
                } else if (prevBoard[i][j] && numLiveNeighbors > 3) {
                    board[i][j] = false;
                } else {
                    board[i][j] = false;
                }
            }
        }

    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == timer) {
            panel.repaint();
        }
    }

}
