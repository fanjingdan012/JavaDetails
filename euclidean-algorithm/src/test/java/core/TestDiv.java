package core;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import core.Div;

public class TestDiv {

	@Test
	public void test_ordinary_HeShu() {
		Div.answer = new ArrayList<Integer>();
		Div.divmain(10);
		assertTrue(Div.answer.contains(2));
		assertTrue(Div.answer.contains(5));
		assertEquals(Div.answer.size(),2);
	}
	@Test
	public void test_ordinary_SuShu() {
		Div.answer = new ArrayList<Integer>();
		Div.divmain(17);
		assertEquals(Div.answer.size(),0);
	}
	@Test
	public void test_big_HeShu() {
		Div.answer = new ArrayList<Integer>();
		Div.divmain(20000);
		assertTrue(Div.answer.contains(2));
		assertTrue(Div.answer.contains(5));
		assertEquals(Div.answer.size(),9);
	}
	@Test
	public void test_big_SuShu() {
		Div.answer = new ArrayList<Integer>();
		Div.divmain(1657);
		assertEquals(Div.answer.size(),0);
	}

}
