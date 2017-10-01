package operation.transformation;

public class TransformationFactory {
    public static Transformation getTransformation(TransformationOperation operation, double a, double b, double c,
                                                   double d, double e, double f) {
        switch (operation) {
            case TRANSLATION:
                return new TranslationTransformation(a, b, c);
            case ROTATION:
                return new RotationTransformation(a, b, c);
            case SCALE:
                return new ScaleTransformation(a, b, c);
            case SHEAR:
                return new ShearTransformation(a, b, c, d, e, f);
        }
        return null;
    }
}
