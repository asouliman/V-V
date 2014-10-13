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
     * Test the move method of the Pawn class.
     *
     * @see Pawn#move(Direction)
     * @type Functional
     * @input Pawn('p', 1, 1)
     * @oracle The X and Y values must change accordingly.
     * @passed No
     * @correction
     * <pre>
     *     l.105-106
     *     - newy > 0
     *     - newx > 0
     *     + newy >= 0
     *     + newx >= 0
     * </pre>
     */
    @Test
    public void testMoveNoException() throws OutOfBoardException {
        Pawn p = create('p', 1, 1);

        p.move(Direction.Up);
        assertEquals(p.getY(), 2);
        p.move(Direction.Down);
        assertEquals(p.getY(), 1);
        p.move(Direction.Left);
        assertEquals(p.getX(), 0);
        p.move(Direction.Right);
        assertEquals(p.getX(), 1);
    }

    /**
     * Test the move method of the Pawn class.
     *
     * @see Pawn#move(Direction)
     * @type Functional
     * @input Pawn('p', 1, 1)
     * @oracle It must throw an OutOfBoardException.
     * @passed Yes
     */
    @Test(expected = OutOfBoardException.class)
    public void testMoveException() throws OutOfBoardException {
        Pawn p = create('p', 1, 1);

        p.move(Direction.Left);
        p.move(Direction.Left);
    }

    /**
     * Test the move, attack, suffer & isDead methods of the Pawn class.
     *
     * @see Pawn#move(Direction)
     * @type Functional
     * @input Pawn('A', 1, 1), Pawn('B', 2, 1)
     * @oracle The pawn A must kill the pawn B.
     * @passed Yes
     */
    @Test
    public void testMoveAttack() throws OutOfBoardException {

        Board mockBoard = mock(Board.class);
        when(mockBoard.getXSize()).thenReturn(3);
        when(mockBoard.getYSize()).thenReturn(3);
        when(mockBoard.isBonusSquare(1,1)).thenReturn(true);

        Pawn p1 = new Pawn('A', 1, 1, mockBoard);
        Pawn p2 = new Pawn('B', 2, 1, mockBoard);

        when(mockBoard.getSquareContent(2,1)).thenReturn(p2);

        String msg = p1.move(Direction.Right);

        assertEquals(msg, "A attacks!\nB loses 2 hitpoints.B is dead.");
        assertTrue(p2.isDead());
    }

}
