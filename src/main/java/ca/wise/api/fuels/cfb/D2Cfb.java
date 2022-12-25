package ca.wise.api.fuels.cfb;

import ca.wise.api.fuels.CfbAttribute;
import lombok.AllArgsConstructor;
import lombok.Builder;

/**
 * CFB calculation parameters for the D-2 and D-1/D-2 (greenup) fuel types.
 */
@Builder
@AllArgsConstructor
public class D2Cfb implements CfbAttribute {
	
	@Override
	public String stream() {
        return "D2";
	}
}
