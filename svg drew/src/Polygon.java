import java.util.Locale;

public class Polygon {
    private Point[] arr;
    private Style style;

    public Polygon(int count,Style style) {
        arr = new Point[count];
        this.style = style;
    }
    public Polygon(int count) {
        this(count,new Style("transparent","black",1));
    }

    public void setPoint(int index, Point point) {
        arr[index] = point;
    }

    public void setPoints(Point[] points){
        arr = points;
    }

    public String toSvg() {
        String pointsString = "";
        for(Point point : arr)
            pointsString += point.x + "," + point.y + " ";

        return String.format(Locale.ENGLISH,"<polygon points=\"%s\" %s />", pointsString,style.toSVG());
    }


}