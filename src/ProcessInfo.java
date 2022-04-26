import java.util.ArrayList;

public class ProcessInfo {
    String name;
    String color;
    int originalBurstTime;
    int BurstTime;
    int ArrivalTime;
    int QuantumTime;
    int AGATFactor;
    int context;
    public int getOriginalBurstTime() {
        return originalBurstTime;
    }
    public void setOriginalBurstTime(int originalBurstTime) {
        this.originalBurstTime = originalBurstTime;
    }

    int Priority;
    int endTime;
    int WaitingTime;
    int X;
    public ProcessInfo() {
        X=0;
    }

    int TurnaroundTime;
    ArrayList<Integer> Quantums=new ArrayList<>();
    ArrayList<Integer> Factors=new ArrayList<>();

    public void setBurstTime(int burstTime) {
        BurstTime = burstTime;
    }

    public void setArrivalTime(int arrivalTime) {
        ArrivalTime = arrivalTime;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setQuantumTime(int quantumTime) {
        QuantumTime = quantumTime;
    }

    public void setAGATFactor(int AGATFactor) {
        this.AGATFactor = AGATFactor;
    }

    public void setPriority(int priority) {
        Priority = priority;
    }

    public void setWaitingTime(int waitingTime) {
        WaitingTime = waitingTime;
    }

    public void setTurnaroundTime(int turnaroundTime) {
        TurnaroundTime = turnaroundTime;
    }

    public void setQuantums(ArrayList<Integer> quantums) {
        Quantums = quantums;
    }

    public void setFactors(ArrayList<Integer> factors) {
        Factors = factors;
    }

    public int getBurstTime() {
        return BurstTime;
    }

    public int getArrivalTime() {
        return ArrivalTime;
    }

    public int getQuantumTime() {
        return QuantumTime;
    }

    public int getAGATFactor() {
        return AGATFactor;
    }

    public int getPriority() {
        return Priority;
    }

    public int getWaitingTime() {
        return WaitingTime;
    }

    public int getTurnaroundTime() {
        return TurnaroundTime;
    }

    public ArrayList<Integer> getQuantums() {
        return Quantums;
    }

    public ArrayList<Integer> getFactors() {
        return Factors;
    }

    public ProcessInfo(String name, String color, int burstTime, int arrivalTime, int quantumTime,
                       int AGATFactor, int priority, int waitingTime, int turnaroundTime) {
        this.name = name;
        this.color = color;
        BurstTime = burstTime;
        ArrivalTime = arrivalTime;
        QuantumTime = quantumTime;
        this.AGATFactor = AGATFactor;
        Priority = priority;
        WaitingTime = waitingTime;
        TurnaroundTime = turnaroundTime;
    }
}
