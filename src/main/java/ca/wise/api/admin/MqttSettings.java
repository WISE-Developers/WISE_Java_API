package ca.wise.api.admin;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MqttSettings {

	@Getter @Setter
	private String host;

	@Getter @Setter
	private Integer port;

	@Getter @Setter
	private String topic;

	@Getter @Setter
	private Integer qos;

	@Getter @Setter
	private String verbosity;

	@Getter @Setter
	private String username;

	@Getter @Setter
	private String password;
}
