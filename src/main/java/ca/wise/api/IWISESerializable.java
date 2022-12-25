package ca.wise.api;

import java.util.List;

import ca.wise.api.validation.ValidationError;

/**
 * @exclude
 */
public interface IWISESerializable {

	/**
	 * Find all errors that may exist in the object.
	 */
	List<ValidationError> checkValid();
}
