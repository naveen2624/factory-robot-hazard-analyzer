import java.util.Scanner;

// 1️⃣ Custom Exception
class RobotSafetyException extends Exception {
    public RobotSafetyException(String message) {
        super(message);
    }
}

// 2️⃣ Machinery Risk Mapper
class MachineryRiskMapper {

    private static final double WORN_RISK = 1.3;
    private static final double FAULTY_RISK = 2.0;
    private static final double CRITICAL_RISK = 3.0;

    public static double getRiskFactor(String machineryState)
            throws RobotSafetyException {

        switch (machineryState) {
            case "Worn": return WORN_RISK;
            case "Faulty": return FAULTY_RISK;
            case "Critical": return CRITICAL_RISK;
            default:
                throw new RobotSafetyException(
                        "❌ Unsupported Machinery State."
                );
        }
    }
}

// 3️⃣ Core Business Logic
class RobotHazardAuditor {

    public double calculateHazardRisk(
            double armPrecision,
            double workerDensity,
            String machineryState
    ) throws RobotSafetyException {

        if (armPrecision < 0.0 || armPrecision > 1.0)
            throw new RobotSafetyException("❌ Invalid Arm Precision.");

        if (workerDensity < 1 || workerDensity > 20)
            throw new RobotSafetyException("❌ Invalid Worker Density.");

        double risk = MachineryRiskMapper.getRiskFactor(machineryState);

        return ((1 - armPrecision) * 15) + (workerDensity * risk);
    }
}

// 4️⃣ MAIN class (must be public)
public class HazardAnalyzer {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        RobotHazardAuditor auditor = new RobotHazardAuditor();

        try {
            System.out.println("Enter Arm Precision:");
            double ap = sc.nextDouble();

            System.out.println("Enter Worker Density:");
            double wd = sc.nextDouble();

            sc.nextLine();
            System.out.println("Enter Machinery State:");
            String ms = sc.nextLine();

            double risk = auditor.calculateHazardRisk(ap, wd, ms);
            System.out.println("Hazard Risk Score: " + risk);

        } catch (RobotSafetyException e) {
            System.out.println(e.getMessage());
        }
    }
}
