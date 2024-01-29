/** this is a new change */
package MyLinkedList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.ArrayList;
import java.util.Random;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

@TestMethodOrder(OrderAnnotation.class)
class MyLinkedListTester {
	private static final int LONG_LIST_LENGTH =10; 

	MyLinkedList<String> shortList;
	MyLinkedList<Integer> emptyList;
	MyLinkedList<Integer> longerList;
	MyLinkedList<Integer> list1;
	
	/**
	 * Creates 4 empty linked list structures for testing:
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	public void setUp() throws Exception {
	    shortList = new MyLinkedList<String>();
		emptyList = new MyLinkedList<Integer>();
		longerList = new MyLinkedList<Integer>();
		list1 = new MyLinkedList<Integer>();
		
	}
	
	
	/**
	 * Initializes 3 linked list structures for testing:
	 *    - shortList is linked list of Strings
	 *    - longerList is a linked list of 10 Integers from 0 .. 9
	 *    - list1 is a list of 3 Integers 
	 * @throws java.lang.Exception
	 */
	private void initLists() {
		// Feel free to use these lists, or add your own
		shortList.add("A");
		shortList.add("B");
		for (int i = 0; i < LONG_LIST_LENGTH; i++)
		{
			longerList.add(i);
		}
		list1.add(65);
		list1.add(21);
		list1.add(42);
		
	}
	

	/**
	 * Checks connectivity of path from head to tail and tail to head.
	 * This has been added for enabling debug....
	 *
	 * @return true, if successful
	 */
	public boolean checkPath(MyLinkedList<?> list) {
		LLNode<?> head = list.getHead();
		LLNode<?> tail = list.getTail();
		int size = list.size();
		
		LLNode<?> node = head.next;
		for (int i = 0; i < size;i++) {
			node = node.next;
		}
		if (node != tail) return false;
		node = node.prev;
		for (int i = 0; i < size;i++) {
			node = node.prev;
		}
		return(node == head);
		
	}
	
	/** Test adding an element into the end of the list, specifically
	 *  public boolean add(E element)
	 *  Leverages size() method as a check
	 * */
	@Test
	@Order(1)
	public void testAdd()
	{
		System.out.println("Test 1: add method");
		System.out.println("        Checking that all lists are empty (size == 0)");
		assertEquals(0,emptyList.size());
		assertTrue(checkPath(emptyList));
		assertEquals(0,shortList.size());
		assertEquals(0,longerList.size());
		assertEquals(0,list1.size());
		
		System.out.println("        Checking that each list can be added to");        
		assertTrue(emptyList.add(0));
		assertTrue(emptyList.size() == 1);
		assertTrue(shortList.add("Hello"));
		assertTrue(shortList.size() == 1);
		assertTrue(list1.add(-1));
		assertTrue(list1.size() == 1);
		for (int i = 0; i < LONG_LIST_LENGTH; i++) {
			assertTrue(longerList.add(i));
			assertTrue(longerList.size() == (i+1));
		}
		System.out.println("Test 1 Passed");        
	}

	/** Test if the get method is working correctly.
	 */
	@Test
	@Order(2)
	public void testGet()
	{
		initLists();
		System.out.println("Test 2: get method");
		System.out.println("        Checking get from an empty list throws exception");
		//test empty list, get should throw an exception
		try {
			emptyList.get(0);
			fail("Check out of bounds: Accessing Empty List");
		}
		catch (IndexOutOfBoundsException e) {
			assertTrue(true,"Access of empty list correctly generated an exception");
		}
		
		System.out.println("        Checking get from short list returns correct values");
		// test short list, first contents, then out of bounds
		assertEquals( "A", shortList.get(0),"Check first");
		assertEquals( "B", shortList.get(1),"Check second");
		
		System.out.println("        Checking get from short list with out of bounds index");
		try {
			shortList.get(-1);
			fail("Check out of bounds: Access using negative index");
		}
		catch (IndexOutOfBoundsException e) {
			assertTrue(true,"Attempt to access using negative index correctly generated an exception");		
		}
		try {
			shortList.get(2);
			fail("Check out of bounds: Access index >= size");
		}
		catch (IndexOutOfBoundsException e) {
			assertTrue(true,"Attempt to access index >= size correctly generated an exception");		
		}
		// test longer list contents
		System.out.println("        Checking get from longer list returns correct values");
		for(int i = 0; i<LONG_LIST_LENGTH; i++ ) {
			assertEquals((Integer)i, longerList.get(i),"Check "+i+ " element");
		}
		
		// test off the end of the longer array
		System.out.println("        Checking get from longer list with out of bounds index");
		try {
			longerList.get(-1);
			fail("Check out of bounds: Access using negative index");
		}
		catch (IndexOutOfBoundsException e) {
			assertTrue(true,"Attempt to access using negative index correctly generated an exception");				
		}
		try {
			longerList.get(LONG_LIST_LENGTH);
			fail("Check out of bounds: Access index >= size");
		}
		catch (IndexOutOfBoundsException e) {
			assertTrue(true,"Attempt to access index >= size correctly generated an exception");		
		}

		// do two adds and make sure that the data was added in the right position
		shortList.add("C");
		shortList.add("D");
		assertEquals( "C", shortList.get(2),"Check third");
		assertEquals( "D", shortList.get(3),"Check fourth");
				
		System.out.println("Test 2 Passed");        
	}
	
