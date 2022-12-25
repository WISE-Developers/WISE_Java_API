package ca.wise.api.input;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
public class AttributeEntry {

	@Getter @Setter
	private String key;
	
	@Getter @Setter
	private Object value;
}
