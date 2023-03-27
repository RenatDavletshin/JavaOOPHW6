public class ChangeDirectionCommandHandler implements CommandHandler {

    @Override
    public String commandName() {
        return "change-direction";
    }

    @Override
    public void handleCommand(RobotMap map, String[] args) {
        Long robotId = Long.parseLong(args[0]);
        RobotMap.Direction newDirection = RobotMap.Direction.valueOf(args[1]);

        RobotMap.Robot robotById = map.getById(robotId);
        if(robotById != null) {
            robotById.changeDirection(newDirection);
        } else {
            System.out.println("Робот с идентификатором " + robotId + " не найден");
        }
    }
}