	/** Test removing an element from the list.
	 * You will want to add more tests.  */
	@Test
	@Order(3)
	public void testRemove()
	{
		initLists();
		//test empty list, get should throw an exception
		System.out.println("Test 3: remove method");
		System.out.println("        Checking remove from empty list generates out of bounds index exception");
		try {
			emptyList.remove(0);
			fail("Check out of bounds: Accessing Empty List");
		}
		catch (IndexOutOfBoundsException e) {
			assertTrue(true,"Access of empty list correctly generated an exception");
		}
		
		System.out.println("        Checking remove from short list");
		assertEquals( "A", shortList.remove(0),"Check first");
		assertEquals( "B", shortList.get(0),"Should be first");
		assertTrue(shortList.size() == 1);
		assertTrue(shortList.add("C"));
		assertTrue(shortList.size() == 2);
		assertEquals( "C", shortList.remove(1),"Check second");
		assertTrue(shortList.size() == 1);
		assertEquals( "B", shortList.remove(0),"Should be first");
		assertTrue(shortList.size() == 0);

		
		System.out.println("        Checking remove from out of bounds index");
		try {
			longerList.get(-1);
			fail("Check out of bounds: Access using negative index");
		}
		catch (IndexOutOfBoundsException e) {
			assertTrue(true,"Attempt to access using negative index correctly generated an exception");		
		}
		try {
			longerList.get(LONG_LIST_LENGTH);
			fail("Check out of bounds: Access index >= size");
		}
		catch (IndexOutOfBoundsException e) {
			assertTrue(true,"Attempt to access index >= size correctly generated an exception");		
		}
		
		System.out.println("        Checking random removal from longerList");
		ArrayList<Integer> checkList = new ArrayList<>();
		for (int i = 0; i < LONG_LIST_LENGTH; i++) {
			checkList.add(longerList.get(i));
			assertEquals(checkList.get(i),longerList.get(i));
		}
		
		Random rn = new Random();
		for (int i = 0; i < LONG_LIST_LENGTH; i++) {
			int rndInd = rn.nextInt(longerList.size());
			Integer removed = longerList.remove(rndInd);
			assertEquals(checkList.get(rndInd),removed);
			checkList.remove(rndInd);
		    assertEquals(checkList.size(),longerList.size());
		}
		System.out.println("Test 3 Passed");        

	}
	
	
	
	
	/** Test the size of the list */
	@Test
	@Order(4)
	public void testSize()
	{
		initLists();
		System.out.println("Test 4: size method");
		System.out.println("        Checking size of empty List");
		assertEquals(0, emptyList.size());
		System.out.println("        Checking size of list1");
		assertEquals(  3, list1.size(),"size check:");
		System.out.println("        Checking size of list1 after adds");
		list1.add(99);
		assertEquals(99,list1.get(list1.size()-1));
		list1.add(100);
		assertEquals(100,list1.get(list1.size()-1));
		assertEquals(5,list1.size(),"size check:");
		System.out.println("        Checking size of list1 after removes");
		list1.remove(list1.size()-1);
		list1.remove(0);
		assertEquals(3, list1.size(),"size check:");

		System.out.println("Test 4 Passed");        
}

	
	
	
	/** Test adding an element into the list at a specified index,
	 * specifically:
	 * public void add(int index, E element)
	 * */
	@Test
	@Order(5)
	public void testAddAtIndex()
	{
		initLists();
		System.out.println("Test 5: add(index,element) method");
		System.out.println("        Checking add with out of bounds index");
		try {
			shortList.add(-1,"FAIL");
			fail("Check out of bounds: add using negative index");
		}
		catch (IndexOutOfBoundsException e) {
			assertTrue(true,"Attempt to add using negative index correctly generated an exception");		
		}
		try {
			shortList.add(shortList.size()+1,"FAIL");  
			fail("Check out of bounds: Add at index > size");
		}
		catch (IndexOutOfBoundsException e) {
			assertTrue(true,"Attempt to add at index > size correctly generated an exception");		
		}

		System.out.println("        Checking add at index = 0");
		emptyList.add(0,100);
		assertEquals(1,emptyList.size());
		assertEquals(100,emptyList.get(0));
		assertTrue("A".equals(shortList.get(0)));
		assertTrue(shortList.size() == 2);
		shortList.add(0,"PASS_0");
		assertTrue("PASS_0".equals(shortList.get(0)));
		assertTrue("A".equals(shortList.get(1)));
		assertTrue(shortList.size() == 3);
		
		System.out.println("        Checking add at index = shortList.size()");
		try {
			shortList.get(shortList.size());  
			fail("Check out of bounds: get at index = size");
		}
		catch (IndexOutOfBoundsException e) {
			assertTrue(true,"Attempt to add at index > size correctly generated an exception");		
		}
		shortList.add(shortList.size(),"PASS_END");

		System.out.println("        Checking adds to longerList at random locations");
		ArrayList<Integer> checkList = new ArrayList<>();
		for (int i = 0; i < LONG_LIST_LENGTH; i++) {
			checkList.add(longerList.get(i));
			assertEquals(checkList.get(i),longerList.get(i));
		}
		Random rn = new Random();
		for (int i = 0; i < LONG_LIST_LENGTH; i++) {
			int rndInd = rn.nextInt(LONG_LIST_LENGTH);
			longerList.add(rndInd,100+i);
			assertTrue(longerList.size() == (checkList.size()+1));
			assertFalse(longerList.get(rndInd) == checkList.get(rndInd));
			assertTrue(longerList.get(rndInd+1)==checkList.get(rndInd));
			checkList.add(rndInd,100+i);
			assertEquals(checkList.get(rndInd),longerList.get(rndInd));
			assertEquals(checkList.get(rndInd+1),longerList.get(rndInd+1));
			assertTrue(longerList.size() == checkList.size());
		}
		for (int i = 0; i < longerList.size(); i++) {
			assertEquals(checkList.get(i),longerList.get(i));
		}
		
		
		System.out.println("        Checking adds to list1 at targeted indices");
		list1.add(0,99);
		list1.add(list1.size(),100);
		list1.add(list1.size()-1,101);
		assertEquals(6, list1.size(),"size check:");
		assertEquals((Integer) 99,list1.get(0));
		assertEquals((Integer) 65,list1.get(1));
		assertEquals((Integer) 21,list1.get(2));
		assertEquals((Integer) 42,list1.get(3));
		assertEquals((Integer) 101,list1.get(4));
		assertEquals((Integer) 100,list1.get(5));
		System.out.println("Test 5 Passed");        
	
	}
	
