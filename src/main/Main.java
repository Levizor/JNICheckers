package main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.*;

public class Main extends JFrame {
    static {
        System.loadLibrary("checkers");
    }

    public static native int makeMove(int[] from, int[] to);

    public static native int getTile(int[] coordinates);

    public static native int[][] getPossibleMoves(int[] coordinates);

    public static native void restartGame();

    public static Board board = new Board();

    public static JFrame frame = new JFrame();

    public static void restart() {
        restartGame();
        board.update();
        frame.add(board);
        frame.repaint();
        frame.revalidate();
    }

    public static void init() {
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);

        frame.add(board);
        frame.setPreferredSize(new Dimension(1000, 1000));
        frame.pack();
        frame.setVisible(true);
        frame.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                int newSize = Math.min(frame.getWidth(), frame.getHeight());
                frame.setSize(newSize, newSize);
            }
        });
    }

    public static void main(String[] args) {
        init();
    }
}
