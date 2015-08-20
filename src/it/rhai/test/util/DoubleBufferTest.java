package it.rhai.test.util;

import it.rhai.util.DoubleBuffer;
import static org.junit.Assert.*;

import org.junit.Test;

public class DoubleBufferTest {

	DoubleBuffer<Object> buffer = new DoubleBuffer<Object>();
	Object ob1 = new Object();
	Object ob2 = new Object();
	Object ob3 = new Object();

	@Test
	public void testAdd() {
		buffer.addElement(ob1);
		buffer.addElement(ob2);
		assertEquals(2, buffer.size());
	}

	@Test
	public void testGet() {
		buffer.addElement(ob1);
		buffer.addElement(ob2);
		buffer.addElement(ob3);
		assertEquals(ob1, buffer.nextElement());
		assertEquals(ob2, buffer.nextElement());
		assertEquals(1, buffer.size());
		assertEquals(ob3, buffer.nextElement());
	}

}
