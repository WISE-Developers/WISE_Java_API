package ca.wise.api.input;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public class StopModellingOptions {
	
	@Getter @Setter
	private String responseTime;
	
	@Getter @Setter
	private Threshold fi90;
	
	@Getter @Setter
	private Threshold fi95;
	
	@Getter @Setter
	private Threshold fi100;
	
	@Getter @Setter
	private Threshold rh;
	
	@Getter @Setter
	private Threshold precip;
	
	@Getter @Setter
	private Threshold area;

	@Builder
	@AllArgsConstructor
	@NoArgsConstructor
	public static class Threshold {
		
		@Getter @Setter
		private Double threshold;
		
		@Getter @Setter
		private String duration;
	}
}
