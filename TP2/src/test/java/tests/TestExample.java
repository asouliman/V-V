package tests;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;
import simpleGame.core.Board;
import simpleGame.core.Pawn;

import java.util.ArrayList;

/**
 * Created by Thomas & Amona on 06/10/14.
 */
@RunWith(MockitoJUnitRunner.class)
public class TestExample {

    @Test
    public void test() {
        //ArrayList<Pawn> pawns = mock(Pawn.class);

        // Board(int numberOfPawns, int sizeX, int sizeY, int xBonus, int yBonus)
        Board b = new Board(2, 5, 5, 2, 2);

        int x = b.getXSize();
        assertEquals(x, 5);
    }

}
