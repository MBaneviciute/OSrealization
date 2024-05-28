public class Supervisor extends Thread {
    private ResourceAllocator allocator;
    private StopCommand stopCommand;

    public Supervisor(ResourceAllocator allocator, StopCommand stopCommand) {
        this.allocator = allocator;
        this.stopCommand = stopCommand;
    }

    @Override
    public void run() {
        while (!stopCommand.isStopRequested()) {
            allocator.processNext();
            try {
                Thread.sleep(1000); // Simulating time taken to process
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
