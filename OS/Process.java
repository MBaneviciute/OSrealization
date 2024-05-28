import java.util.Queue;
import java.util.LinkedList;

public class Process {
    private int id;
    private String state;
    private int priority;
    private String type;
    private String name;
    private Queue<Command> commandQueue;
    private int result;

    public Process(int id, String state, int priority, String type, String name) {
        this.id = id;
        this.state = state;
        this.priority = priority;
        this.type = type;
        this.name = name;
        this.commandQueue = new LinkedList<>();
    }

    // Getters and setters for the attributes
    public int getId() {
        return id;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public int getPriority() {
        return priority;
    }

    public String getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public void addCommand(Command command) {
        commandQueue.add(command);
    }

    public void executeCommands() {
        while (!commandQueue.isEmpty()) {
            Command command = commandQueue.poll();
            command.execute(this);
        }
    }

    public void setResult(int result) {
        this.result = result;
    }

    public int getResult() {
        return result;
    }

    @Override
    public String toString() {
        return "Process{" +
                "id=" + id +
                ", state='" + state + '\'' +
                ", priority=" + priority +
                ", type='" + type + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
