import java.util.Scanner;

// Custom Exception
class RobotSafetyException extends Exception {
    public RobotSafetyException(String message) {
        super(message);
    }
}

public class HazardAnalyzer {

    // Machinery risk constants (conceptually enum-like)
    private static final double WORN_RISK = 1.3;
    private static final double FAULTY_RISK = 2.0;
    private static final double CRITICAL_RISK = 3.0;

    // Machinery State → Risk Mapping (Business Rule Encapsulation)
    public static double getMachineryRiskFactor(String machineryState)
            throws RobotSafetyException {

        switch (machineryState) { // Case-sensitive
            case "Worn":
                return WORN_RISK;
            case "Faulty":
                return FAULTY_RISK;
            case "Critical":
                return CRITICAL_RISK;
            default:
                throw new RobotSafetyException(
                        "❌ Unsupported Machinery State. Allowed values: Worn, Faulty, Critical."
                );
        }
    }

    public static double HazardRiskCalculator(
            double armPrecision,
            double workerDensity,
            String machineryState
    ) throws RobotSafetyException {

        // Validate Arm Precision
        if (armPrecision < 0.0 || armPrecision > 1.0) {
            throw new RobotSafetyException(
                    "❌ Arm Precision must be between 0.0 and 1.0."
            );
        }

        // Validate Worker Density
        if (workerDensity < 1 || workerDensity > 20) {
            throw new RobotSafetyException(
                    "❌ Worker Density must be between 1 and 20."
            );
        }

        // Get machinery risk using mapping
        double machineryRiskFactor = getMachineryRiskFactor(machineryState);

        return ((1 - armPrecision) * 15) + (workerDensity * machineryRiskFactor);
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        try {
            System.out.println("Factory Robot Hazard Analyzer");

            System.out.println("Enter Arm Precision (0.0 - 1.0):");
            double armPrecision = sc.nextDouble();

            System.out.println("Enter Worker Density (1 - 20):");
            double workerDensity = sc.nextDouble();

            sc.nextLine(); // clear buffer

            System.out.println("Enter Machinery State (Worn / Faulty / Critical):");
            String machineryState = sc.nextLine(); // Case-sensitive input

            double hazardRisk = HazardRiskCalculator(
                    armPrecision,
                    workerDensity,
                    machineryState
            );

            System.out.println("Arm Precision: " + armPrecision);
            System.out.println("Worker Density: " + workerDensity);
            System.out.println("Machinery State: " + machineryState);
            System.out.println("Hazard Risk Score: " + hazardRisk);

        } catch (RobotSafetyException e) {
            System.out.println(e.getMessage());

        } catch (Exception e) {
            System.out.println("❌ Invalid input type. Please enter correct values.");
        }
    }
}
