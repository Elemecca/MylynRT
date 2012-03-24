package com.maltera.mylyn.internal.rt.core.restv1.formio;

/** Provides information about a specific field.
 * This is unfortunately necessary because correct parsing and composition of
 * RT RESTv1 forms requires specific information about the fields involved.
 */
public interface IFieldInfo {

	/** Returns whether the field can have multiple values.
	 * This is used to determine whether commas appearing in field values are
	 * delimiters or just part of the value.
	 * @return whether the field may have more than one value
	 */
	public boolean isList();
}
