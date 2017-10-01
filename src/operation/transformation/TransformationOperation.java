package operation.transformation;

public enum TransformationOperation {
    TRANSLATION("Translation"),
    ROTATION("Rotation"),
    SCALE("Scale"),
    SHEAR("Shear");

    private String label;

    TransformationOperation(String label) {
        this.label = label;
    }

    @Override
    public String toString() {
        return label;
    }
}