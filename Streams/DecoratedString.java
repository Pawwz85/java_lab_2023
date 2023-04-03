import java.util.ArrayList;
import java.util.List;

public class DecoratedString  {

    private List<DecoratedString> wrapped = new ArrayList<>();
    private String wrappingColor;
    private String content;

    public DecoratedString (String format)
    {
        this.content = fromFormat(format);
        this.wrappingColor = "none";
    }
    public DecoratedString (String format, String color)
    {
        this.content = fromFormat(format);
        this.wrappingColor = color;
    }
     public DecoratedString (String format, String color, List<DecoratedString> wr)
     {
         this.content = fromFormat(format);
         this.wrappingColor = color;
         this.wrapped = wr;
     }
     private String getColor()
    {
        switch (wrappingColor)
        {
            case "none":
                return ConsoleColors.RESET;
            case "red":
                return ConsoleColors.RED;
            case "blue":
                return ConsoleColors.BLUE;
            default:
                return "";
        }

    }
     private String fromFormat(String format)
     {
         String result = format;
         for(var w : wrapped)
         result = result.replaceFirst("[%d]", w.toString() + "[%]");
         return result;
     }


    public String toString() {
        return getColor() + content.replaceAll("[%]", getColor()) + ConsoleColors.RESET;
    }
    public DecoratedString findAndColor(String substring, String color)
    {
        DecoratedString target = new DecoratedString(substring, color);
       return new DecoratedString(content.replaceAll(substring, target.toString() + getColor()),
               wrappingColor, wrapped);
    }
}
