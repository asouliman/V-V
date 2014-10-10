package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
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

    /**
     * Helper class
     * @param numberOfPawn
     * @param sizeX
     * @param sizeY
     * @param xBonus
     * @param yBonus
     * @return Board
     */
    Board create(int numberOfPawn, int sizeX, int sizeY, int xBonus, int yBonus) {
        return new Board(numberOfPawn, sizeX, sizeY, xBonus, yBonus);
    }

    /**
     * Test to get the board width
     *
     * @see Board#getXSize()
     * @type Functional
     * @input Board(2, 2, 2, 2, 2)
     * @oracle It must be 2
     * @passed Yes
     */
    @Test
    public void testXSize() {
        Board b = create(2, 2, 2, 2, 2);

        int x = b.getXSize();
        assertEquals(x, 2);
    }

    /**
     * Test to get the board height
     *
     * @see Board#getYSize()
     * @type Functional
     * @input Board(2, 2, 2, 2, 2)
     * @oracle It must be 2
     * @passed Yes
     */
    @Test
    public void testYSize() {
        Board b = create(2, 2, 2, 2, 2);

        int y = b.getYSize();
        assertEquals(y, 2);
    }


    /**
     * Test to get the number of pawn in the board
     *
     * @see Board#numberOfPawns() ()
     * @type Functional
     * @input Board(1, 2, 2, 2, 2)
     * @oracle It must be 1
     * @passed Yes
     */
    @Test
    public void testNumberOfPawns() {
        Board b = create(1, 2, 2, 2, 2);

        int numberOfPawns = b.numberOfPawns();
        assertEquals(numberOfPawns, 1);
    }

    /**
     * Test to remove every pawns in the board
     *
     * @see Board#removeAllPawns() ()
     * @type Functional
     * @input Board(2, 2, 2, 2, 2)
     * @oracle Number of pawns must be 0
     * @passed Yes
     */
    @Test
    public void testRemoveAllPawns() {
        Board b = create(2, 2, 2, 2, 2);

        b.removeAllPawns();
        int numberOfPawns = b.numberOfPawns();
        assertEquals(numberOfPawns, 0);
    }

    /**
     * Test to check the board bonus position
     *
     * @see Board#isBonusSquare(int, int)
     * @type Functional
     * @input Board(2, 2, 2, 2, 2)
     * @oracle It must be true on coordinates 2, 2
     * @passed Yes
     */
    @Test
    public void testIsBonusSquare() {
        Board b = create(2, 2, 2, 2, 2);

        assertTrue(b.isBonusSquare(2, 2));
        assertFalse(b.isBonusSquare(1, 1));
        assertFalse(b.isBonusSquare(2, 1));
        assertFalse(b.isBonusSquare(1, 2));
    }

    /**
     * Test the maxGold method
     *
     * @see Board#maxGold()
     * @type Functional
     * @input Board(2, 2, 2, 2, 2)
     * @oracle It must be 0 at the begining
     * @passed Yes
     */
    @Test
    public void testMaxGold() {
        Board b = create(2, 2, 2, 2, 2);

        assertEquals(b.maxGold(), 0);
    }

    /**
     * Test to get the board sprites
     *
     * @see Board#squareContentSprite(int, int)
     * @type Functional
     * @input Board(2, 4, 4, 0, 0)
     * @oracle It must be '#' on (0, 0), 'c' on (1, 1), '2' on (2, 2), '.' on (2, 1)
     * @passed Yes
     */
    @Test
    public void testSquareContentSprite() {
        Board b = create(2, 4, 4, 0, 0);

        b.removeAllPawns();

        Pawn p1 = new Pawn('1', 1, 1, b);
        Pawn p2 = new Pawn('2', 2, 2, b);

        b.addPawn(p1);
        b.addPawn(p2);

        b.getNextPawn();

        assertEquals(b.squareContentSprite(0, 0), '#');
        assertEquals(b.squareContentSprite(1, 1), 'c');
        assertEquals(b.squareContentSprite(2, 2), '2');
        assertEquals(b.squareContentSprite(2, 1), '.');
    }
}
