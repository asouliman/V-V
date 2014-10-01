package system;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.InputMismatchException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by thomas on 29/09/14.
 */
public class TestPhonyList {

    /*
     * Helper method to create lists.
     */
    private PhonyList<Integer> list(Integer... content) {
        PhonyList<Integer> list = new PhonyList<>();
        for (Integer i : content)
            list.add(i);
        return list;
    }

    /*
     * Helper method to create a big list [1,2,3,...,10000]
     */
    private PhonyList<Integer> thousandElementsList() {
        PhonyList<Integer> thousandElementsList = new PhonyList<>();
        for (int i = 1; i <= 10000; i++) {
            thousandElementsList.add(i);
        }
        return thousandElementsList;
    }

    /**
     * Tests the get method of a list.
     *
     * @see PhonyList#get(int)
     * @type Functional
     * @input []
     * @oracle The value returned with get must correspond to the value added.
     * @passed Yes
     */
    @Test
    public void get_Test() {
        // Creating a call context
        PhonyList<Integer> actual = list();

        int value = 0;

        actual.add(0);
        actual.add(1);

        // Calling the tested operation
        value = actual.get(1);

        // Oracle
        assertEquals(value, 1);
    }

    /**
     * Tests the isEmpty method of a list.
     *
     * @see PhonyList#isEmpty
     * @type Functional
     * @input []
     * @oracle The value returned with get must correspond if the list is empty or not.
     * @passed Yes
     */
    @Test
    public void isEmpty_Test() {
        // Creating a call context
        PhonyList<Integer> actual = list();
        boolean empty;

        actual.add(0);

        // Calling the tested operation
        empty = actual.isEmpty();

        // Oracle
        assertEquals(empty, false);

        actual.clear();
        empty = actual.isEmpty();

        assertEquals(empty, true);
    }

    /**
     * Tests the contains method of a list.
     *
     * @see PhonyList#contains(Object)
     * @type Functional
     * @input []
     * @oracle The value returned with contains must correspond if the object is in the list or not.
     * @passed Yes
     */
    @Test
    public void contains_Test() {
        // Creating a call context
        PhonyList<Integer> actual = list();

        boolean value;

        actual.add(0);
        actual.add(1);

        // Calling the tested operation
        value = actual.contains(1);

        // Oracle
        assertEquals(value, true);

        actual.clear();
        value = actual.contains(1);

        assertEquals(value, false);
    }

    /**
     * Tests the contains method of a list.
     *
     * @see PhonyList#contains(Object)
     * @type Functional
     * @input []
     * @oracle The value returned with contains must correspond if the object is in the list or not.
     * @passed Yes
     */
    @Test
    public void set_Test() {
        // Creating a call context
        PhonyList<Integer> actual = list();

        int oldValue = 0;

        actual.add(0);
        actual.add(1);

        // Calling the tested operation
        oldValue = actual.set(1, 2);

        // Oracle
        assertEquals(1, oldValue);
    }

    /**
     * Tests the removeAll method of a list.
     *
     * @see PhonyList#removeAll(Collection)
     * @type Functional
     * @input []
     * @oracle The value returned with contains must correspond if the object is in the list or not.
     * @passed Yes
     */
    @Test
    public void removeAll_Test() {
        // Creating a call context
        PhonyList<Integer> actual = list();

        boolean value;

        actual.add(0);
        actual.add(1);
        actual.add(2);

        Collection<Integer> collection = new ArrayList<Integer>();
        collection.add(1);
        collection.add(2);

        // Calling the tested operation
        value = actual.removeAll(collection);

        // Oracle
        assertTrue(value);
    }

    /**
     * Tests the remove method of a list.
     *
     * @see PhonyList#remove(Object)
     * @type Functional
     * @input []
     * @oracle The value returned with remove must correspond if the object was in the list or not.
     * @passed Yes
     */
    @Test
    public void remove_Test()
    {
        PhonyList<Integer> actual = list();
        actual.add(1);
        actual.add(2);
        actual.add(3);

        boolean res = actual.remove((Integer)3);

        assertTrue(res);
    }

    /**
     * Tests the remove method of a list.
     *
     * @see PhonyList#remove(Object)
     * @type Functional
     * @input []
     * @oracle The value returned with remove must correspond if the object was in the list or not.
     * @passed Yes
     */
    @Test
    public void removeFalse_Test()
    {
        PhonyList<Integer> actual = list();
        actual.add(1);
        actual.add(2);
        actual.add(3);

        boolean res = actual.remove((Integer)4);

        assertFalse(res);
    }

