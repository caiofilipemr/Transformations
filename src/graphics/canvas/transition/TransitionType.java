package graphics.canvas.transition;

public enum TransitionType {
    FARTHEST_POINT("Farthest point"),
    ALL_POINTS("All points"),
    IN_ORDER("X -> Y -> Z");

    private final String label;

    TransitionType(String label) {
        this.label = label;
    }

    @Override
    public String toString() {
        return label;
    }
}
