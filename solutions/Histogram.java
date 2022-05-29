import java.util.Arrays;
import java.util.Scanner;

public class Histogram {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int numberOfTestCases = scanner.nextInt();
        for(int i=0;i<numberOfTestCases;i++){;
            int numberOfDataPoints = scanner.nextInt();
            int numberOfBins = scanner.nextInt();
            int[] data = new int[numberOfDataPoints];
            for(int j=0;j<data.length;j++){
                data[j] = scanner.nextInt();
            }
            Arrays.sort(data);
            int min = data[0];
            int max = data[numberOfDataPoints-1];
            int interval = (max-min)/numberOfBins;
            int[] cutsoffs = new int[numberOfBins+1];
            cutsoffs[0] = min;
            for(int k=1;k< cutsoffs.length;k++){
                int value = cutsoffs[k-1]+interval;
                cutsoffs[k] = value;
            }
            int k= 1;
            int j =0;
            int temp = 0;
            int[] counts = new int[numberOfBins];
            for(int g=0;g<numberOfDataPoints;g++) {
                if (data[g] < cutsoffs[k]) {
                    temp++;
                } else {
                    if(data[g] == max){
                        temp++;
                    }
                    counts[j] = temp;
                    temp=1;
                    k++;
                    j++;
                }
            }
            for(Integer cutsoff : cutsoffs)
                System.out.print(cutsoff + " ");
            System.out.println();
            for(Integer count: counts)
                System.out.print(count + " ");
            System.out.println();
        }
    }
}
