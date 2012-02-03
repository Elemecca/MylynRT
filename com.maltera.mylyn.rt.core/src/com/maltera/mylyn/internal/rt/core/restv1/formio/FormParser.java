/*******************************************************************************
 * Copyright (c) 2011 Sam Hanes and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Sam Hanes - initial API and implementation
 *******************************************************************************/

package com.maltera.mylyn.internal.rt.core.restv1.formio;

import java.nio.CharBuffer;

/**
 * Unfortunately forms can't be parsed from a stream as at least two passes
 * over the input are required. 
 */
public class FormParser {
	private CharBuffer source;
	private Form form = new Form();
	
	public FormParser (CharSequence source) {
		this( CharBuffer.wrap( source ) );
	}
	
	public FormParser (CharBuffer source) {
		if (null == source)
			throw new NullPointerException( "source may not be null" );
		this.source = source;
	}

	
}
