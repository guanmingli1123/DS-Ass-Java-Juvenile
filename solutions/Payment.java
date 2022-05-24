/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package payment;

import java.util.Collections;
import java.util.Scanner;
import java.util.PriorityQueue;
public class Payment {

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
            System.out.println("Please enter your transaction time, id and tier: ");
            transaction = in.nextLine();
            if (transaction.equals("EXIT")) {
                break;
            }
            details = transaction.split(" ");
            timer = Long.parseLong(details[0]);
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
                 while(!q.isEmpty()){
                    Transaction t = q.poll();
                    System.out.println(t + " ");
                }
            
            }
       
            

        }
    }

}
