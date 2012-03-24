package com.maltera.mylyn.internal.rt.core.restv1.formio;

/** Defines the field name space for a specific context.
 * This is unfortunately necessary because correct parsing and composition of
 * RT RESTv1 forms requires specific information about the fields involved.
 */
public interface IFieldResolver {
	
	public IFieldInfo getField (String name);
}
