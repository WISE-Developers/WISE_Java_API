package ca.wise.api.output;

public enum GridFileInterpolation {
	/**
	 * Interpolate using the nearest vertex to the centre of the grid cell.
	 */
	CLOSEST_VERTEX("ClosestVertex"),
	/**
	 * Interpolate using inverse distance weighting.
	 */
	IDW("IDW"),
	/**
	 * Interpolate using voronoi area weighting.
	 */
	AREA_WEIGHTING("AreaWeighting"),
	CALCULATE("Calculate"),
	DISCRETIZED("Discretized"),
	VORONOI_OVERLAP("VoronoiOverlap");
	
	public final String value;
	
	GridFileInterpolation(String value) {
		this.value = value;
	}
	
	public static GridFileInterpolation fromValue(String value) {
		for (GridFileInterpolation interp : values()) {
			if (interp.value.equals(value))
				return interp;
		}
		return IDW;
	}
}
