import java.util.List;

public class MultiprogrammingOS {
    public static void main(String[] args) {
        String filePath = "input.txt"; // Update this to the actual file path
        List<Process> processes = FileParser.parseFile(filePath);

        ResourceAllocator allocator = new ResourceAllocator();
        Supervisor supervisor = new Supervisor(allocator);

        for (Process process : processes) {
            allocator.requestResource(process);
        }

        supervisor.start();
    }
}
