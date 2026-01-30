import java.util.Scanner;
public class HazardAnalyzer {
    public static double HazardRiskCalculator(double armPrecision, double workerDensity, String machineryState){
        double machineryStatenum=0;
        switch (machineryState){
            case "worn":
                machineryStatenum=1.3;
                break;

            case "faulty":
                machineryStatenum=2;
                break;
            case "critical":
                machineryStatenum=3;

        }
        return ((1-armPrecision)*15)+(workerDensity*machineryStatenum);
    }
    public static void main(String[] args){
        System.out.print("Factory Robot Hazard Analyzer");
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter Arm Precision (0.0 - 1.0):");
        if (!sc.hasNextDouble()) {
            System.out.println("❌ Wrong input: Arm Precision must be a decimal number.");
            return;
        }
        double armPrecision = sc.nextDouble();

        if (armPrecision < 0.0 || armPrecision > 1.0) {
            System.out.println("❌ Wrong input: Arm Precision must be between 0.0 and 1.0.");
            return;
        }

        // Worker Density
        System.out.println("Enter Worker Density (1 - 20):");
        if (!sc.hasNextDouble()) {
            System.out.println("❌ Wrong input: Worker Density must be a number.");
            return;
        }
        double workerDensity = sc.nextDouble();

        if (workerDensity < 1 || workerDensity > 20) {
            System.out.println("❌ Wrong input: Worker Density must be between 1 and 20.");
            return;
        }

        sc.nextLine(); // clear buffer

        // Machinery State
        System.out.println("Enter Machinery State (worn / faulty / critical):");
        String machineryState = sc.nextLine().toLowerCase();

        if (!machineryState.equals("worn") &&
                !machineryState.equals("faulty") &&
                !machineryState.equals("critical")) {

            System.out.println("❌ Wrong input: Machinery State must be 'worn', 'faulty', or 'critical'.");
            return;
        }



        System.out.println("Arm Precision: " + armPrecision);
        System.out.println("Worker Density: " + workerDensity);
        System.out.println("Machinery State: " + machineryState);
        double hazardRisk=HazardRiskCalculator(armPrecision,workerDensity,machineryState);
        System.out.println("Hazard Risk Score: " + hazardRisk);
    }
}
