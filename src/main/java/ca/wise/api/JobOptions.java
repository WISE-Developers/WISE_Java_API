package ca.wise.api;

import java.util.Collections;
import java.util.List;

import ca.wise.api.validation.ValidationError;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Options for running the job not directly related to
 * scenarios or fire growth.
 */
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class JobOptions implements IWISESerializable {
	
	/**
	 * The type of load balancing to use to run the job.
	 */
	@Getter @Setter
	@Builder.Default
	private LoadBalanceType loadBalance = LoadBalanceType.NONE;

	/**
	 * A priority to use to sort the job queue. When a
	 * job is recieved by an instance of W.I.S.E. Manager
	 * it will be placed in the job queue immediately
	 * below the first job found with the same or higher
	 * priority that isn't already running starting from
	 * the bottom of the queue.
	 * 
	 * The priority can be any valid integer value.
	 */
	@Getter @Setter
	@Builder.Default
	private int priority = 0;

	/**
	 * Should the job be validated by W.I.S.E. instead of
	 * being run. The user can redo the job if there
	 * is a validation error or restart the job so
	 * that it simulates in W.I.S.E. using MQTT commands.
	 */
	@Getter @Setter
	@Builder.Default
	private boolean validate = false;
	
	@Getter @Setter
	private Integer tempOutputInterval;
	
	@Getter @Setter
	private Integer tempOutputCount;
	
	@Getter @Setter
	private Integer exportFormat;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<ValidationError> checkValid() {
		return Collections.emptyList();
	}
}
