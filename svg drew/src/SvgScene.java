import java.util.ArrayList;
import java.util.List;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
public class SvgScene {
    private List<Polygon>  shapes = new ArrayList <>();

    public void add(Polygon pol)
    {
        shapes.add(pol);
    }
    public void saveHtml(String path)
    {

        FileWriter fw = null;
        try {
            fw = new FileWriter(path);

            fw.write("<html>\n<body>\n<svg width=\"1000\" height=\"1000\">");
            for(var pol : shapes)
            {
                fw.write(pol.toSvg() + "\n");
            }
            fw.write("</svg>\n</body>\n</html>");
            fw.close();
        } catch (IOException e)
        {
            throw new RuntimeException(e);
        }



    }
}
