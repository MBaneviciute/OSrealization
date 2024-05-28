import java.util.List;

public class Supervisor extends Thread {
    private ResourceAllocator allocator;

    public Supervisor(ResourceAllocator allocator) {
        this.allocator = allocator;
    }

    @Override
    public void run() {
        while (true) {
            allocator.allocateResource();
            try {
                Thread.sleep(1000); // Simulate time slicing
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

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