	/** Test setting an element in the list */
	@Test
	@Order(6)
	public void testSet()
	{
		initLists();
		System.out.println("Test 6: set(index,element) method");
		System.out.println("        Checking set with out of bounds index");
		try {
			shortList.set(-1,"FAIL");
			fail("Check out of bounds: Set using negative index");
		}
		catch (IndexOutOfBoundsException e) {
			assertTrue(true,"Attempt to set using negative index correctly generated an exception");		
		}
		try {
			shortList.set(shortList.size(),"FAIL");  
			fail("Check out of bounds: Set at index >= size");
		}
		catch (IndexOutOfBoundsException e) {
			assertTrue(true,"Attempt to set at index >= size correctly generated an exception");		
		}

		System.out.println("        Checking set on shortList - convert to lowercase");
		assertTrue("A".equals(shortList.get(0)));
		shortList.set(0,shortList.get(0).toLowerCase());
		assertTrue("a".equals(shortList.get(0)));
		assertTrue("B".equals(shortList.get(1)));
		shortList.set(1,shortList.get(1).toLowerCase());
		assertTrue("b".equals(shortList.get(1)));

		System.out.println("        Checking set on list1 - simple check");
		list1.add(0,99);
		list1.add(list1.size(),100);
		list1.add(list1.size()-1,101);
		Integer old = list1.set(4,10);
		assertEquals((Integer) 101,old);
		assertEquals((Integer) 10, list1.get(4));

		System.out.println("        Checking set at random indices - multiply by -1");
		ArrayList<Integer> checkList = new ArrayList<>();
		for (int i = 0; i < LONG_LIST_LENGTH; i++) {
			checkList.add(longerList.get(i));
			assertEquals(checkList.get(i),longerList.get(i));
		}
		Random rn = new Random();
		for (int i = 0; i < LONG_LIST_LENGTH; i++) {
			int rndInd = rn.nextInt(LONG_LIST_LENGTH);
			longerList.set(rndInd,(-1*longerList.get(rndInd)));
			if (rndInd != 0) 
				assertFalse(longerList.get(rndInd) == checkList.get(rndInd));
			else 
				assertTrue(longerList.get(rndInd) == checkList.get(rndInd));
			
			checkList.set(rndInd,(-1*checkList.get(rndInd)));
			assertEquals(checkList.get(rndInd),longerList.get(rndInd));
		}
		for (int i = 0; i < longerList.size(); i++) {
			assertEquals(checkList.get(i),longerList.get(i));
		}	
		System.out.println("Test 6 Passed");     
	}

}
