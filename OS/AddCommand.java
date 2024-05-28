public class AddCommand implements Command {
    private int operand1;
    private int operand2;

    public AddCommand(int operand1, int operand2) {
        this.operand1 = operand1;
        this.operand2 = operand2;
    }

    @Override
    public void execute(Process process) {
        int result = operand1 + operand2;
        process.setResult(result);
        System.out.println(process.getName() + " executed ADD: " + operand1 + " + " + operand2 + " = " + result);
    }
}
