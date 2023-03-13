public class Main {

    public static void main(String[] args) {
        Shape poly = new Polygon(new Vec2[]{new Vec2(120,60), new Vec2(270,280), new Vec2(240,320), new Vec2(110,80)});
        //Shape poly = new SolidFilledPolygon(new Vec2[]{new Vec2(120,60), new Vec2(270,280), new Vec2(240,320), new Vec2(110,80)},"red");
        poly=new SolidFillShapeDecorator(poly,"green");
        Shape ellipse = new Ellipse(new Vec2(300,400),200,50);
        ellipse = new SolidFillShapeDecorator(ellipse,"yellow");
        SvgScene scene=new SvgScene();
        scene.add(poly);
        scene.add(ellipse);
        scene.saveHtml("scene.html");

    }
}