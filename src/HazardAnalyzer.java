import java.util.Scanner;
public class HazardAnalyzer {
    public static void main(String[] args){
        System.out.print("Factory Robot Hazard Analyzer");
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter Arm Precision(0.0-1.0):");
        double armPrecision = sc.nextDouble();

        System.out.println("Enter Worker Density(1-20):");
        double workerDensity = sc.nextDouble();

        sc.nextLine(); // clear buffer
        System.out.println("Enter Machinery State(worn/faulty/critical):");
        double machineryState = sc.nextDouble();
        double machineryStatenum=machineryState;


        System.out.println("Arm Precision: " + armPrecision);
        System.out.println("Worker Density: " + workerDensity);
        System.out.println("Machinery State: " + machineryState);
        double hazardRisk=((1-armPrecision)*15)+(workerDensity*machineryStatenum);
        System.out.println("Hazard Risk Score: " + hazardRisk);
    }
}
