package graphics.canvas.transition;

public class TransitionFactory {
    public static Transition createTransition(TransitionType transitionType) {
        switch (transitionType) {
            case FARTHEST_POINT:
                return new FarthestPointTransition();
            case ALL_POINTS:
                return new AllPointsTransition();
            case IN_ORDER:
                return new InOrderTransition();
            default:
                return new FarthestPointTransition();
        }
    }
}
