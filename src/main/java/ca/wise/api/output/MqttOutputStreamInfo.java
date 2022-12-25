package ca.wise.api.output;

import java.util.Collections;
import java.util.List;

import ca.wise.api.validation.ValidationError;

/**
 * Stream the output file over MQTT. Requires a custom MQTT client
 * receiving file requests.
 */
public class MqttOutputStreamInfo extends OutputStreamInfo {

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<ValidationError> checkValid() {
		return Collections.emptyList();
	}
}
