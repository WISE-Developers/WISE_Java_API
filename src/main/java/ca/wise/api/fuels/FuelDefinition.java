package ca.wise.api.fuels;

import ca.wise.api.Color;
import ca.wise.api.color.RGBColor;
import ca.wise.api.fuels.acc.ClosedAccAlpha;
import ca.wise.api.fuels.acc.OpenAccAlpha;
import ca.wise.api.fuels.cfb.C1Cfb;
import ca.wise.api.fuels.cfb.D2Cfb;
import ca.wise.api.fuels.fl.Alexander82FlameLength;
import ca.wise.api.fuels.fl.Alexander82TreeFlameLength;
import ca.wise.api.fuels.fmc.FmcCalc;
import ca.wise.api.fuels.fmc.FmcNoCalc;
import ca.wise.api.fuels.isf.C1Isf;
import ca.wise.api.fuels.isf.M1Isf;
import ca.wise.api.fuels.isf.M3M4Isf;
import ca.wise.api.fuels.isf.O1Isf;
import ca.wise.api.fuels.lb.C1Lb;
import ca.wise.api.fuels.lb.O1Lb;
import ca.wise.api.fuels.rsi.C1Rsi;
import ca.wise.api.fuels.rsi.C6Rsi;
import ca.wise.api.fuels.rsi.ConstantRsi;
import ca.wise.api.fuels.rsi.D2Rsi;
import ca.wise.api.fuels.rsi.M1Rsi;
import ca.wise.api.fuels.rsi.M3Rsi;
import ca.wise.api.fuels.rsi.M4Rsi;
import ca.wise.api.fuels.rsi.O1Rsi;
import ca.wise.api.fuels.sfc.C1Sfc;
import ca.wise.api.fuels.sfc.C2Sfc;
import ca.wise.api.fuels.sfc.C7Sfc;
import ca.wise.api.fuels.sfc.D2Sfc;
import ca.wise.api.fuels.sfc.M1Sfc;
import ca.wise.api.fuels.sfc.O1Sfc;
import ca.wise.api.fuels.sfc.S1Sfc;
import ca.wise.api.fuels.spread.C6Spread;
import ca.wise.api.fuels.spread.D1Spread;
import ca.wise.api.fuels.spread.MixedDeadSpread;
import ca.wise.api.fuels.spread.MixedSpread;
import ca.wise.api.fuels.spread.NonSpread;
import ca.wise.api.fuels.spread.NzSpread;
import ca.wise.api.fuels.spread.O1Spread;
import ca.wise.api.fuels.spread.O1abSpread;
import ca.wise.api.fuels.spread.S1Spread;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FuelDefinition {

    /**
     * The name of the of the default fuel type that will be used to fill in
     * any unspecified parameters.
     */
	@Getter @Setter
	private FuelTypes defaultFuel;

    /**
     * The user specified name for the fuel. If not present the value in {@link defaultName}
     * will be used.
     */
	@Getter @Setter
	private String name;

    /**
     * A grid index code that will link this fuel type to a value in the fuel map.
     */
	@Getter @Setter
	private int index;

    /**
     * A color to use to display the fuel if the created FGM were to be opened in Prometheus.
     * Can be either an {@link RGBColor} or an {@link HSLColor}.
     */
	@Getter @Setter
	@Builder.Default
	private Color color = new RGBColor(0xff, 0xff, 0xff);

    /**
     * The spread parameters for the fuel being defined. Can be one of {@link C1Spread},
     * {@link C6Spread}, {@link S1Spread}, {@link D1Spread}, {@link NzSpread},
     * {@link O1Spread}, {@link O1abSpread}, {@link NonSpread}, {@link MixedSpread},
     * or {@link MixedDeadSpread}.
     */
	@Getter @Setter
	private SpreadAttribute spreadParms;

    /**
     * The FMC calculation parameters for the fuel being defined. Can be one of {@link FmcCalc}
     * or {@link FmcNoCalc}.
     */
	@Getter @Setter
	private FmcAttribute fmcAttribute;

    /**
     * The SFC calculation parameters for the fuel being defined. Can be one of {@link C1Sfc},
     * {@link C2Sfc}, {@link C7Sfc}, {@link D2Sfc}, {@link M1Sfc}, {@link O1Sfc}, or {@link S1Sfc}.
     */
	@Getter @Setter
	private SfcAttribute sfcAttribute;

    /**
     * The SFC calculation parameters during greenup for the fuel being defined. Can be one of {@link C1Sfc},
     * {@link C2Sfc}, {@link C7Sfc}, {@link D2Sfc}, {@link M1Sfc}, {@link O1Sfc}, or {@link S1Sfc}.
     */
	@Getter @Setter
	private SfcAttribute sfcGreenupAttribute;

    /**
     * The RSI calculation parameters for the fuel being defined. Can be one of {@link C1Rsi},
     * {@link C6Rsi}, {@link D2Rsi}, {@link M1Rsi}, {@link M3Rsi}, {@link M4Rsi}, {@link O1Rsi}, or {@link ConstantRsi}.
     */
	@Getter @Setter
	private RsiAttribute rsiAttribute;

    /**
     * The RSI calculation parameters during greenup for the fuel being defined. Can be one of {@link C1Rsi},
     * {@link C6Rsi}, {@link D2Rsi}, {@link M1Rsi}, {@link M3Rsi}, {@link M4Rsi}, {@link O1Rsi}, or {@link ConstantRsi}.
     */
	@Getter @Setter
	private RsiAttribute rsiGreenupAttribute;

    /**
     * The ISF calculation parameters for the fuel being defined. Can be one of {@link C1Isf},
     * {@link M1Isf}, {@link M3M4Isf}, or {@link O1Isf}.
     */
	@Getter @Setter
	private IsfAttribute isfAttribute;

    /**
     * The ISF calculation parameters during greenup for the fuel being defined. Can be one of {@link C1Isf},
     * {@link M1Isf}, {@link M3M4Isf}, or {@link O1Isf}.
     */
	@Getter @Setter
	private IsfAttribute isfGreenupAttribute;

    /**
     * The Acc Alpha calculation parameters for the fuel being defined. Can be one of {@link OpenAccAlpha} or {@link ClosedAccAlpha}.
     */
	@Getter @Setter
	private AccAlphaAttribute accAlphaAttribute;

    /**
     * The LB calculation parameters for the fuel being defined. Can be one of {@link C1Lb} or {@link O1Lb}.
     */
	@Getter @Setter
	private LbAttribute lbAttribute;

    /**
     * The CFB calculation parameters for the fuel being defined. Can be one of {@link C1Cfb} or {@link D2Cfb}.
     */
	@Getter @Setter
	private CfbAttribute cfbAttribute;

    /**
     * The CFB calculation parameters during greenup for the fuel being defined. Can be one of {@link C1Cfb} or {@link D2Cfb}.
     */
	@Getter @Setter
	private CfbAttribute cfbGreenupAttribute;

    /**
     * The flame length calculation parameters for the fuel being defined. Can be one of {@link Alexander82FlameLength}
     * or {@link Alexander82TreeFlameLength}.
     */
	@Getter @Setter
	private FlameLengthAttribute flameLengthAttribute;
	
	@Override
	public String toString() {
        String s = name.replace("|", "_") + "|" + defaultFuel + "|" + index + "|" + color.stream();
        if (this.spreadParms != null) {
            s += "|SP|" + spreadParms.stream();
        }
        if (this.fmcAttribute != null) {
            s += "|FMC|" + fmcAttribute.stream();
        }
        if (this.sfcAttribute != null) {
            s += "|SFC|" + sfcAttribute.stream();
        }
        if (this.sfcGreenupAttribute != null) {
            s += "|SFCG|" + sfcGreenupAttribute.stream();
        }
        if (this.rsiAttribute != null) {
            s += "|RSI|" + rsiAttribute.stream();
        }
        if (this.rsiGreenupAttribute != null) {
            s += "|RSIG|" + rsiGreenupAttribute.stream();
        }
        if (this.isfAttribute != null) {
            s += "|ISF|" + isfAttribute.stream();
        }
        if (this.isfGreenupAttribute != null) {
            s += "|ISFG|" + isfGreenupAttribute.stream();
        }
        if (this.accAlphaAttribute != null) {
            s += "|AA|" + accAlphaAttribute.stream();
        }
        if (this.lbAttribute != null) {
            s += "|LB|" + lbAttribute.stream();
        }
        if (this.cfbAttribute != null) {
            s += "|CFB|" + cfbAttribute.stream();
        }
        if (this.cfbGreenupAttribute != null) {
            s += "|CFBG|" + cfbGreenupAttribute.stream();
        }
        if (this.flameLengthAttribute != null) {
            s += "|FL|" + flameLengthAttribute.stream();
        }

        return s;
	}
}
