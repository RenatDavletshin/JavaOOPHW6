public class MoveRobotCommandHandler implements CommandHandler {

    @Override
    public String commandName() {
        return "move-robot";
    }

    @Override
    public void handleCommand(RobotMap map, String[] args) {
        try {
            map.getById(Long.parseLong(args[0])).move();
        } catch (PositionException e) {
            System.out.println("При создании робота возникло исключение: " + e.getMessage() + "Попробуйте снова");
        }
    }
}