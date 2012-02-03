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

package com.maltera.mylyn.internal.rt.core.restv1;

import org.apache.commons.httpclient.HttpClient;
import org.eclipse.mylyn.commons.net.AbstractWebLocation;
import org.eclipse.mylyn.commons.net.WebUtil;

/**
 * @author Sam Hanes
 *
 */
public class RtClient {
	private static final String USER_AGENT = "RTConnector";
	
	private final AbstractWebLocation location;
	private final HttpClient httpClient;
	
	public RtClient(AbstractWebLocation location) {
		this.location = location;
		
		this.httpClient = new HttpClient( WebUtil.getConnectionManager() );
		WebUtil.configureHttpClient( this.httpClient, USER_AGENT );
	}
}
