import java.util.Scanner;
import java.util.PriorityQueue;


public class Payment {

    public static void main(String[] args) {
        // PriorityQueue for the transaction
        PriorityQueue<Transaction> q = new PriorityQueue<>();
        //All the transaction details
        String transaction;
        //Split all the details and store inside details
        String[] details;
        //Timer of the transaction
        long timer;
        //ID of the transaction
        String id;
        //Tier of the transaction
        String tier;
        //the 4th digit of the first transaction
        int digit1=0;
        //timer of the first transaction
        long timer1;
        //the 4th digit of the continuous transaction
        int digit2;
        //timer of the next transaction
        long timer2;
        //A checker to prevent they check for the transaction time
        int digit3=0;

        Scanner in = new Scanner(System.in);

        while (true) {
            transaction = in.nextLine();
            if (transaction.equals("EXIT")) {
                break;
            }
            if (transaction.equals("REBOOT")) {
                q.clear();
                continue;
            }
            details = transaction.split(" ");
            timer = Long.valueOf(details[0]);
            id = details[1];
            tier = details[2];
            Transaction t1 = new Transaction(timer, id, tier);
            if (q.peek() != null && digit3 < digit1) {
                timer1 = q.peek().getTimer();
                digit1 = (int) (timer1 % 10000 / 1000);
            }
            //Store inside the queue
            q.offer(new Transaction(timer, id, tier));
            timer2 = t1.getTimer();
            if(timer2 %1000 ==0){
                digit2 = digit1;
            }else{
                digit2 = (int) (timer2 % 10000 / 1000);
            }
            while (q.size() == 1) {
                timer1 = timer2;
                digit1 = (int) (timer1 % 10000 / 1000);
                break;
            }
            //All the first 100th transaction
            String ans = "";
            if (digit2 != digit1) {
                int size = q.size();
                //If the size inside not enough 100 then store all the transaction
                if(size<100){
                    while(!q.isEmpty()){
                        Transaction t = q.poll();
                        ans = ans + t;
                    }
                    //If not, then only store first 100th transaction
                }else {
                    for (int i = 0; i < 100; i++) {
                        if (!q.isEmpty()) {
                            Transaction t = q.poll();
                            ans = ans + t;
                        }
                    }
                }
                //Print all the transaction inside this string
                System.out.println(ans);
            }
            //Make sure the digit is the same at the end of every round
            digit1 = digit3 = digit2;
        }
    }
}

class Transaction implements Comparable<Transaction> {
    //Timer that transaction initialized
    long timer;

    //ID of the transaction
    String id;

    //Tier of the user for this transaction
    String tier;

    //The starting time of this transaction
    Long stime;

    /***
     * Initialize the transaction
     * @param timer the timer of the transaction
     * @param id ID of this transaction
     * @param tier Tier of the user for this transaction
     */
    public Transaction(long timer, String id, String tier) {
        this.timer = timer;
        this.id = id;
        this.tier = tier;
        getStartingTime();
    }

    /***
     * Get the starting time of transaction
     * @return starting time of transaction
     */
    public Long getSTime() {
        return stime;
    }

    /***
     * Get the time of transaction
     * @return time of transaction
     */
    public Long getTimer() {
        return timer;
    }

    /***
     * return the starting time based on the tier
     */
    public void getStartingTime() {
        switch (tier) {
            case "PLATINUM":
                stime = timer - 3000;
                break;
            case "GOLD":
                stime = timer - 2000;
                break;
            case "SILVER":
                stime = timer - 1000;
                break;
            case "BRONZE":
                stime = timer;
                break;
        }
    }

    /***
     * Compare the starting time for the transaction and timer if the starting time is the same
     * @param o1 the object to be compared.
     * @return integer to show which object first
     */
    @Override
    public int compareTo(Transaction o1) {
        if(this.getSTime().compareTo(o1.getSTime())== 0){
            return this.getTimer().compareTo(o1.getTimer());
        }else{
            return this.getSTime().compareTo(o1.getSTime());
        }
    }

    /***
     * Print ID of the transaction
     * @return
     */
    @Override
    public String toString() {
        return id + " ";
    }
}
