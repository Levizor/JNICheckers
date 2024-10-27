package main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.*;

public class Main extends JFrame {
    static {
        System.loadLibrary("checkers");
    }
    public static native int makeMove(int[] from, int[] to);

    public static native int getTile(int[] coordinates);

    public static native int[][] getPossibleMoves(int[] coordinates);

    public static Board board = new Board();

    public Main(){
        System.out.println("Opening window");
        setLocation(460, 40);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        add(board);
        setPreferredSize(new Dimension(1000, 1000));
        pack();
        setVisible(true);
        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                // Get the current size of the window
                int newSize = Math.min(getWidth(), getHeight());
                setSize(newSize, newSize);  // Set both width and height to newSize
            }
        });
    }

    static int[][] getMove(){
        int[][] arr ={{5,0}, {4,1}};
        return arr;
    }

    static boolean moved(){return true;};

    public static void main(String[] args) {

        //initialization code
        Main main = new Main();

/*
        while(true){
            if (moved()){
                int [][] move = getMove();
                if(makeMove(move)){
                    printTable();
                }else {
                    System.out.println("Not up to the rules");
                    System.exit(-1);
                }
            }
        }
*/
    }
}
