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

package com.maltera.mylyn.internal.rt.ui;

import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;

/**
 * @author Sam Hanes
 */
public class RtUiPlugin extends AbstractUIPlugin {

	public static final String ID_PLUGIN = "com.maltera.mylyn.internal.rt.ui"; //$NON-NLS-1$

	private static RtUiPlugin plugin;

	public RtUiPlugin() {
		plugin = this;
	}

	@Override
	public void start(BundleContext context) throws Exception {
		super.start(context);
		plugin = this;
	}

	@Override
	public void stop(BundleContext context) throws Exception {
		plugin = null;
		super.stop(context);
	}

	public static RtUiPlugin getDefault() {
		return plugin;
	}

}
