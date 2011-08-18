package obsolete;

import static org.junit.Assert.*;


import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * 
 * @author kto
 * @obsolete
 */
public class ArrayList2DTest {

	private ArrayList2D<Integer> positions;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		positions = new ArrayList2D<Integer>();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testArrayList2D() {
	}

//	@Test
//	public void testEnsureCapacityInt() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testEnsureCapacityIntInt() {
//		fail("Not yet implemented");
//	}
//
	@Test
	public void testAdd() {
		positions.Add(1, 0);
		assertEquals(1, positions.getNumRows());
		assertEquals(true, positions.contains(1));
		assertEquals(1, positions.getNumCols(0));
		
		positions.Add(4, 0);
		assertEquals(1, positions.getNumRows());
		assertEquals(2, positions.getNumCols(0));
		
		assertEquals(4, positions.get(0, 1).intValue());

		positions.Add(4, 2);
		assertEquals(3, positions.getNumRows());
		assertEquals(2, positions.getNumCols(0));
		assertEquals(0, positions.getNumCols(1));
		assertEquals(1, positions.getNumCols(2));
		
		positions.Add(9, 2);
		assertEquals(3, positions.getNumRows());
		assertEquals(2, positions.getNumCols(0));
		assertEquals(0, positions.getNumCols(1));
		assertEquals(2, positions.getNumCols(2));

		positions.Add(16, 2);
		assertEquals(3, positions.getNumRows());
		assertEquals(2, positions.getNumCols(0));
		assertEquals(0, positions.getNumCols(1));
		assertEquals(3, positions.getNumCols(2));

	}
//
//	@Test
//	public void testGet() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testSet() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testRemove() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testContains() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testGetNumRows() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testGetNumCols() {
//		fail("Not yet implemented");
//	}

}
