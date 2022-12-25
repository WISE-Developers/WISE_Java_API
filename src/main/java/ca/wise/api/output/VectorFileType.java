package ca.wise.api.output;

public enum VectorFileType {
	KML("KML"),
	SHP("SHP");
	
	public final String value;
	
	VectorFileType(String value) {
		this.value = value;
	}
}
