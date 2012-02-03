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

import java.util.HashMap;
import java.util.Map;

import org.eclipse.mylyn.commons.net.AbstractWebLocation;
import org.eclipse.mylyn.tasks.core.IRepositoryListener;
import org.eclipse.mylyn.tasks.core.TaskRepository;
import org.eclipse.mylyn.tasks.core.TaskRepositoryLocationFactory;

/**
 * @author Sam Hanes
 *
 */
public class RtClientManager implements IRepositoryListener {
	private final TaskRepositoryLocationFactory locationFactory;
	
	private final Map<String, RtClient> clientsByUrl = new HashMap<String, RtClient>();
	
	public RtClientManager() {
		locationFactory = new TaskRepositoryLocationFactory();
	}
	
	public RtClient getClient (TaskRepository repository) {
		RtClient client = clientsByUrl.get( repository.getRepositoryUrl() );
		
		// if it doesn't exist, synchronize to prevent duplicates
		if (client == null) synchronized (this) {
			// try again in case it was created while we were waiting
			client = clientsByUrl.get( repository.getRepositoryUrl() );
			if (client != null) return client;
			
			AbstractWebLocation location = locationFactory.createWebLocation( repository );
			client = new RtClient( location );
			clientsByUrl.put( repository.getRepositoryUrl(), client );
		}
		
		return client;
	}
	
	public synchronized void removeClient (TaskRepository repository) {
		clientsByUrl.remove( repository.getRepositoryUrl() );
	}
	
	public synchronized void clearClients() {
		clientsByUrl.clear();
	}

	public synchronized void repositoryAdded (TaskRepository repository) {
		// ensure there isn't a stale client lurking in the cache
		removeClient( repository );
	}

	public synchronized void repositoryRemoved (TaskRepository repository) {
		removeClient( repository );
	}

	public synchronized void repositorySettingsChanged(TaskRepository repository) {
		// the client needs to re-init, so get rid of the cached one
		removeClient( repository );
	}

	public synchronized void repositoryUrlChanged(TaskRepository repository, String oldUrl) {
		// remove the cache entry for the old URL, since it's no longer correct
		// don't re-add it at the new URL because the client need to re-init
		clientsByUrl.remove( oldUrl );
	}
}
