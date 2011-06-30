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

package com.maltera.mylyn.internal.rt.core;

import org.eclipse.core.runtime.Plugin;
import org.osgi.framework.BundleContext;

/**
 * @author Sam Hanes
 */
public class RtCorePlugin extends Plugin {

	public static final String ID_PLUGIN = "org.eclipse.mylyn.trac.core"; //$NON-NLS-1$

	public static final String ENCODING_UTF_8 = "UTF-8"; //$NON-NLS-1$

	private static RtCorePlugin plugin;

	public final static String CONNECTOR_KIND = "rt"; //$NON-NLS-1$

	private RtRepositoryConnector connector;

	public RtCorePlugin() {
	}

	public static RtCorePlugin getDefault() {
		return plugin;
	}

	@Override
	public void start(BundleContext context) throws Exception {
		super.start(context);
		plugin = this;
	}

	@Override
	public void stop(BundleContext context) throws Exception {
		if (connector != null) {
			//connector.stop();
			connector = null;
		}

		plugin = null;
		super.stop(context);
	}

	public RtRepositoryConnector getConnector() {
		return connector;
	}

	void setConnector(RtRepositoryConnector connector) {
		this.connector = connector;
	}

}
