package gameoflife;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.FileNotFoundException;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 *
 * @author Miles Sorlie
 */
public class GamePanel extends JPanel {

    public GameOfLife game;

    public GamePanel(GameOfLife gol, int width, int height) throws FileNotFoundException {
        setPreferredSize(new Dimension(width * 7, height * 7));
        game = gol;
    }

    @Override
    public void paint(Graphics g) {
        int xLoc = 0;
        int yLoc = 0;
        for (int i = 0; i < game.board.length; i++) {
            xLoc = 0; // reset x coord to left side of panel
            for (int j = 0; j < game.board[0].length; j++) {
                if (game.board[i][j]) {
                    g.fillRect(xLoc, yLoc, 7, 7);
                }
                xLoc += 7;
            }
            yLoc += 7;
        }
    }
}
