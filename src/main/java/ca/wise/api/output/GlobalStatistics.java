package ca.wise.api.output;

/**
 * All supported statistics values that can be used across the API.
 * Not all locations will support all statistics.
 */
public enum GlobalStatistics {
	INVALID(-1),
    DATE_TIME(0),
    ELAPSED_TIME(1),
    TIME_STEP_DURATION(2),
    TEMPERATURE(3),
    DEW_POINT(4),
    RELATIVE_HUMIDITY(5),
    WIND_SPEED(6),
    WIND_DIRECTION(7),
    PRECIPITATION(8),
    HFFMC(9),
    HISI(10),
    DMC(11),
    DC(12),
    HFWI(13),
    BUI(14),
    FFMC(15),
    ISI(16),
    FWI(17),
    TIMESTEP_AREA(18),
    TIMESTEP_BURN_AREA(19),
    TOTAL_AREA(20),
    /**
     * Total area of the fire. (sq. metres)
     */
    TOTAL_BURN_AREA(21),
    /**
     * Rate of change in the fire area. (sq. metres)
     */
    AREA_GROWTH_RATE(22),
    /**
     * Total exterior fire perimeter), including active and inactive portions. (metres)
     */
    EXTERIOR_PERIMETER(23),
    /**
     * Rate of change in the exterior perimeter growth rate. (metres per minute)
     */
    EXTERIOR_PERIMETER_GROWTH_RATE(24),
    /**
     * Portion of the fire front considered active (interior and exterior) (where 1 or both vertices are active). (metres)
     */
    ACTIVE_PERIMETER(25),
    /**
     * Rate of change in the active perimeter growth rate. (metres per minute)
     */
    ACTIVE_PERIMETER_GROWTH_RATE(26),
    /**
     * Total fire perimeter), including interior and exterior and active/inactive portions. (metres)
     */
    TOTAL_PERIMETER(27),
    /**
     * Rate of change in the total perimeter growth rate. (metres per minute)
     */
    TOTAL_PERIMETER_GROWTH_RATE(28),
    FI_LT_10(29),
    FI_10_500(30),
    FI_500_2000(31),
    FI_2000_4000(32),
    FI_4000_10000(33),
    FI_GT_10000(34),
    ROS_0_1(35),
    ROS_2_4(36),
    ROS_5_8(37),
    ROS_9_14(38),
    ROS_GT_15(39),
    /**
     * Maximum rate of spread calculated from Dr. Richards' ellipse equations (metres per minute).
     */
    MAX_ROS(40),
    MAX_FI(41),
    /**
     * Maximum flame length (metres)), based on ROS from Dr. Richards' ellipse equations.
     */
    MAX_FL(42),
    /**
     * Maximum crown fraction burned (unitless)), based on ROS from Dr. Richards' ellipse equations.
     */
    MAX_CFB(43),
    /**
     * Maximum crown fuel consumption (kg/m2)), based on ROS from Dr. Richards' ellipse equations.
     */
    MAX_CFC(44),
    /**
     * Maximum surface fuel consumption (kg/m2)), based on ROS from Dr. Richards' ellipse equations.
     */
    MAX_SFC(45),
    /**
     * Maximum total fuel consumption (kg/m2)), based on ROS from Dr. Richards' ellipse equations.
     */
    MAX_TFC(46),
    TOTAL_FUEL_CONSUMED(47),
    CROWN_FUEL_CONSUMED(48),
    SURFACE_FUEL_CONSUMED(49),
    /**
     * Number of active vertices defining the fire perimeter(s).
     */
    NUM_ACTIVE_VERTICES(50),
    /**
     * Number of vertices defining the fire perimeter(s).
     */
    NUM_VERTICES(51),
    /**
     * Total), cumulative number of verticies defining the simulation's perimeters.
     */
    CUMULATIVE_VERTICES(52),
    /**
     * Cumulative number of active vertices defining the fire perimeter(s).
     */
    CUMULATIVE_ACTIVE_VERTICES(53),
    /**
     * Number of fire fronts (interior and exterior) which have at least 1 active vertex.
     */
    NUM_ACTIVE_FRONTS(54),
    /**
     * Number of fire fronts (interior and exterior).
     */
    NUM_FRONTS(55),
    MEMORY_USED_START(56),
    MEMORY_USED_END(57),
    NUM_TIMESTEPS(58),
    NUM_DISPLAY_TIMESTEPS(59),
    NUM_EVENT_TIMESTEPS(60),
    NUM_CALC_TIMESTEPS(61),
    /**
     * Number of real-time (clock) seconds to calculate the current display time step.
     */
    TICKS(62),
    /**
     * Number of real-time (clock) seconds to calculate all display time steps.
     */
    PROCESSING_TIME(63),
    /**
     * Number of simulated seconds that burning was allowed since the start of the simulation.
     */
    GROWTH_TIME(64),
    RAZ(65),
    BURN_GRID(66),
    FIRE_ARRIVAL_TIME(67),
    FIRE_ARRIVAL_TIME_MIN(68),
    FIRE_ARRIVAL_TIME_MAX(69),
    HROS(70),
    FROS(71),
    BROS(72),
    RSS(73),
    RADIATIVE_POWER(74),
    /**
     * Maximum fire intensity), based on ROS the standard FBP equations.
     */
    HFI(75),
    /**
     * Maximum crown fraction burned (unitless)), based on ROS from standard FBP equations.
     */
    HCFB(76),
    /**
     * The current simulation time as of the end of the timestep.
     */
    CURRENT_TIME(77),
    /**
     * The name of the scenario that is reporting statistics.
     */
    SCENARIO_NAME(78),
    BURN_PERCENTAGE(79),
    /**
     * Change in the total perimeter growth. (metres)
     */
    TOTAL_PERIMETER_CHANGE(80),
    /**
     * Change in the exterior perimeter growth. (metres)
     */
    EXTERIOR_PERIMETER_CHANGE(81),
    /**
     * Change in the active perimeter growth. (metres)
     */
    ACTIVE_PERIMETER_CHANGE(82),
    /**
     * Change in fire area. (sq. metres)
     */
    AREA_CHANGE(83),
    BURN(84),
    HROS_MAP(85),
    FROS_MAP(86),
    BROS_MAP(87),
    RSS_MAP(88),
    RAZ_MAP(89),
    FMC_MAP(90),
    CFB_MAP(91),
    CFC_MAP(92),
    SFC_MAP(93),
    TFC_MAP(94),
    FI_MAP(95),
    FL_MAP(96),
    CURINGDEGREE_MAP(97),
    GREENUP_MAP(98),
    PC_MAP(99),
    PDF_MAP(100),
    CBH_MAP(101),
    TREE_HEIGHT_MAP(102),
    FUEL_LOAD_MAP(103),
    CFL_MAP(104),
    GRASSPHENOLOGY_MAP(105),
    ROSVECTOR_MAP(106),
    DIRVECTOR_MAP(107),
    CRITICAL_PATH(108),
    CRITICAL_PATH_PERCENTAGE(109);
	
	public final int value;
	
	GlobalStatistics(int value) {
		this.value = value;
	}
	
	public static GlobalStatistics fromValue(int value) {
		for (GlobalStatistics stat : values()) {
			if (stat.value == value)
				return stat;
		}
		return INVALID;
	}
}
