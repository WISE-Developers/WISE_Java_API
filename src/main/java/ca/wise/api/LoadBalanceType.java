package ca.wise.api;

/**
 * The types of load balancing available in W.I.S.E..
 */
public enum LoadBalanceType {
	/**
	 * Don't use any load balancing. The generated FGM will be sent to
	 * a single instance of W.I.S.E. Manager and it will run all scenarios.
	 */
	NONE(0),
	/**
	 * Every instance of W.I.S.E. Manager in the same cluster will receive
	 * the generated FGM. An external service will provide scenario
	 * indices to run so that each instance of W.I.S.E. that is processing
	 * the FGM will run different scenarios. The indices will be
	 * communicated to the W.I.S.E. instance over MQTT. See the
	 * [MQTT documentation](https://spydmobile.bitbucket.io/psaas_mqtt/#topic-psaas/{originator}/delegator/balance)
	 * for more information.
	 */
	EXTERNAL_COUNTER(1),
	/**
	 * The generated FGM will be sent to a single instance of W.I.S.E.
	 * Manager. A file that the user creates will provide scenario
	 * indices to the instance of W.I.S.E. that runs the FGM. The file
	 * must be named balance.txt and each line must contain a valid
	 * scenario index that should be run. Typically used for debugging
	 * to force W.I.S.E. to only process a single scenario when many
	 * are present in the FGM.
	 */
	LOCAL_FILE(2);
	
	public final int value;
	
	LoadBalanceType(int value) {
		this.value = value;
	}
	
	public static LoadBalanceType fromValue(int value) {
		for (LoadBalanceType val : values()) {
			if (val.value == value)
				return val;
		}
		return NONE;
	}
}
