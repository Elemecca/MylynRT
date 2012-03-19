package com.maltera.mylyn.internal.rt.core.restv1.formio;

public class FormSyntaxException
extends Exception {
	private final int line, column;
	
	public FormSyntaxException (int line, int column, String message) {
		super( message );
		this.line = line;
		this.column = column;
	}
	
	public String toString() {
		return String.valueOf( line ) + "." + String.valueOf( column )
				+ ": " + super.getMessage();
	}
}
