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

import java.net.MalformedURLException;
import java.net.URL;

import org.eclipse.mylyn.tasks.core.TaskRepository;
import org.eclipse.mylyn.tasks.ui.wizards.AbstractRepositorySettingsPage;
import org.eclipse.swt.widgets.Composite;

import com.maltera.mylyn.internal.rt.core.RtCorePlugin;

/**
 * @author Sam Hanes
 *
 */
public class RtRepositorySettingsPage extends AbstractRepositorySettingsPage {

	private static final String TITLE = Messages.RtRepositorySettingsPage_RT_Repository_Settings;

	private static final String DESCRIPTION = Messages.RtRepositorySettingsPage_EXAMPLE_BESTPRACTICAL;
	
	public RtRepositorySettingsPage(TaskRepository taskRepository) {
		super(TITLE, DESCRIPTION, taskRepository);
		setNeedsEncoding(false);
	}

	@Override
	public String getConnectorKind() {
		return RtCorePlugin.CONNECTOR_KIND;
	}

	@Override
	protected void createAdditionalControls(Composite parent) {
		// TODO Auto-generated method stub

	}

	@Override
	protected boolean isValidUrl(String url) {
		if ((url.startsWith(URL_PREFIX_HTTPS) || url.startsWith(URL_PREFIX_HTTP)) && !url.endsWith("/")) { //$NON-NLS-1$
			try {
				new URL(url);
				return true;
			} catch (MalformedURLException e) {
			}
		}
		return false;
	}
	
	@Override
	public boolean canValidate() {
		return false;
	}

	@Override
	protected Validator getValidator(TaskRepository repository) {
		// TODO Auto-generated method stub
		return null;
	}

}
