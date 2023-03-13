import java.util.Locale;
public class TransformationDecorator extends ShapeDecorator{

    private String translate, rotate, scale;
    public TransformationDecorator(Shape decoratedShape) {
        super(decoratedShape);
    }

    public static class Builder
    {
        double rotateAngle;
       private boolean translate, rotate, scale;
       private Vec2   translateVector,  rotateCenter, scaleVector;
        private Shape shape;


        public Builder() {
            Shape shape;
        }
        public Builder translate(Vec2 translateVector)
        {
            this.translateVector = translateVector;
            this.translate = true;
        }
        public Builder rotate(Vec2 rotateCenter, double rotateAngle)
        {
            this.translateVector = rotateCenter;
            this.rotateAngle = rotateAngle;
            this.rotate = true;
        }
        public Builder scale(Vec2 scaleVector)
        {
            this.translateVector = scaleVector;
            this.scale = true;
        }
        public Builder shape(Shape shape)
        {
        this.shape = shape;
        return this;
        }
        public TransformationDecorator build()
        {
            TransformationDecorator decorator = new TransformationDecorator(shape);


            decorator.translate = translate ? String.format(Locale.ENGLISH, "translate(%f %f) ", translateVector.x, translateVector.y):
                                     String.format(Locale.ENGLISH, "translate(%f %f) ", 0, 0);
            decorator.rotate = rotate ? String.format(Locale.ENGLISH, "rotate(%f, %f, %f) ",rotateAngle ,rotateCenter.x, rotateCenter.y):
                    String.format(Locale.ENGLISH, "translate(%f %f) ", 0, 0, 0);
            decorator.scale = scale ? String.format(Locale.ENGLISH, "scale(%f %f) ", scaleVector.x, scaleVector.y):
                    String.format(Locale.ENGLISH, "scale(%f %f) ", 0, 0);
        }
    }
    public toSvg(String parameters)
    {
        return super.toSvg(String.format("transform=\"%s %s %s\" %s", this.translate, this.rotate, this.scale, parameters));
    }
}
