package operation.transformation;

/**
 * Created by caiofilipemr on 30/09/17.
 */
public class TransformationFactory {
    public static Transformation getTransformation(TransformationOperation operation, double x, double y, double z) {
        switch (operation) {
            case TRANSLATION:
                return new TranslationTransformation(x, y, z);
            case ROTATION:
                break;
            case SCALE:
                break;
            case SHEAR:
                break;
        }
        return null;
    }
}
