package com.maltera.mylyn.internal.rt.core.restv1.formio;

import org.junit.*;
import static org.junit.Assert.*;

public class TestFieldParsing {
	private FormParser parser;
	
	private void prepareParser (String source) {
		parser = new FormParser( source );
	}
	
	@Test public void testSimpleEmptyField()
	throws Exception {
		prepareParser( "AdminCc:\n" );
		parser.matchKey();
	}
}
