import java.util.ArrayList;
import java.util.List;

public class Process {
    private int id;
    private String state;
    private int priority;
    private String user;
    private String name;
    private List<Command> commands;
    private int result;

    public Process(int id, String state, int priority, String user, String name) {
        this.id = id;
        this.state = state;
        this.priority = priority;
        this.user = user;
        this.name = name;
        this.commands = new ArrayList<>();
    }

    public void addCommand(Command command) {
        commands.add(command);
    }

    public void run() {
        for (Command command : commands) {
            command.execute(this);
        }
        this.state = "Completed";
    }

    public void setResult(int result) {
        this.result = result;
    }

    public int getResult() {
        return result;
    }

    public String getName() {
        return name;
    }
}
