import java.util.LinkedList;
import java.util.Queue;

public class ResourceAllocator {
    private Queue<Process> processQueue;

    public ResourceAllocator() {
        this.processQueue = new LinkedList<>();
    }

    public synchronized void requestResource(Process process) {
        System.out.println(process.getName() + " is requesting a resource.");
        processQueue.add(process);
    }

    public synchronized void processNext() {
        Process nextProcess = processQueue.poll();
        if (nextProcess != null) {
            System.out.println(nextProcess.getName() + " is acquiring the resource.");
            nextProcess.run();
            System.out.println(nextProcess.getName() + " has released the resource.");
        }
    }
}