package operation.projection;

public class ProjectionFactory {
    public static Projection getProjection(ProjectionOperation operation) {
        switch (operation) {
            case ISOMETRIC:
                return new IsometricProjection();
            case PLAN_PERSPECTIVE:
                break;
        }
        return null;
    }
}
