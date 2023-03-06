
import java.util.Locale;

public class Style {

    private final String fillColor, strokeColor;
    private final double strokeWidth;
    public Style(String fillColor, String strokeColor, double strokeWidth) {
        this.fillColor = fillColor;
        this.strokeColor = strokeColor;
        this.strokeWidth = strokeWidth;
    }
    //style="fill:lime;stroke:purple;stroke-width:1" />

//    return String.format(Locale.ENGLISH,"<polygon points=\"%s\" />", pointsString);

    public String toSVG() {
        return String.format(Locale.ENGLISH,"style=\"fill:%s;stroke:%s;stroke-width:%f\"", fillColor,strokeColor,strokeWidth);
    }
}