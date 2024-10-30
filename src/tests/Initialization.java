package tests;

import main.CheckerType;
import main.Main;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import static org.junit.jupiter.api.Assertions.*;

public class Initialization {

    @BeforeAll
    public static void setUp(){
        Main.init();
    }

    //board initialized correctly
    @TestFactory
    public Collection<DynamicTest> CheckerPositionInitializedCorrectly(){
        ArrayList<DynamicTest> tests = new ArrayList<>(64);
        for(int i=0; i<8; i++){
            for(int j=0; j<8; j++){
                int[] coords = {i, j};
                if((i+j)%2==1){
                    if(i<3) tests.add(DynamicTest.dynamicTest(Arrays.toString(coords), () -> assertEquals(CheckerType.Blackman.ordinal(), Main.getTile(coords))));
                    if(i>4) tests.add(DynamicTest.dynamicTest(Arrays.toString(coords), () -> assertEquals(CheckerType.Whiteman.ordinal(), Main.getTile(coords))));
                }else{
                    tests.add(DynamicTest.dynamicTest(Arrays.toString(coords), () -> assertEquals(CheckerType.Blank.ordinal(), Main.getTile(coords))));
                }
            }
        }
        return tests;
    }

    //ready for the game (white can move)
    @TestFactory
    public Collection<DynamicTest> FrontWhitePiecesCanMove(){
        ArrayList<DynamicTest> tests = new ArrayList<>(4);
        int[][] coords = new int[4][2];
        int row = 5;
        for(int i=0; i<4; i++){
            coords[i][0] = row;
            coords[i][1] = i*2;
        }
        for(int i=0; i<4; i++){
            int finalI = i;
            tests.add(
                    DynamicTest.dynamicTest(
                            "[%d, %d]".formatted(row, i),
                            () -> assertTrue(Main.getPossibleMoves(coords[finalI]).length>0)
                    )
            );
        }
        return tests;
    }
}
