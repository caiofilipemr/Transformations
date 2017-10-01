package operation.projection;

public enum ProjectionOperation {
    ISOMETRIC("Isometric"),
    PLAN_PERSPECTIVE("Plan perspective");

    private String label;

    ProjectionOperation(String label) {
        this.label = label;
    }

    @Override
    public String toString() {
        return label;
    }
}
