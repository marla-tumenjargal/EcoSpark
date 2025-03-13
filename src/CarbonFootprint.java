import java.util.Date;
import java.util.HashMap;
import java.util.Map;

class CarbonFootprint {
    private double homeEmissions;
    private double transportEmissions;
    private double foodEmissions;
    private double wasteEmissions;
    private double totalEmissions;
    private Map<String, Double> detailedBreakdown;
    private Date calculationDate;

    public CarbonFootprint() {
        this.homeEmissions = 0;
        this.transportEmissions = 0;
        this.foodEmissions = 0;
        this.wasteEmissions = 0;
        this.totalEmissions = 0;
        this.detailedBreakdown = new HashMap<>();
        this.calculationDate = null;
    }

    // Calculate carbon footprint based on user inputs
    public void calculateFootprint(double electricityUsage, double gasUsage, double carMiles,
                                   double publicTransportMiles, double flightMiles,
                                   boolean isVegetarian, int meatMealsPerWeek,
                                   double wasteProducedKg) {

        // Home emissions calculation
        this.homeEmissions = (electricityUsage * 0.92) + (gasUsage * 0.18);

        // Transport emissions calculation
        this.transportEmissions = (carMiles * 0.35) + (publicTransportMiles * 0.14) + (flightMiles * 0.44);

        // Food emissions calculation
        if (isVegetarian) {
            this.foodEmissions = 1.5; // Base value for vegetarian diet
        } else {
            this.foodEmissions = 2.5 + (meatMealsPerWeek * 0.5); // Base value plus meat consumption
        }

        // Waste emissions calculation
        this.wasteEmissions = wasteProducedKg * 0.57;

        // Calculate total emissions
        this.totalEmissions = this.homeEmissions + this.transportEmissions + this.foodEmissions + this.wasteEmissions;

        // Create detailed breakdown
        this.detailedBreakdown = new HashMap<>();
        this.detailedBreakdown.put("Home", this.homeEmissions);
        this.detailedBreakdown.put("Transport", this.transportEmissions);
        this.detailedBreakdown.put("Food", this.foodEmissions);
        this.detailedBreakdown.put("Waste", this.wasteEmissions);

        this.calculationDate = new Date();

        System.out.println("\nCarbon footprint calculation complete!");
    }

    // Getters for emissions
    public double getHomeEmissions() {
        return homeEmissions;
    }

    public double getTransportEmissions() {
        return transportEmissions;
    }

    public double getFoodEmissions() {
        return foodEmissions;
    }

    public double getWasteEmissions() {
        return wasteEmissions;
    }

    public double getTotalEmissions() {
        return totalEmissions;
    }

    public Map<String, Double> getDetailedBreakdown() {
        return detailedBreakdown;
    }

    public Date getCalculationDate() {
        return calculationDate;
    }
}
