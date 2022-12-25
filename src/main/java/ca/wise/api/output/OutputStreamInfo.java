package ca.wise.api.output;

import ca.wise.api.IWISESerializable;

/**
 * After all simulations have completed the output files can be streamed to another
 * location to be consumed by a client side application.
 * @see MqttOutputStreamInfo
 * @see GeoServerOutputStreamInfo
 */
public abstract class OutputStreamInfo implements IWISESerializable { }
