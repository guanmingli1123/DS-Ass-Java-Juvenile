import java.util.Scanner;
import java.util.ArrayList;
import java.util.Collections;

public class Payment{
    
    static String transaction;
    static Long timer;
    static String id;
    static String tier;
    static String[] details;
    static Transaction t;
    static int digit1;
    static int digit2 = 0;
    static long timer1;
    static long timer2;
    static Queue q = new Queue();

    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);
        while (in.hasNextLine()) {
            transaction = in.nextLine();
            if (transaction.equals("EXIT")) {
                break;
            } else if (transaction.equals("REBOOT")) {
                q.clear();
            } else {
                details = transaction.split(" ");
                timer = Long.parseLong(details[0]);
                id = details[1];
                tier = details[2];
                t = new Transaction(timer, id, tier);
                q.enqueue(t);

                timer1 = t.getTimer();
                if (timer1 % 1000 == 0) {
                    digit1 = digit2;
                } else {
                    digit1 = (int) (timer1 % 10000 / 1000);
                }

                if (q.getSize() == 1) {
                    digit2 = digit1;
                }

                if (digit1 != digit2) {
                    digit2 = digit1;
                    int size = q.getSize();
                    if (size < 100){                        
                        while (!q.isEmpty()){
                            System.out.print(q.dequeue().toString() + " ");
                        }
                        System.out.println();
                    }else {
                        q.Sort();
                        for (int x = 0; x < 100; x++) {
                            if (!q.isEmpty()){
                                System.out.print(q.dequeue().toString() + " ");
                            }
                        
                        }
                        System.out.println();
                    }
                   
                }

            }
        }

    }
}




class Sort{

    static ArrayList<Transaction> temp;

    public static void Sort(ArrayList<Transaction> t,  int left, int right) {
        int middle = (left + right) / 2;
        if (left < right) {
            Sort(t,  left, middle);  //sort left half
            Sort(t,  middle + 1, right);  //sort right half
            SortedLists(t,  left, middle, right);
        } 
    }

    public static void SortedLists(ArrayList<Transaction> t,  int left, int middle, int right) {
        temp = new ArrayList<Transaction>();
        int tempLeft = left;
        int tempRight = middle + 1;
        while (tempLeft <= middle && tempRight <= right)
            if (t.get(tempLeft).getStartingTime() <= (t.get(tempRight).getStartingTime())) {
                {
                    temp.add(t.get(tempLeft));
                }
                tempLeft++;
            } else {
                {
                    temp.add(t.get(tempRight));
                }
                tempRight++;
            }

        while (tempLeft <= middle) {
            {
                temp.add(t.get(tempLeft));
            }
            tempLeft++;
        }

        while (tempRight <= right) {
            {
                temp.add(t.get(tempRight));
            }
            tempRight++;
        }
        int i = left;
        {
            for (Transaction value : temp) {
                t.set(i, value);
                i++;
            }
        }
    }
    

}


class Queue {
    
    ArrayList<Transaction> list;

    public Queue() {
        list = new ArrayList<>();
    }

    public void enqueue(Transaction a){
        if (list.size() == 0 ){
            list.add(0,a);;
        }else {
            list.add(a);
        }
    }
    
     public Transaction dequeue(){
        if (list.isEmpty()) return null;
        return list.remove(0);
    }

    public void setList(ArrayList<Transaction> l){
        Collections.copy(list, l);
    }

    public int getSize(){
        return list.size();
    }

    public Transaction get(int i){
        if(list.isEmpty()) return null;
        return list.get(i);
    }

    public void Sort(){
        Sort.Sort(list, 0, list.size()-1);
    }

    public boolean isEmpty(){
        return list.isEmpty();
    }
    
    public void clear() {
        list.clear();
    }

}

class Transaction{

    private long timer;
    private String tier;
    private String id;
    private long startingTime;

    public Transaction(long timer, String id, String tier) {
        this.timer = timer;
        this.tier = tier;
        this.id = id;
        calculateStartingTime(timer);
    }
    
    public String getId() {
        return this.id;
    }

    public long getTimer(){
        return this.timer;
    }
    
    public String getTier() {
        return tier;
    }

    public void calculateStartingTime(long timer) {

        switch (this.tier) {
            case "PLATINUM":
                startingTime = timer - 3000 ;
                break;
            case "GOLD":
                startingTime = timer - 2000 ;
                break;
            case "SILVER":
                startingTime = timer - 1000 ;
                break;
            case "BRONZE":
                startingTime = timer - 0 ;
                break;
        }

    }
    
    public long getStartingTime() {
        return this.startingTime;
    }

    public String toString() {
        return id + " ";
    }

}
