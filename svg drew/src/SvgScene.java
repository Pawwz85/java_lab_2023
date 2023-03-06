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
        try
        {
            FileWriter fw = new FileWriter(path);
            fw.close();
        }
        catch (Exception e)
        {
            e.getStackTrace();
        }
    }
}
