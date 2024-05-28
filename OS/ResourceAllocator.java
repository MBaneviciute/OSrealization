import java.util.Queue;
import java.util.LinkedList;

public class ResourceAllocator {
    private Queue<Process> processQueue;

    public ResourceAllocator() {
        processQueue = new LinkedList<>();
    }

    public synchronized void requestResource(Process process) {
        processQueue.add(process);
        System.out.println(process.getName() + " is requesting resource.");
    }

    public synchronized void allocateResource() {
        if (!processQueue.isEmpty()) {
            Process process = processQueue.poll();
            process.setState("Running");
            System.out.println(process.getName() + " is allocated a resource and is now running.");
            process.executeCommands();
            System.out.println(process.getName() + " result: " + process.getResult());
        }
    }
}
