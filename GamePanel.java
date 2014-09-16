package gameoflife;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 *
 * @author Miles Sorlie
 */
public class GamePanel extends JPanel implements ActionListener {

    public GameOfLife game;
    private static final int DELAY = 500;
    Timer timer = new Timer(DELAY, this);

    public GamePanel(GameOfLife gol, int width, int height) throws FileNotFoundException {
        timer.start();
        setBackground(Color.WHITE);
        setPreferredSize(new Dimension(width * 7, height * 7));
        game = gol;
    }

    @Override
    public void paint(Graphics g) {
        int xLoc = 0;
        int yLoc = 0;
        for (int i = 0; i < game.curBoard.length; i++) {
            xLoc = 0; // reset x coord to left side of panel
            for (int j = 0; j < game.curBoard[0].length; j++) {
                if (game.curBoard[i][j]) {
                    g.fillRect(xLoc, yLoc, 7, 7);
                }
                xLoc += 7;
            }
            yLoc += 7;
        }
    }
    
    @Override
    public void actionPerformed(ActionEvent ev){
        if(ev.getSource()==timer){
          repaint();// this will call at every 1 second
        }
    }
}