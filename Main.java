import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println("Введите команду для создания карты: ");
        RobotMap map = null;

        while (true) {
            String command = in.nextLine();
            if (command.startsWith("create-map")) {
                String[] split = command.split(" ");
                String[] arguments = Arrays.copyOfRange(split, 1, split.length);

                try {
                    map = new RobotMap(Integer.parseInt(arguments[0]),Integer.parseInt(arguments[1]));
                    break;
                } catch (IllegalArgumentException e) {
                    System.out.println("при создании карты возникло исключение: " + e.getMessage() + ". Попробуйте еще раз");
                }
            } else {
                System.out.println("Команда не найдена. Попробуйте еще раз");
            }
        }

        System.out.println("Введите одну из доступных команд:");
        System.out.println(" create-robot\n move-robot\n change-direction\n exit");

        CommandManager commandManager = new CommandManager(map, List.of(
            new CreateRobotCommandHandler(),
            new MoveRobotCommandHandler(),
            new ChangeDirectionCommandHandler()
        ));

        while (true) {
            String command = in.nextLine();
        
            if (command.equals("exit")) break;
            
            commandManager.handleCommand(command);

        }
    }
}