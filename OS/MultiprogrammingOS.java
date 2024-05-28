public class MultiprogrammingOS {
    public static void main(String[] args) {
        ResourceAllocator allocator = new ResourceAllocator();
        StopCommand stopCommand = new StopCommand();
        Supervisor supervisor = new Supervisor(allocator, stopCommand);

        UserInputListener userInputListener = new UserInputListener(stopCommand, allocator);
        userInputListener.start();
        supervisor.start();

        try {
            supervisor.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Multiprogramming OS terminated.");
    }
}
