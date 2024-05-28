import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileParser {

    public static List<Process> parseFile(String filePath) {
        List<Process> processes = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            Process currentProcess = null;
            String commandType = null;
            while ((line = reader.readLine()) != null) {
                line = line.trim();
                if (line.startsWith("$STA")) {
                    String name = line.split(" ")[1];
                    currentProcess = new Process(1, "New", 1, "user", name);
                } else if (line.startsWith("$COD")) {
                    // Skip $COD line
                } else if (line.startsWith("ADD") || line.startsWith("SUBTRACT") || line.startsWith("MULTIPLY") || line.startsWith("DIVIDE")) {
                    commandType = line.split(" ")[0];
                } else if (line.startsWith("$DAT")) {
                    // The next line will contain the operands
                    String[] parts = reader.readLine().trim().split(" ");
                    int op1 = Integer.parseInt(parts[0]);
                    int op2 = Integer.parseInt(parts[1]);
                    if ("ADD".equals(commandType)) {
                        currentProcess.addCommand(new AddCommand(op1, op2));
                    } else if ("SUBTRACT".equals(commandType)) {
                        currentProcess.addCommand(new SubtractCommand(op1, op2));
                    } else if ("MULTIPLY".equals(commandType)) {
                        currentProcess.addCommand(new MultiplyCommand(op1, op2));
                    } else if ("DIVIDE".equals(commandType)) {
                        currentProcess.addCommand(new DivideCommand(op1, op2));
                    }
                } else if (line.startsWith("$STO")) {
                    processes.add(currentProcess);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return processes;
    }
}
