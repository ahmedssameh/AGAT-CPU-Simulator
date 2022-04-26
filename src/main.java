import java.util.Scanner;

public class main {
    public static void main(String[] args){
        AGAT obj=new AGAT();
        int num;
        //ProcessInfo pro=new ProcessInfo();
        System.out.println("Enter number of process you want");
        Scanner input=new Scanner(System.in);
        num=input.nextInt();
        //System.out.println("Enter Context Switch Time");
        //pro.context=input.nextInt();
        int bursttime;
        for(int i=0;i<num;i++){
            ProcessInfo pro=new ProcessInfo();
            System.out.println("Enter process name ");
            pro.setName(input.next());
            System.out.println("Enter burst time ");
            bursttime=input.nextInt();
            pro.setBurstTime(bursttime);
            pro.setOriginalBurstTime(bursttime);
            System.out.println("Enter arrival time ");
            pro.setArrivalTime(input.nextInt());
            System.out.println("Enter process priority ");
            pro.setPriority(input.nextInt());
            System.out.println("Enter Quantum time ");
            pro.setQuantumTime(input.nextInt());
            obj.waitingQueue.add(pro);pro.getQuantums().add(pro.getQuantumTime());
        }
        obj.CalculateV1();
        System.out.println("AGAT Process Schedule");
        while (obj.check()) {
            obj.execution();
            AGAT.currentTime+=1;
        }
        obj.calculateWaitingTimeTurnAround();

        for(ProcessInfo p: obj.waitingQueue){
            System.out.println("Quantum history for "+p.name);
            for(int quantum:p.getQuantums()){
                System.out.println(quantum);
            }
            System.out.println();
        }
        for(ProcessInfo p: obj.waitingQueue){
            System.out.println("AGAT factor history for "+p.name);
            for(int AGAT:p.getFactors()){
                System.out.println(AGAT);
            }
            System.out.println();
        }
        int averagewait=obj.AverageWait();
        System.out.println("AverageWait:"+averagewait);

        int averageturn=obj.AverageTurnAround();
        System.out.println("Average Turnaround:"+averageturn);
    }
}
