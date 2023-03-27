import java.util.HashMap;
import java.util.Map;

public class RobotMap {
    
    private final int n;
    private final int m;
    public Map<Long, Robot> robots;
 
    public RobotMap(int n, int m) {
        if (n < 0 || m < 0) {
            throw new IllegalArgumentException();
        }
        this.n = n;
        this.m = m;
        this.robots = new HashMap<>();
    }

    public Robot getById(Long id) {
        return robots.get(id);
    }

    public Robot createRobot(Point position) throws PositionException {
        checkPosition(position);

        Robot robot = new Robot(position);
        robots.put(robot.id, robot);
        return robot;
    }

    private void checkPosition(Point position) throws PositionException{
        if (!isFree(position)) {
            throw new PositionException("Точка " + position + " занята!");
        }

        if (position.getX() < 0 || position.getY() < 0 || position.getX() > n || position.getY() > m) {
            throw new PositionException("Неккоректное значение точки: " + position);
        }
    }

    private boolean isFree(Point position) {
        return robots.values().stream()
                .map(Robot::getPosition)
                .noneMatch(position::equals);
    }

    public class Robot {

        private static Long sequence = 1L;

        private final Long id;
        private Point position;
        public Direction direction;

        public Robot(Point position) {
            this.id = sequence++;
            this.position = position;
            this.direction = direction.TOP;
        }

        public Long getId() {
            return id;
        }

        public Point getPosition() {
            return position;
        }

        public Direction getDirection() {
            return direction;
        }

        public void move () throws PositionException {
            Point newPosition = switch (direction) {
                case TOP -> new Point(position.getX() - 1, position.getY());
                case RIGHT -> new Point(position.getX(), position.getY() + 1);
                case BOTTOM -> new Point(position.getX() + 1, position.getY());
                case LEFT -> new Point(position.getX(), position.getY() - 1);
            };

            checkPosition(newPosition);

            position = newPosition;
        }

        public void changeDirection(Direction direction) {
            this.direction = direction;
        }

        @Override
        public String toString() {
            return String.format("[%s] %s", id.toString(), position.toString());
        }
    }

    public enum Direction {
        
        TOP, RIGHT, BOTTOM, LEFT;

        public static Direction ofString(String str) {
            Direction[] values = values();
            for (Direction value : values) {
                if (str.equals(value.name())) {
                    return value;
                }
            }
            return null;
        }
    }
}