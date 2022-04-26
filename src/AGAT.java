import java.util.*;

public class AGAT {
    static int currentTime=0;
    ProcessInfo currentProcess;
    double V1;
    int f=0;
    public Queue<ProcessInfo> ReadyQueue=new LinkedList<>();
    public ArrayList<ProcessInfo> waitingQueue=new ArrayList<>();
    ArrayList<ProcessInfo> deadList=new ArrayList<>();
    void AddInReadyQueue(){
        //Iterator<ProcessInfo> waitQ =waitingQueue.iterator();
        for(int i=0;i<waitingQueue.size();i++){
            if(waitingQueue.get(i).ArrivalTime<=currentTime&&waitingQueue.get(i).X==0){
                waitingQueue.get(i).X=1;
                ReadyQueue.add(waitingQueue.get(i));
                //waitingQueue.remove(i);
            }
        }
    }
    boolean check(){
        int flag=0;
        for(ProcessInfo p:waitingQueue){
            if(p.X==0){
                flag=1;
            }
        }
        if (flag==0){
            return false;
        }else return true;
    }
    double getV2(){
        double max=0,V2;
        for(ProcessInfo p:waitingQueue){
            if(p.BurstTime>max) max=p.BurstTime;
        }
        if(max>10)V2=max/10;
        else V2=1.0;
        return V2;
    }
    void CalculateV1(){
        double max=0;
        for(ProcessInfo p:waitingQueue){
            if(p.ArrivalTime>max) max=p.ArrivalTime;
        }
        if(max>10) V1=max/10;
        else V1=1.0;

    }
    void calculateQuantum(int choice,ProcessInfo p){
        //with least AGAT factor process
        if(choice==0){
            int newQuantum=p.getQuantumTime()+2;
            //currentTime+=Math.ceil(currentProcess.QuantumTime*0.6);
            p.setBurstTime(p.getBurstTime()-p.getQuantumTime());
            p.setQuantumTime(newQuantum);
            p.getQuantums().add(newQuantum);
        }
        //rounding 40%
        if(choice==1){
            int remaining;
            remaining= (int) (p.getQuantumTime()-Math.round(p.QuantumTime*0.4));
            p.setBurstTime((int) (p.getBurstTime() - Math.round(p.getQuantumTime() * 0.4)));
            p.setQuantumTime(remaining+p.getQuantumTime());
            p.getQuantums().add(p.getQuantumTime());
        }
        if(choice==2){
            p.setQuantumTime(0);
            p.getQuantums().add(0);
            p.X=2;
            deadList.add(p);

        }

    }
    void execution(){
        ProcessInfo mini=new ProcessInfo();
        while(true){
            int flag=0,w=0;
            //current=0
            AddInReadyQueue();
            if(ReadyQueue.isEmpty()) break;
            else { //  p1
                if(f==0)currentProcess=ReadyQueue.poll();
                // empty
                else{currentProcess=mini; ReadyQueue.remove(mini);}

                System.out.println("------------------------------------------------------");
                System.out.println("Process Name "+currentProcess.name+' '+currentTime+" Burst Time "+currentProcess.getBurstTime()+
                        " Quantum Time "+currentProcess.getQuantumTime()+" AGAT factor "+currentProcess.getAGATFactor());
                System.out.println("------------------------------------------------------");
                int timeprocess=0;
                timeprocess= (int) Math.round(currentProcess.QuantumTime*0.4);
                currentTime+=timeprocess;
                AddInReadyQueue();
                while(ReadyQueue.isEmpty()){
                    w=1;
                    currentTime++;AddInReadyQueue();
                    if(currentProcess.getQuantumTime()==timeprocess||currentProcess.getBurstTime()==timeprocess) {flag=1;break;}
                    if(currentProcess.getBurstTime()!=timeprocess) timeprocess++; // curernt=3

                    //  p2
                }
                //if(currentProcess.getBurstTime()<=timeprocess)currentProcess.X=2;
                mini=calculateAGAT();
                if(w==1&&currentProcess.getAGATFactor()>mini.getAGATFactor()){
                    ReadyQueue.add(currentProcess);
                    int remaining;
                    remaining=(currentProcess.getQuantumTime()-timeprocess);
                    currentProcess.setBurstTime((currentProcess.getBurstTime() - timeprocess));
                    currentProcess.setQuantumTime(remaining+currentProcess.getQuantumTime());
                    currentProcess.getQuantums().add(currentProcess.getQuantumTime());

                    f=1;
                }
                else if(flag==1){
                    ReadyQueue.add(currentProcess);
                    calculateQuantum(0,currentProcess);
                }
                else if(currentProcess.getAGATFactor()==mini.getAGATFactor()&&w==1){
                    ReadyQueue.add(currentProcess);
                    currentTime+=(currentProcess.getQuantumTime()-timeprocess)-timeprocess;
                    calculateQuantum(0,currentProcess);
                    f=0;
                }

                else if(currentProcess.getAGATFactor()> mini.getAGATFactor()&&w==0){
                    ReadyQueue.add(currentProcess);
                    calculateQuantum(1,currentProcess);
                    f=1;
                }
                else if(currentProcess.getAGATFactor()==mini.AGATFactor&&w==0){
                    currentTime+=currentProcess.getQuantumTime()-timeprocess;
                    ReadyQueue.add(currentProcess);
                    calculateQuantum(0,currentProcess);
                    f=0;
                }
                if(currentProcess.getBurstTime()<=0){
                    currentTime+=currentProcess.getBurstTime();
                    currentProcess.endTime=currentTime;
                    currentProcess.setBurstTime(0);
                    calculateQuantum(2,currentProcess);
                    ReadyQueue.remove(currentProcess);
                }
            }
           //currentTime+=currentProcess.context;
        }
    }
    ProcessInfo calculateAGAT(){
        ProcessInfo minmum_factor=new ProcessInfo();
        minmum_factor.setAGATFactor(1000000);
        for (ProcessInfo p:waitingQueue) {
            if (p.X == 1) {
                p.AGATFactor = (int) ((10 - p.Priority) + Math.ceil(p.ArrivalTime / V1) + Math.ceil(p.BurstTime / getV2()));
                p.getFactors().add(p.getAGATFactor());
                if (p.getAGATFactor() < minmum_factor.getAGATFactor()) {
                    minmum_factor = p;
                }
            }
        }
        return minmum_factor;
    }
    void calculateWaitingTimeTurnAround() {
        for (ProcessInfo p : waitingQueue) {
            p.WaitingTime = (p.endTime - p.ArrivalTime) - p.originalBurstTime;
            p.TurnaroundTime = p.endTime - p.ArrivalTime;
        }
    }
    int AverageWait(){
        int sum=0;
        for(ProcessInfo p:waitingQueue){
            sum+= p.WaitingTime;
        }
        return sum/waitingQueue.size();
    }
    int AverageTurnAround(){
        int sum=0;
        for(ProcessInfo p:waitingQueue){
            sum+= p.TurnaroundTime;
        }
        return sum/waitingQueue.size();
    }
}
