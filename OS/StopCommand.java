public class StopCommand {
    private volatile boolean stop;

    public StopCommand() {
        this.stop = false;
    }

    public synchronized void requestStop() {
        this.stop = true;
    }

    public synchronized boolean isStopRequested() {
        return this.stop;
    }
}
