import java.util.Scanner;

public class Histogram {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int numberOfTestCases = Integer.valueOf(scanner.nextLine());
        for(int i=0;i<numberOfTestCases;i++){
            String input = scanner.nextLine();
            int numberOfDataPoints = Integer.valueOf(input.split(" ")[0]);
            int numberOfBins = Integer.valueOf(input.split(" ")[1]);
            String[] inputs = scanner.nextLine().split(" ");
            int[] data = new int[inputs.length];
            for(int j=0;j<inputs.length;j++){
                data[j] = Integer.valueOf(inputs[j]);
            }
            int min = Integer.valueOf(data[0]);
            int max = Integer.valueOf(data[0]);
            for(int z=1;z<data.length;z++){
                if(min > data[z]){
                    min = data[z];
                }
                if(max < data[z]){
                    max = data[z];
                }
            }
            int interval = (max-min)/2;
            int[] cutsoff = new int[numberOfBins+1];
            cutsoff[0] = min;
            for(int k=1;k< cutsoff.length;k++){
                int value = cutsoff[k-1]+interval;
                cutsoff[k] = value;
            }
            int[] counts = new int[numberOfBins];
            for(int g=0;g<numberOfBins;g++){
                int count =0;
                for(int h=0;h<numberOfDataPoints;h++){
                    if(h> cutsoff[g] && h< cutsoff[g+1]){
                        count++;
                    }
                }
                counts[g] = count;
            }
            for(int q=0;q< cutsoff.length;q++){
                if(q == cutsoff.length-1){
                    System.out.print(cutsoff[q] + "\n");
                }
                System.out.print(cutsoff[q] + " ");
            }
            for(int m=0;m< counts.length;m++){
                if(m == counts.length-1){
                    System.out.print(counts[m] + "\n");
                }
                System.out.print(counts[m] + " ");
            }
        }
    }
}
