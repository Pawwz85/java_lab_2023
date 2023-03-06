import java.util.Locale;

public class Style {



    public String toSVG()
    {
        return String.format(Locale.ENGLISH,"style=\"fill:%s;stroke:%s-width%f");
    }
}