    /**
     * Tests the remove method of a list.
     *
     * @see PhonyList#remove(Object)
     * @type Functional
     * @input []
     * @oracle The value returned with remove must correspond if the object was in the list or not.
     * @passed Yes
     */
    @Test
    public void remove_nullFound()
    {
        PhonyList<Integer> actual = list();
        actual.add(1);
        actual.add(2);
        actual.add(null);

        boolean res = actual.remove(null);

        assertTrue(res);
    }

    @Test
    public void remove_nullNotFound()
    {
        PhonyList<Integer> actual = list();
        actual.add(1);
        actual.add(2);
        actual.add(3);

        boolean res = actual.remove(null);

        assertFalse(res);
    }

    /**
     * Tests the addAll method of a list.
     *
     * @see PhonyList#addAll(int, Collection)
     * @type Functional
     * @input []
     * @oracle The value returned with addAll
     * @passed Yes
     */
    @Test
    public void addAll_Test()
    {
        PhonyList<Integer> actual = list();
        actual.add(0);
        actual.add(3);

        Collection<Integer> toInsert = new ArrayList<Integer>();
        toInsert.add((Integer)1);
        toInsert.add((Integer)2);

        actual.addAll(1, toInsert);

        assertEquals((Integer) 1, actual.get(1));
    }

    @Test
    public void addAll_ZeroMoved()
    {
        PhonyList<Integer> actual = list();
        actual.add(0);
        actual.add(3);

        Collection<Integer> toInsert = new ArrayList<Integer>();
        toInsert.add((Integer)1);
        toInsert.add((Integer)2);

        actual.addAll(2, toInsert);

        assertEquals((Integer) 1, actual.get(2));
    }

    @Test
    public void addAll_ReturnTrue()
    {
        PhonyList<Integer> actual = list();
        actual.add(0);
        actual.add(3);

        Collection<Integer> toInsert = new ArrayList<Integer>();
        toInsert.add((Integer)1);
        toInsert.add((Integer)2);

        boolean res = actual.addAll(1, toInsert);

        assertTrue(res);
    }

    @Test
    public void addAll_ReturnFalse()
    {
        PhonyList<Integer> actual = list();
        actual.add(0);
        actual.add(3);

        Collection<Integer> toInsert = new ArrayList<Integer>();

        boolean res = actual.addAll(1, toInsert);

        assertFalse(res);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void rangeCheck_Test()
    {
        PhonyList<Integer> actual = list();
        actual.add(0);

        Integer res = actual.get(2);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void rangeCheckForAdd_NegativeIndex()
    {
        PhonyList<Integer> actual = list();
        actual.add(0);

        Collection<Integer> toInsert = new ArrayList<Integer>();
        toInsert.add((Integer)1);

        actual.addAll(-1, toInsert);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void rangeCheckForAdd_OverSizeIndex()
    {
        PhonyList<Integer> actual = list();
        actual.add(0);

        Collection<Integer> toInsert = new ArrayList<Integer>();
        toInsert.add((Integer)1);

        actual.addAll(2, toInsert);
    }

    @Test
    public void removeRange_Test()
    {
        PhonyList<Integer> actual = list();
        actual.add(0);
        actual.add(1);
        actual.add(2);

        actual.removeRange(0, 1);

        assertEquals((Integer)1, actual.get(0));
    }

    @Test(expected = NullPointerException.class)
    public void removeAllNull_Test() {
        // Creating a call context
        PhonyList<Integer> actual = list();

        boolean value;

        actual.add(0);
        actual.add(1);
        actual.add(2);

        // Calling the tested operation
        value = actual.removeAll(null);
    }

    @Test
    public void removeAllNonExisting_Test() {
        // Creating a call context
        PhonyList<Integer> actual = list();

        boolean value;

        actual.add(0);
        actual.add(1);
        actual.add(2);

        Collection<Integer> collection = new ArrayList<Integer>();
        collection.add(3);
        collection.add(4);
        collection.add(5);

        // Calling the tested operation
        value = actual.removeAll(collection);

        assertFalse(value);
    }

    @Test
    public void indexOf_nullNotFound()
    {
        // Creating a call context
        PhonyList<Integer> actual = list(1,2);

        int res = actual.indexOf(null);

        assertEquals(-1, res);
    }

}
