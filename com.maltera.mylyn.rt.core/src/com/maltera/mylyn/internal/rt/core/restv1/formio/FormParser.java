/******************************************************************************
 * Copyright (c) 2011 Sam Hanes and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Sam Hanes - initial API and implementation
 ******************************************************************************/

package com.maltera.mylyn.internal.rt.core.restv1.formio;

import java.nio.BufferUnderflowException;
import java.nio.CharBuffer;

/**
 * Unfortunately forms can't be parsed from a stream as at least two passes
 * over the input are required. 
 */
public class FormParser {
	private CharBuffer source;
	int position = 0;
	int capture = -1;
	
	private Form form = new Form();
	
	public FormParser (CharSequence source) {
		this( CharBuffer.wrap( source ) );
	}
	
	public FormParser (CharBuffer source) {
		if (null == source)
			throw new NullPointerException( "source may not be null" );
		this.source = source;
	}
	
	private char LA (int distance) {
		return source.get( position + distance - 1 );
	}
	
	private char LA() {
		return LA( 1 );
	}

	private boolean matchAhead (int distance, String match) {
		for (int idx = 0; idx < match.length(); idx++) {
			if (LA( distance + idx ) != match.charAt( idx )) return false;
		}
		return true;
	}
	
	private void consumeChar() {
		position++;
	}
	
	/** Consumes up to and including the next newline.
	 */
	private void consumeLine() {
		try {
			char current;
			do {
				current = LA();
				consumeChar();
			} while ('\n' != current);
		} catch (BufferUnderflowException caught) {
			// we've hit the end of the buffer, just return
		}
	}
	
	/** Consumes unlimited non-line break whitespace.
	 */
	private void consumeSpace() {
		try {
			char current;
			do {
				current = LA();
				consumeChar();
			} while ('\n' != current && Character.isWhitespace( current ));
		} catch (BufferUnderflowException caught) {
			// we've hit the end of the buffer, just return
		}
	}
	
	private void captureStart() {
		if (-1 != capture) throw new IllegalStateException(
				"attempting to start capture while it's already running" );
		capture = position;
	}
	
	private String captureEnd() {
		if (capture < 0)
			throw new IllegalStateException( "capture is not running" );
		String result = source.subSequence( capture, position ).toString();
		capture = -1;
		return result;
	}
	
	
	private void matchKey() {
		/* TODO: this should also support C(?:ustom)?F(?:ield)?-
		 * per form_parse, but form_compose only generates CF.{} */
		if (LA( 4 ) == '{') {
			match( "CF.{" );
			
		}
	}
	
	private void matchEntry() {
		int start = source.position() - 1;
		while (null != current && ':' != current)
			consumeChar();
		String key = source.subSequence(
				start, source.position() - 1 ).toString();
		
		// there may be optional whitespace between colon and value
		consumeSpace();
		
		
	}
}
