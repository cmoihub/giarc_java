package ass3;

import static org.junit.Assert.*;

import java.util.*;

import org.junit.Test;

public class TestStackReferenceBased {

	@Test
	public void test() {
	//	fail("Not yet implemented");
	}
	
	@Test
	public void testPush(){
		StackReferenceBased srb = 
				new StackReferenceBased();
		srb.createStack();
		srb.push(0.01);
		assertEquals("0.01", srb.toString());
		srb.push(0.03);
		assertEquals("0.030.01", srb.toString());
		assertFalse(srb.isEmpty());
	}
	/*
	public void testPop(){
		StackReferenceBased <Float> srb = 
				new StackReferenceBased<>();
	}*/
	
	@Test
	public void testPopAll(){
		List<Integer> integers = Arrays.asList(1,2,3,4,5,6);
		StackReferenceBased srb = 
				new StackReferenceBased();
		srb.createStack();
		for(Integer integer: integers){
			srb.push(integer);
		}
		assertFalse(srb.isEmpty());
		srb.popAll();
		assertTrue(srb.isEmpty());
	}
	
	@Test
	public void testIsEmpty(){
		StackReferenceBased srb = 
				new StackReferenceBased();
		srb.createStack();
		assertTrue(srb.isEmpty());
	}
}