public interface Command {
    void execute(Process process);
}

class AddCommand implements Command {
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

class SubtractCommand implements Command {
    private int operand1;
    private int operand2;

    public SubtractCommand(int operand1, int operand2) {
        this.operand1 = operand1;
        this.operand2 = operand2;
    }

    @Override
    public void execute(Process process) {
        int result = operand1 - operand2;
        process.setResult(result);
        System.out.println(process.getName() + " executed SUBTRACT: " + operand1 + " - " + operand2 + " = " + result);
    }
}

class MultiplyCommand implements Command {
    private int operand1;
    private int operand2;

    public MultiplyCommand(int operand1, int operand2) {
        this.operand1 = operand1;
        this.operand2 = operand2;
    }

    @Override
    public void execute(Process process) {
        int result = operand1 * operand2;
        process.setResult(result);
        System.out.println(process.getName() + " executed MULTIPLY: " + operand1 + " * " + operand2 + " = " + result);
    }
}

class DivideCommand implements Command {
    private int operand1;
    private int operand2;

    public DivideCommand(int operand1, int operand2) {
        this.operand1 = operand1;
        this.operand2 = operand2;
    }

    @Override
    public void execute(Process process) {
        if (operand2 == 0) {
            System.out.println(process.getName() + " executed DIVIDE: Division by zero error");
            process.setResult(Integer.MIN_VALUE); // Special value to indicate error
        } else {
            int result = operand1 / operand2;
            process.setResult(result);
            System.out.println(process.getName() + " executed DIVIDE: " + operand1 + " / " + operand2 + " = " + result);
        }
    }
}
