
public class Main {
    public static void main(String[] args) {
        Segment segment = new Segment(new Point(10,100), new Point(110,99));
        Segment[] perp2 = Segment.perpendicular(segment, segment.getP1());

        System.out.println(segment.toSVG());
        for(int i=0;i<perp2.length;i++)
            System.out.println(perp2[i].toSVG());
        //System.out.println(segment.distance());

        Polygon poly = new Polygon(4,new Style("green","red",5));
        poly.setPoints(new Point[]{new Point(120,30), new Point(170,180), new Point(240,320), new Point(110,30)});
        System.out.println(poly.toSvg());


    }
}