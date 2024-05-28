import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileParser {
    public static List<Process> parseFile(String filePath) {
        List<Process> processes = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            String programName = "";
            String operation = "";
            List<Integer> dataLines = new ArrayList<>();
            boolean dataSection = false;

            while ((line = br.readLine()) != null) {
                line = line.trim();
                if (line.startsWith("$STA")) {
                    programName = line.split(" ")[1];
                } else if (line.equals("$COD")) {
                    // Skip CODE section
                } else if (line.equals("$DAT")) {
                    dataSection = true;
                } else if (line.startsWith("$STO")) {
                    String stopProgramName = line.split(" ")[1];
                    if (!programName.equals(stopProgramName)) {
                        throw new IllegalArgumentException("Mismatched program names in START and STOP sections");
                    }
                    if (dataLines.size() != 2) {
                        throw new IllegalArgumentException("DATA section must contain exactly two numbers");
                    }
                    Process process = new Process(processes.size() + 1, "Waiting", 1, "User", programName);
                    switch (operation) {
                        case "ADD":
                            process.addCommand(new AddCommand(dataLines.get(0), dataLines.get(1)));
                            break;
                        case "SUBTRACT":
                            process.addCommand(new SubtractCommand(dataLines.get(0), dataLines.get(1)));
                            break;
                        case "MULTIPLY":
                            process.addCommand(new MultiplyCommand(dataLines.get(0), dataLines.get(1)));
                            break;
                        case "DIVIDE":
                            process.addCommand(new DivideCommand(dataLines.get(0), dataLines.get(1)));
                            break;
                        default:
                            throw new IllegalArgumentException("Unsupported operation: " + operation);
                    }
                    processes.add(process);
                    // Reset for next process
                    programName = "";
                    dataLines.clear();
                    dataSection = false;
                } else if (dataSection) {
                    String[] parts = line.split(" ");
                    for (String part : parts) {
                        dataLines.add(Integer.parseInt(part));
                    }
                } else {
                    operation = line.toUpperCase();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return processes;
    }
}
