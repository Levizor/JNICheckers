package tests;

import junit.framework.TestCase;
import main.CheckerType;
import main.Main;
import main.StateType;
import org.junit.jupiter.api.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ManChecker {
    int[] checkerCoards = {5, 2};
    @BeforeEach
    public void restart(){
        Main.restartGame();
    }

    @Test
    public void whiteMovePassesTurnToBlack(){
        int[] blackCheckerCords = {2, 1};
        int[] whiteCheckerCords = {5, 0};
        assertEquals(0, Main.getPossibleMoves(blackCheckerCords).length);
        Main.makeMove(whiteCheckerCords, new int[]{4, 1});
        assertTrue(Main.getPossibleMoves(blackCheckerCords).length>0);
    }

    @TestFactory
    public Collection<DynamicTest> CheckerCantMoveAnywhereExceptPossibleMoves(){
        ArrayList<DynamicTest> tests = new ArrayList<>(62);
        int[][] possibleMoves = Main.getPossibleMoves(checkerCoards);
        for(int i = 0; i < 8; i++){
            for(int j = 0; j < 8; j++){
                int[] coords = {i, j};
                //omiting assumed possible moves
                if(Arrays.equals(possibleMoves[0], coords) || Arrays.equals(possibleMoves[1], coords)){
                    continue;
                }

                tests.add(DynamicTest.dynamicTest(
                        Arrays.toString(coords),
                        () -> assertEquals(
                                StateType.WrongMove.ordinal(), Main.makeMove(checkerCoards, coords)
                        )
                ));
            }
        }
        return tests;
    }

    @Test
    public void ManCanMoveToAllPossibleMoves(){
        int[][] possibleMoves = Main.getPossibleMoves(checkerCoards);
        assertEquals(2, possibleMoves.length);
        for(int i=0; i<2; i++){
            assertEquals(StateType.Moved.ordinal(), Main.makeMove(checkerCoards, possibleMoves[i]));
            Main.restartGame();
        }
    }

    @Test
    public void ManCanCaptureAll4Sides(){
        Main.makeMove(new int[]{5, 4}, new int[]{4, 5});
        Main.makeMove(new int[]{2, 3}, new int[]{3, 4});
        assertEquals(StateType.Moved.ordinal(), Main.makeMove(new int[]{4, 5}, new int[]{2, 3}));
        Main.restartGame();
        Main.makeMove(new int[]{5, 4}, new int[]{4, 3});
        Main.makeMove(new int[]{2, 5}, new int[]{3, 4});
        assertEquals(StateType.Moved.ordinal(), Main.makeMove(new int[]{4, 3}, new int[]{2, 5}));
        Main.restartGame();

        Main.makeMove(new int[]{5, 6}, new int[]{4, 5});
        Main.makeMove(new int[]{2, 7}, new int[]{3, 6});
        Main.makeMove(new int[]{4, 5}, new int[]{2, 7});
        Main.makeMove(new int[]{2, 5}, new int[]{3, 6});
        assertEquals(StateType.Moved.ordinal(), Main.makeMove(new int[]{2, 7}, new int[]{4, 5}));
        Main.restartGame();

        Main.makeMove(new int[]{5, 0}, new int[]{4, 1});
        Main.makeMove(new int[]{2, 1}, new int[]{3, 2});
        Main.makeMove(new int[]{4, 1}, new int[]{3, 0});
        Main.makeMove(new int[]{2, 7}, new int[]{3, 6});
        Main.makeMove(new int[]{5, 2}, new int[]{4, 3});
        Main.makeMove(new int[]{3, 2}, new int[]{4, 1});
        assertEquals(StateType.Moved.ordinal(), Main.makeMove(new int[]{3, 0}, new int[]{5, 2}));
    }

    @Test
    public void ManCanCaptureMoreThanOnePieceInOneTurn(){
        Main.makeMove(new int[]{5, 6}, new int[]{4, 7});
        Main.makeMove(new int[]{2, 3}, new int[]{3, 4});
        Main.makeMove(new int[]{6, 5}, new int[]{5, 6});
        Main.makeMove(new int[]{2, 5}, new int[]{3, 6});

        assertEquals(StateType.Moved.ordinal(), Main.makeMove(new int[]{4, 7}, new int[]{2, 5}));
        assertEquals(StateType.Moved.ordinal(), Main.makeMove(new int[]{2, 5}, new int[]{4, 3}));
    }

    @Test
    public void CaptureRemovesCapturedChecker(){
        Main.makeMove(new int[]{5, 6}, new int[]{4, 7});
        Main.makeMove(new int[]{2, 5}, new int[]{3, 6});
        assertEquals(CheckerType.Blackman.ordinal(), Main.getTile(new int[]{3, 6}));
        Main.makeMove(new int[]{4, 7}, new int[]{2, 5});
        assertEquals(CheckerType.Blank.ordinal(), Main.getTile(new int[]{3, 6}));
    }
}
