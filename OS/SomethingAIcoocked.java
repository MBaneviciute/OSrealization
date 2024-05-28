// Base class for all processes
abstract class Process {
    private int pid; // Process ID
    private ProcessState state; // Process state (RUNNING, BLOCKED, READY, SUSPENDED)
    private ProcessType type; // Process type (SYSTEM, USER)
    private List<Register> registers; // Processor registers
    private int priority; // Process priority
    private String name; // Process name

    // Constructor
    public Process(int pid, ProcessState state, ProcessType type, List<Register> registers, int priority, String name) {
        this.pid = pid;
        this.state = state;
        this.type = type;
        this.registers = registers;
        this.priority = priority;
        this.name = name;
    }

    // Getters and setters
    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public ProcessState getState() {
        return state;
    }

    public void setState(ProcessState state) {
        this.state = state;
    }

    public ProcessType getType() {
        return type;
    }

    public void setType(ProcessType type) {
        this.type = type;
    }

    public List<Register> getRegisters() {
        return registers;
    }

    public void setRegisters(List<Register> registers) {
        this.registers = registers;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    // Abstract method to run the process
    public abstract void run();
}

// Enum for process states
enum ProcessState {
    RUNNING, BLOCKED, READY, SUSPENDED
}

// Enum for process types
enum ProcessType {
    SYSTEM, USER
}

// Class for process descriptors
class ProcessDescriptor {
    private int pid; // Process ID
    private ProcessState state; // Process state
    private ProcessType type; // Process type
    private int priority; // Process priority
    private String name; // Process name

    // Constructor
    public ProcessDescriptor(int pid, ProcessState state, ProcessType type, int priority, String name) {
        this.pid = pid;
        this.state = state;
        this.type = type;
        this.priority = priority;
        this.name = name;
    }

    // Getters and setters
    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public ProcessState getState() {
        return state;
    }

    public void setState(ProcessState state) {
        this.state = state;
    }

    public ProcessType getType() {
        return type;
    }

    public void setType(ProcessType type) {
        this.type = type;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

// Class for memory management resources
class MemoryManagementResources {
    private List<PageTableEntry> pageTables; // Page tables
    private List<MemoryRegion> memoryRegions; // Occupied memory regions

    // Constructor
    public MemoryManagementResources(List<PageTableEntry> pageTables, List<MemoryRegion> memoryRegions) {
        this.pageTables = pageTables;
        this.memoryRegions = memoryRegions;
    }

    // Getters and setters
    public List<PageTableEntry> getPageTables() {
        return pageTables;
    }

    public void setPageTables(List<PageTableEntry> pageTables) {
        this.pageTables = pageTables;
    }

    public List<MemoryRegion> getMemoryRegions() {
        return memoryRegions;
    }

    public void setMemoryRegions(List<MemoryRegion> memoryRegions) {
        this.memoryRegions = memoryRegions;
    }
}

// Class for I/O resources
// Class for I/O resources
class IOResources {
    private IOResourceType type; // I/O resource type (FILE, DEVICE)
    private String identifier; // Resource identifier
    private IOResourceState state; // Resource state (AVAILABLE, BUSY, OPEN, CLOSED)
    private List<AccessPermission> permissions; // Access permissions (READ, WRITE)
    private Process owner; // Process that owns the resource
    private Queue<Process> waitingQueue; // Queue of processes waiting for the resource

    // Constructor
    public IOResources(IOResourceType type, String identifier, IOResourceState state, List<AccessPermission> permissions, Process owner) {
        this.type = type;
        this.identifier = identifier;
        this.state = state;
        this.permissions = permissions;
        this.owner = owner;
        this.waitingQueue = new LinkedList<>();
    }

    // Getters and setters
    public IOResourceType getType() {
        return type;
    }

    public void setType(IOResourceType type) {
        this.type = type;
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public IOResourceState getState() {
        return state;
    }

    public void setState(IOResourceState state) {
        this.state = state;
    }

    public List<AccessPermission> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<AccessPermission> permissions) {
        this.permissions = permissions;
    }

    public Process getOwner() {
        return owner;
    }

    public void setOwner(Process owner) {
        this.owner = owner;
    }

    public Queue<Process> getWaitingQueue() {
        return waitingQueue;
    }
}

// Enum for I/O resource types
enum IOResourceType {
    FILE, DEVICE
}

// Enum for I/O resource states
enum IOResourceState {
    AVAILABLE, BUSY, OPEN, CLOSED
}

// Enum for access permissions
enum AccessPermission {
    READ, WRITE
}

// Class for resource descriptor
class ResourceDescriptor {
    private String id; // Resource ID
    private ResourceType type; // Resource type
    private ResourceState state; // Resource state

    // Constructor
    public ResourceDescriptor(String id, ResourceType type, ResourceState state) {
        this.id = id;
        this.type = type;
        this.state = state;
    }

    // Getters and setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ResourceType getType() {
        return type;
    }

    public void setType(ResourceType type) {
        this.type = type;
    }

    public ResourceState getState() {
        return state;
    }

    public void setState(ResourceState state) {
        this.state = state;
    }
}

// Enum for resource types (can be extended for different resource types)
enum ResourceType {
    MEMORY, FILE, IOResource
}

// Interface for process primitives (create, destroy, suspend, resume)
interface ProcessPrimitive {
    void createProcess(Process parent, ProcessState state, int priority, List<Resource> resources, String name);
    void destroyProcess(Process process);
    void suspendProcess(Process process);
    void resumeProcess(Process process);
}

// Class for scheduler (uses a simple preemptive priority scheduling algorithm)
class Scheduler {
    private Queue<Process> readyQueue; // Queue of ready processes

    public Scheduler() {
        this.readyQueue = new LinkedList<>();
    }

    public void addProcess(Process process) {
        readyQueue.add(process);
    }

    public Process getNextProcess() {
        Process process = readyQueue.poll();
        if (process != null) {
            return process;
        } else {
            return null; // No ready processes
        }
    }
}

// Basic process examples (StartStopProcess, ReadInputProcess, etc.)
// (Implementation details omitted for brevity)

// ... Implement
// Basic process examples (StartStopProcess, ReadInputProcess, etc.)

class StartStopProcess extends Process {

    public StartStopProcess(int pid, ProcessState state, ProcessType type, List<Register> registers, int priority, String name) {
        super(pid, state, type, registers, priority, name);
    }

    @Override
    public void run() {
        // Simulate some work
        System.out.println("StartStopProcess: Running...");
        try {
            Thread.sleep(1000); // Simulate work time
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("StartStopProcess: Finished.");
    }
}

class ReadInputProcess extends Process {

    private IOResources inputResource; // Resource for reading input

    public ReadInputProcess(int pid, ProcessState state, ProcessType type, List<Register> registers, int priority, String name, IOResources inputResource) {
        super(pid, state, type, registers, priority, name);
        this.inputResource = inputResource;
    }

    @Override
    public void run() {
        // Request access to input resource
        if (!inputResource.getState().equals(IOResourceState.AVAILABLE)) {
            System.out.println("ReadInputProcess: Waiting for input resource...");
            inputResource.getWaitingQueue().add(this);
            setState(ProcessState.BLOCKED);
            return;
        }

        // Simulate reading input
        System.out.println("ReadInputProcess: Reading input...");
        try {
            Thread.sleep(500); // Simulate reading time
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        inputResource.setState(IOResourceState.AVAILABLE); // Release resource
        System.out.println("ReadInputProcess: Finished reading input.");
    }
}

    
