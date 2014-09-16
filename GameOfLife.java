package gameoflife;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import javax.swing.JFrame;

/**
 *
 * @author Miles Sorlie
 */
public class GameOfLife extends JFrame  {

    GamePanel panel;
    boolean curBoard[][];
    boolean prevBoard[][];
    int panelWidth, panelHeight;
    

    public GameOfLife() throws FileNotFoundException {
        super("Game of Life");
        String fileName = "start_board_3.in";
        readBoard(fileName);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        panel = new GamePanel(this, panelWidth, panelHeight);
        add(panel);
        pack();
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
        printBoard();
        
        
        while (true) {
            runGame();
        }
    }

    public void readBoard(String fileName) throws FileNotFoundException {
        Scanner in = new Scanner(new File(fileName));
        panelWidth = in.nextInt();
        panelHeight = in.nextInt();
        in.nextLine();
        curBoard = new boolean[panelWidth][panelHeight];
        prevBoard = new boolean[panelWidth][panelHeight];
        String line = in.nextLine();
        for (int i = 0; i < panelWidth; i++) {
            for (int j = 0; j < panelHeight; j++) {
                if (line.charAt(j) == 'x') {
                    curBoard[i][j] = true;
                    prevBoard[i][j] = true;
                }
            }
            if (in.hasNextLine()) {
                line = in.nextLine();
            }
        }
    }
    
    private void printBoard() {
        for (int i = 0; i < curBoard.length; i++) {
            for (int j = 0; j < curBoard[0].length; j++) {
                if (curBoard[i][j]) 
                    System.out.print("x");
                else    
                    System.out.print(" ");
            }
            System.out.print("\n");
        }
        System.out.println("-----------------------------------------------"
                + "-------------------------------------");
    }

    public void runGame() {
        copyBoards();
        for (int i = 0; i < prevBoard.length; i++) {
            for (int j = 0; j < prevBoard[0].length; j++) {
                int numLiveNeighbors = 0;
                for (int k = -1; k <= 1; k++) {
                    for (int l = -1; l <= 1; l++) {
                        if (k != 0 || l != 0) {
                            try {
                                if (prevBoard[i + k][j + l]) {
//                                    System.out.println("i + k = " + (i + k) + ", j + l = " + (j + l));
                                    numLiveNeighbors++;
                                }
                            } catch (IndexOutOfBoundsException e) {
                                //System.out.println("Out of array");
                            }
                        }
                    }
                }

                if (!prevBoard[i][j] && numLiveNeighbors == 3) {
                    curBoard[i][j] = true;
                } else if (prevBoard[i][j] && numLiveNeighbors == 1) {
                    curBoard[i][j] = false;
                } else if (prevBoard[i][j] && numLiveNeighbors == 2) {
                    curBoard[i][j] = true;
                } else if (prevBoard[i][j] && numLiveNeighbors == 3) {
                    curBoard[i][j] = true;
                } else if (prevBoard[i][j] && numLiveNeighbors > 3) {
                    curBoard[i][j] = false;
                } else {
                    curBoard[i][j] = false;
                }
            }
        }
    }
    
    private void copyBoards() {
        for (int i = 0; i < prevBoard.length; i++) {
            System.arraycopy(curBoard[i], 0, prevBoard[i], 0, prevBoard[0].length);
        }
    }
}