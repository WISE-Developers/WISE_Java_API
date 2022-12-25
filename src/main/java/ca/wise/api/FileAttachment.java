package ca.wise.api;

import lombok.Getter;
import lombok.Setter;

/**
 * Stores file contents for use in the simulation. All file
 * names must begin with `attachment:/`.
 */
public class FileAttachment {

	@Getter @Setter
	private String filename;
	
	@Getter @Setter
	private String contents;

	@Getter @Setter
	private byte[] data = null;
	
	public FileAttachment(String filename, String contents) {
		this.filename = filename;
		this.contents = contents;
	}
	
	public FileAttachment(String filename, byte[] data) {
		this.filename = filename;
		this.data = data;
	}
}
