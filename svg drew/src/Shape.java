abstract public class Shape {
    protected  Style style;
    public Shape(Style style) {
        this.style = style;
    }

    public abstract void toSVG();
}
