import java.util.Collections;
import java.util.Scanner;
import java.util.PriorityQueue;

class Transaction implements Comparable<Transaction>  {
    long timer;
    String id;
    String tier;
    Integer time;

    public Transaction(long timer, String id, String tier) {
        this.timer = timer;
        this.id = id;
        this.tier = tier;
    }

    public long getTimer() {
        return timer;
    }

    public String getId() {
        return id;
    }
    
    public String getTier() {
        return tier;
    }

    public Integer getTime() {
        switch(tier){
        case "PLATINUM":
            return time = 3000;
        case "GOLD":
            return time = 2000;
        case "SILVER":
            return time = 1000;
        case "BRONZE":
            return time = 0;
        case "":
            return time = -1;
        default:
            break;
        }
        return time;
    }
    
    
    @Override
    public int compareTo(Transaction o1) {
        return this.getTime().compareTo(o1.getTime());
    }

    @Override
    public String toString() {
        return id + " "  ;
    }
    
    
}

public class Payment{

    public static void main(String[] args) {
        PriorityQueue<Transaction> q = new PriorityQueue<>(Collections.reverseOrder());
        String transaction;
        String[] details;
        long timer;
        String id;
        String tier;
        int digit1=0;
        long timer1;
        int digit2;
        long timer2;

        while (true) {
            Scanner in = new Scanner(System.in);
            transaction = in.nextLine();
            if (transaction.equals("EXIT")) {
                break;
            }
            details = transaction.split(" ");
            timer = Long.valueOf(details[0]);
            id = details[1];
            tier = details[2];
            Transaction t1 = new Transaction(timer, id, tier);
            q.offer(new Transaction(timer, id, tier));
            while(q.size()==1){
                timer1 = t1.getTimer();
                digit1 = (int) (timer1 % 10000 / 1000);
                break;
            } 
            timer2 = t1.getTimer();
            digit2 = (int) (timer2 % 10000 / 1000);
            timer1 = timer2;
            if(digit2 > digit1){
                 for(int i=0;i<100;i++){
                    if(!q.isEmpty()){
                        Transaction t = q.poll();
                        System.out.print(t + " ");
                    }
                }
                System.out.println();
            }
        }
    }

    public static boolean isNumeric(String str) {
    if (str == null) {
        return false;
    }
    int sz = str.length();
    for (int i = 0; i < sz; i++) {
        if (Character.isDigit(str.charAt(i)) == false) {
            return false;
        }
    }
    return true;
}
}
