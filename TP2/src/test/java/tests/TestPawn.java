package tests;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;
import simpleGame.core.Board;
import simpleGame.core.Direction;
import simpleGame.core.Pawn;
import simpleGame.exception.OutOfBoardException;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by Thomas & Amona on 06/10/14.
 */
@RunWith(MockitoJUnitRunner.class)
public class TestPawn {

    /**
     * Helper method
     */
    Pawn create(char n, int x, int y) {
        Board mockBoard = mock(Board.class);
        when(mockBoard.getXSize()).thenReturn(4);
        when(mockBoard.getYSize()).thenReturn(4);

        return new Pawn(n, x, y, mockBoard);
    }


    /**
     * Test to get the board width
     *
     * @see Pawn#move(Direction)
     * @type Functional
     * @input Pawn('p', 1, 1)
     * @oracle The X and Y values must change accordingly
     * @passed No
     */
    @Test
    public void testMoveNoException() {
        Pawn p = create('p', 1, 1);

        try {
            p.move(Direction.Up);
            assertEquals(p.getY(), 2);
            p.move(Direction.Down);
            assertEquals(p.getY(), 1);
            p.move(Direction.Left);
            assertEquals(p.getX(), 0);
            p.move(Direction.Right);
            assertEquals(p.getX(), 1);
        } catch (OutOfBoardException e) {

        }
    }

    /**
     * TODO
     */
    @Test
    public void testMoveException() {
        Pawn p = create('p', 1, 1);

        try {
            p.move(Direction.Left);
            p.move(Direction.Left);
            p.move(Direction.Left);
            p.move(Direction.Left);
        } catch (OutOfBoardException e) {
            //System.out.println("OutOfBoardException");
        }
    }

    @Test
    public void testIsDead() {
        Pawn p = create('p', 0, 0);
        Pawn p2 = create('e', 1, 1);

        assertEquals(false, p.isDead());
    }
}
