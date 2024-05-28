import java.util.Scanner;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

public class UserInputListener extends Thread {
    private StopCommand stopCommand;
    private ResourceAllocator allocator;
    private BlockingQueue<String> inputQueue;

    public UserInputListener(StopCommand stopCommand, ResourceAllocator allocator) {
        this.stopCommand = stopCommand;
        this.allocator = allocator;
        this.inputQueue = new LinkedBlockingQueue<>();
    }

    @Override
    public void run() {
        Scanner scanner = new Scanner(System.in);
        Thread inputThread = new Thread(() -> {
            while (!stopCommand.isStopRequested()) {
                String input = scanner.nextLine();
                inputQueue.add(input);
            }
            scanner.close();
        });

        inputThread.start();

        try {
            while (!stopCommand.isStopRequested()) {
                String input = inputQueue.poll(30, TimeUnit.SECONDS);
                if (input == null) {
                    stopCommand.requestStop();
                    break;
                }
                try {
                    processInput(input);
                } catch (Exception e) {
                    System.out.println("Error processing input: " + e.getMessage());
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void processInput(String input) {
        String[] parts = input.trim().split("\\s+");
        if (parts.length != 3 && !parts[0].equalsIgnoreCase("EXIT")) {
            throw new IllegalArgumentException("Invalid input format. Expected format: COMMAND operand1 operand2");
        }

        String commandType = parts[0].toUpperCase();
        if (commandType.equals("EXIT")) {
            stopCommand.requestStop();
            return;
        }

        int operand1;
        int operand2;
        try {
            operand1 = Integer.parseInt(parts[1]);
            operand2 = Integer.parseInt(parts[2]);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Operands must be integers.");
        }

        Process process = new Process(1, "New", 1, "user", "commandLineProcess");

        switch (commandType) {
            case "ADD":
                process.addCommand(new AddCommand(operand1, operand2));
                break;
            case "SUBTRACT":
                process.addCommand(new SubtractCommand(operand1, operand2));
                break;
            case "MULTIPLY":
                process.addCommand(new MultiplyCommand(operand1, operand2));
                break;
            case "DIVIDE":
                process.addCommand(new DivideCommand(operand1, operand2));
                break;
            default:
                throw new IllegalArgumentException("Unknown command: " + commandType);
        }

        allocator.requestResource(process);
    }
}
