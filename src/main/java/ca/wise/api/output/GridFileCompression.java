package ca.wise.api.output;

/**
 * If the grid file is a TIF file its contents can be
 * compressed. This describes the algorithm used to
 * compress the data.
 */
public enum GridFileCompression {
	NONE(0),
	/**
	 * Should only be used with byte data.
	 */
	JPEG(1),
	LZW(2),
	PACKBITS(3),
	DEFLATE(4),
	/**
	 * Should only be used with bit data.
	 */
	CCITTRLE(5),
	/**
	 * Should only be used with bit data.
	 */
	CCITTFAX3(6),
	/**
	 * Should only be used with bit data.
	 */
	CCITTFAX4(7),
	LZMA(8),
	ZSTD(9),
	LERC(10),
	LERC_DEFLATE(11),
	LERC_ZSTD(12),
	WEBP(13);
	
	public final int value;
	
	GridFileCompression(int value) {
		this.value = value;
	}
	
	public static GridFileCompression fromValue(int value) {
		for (GridFileCompression compress : values()) {
			if (compress.value == value)
				return compress;
		}
		return NONE;
	}
}
