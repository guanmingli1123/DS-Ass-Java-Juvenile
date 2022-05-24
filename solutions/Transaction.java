package payment;

public  class Transaction implements Comparable<Transaction>  {
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
