package operation.projection;

import gc.Point;

public class ProjectionFactory {
    public static Projection getProjection(ProjectionOperation operation, Point point) {
        switch (operation) {
            case ISOMETRIC:
                return new IsometricProjection();
            case PLAN_PERSPECTIVE:
                return new PlanPerspectiveProjection(point);
        }
        return null;
    }
}
