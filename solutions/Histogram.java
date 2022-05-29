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
            int min = data[0];
            int max = data[0];
            for(int z=1;z<data.length;z++){
                if(min > data[z]){
                    min = data[z];
                }
                if(max < data[z]){
                    max = data[z];
                }
            }
            int interval = (max-min)/numberOfBins;
            int[] cutsoffs = new int[numberOfBins+1];
            cutsoffs[0] = min;
            for(int k=1;k< cutsoffs.length;k++){
                int value = cutsoffs[k-1]+interval;
                cutsoffs[k] = value;
            }
            int[] counts = new int[numberOfBins];
            for(int g=0;g<numberOfBins;g++){
                int count =0;
                for(int h=0;h<numberOfDataPoints;h++){
                    if(cutsoffs[g+1] == max){
                        if(data[h]>= cutsoffs[g] && data[h]<= cutsoffs[g+1]){
                            count++;
                            continue;
                        }
                    }
                    if(data[h]>= cutsoffs[g] && data[h]< cutsoffs[g+1]){
                        count++;
                    }
                }
                counts[g] = count;
            }
            for(Integer cutsoff: cutsoffs)
                System.out.print(cutsoff + " ");
            System.out.println();                                 
            for(Integer count: counts)
                System.out.print(count + " ");
            System.out.println();                                 
        }
    }
}
