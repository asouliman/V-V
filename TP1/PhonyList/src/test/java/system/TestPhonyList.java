package system;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by Thomas & Amona on 29/09/14.
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
     * @input list=[0,1], o=1
     * @oracle It must return 1.
     * @passed Yes
     */
    @Test
    public void get_Test() {
        // Creating a call context
        PhonyList<Integer> list = list(0, 1);

        // Calling the tested operation
        int value = list.get(1);

        // Oracle
        assertEquals(value, 1);
    }

    /**
     * Tests the isEmpty method on a non empty list.
     *
     * @see PhonyList#isEmpty
     * @type Functional
     * @input list=[0]
     * @oracle It must return False.
     * @passed Yes
     */
    @Test
    public void isEmpty_False() {
        // Creating a call context
        PhonyList<Integer> list = list(0);

        // Calling the tested operation
        boolean empty = list.isEmpty();

        // Oracle
        assertFalse(empty);
    }

    /**
     * Tests the isEmpty method on an empty list.
     *
     * @see PhonyList#isEmpty
     * @type Functional
     * @input list=[]
     * @oracle It must return True.
     * @passed Yes
     */
    @Test
    public void isEmpty_True() {
        // Creating a call context
        PhonyList<Integer> list = list();

        // Calling the tested operation
        boolean empty = list.isEmpty();

        // Oracle
        assertTrue(empty);
    }

    /**
     * Tests the contains method of a list.
     *
     * @see PhonyList#contains(Object)
     * @type Functional
     * @input list=[0,1], o=1
     * @oracle It must return True.
     * @passed Yes
     */
    @Test
    public void contains_True() {
        // Creating a call context
        PhonyList<Integer> list = list(0, 1);

        // Calling the tested operation
        boolean contains = list.contains(1);

        // Oracle
        assertTrue(contains);
    }

    /**
     * Tests the contains method of a list.
     *
     * @see PhonyList#contains(Object)
     * @type Functional
     * @input list=[0,1], o=2
     * @oracle It must return False.
     * @passed Yes
     */
    @Test
    public void contains_False() {
        // Creating a call context
        PhonyList<Integer> list = list(0, 1);

        // Calling the tested operation
        boolean contains = list.contains(2);

        // Oracle
        assertFalse(contains);
    }

    /**
     * Tests the set method of a list.
     *
     * @see PhonyList#set(int, Object)
     * @type Functional
     * @input list=[0,1], o=2
     * @oracle It must return 1.
     * @passed Yes
     */
    @Test
    public void set_TestOldValue() {
        // Creating a call context
        PhonyList<Integer> list = list(0, 1);

        // Calling the tested operation
        int oldValue = list.set(1, 2);

        // Oracle
        assertEquals(1, oldValue);
    }

    /**
     * Tests the set method of a list.
     *
     * @see PhonyList#set(int, Object)
     * @type Functional
     * @input list=[0,1], o=2
     * @oracle It must return 2.
     * @passed No
     * @correction
     * <pre>
     *     l.245
     *     - elementData[++index] = element;
     *     + elementData[index] = element;
     * </pre>
     */
    @Test
    public void set_TestNewValue() {
        // Creating a call context
        PhonyList<Integer> list = list(0, 1);
        int index = 1;

        // Calling the tested operation
        int oldValue = list.set(index, 2);

        // Oracle
        assertEquals(2, (int)list.get(index));
    }

    /**
     * Tests the removeAll method of a list.
     *
     * @see PhonyList#removeAll(Collection)
     * @type Functional
     * @input list=[0,1,2], collection=[1,2]
     * @oracle It must return True.
     * @passed Yes
     */
    @Test
    public void removeAll_Good() {
        // Creating a call context
        PhonyList<Integer> list = list(0, 1, 2);

        Collection<Integer> collection = new ArrayList<Integer>();
        collection.add(1);
        collection.add(2);

        // Calling the tested operation
        boolean value = list.removeAll(collection);

        // Oracle
        assertTrue(value);
    }

    /**
     * Tests the removeAll method of a list.
     *
     * @see PhonyList#removeAll(Collection)
     * @type Functional
     * @input list=[0,1,2], collection=null
     * @oracle It must throw a NullPointerException.
     * @passed Yes
     */
    @Test(expected = NullPointerException.class)
    public void removeAll_Null() {
        // Creating a call context
        PhonyList<Integer> list = list(0, 1, 2);

        // Calling the tested operation
        boolean value = list.removeAll(null);
    }

    /**
     * Tests the removeAll method on an empty list.
     *
     * @see PhonyList#removeAll(Collection)
     * @type Functional
     * @input list=[0,1,2], collection=[3,4,5]
     * @oracle It must return False.
     * @passed Yes
     */
    @Test
    public void removeAll_NotPresentInList() {
        // Creating a call context
        PhonyList<Integer> list = list(0, 1 ,2);

        Collection<Integer> collection = new ArrayList<Integer>();
        collection.add(3);
        collection.add(4);
        collection.add(5);

        // Calling the tested operation
        boolean value = list.removeAll(collection);

        // Oracle
        assertFalse(value);
    }

    /**
     * Test removing an element of a list other than the last one.
     *
     * @see PhonyList#remove(Object)
     * @type Functional
     * @input list=[1,2,3], o=1
     * @oracle It must return True.
     * @passed Yes
     */
    @Test
    public void remove_NotLastElement() {
        // Creating a call context
        PhonyList<Integer> list = list(1, 2, 3);

        // Calling the tested operation
        boolean res = list.remove((Integer)1);

        // Oracle
        assertTrue(res);
    }

    /**
     * Test trying to remove an element not present in the list.
     *
     * @see PhonyList#remove(Object)
     * @type Functional
     * @input list=[1,2,3], o=4
     * @oracle It must return False.
     * @passed Yes
     */
    @Test
    public void remove_NotPresentInList() {
        // Creating a call context
        PhonyList<Integer> list = list(1, 2, 3);

        // Calling the tested operation
        boolean res = list.remove((Integer)4);

        // Oracle
        assertFalse(res);
    }

    /**
     * Test removing a null element from a list.
     *
     * @see PhonyList#remove(Object)
     * @type Functional
     * @input list=[1,2,null], o=null
     * @oracle It must return True.
     * @passed Yes
     */
    @Test
    public void remove_NullFound() {
        // Creating a call context
        PhonyList<Integer> list = list(1, 2, null);

        // Calling the tested operation
        boolean res = list.remove(null);

        // Oracle
        assertTrue(res);
    }

    /**
     * Test trying to remove a null element from a list.
     *
     * @see PhonyList#remove(Object)
     * @type Functional
     * @input list=[1,2,3], o=null
     * @oracle It must return False.
     * @passed Yes
     */
    @Test
    public void remove_NullNotFound() {
        // Creating a call context
        PhonyList<Integer> list = list(1, 2, 3);

        // Calling the tested operation
        boolean res = list.remove(null);

        // Oracle
        assertFalse(res);
    }

    /**
     * Test removing the last element of a list.
     *
     * @see PhonyList#remove(Object)
     * @type Functional
     * @input list=[0,1,2], o=2
     * @oracle It must return True.
     * @passed Yes
     */
    @Test
    public void remove_LastElement() {
        // Creating a call context
        PhonyList<Integer> list = list(0,1,2);

        // Calling the tested operation
        boolean res = list.remove((Integer)2);

        // Oracle
        assertTrue(res);
    }

    /**
     * Tests the addAll method of a list.
     *
     * @see PhonyList#addAll(int, Collection)
     * @type Functional
     * @input list=[0,3], toInsert=[1,2]
     * @oracle It must return 1.
     * @passed Yes
     */
    @Test
    public void addAll_ElementMoved() {
        // Creating a call context
        PhonyList<Integer> list = list(0, 3);

        Collection<Integer> toInsert = new ArrayList<Integer>();
        toInsert.add((Integer)1);
        toInsert.add((Integer)2);

        // Calling the tested operation
        list.addAll(1, toInsert);

        // Oracle
        assertEquals((Integer) 1, list.get(1));
    }

    /**
     * Tests the addAll method of a list.
     *
     * @see PhonyList#addAll(int, Collection)
     * @type Functional
     * @input list=[0,3], toInsert=[1,2]
     * @oracle It must return 1.
     * @passed Yes
     */
    @Test
    public void addAll_ZeroMoved() {
        // Creating a call context
        PhonyList<Integer> list = list(0, 3);

        Collection<Integer> toInsert = new ArrayList<Integer>();
        toInsert.add((Integer)1);
        toInsert.add((Integer)2);

        // Calling the tested operation
        list.addAll(2, toInsert);

        // Oracle
        assertEquals((Integer) 1, list.get(2));
    }

    /**
     * Tests the addAll method of a list.
     *
     * @see PhonyList#addAll(int, Collection)
     * @type Functional
     * @input list=[0,3], toInsert=[1,2]
     * @oracle It must return True.
     * @passed Yes
     */
    @Test
    public void addAll_ReturnTrue() {
        // Creating a call context
        PhonyList<Integer> list = list(0, 3);

        Collection<Integer> toInsert = new ArrayList<Integer>();
        toInsert.add((Integer)1);
        toInsert.add((Integer)2);

        // Calling the tested operation
        boolean res = list.addAll(1, toInsert);

        // Oracle
        assertTrue(res);
    }

    /**
     * Tests the addAll method of a list.
     *
     * @see PhonyList#addAll(int, Collection)
     * @type Functional
     * @input list=[0,3], toInsert=[]
     * @oracle It must return False.
     * @passed Yes
     */
    @Test
    public void addAll_ReturnFalse() {
        // Creating a call context
        PhonyList<Integer> list = list(0, 3);

        Collection<Integer> toInsert = new ArrayList<Integer>();

        // Calling the tested operation
        boolean res = list.addAll(1, toInsert);

        // Oracle
        assertFalse(res);
    }

    /**
     * Tests the rangeCheck method by calling the get method.
     *
     * @see PhonyList#rangeCheck(int)
     * @type Functional
     * @input list=[0], o=2
     * @oracle It must throw an IndexOutOfBoundsException.
     * @passed Yes
     */
    @Test(expected = IndexOutOfBoundsException.class)
    public void rangeCheck_IndexOutOfBounds() {
        // Creating a call context
        PhonyList<Integer> list = list(0);

        // Calling the tested operation
        Integer res = list.get(2);
    }

    /**
     * Tests the rangeCheckForAdd method by calling the addAll method.
     *
     * @see PhonyList#rangeCheckForAdd(int)
     * @type Functional
     * @input list=[0], toInsert=[1], index=-1
     * @oracle It must throw an IndexOutOfBoundsException.
     * @passed Yes
     */
    @Test(expected = IndexOutOfBoundsException.class)
    public void rangeCheckForAdd_NegativeIndex() {
        // Creating a call context
        PhonyList<Integer> list = list(0);

        Collection<Integer> toInsert = new ArrayList<Integer>();
        toInsert.add((Integer)1);

        // Calling the tested operation
        list.addAll(-1, toInsert);
    }

    /**
     * Tests the rangeCheckForAdd method by calling the addAll method.
     *
     * @see PhonyList#rangeCheckForAdd(int)
     * @type Functional
     * @input list=[0], toInsert=[1], index=2
     * @oracle It must throw an IndexOutOfBoundsException.
     * @passed Yes
     */
    @Test(expected = IndexOutOfBoundsException.class)
    public void rangeCheckForAdd_OverSizeIndex() {
        // Creating a call context
        PhonyList<Integer> list = list(0);

        Collection<Integer> toInsert = new ArrayList<Integer>();
        toInsert.add((Integer)1);

        // Calling the tested operation
        list.addAll(2, toInsert);
    }

    /**
     * Tests the removeRange method.
     *
     * @see PhonyList#removeRange(int, int)
     * @type Functional
     * @input list=[0,1,2], i=0, j=1
     * @oracle It must return 1.
     * @passed Yes
     */
    @Test
    public void removeRange_Test() {
        // Creating a call context
        PhonyList<Integer> list = list(0, 1, 2);

        // Calling the tested operation
        list.removeRange(0, 1);

        // Oracle
        assertEquals((Integer)1, list.get(0));
    }

    /**
     * Tests the indexOf method.
     *
     * @see PhonyList#indexOf(Object)
     * @type Functional
     * @input list=[1,2], o=null
     * @oracle It must return -1.
     * @passed Yes
     */
    @Test
    public void indexOf_NullNotFound() {
        // Creating a call context
        PhonyList<Integer> list = list(1,2);

        // Calling the tested operation
        int res = list.indexOf(null);

        // Oracle
        assertEquals(-1, res);
    }

}
