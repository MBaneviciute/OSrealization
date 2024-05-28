import java.util.LinkedList;
import java.util.Queue;

public class ResourceAllocator {
    private Queue<Process> processQueue;

    public ResourceAllocator() {
        this.processQueue = new LinkedList<>();
    }

    public void requestResource(Process process) {
        processQueue.add(process);
    }

    public void processNext() {
        Process nextProcess = processQueue.poll();
        if (nextProcess != null) {
            nextProcess.run();
        }
    }
}
