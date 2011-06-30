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

package com.maltera.mylyn.internal.rt.ui.wizard;

import org.eclipse.osgi.util.NLS;

/**
 * @author Sam Hanes
 *
 */
public class Messages extends NLS {
	private static final String BUNDLE_NAME = "com.maltera.mylyn.internal.rt.ui.wizard.messages"; //$NON-NLS-1$
	
	static {
		// load message values from bundle file
		reloadMessages();
	}

	public static void reloadMessages() {
		NLS.initializeMessages(BUNDLE_NAME, Messages.class);
	}
	
	public static String RtRepositorySettingsPage_RT_Repository_Settings;
	public static String RtRepositorySettingsPage_EXAMPLE_BESTPRACTICAL;
}
