package CS284;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class IDLListTest {
	
	@Test
	public void addTest() {
		IDLList<Integer> other = new IDLList<Integer>();
		assertEquals(true, other.add(10));
		assertEquals(true, other.add(11));
		assertEquals(true, other.add(1000));
		assertEquals(true, other.add(4));
		assertEquals(true, other.add(0));
	}
	@Test
	public void appendTest() {
		IDLList<Integer> other = new IDLList<Integer>();	
		assertEquals(true, other.append(10));
		assertEquals(true, other.append(9));
		assertEquals(true, other.append(8));
		assertEquals(true, other.append(7));
		assertEquals(true, other.append(6));
	}
	@Test
	public void getHeadTest() {
		IDLList<Integer> other = new IDLList<Integer>();	
		other.add(4);
		assertEquals(4, other.getHead());
		other.add(5);
		assertEquals(5, other.getHead());
		other.add(6);
		assertEquals(6, other.getHead());
	}
	@Test
	public void getTest() {
		IDLList<Integer> other = new IDLList<Integer>();	
		other.add(1);
		other.add(1);
		other.add(1);
		other.add(1);
		other.add(1);
		assertEquals(1, other.get(0));
		assertEquals(1, other.get(1));
		assertEquals(1, other.get(2));
		assertEquals(1, other.get(3));
		assertEquals(1, other.get(4));
	}
	@Test
	public void getLastTest() {
		IDLList<Integer> other = new IDLList<Integer>();	
		other.add(5);
		other.add(4);
		assertEquals(5, other.getLast());
		other.add(3);
		other.add(2);
		other.add(1);
		assertEquals(5, other.getLast());
		
	}
	@Test
	public void removeTest() {
		IDLList<Integer> other = new IDLList<Integer>();	
		other.add(5);
		other.add(4);
		assertEquals(false, other.remove(17));
		assertEquals(false, other.remove(2));
		assertEquals(true, other.remove(5));
	}	
	@Test
	public void removeLastTest() {
		IDLList<Integer> other = new IDLList<Integer>();
		other.add(1);
		other.add(2);
		other.add(3);
		assertEquals(1, other.removeLast().intValue());
		other.add(4);
		assertEquals(2, other.removeLast().intValue());
		other.add(5);
		assertEquals(3, other.removeLast().intValue());
	}
}