package ca.wise.api;

/**
 * A generic exception thrown when errors occur within the API.
 */
public class WISEException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public WISEException(String comment) {
		super(comment);
	}
}
