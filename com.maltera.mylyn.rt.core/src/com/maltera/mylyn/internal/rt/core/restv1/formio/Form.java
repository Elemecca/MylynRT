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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/** Represents an RT form, a set of key-value pairs.
 * This is a lot like a <code>Map</code>, but it allows duplicate keys. RT's
 * restrictions on keys are also enforced.
 * @author Sam Hanes
 */
public class Form {
	private final Map<String, List<String>> keys =
			new HashMap<String, List<String>>();
	
	/** Monitor to prevent clobbering of new keys added concurrently. */
	private final Object keyLock = new Object();
	
	public Form() {
	}
	
	/** Fetches or creates the values list for a key.
	 * @param key the key whose values list should be returned
	 * @return the values list for the given key
	 * @throws IllegalArgumentException if the key is not valid
	 */
	private List<String> getValues (String key) {
		if (null == key)
			throw new NullPointerException( "key must not be null" );
		
		List<String> values = keys.get( key );
		if (values != null) return values;
		
		this.validateKey( key );
		
		synchronized (keyLock) {
			// it might have been created while we were waiting on the lock
			values = keys.get( key );
			if (values != null) return values;
			
			values = new ArrayList<String>();
			keys.put( key, values );
		}
		
		return values;
	}
	
	/** Checks whether the given field name is valid.
	 * @param key the field name to be checked
	 * @throws IllegalArgumentException if the field name is invalid
	 */
	public void validateKey (String key) {
		if (null == key || key.isEmpty())
			throw new IllegalArgumentException(
					"key must have at least one character" );
		
		char c;
		if (key.startsWith( "CF.{" )) { // no support for old CF syntax
			if (!key.endsWith( "}" )) throw new IllegalArgumentException(
					"custom field must be terminated with a close brace" );
			
			int length = key.length() - 1;
			for (int idx = 4; idx < length; idx++) {
				c = key.charAt( idx );
				if (!( (c >= 'A' && c <= 'Z') || (c >= 'a' && c <= 'z')
						|| (c >= '0' && c <= '9') || c == '_' || c == '-'
						|| c == ':' || c == '(' || c == ')' || c == '/'
						|| Character.isWhitespace( c ) ))
					throw new IllegalArgumentException(
							"key may only contain letters, digits, " +
							"underscores and hyphens" );
			}
		} else {
			c = key.charAt( 0 );
			if (!( (c >= 'A' && c <= 'Z') || (c >= 'a' && c <= 'z') ))
				throw new IllegalArgumentException(
						"first character of key must be a letter" );
			
			int length = key.length();
			for (int idx = 1; idx < length; idx++) {
				c = key.charAt( idx );
				if (!( (c >= 'A' && c <= 'Z') || (c >= 'a' && c <= 'z')
						|| (c >= '0' && c <= '9') || c == '_' || c == '-' ))
					throw new IllegalArgumentException(
							"key may only contain letters, digits, " +
							"underscores and hyphens" );
			}
		}
	}
	
	/** Adds the given value to the list of values for the given key.
	 * @param key the key to which the value should be added
	 * @param value the value to be added
	 */
	public void add (String key, String value) {
		getValues( key ).add( value );
	}
}
