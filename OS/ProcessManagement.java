public class ProcessManagement {
    public static void main(String[] args) {
        ResourceAllocator allocator = new ResourceAllocator();

        for (int i = 1; i <= 5; i++) {
            final int processId = i;
            Process process = new Process(processId, "New", 1, "User" + processId, "Process" + processId);
            process.addCommand(new Command() {
                @Override
                public void execute(Process process) {
                    System.out.println(process.getName() + " is executing a command.");
                }
            });
            
            new Thread(() -> {
                allocator.requestResource(process);
                allocator.processNext();
            }).start();
        }
    }
}