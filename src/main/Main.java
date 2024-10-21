package main;

import java.util.*;

public class Main {

    public static Board board = new Board();

    static int[][] getMove(){
        int[][] arr ={{5,0}, {4,1}};
        return arr;
    }

    static boolean moved(){return true;};
    static {
        System.loadLibrary("checkers");
    }

    public static native int[][] getTable();

    public static native boolean makeMove(int[][] move);

    public static void printTable(){
        var x = getTable();
        Arrays.stream(x)
                .forEach(a ->
                        System.out.println(Arrays.toString(a))
                );
    }

    public static void main(String[] args) {

        //initialization code
        printTable();

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
    }

}
