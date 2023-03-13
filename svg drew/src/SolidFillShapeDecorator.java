public class SolidFillShapeDecorator extends ShapeDecorator {

    protected String color;
    public SolidFillShapeDecorator  (Shape  decoratedShape, String color)
    {
        super(decoratedShape);
        this.color = color;
    }

    @Override
    public String toSvg(String parameters) {
        return super.toSvg(parameters);
    }
}
