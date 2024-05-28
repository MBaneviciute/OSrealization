public class ProcessManagement {
    public static void main(String[] args) {
        for (int i = 1; i <= 5; i++) {
            final int processId = i;
            new Thread(() -> {
                System.out.println("Process " + processId + " is running.");
            }).start();
        }
    }
}
