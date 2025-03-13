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

    // Display carbon footprint results
    public void displayFootprint() {
        if (this.calculationDate == null) {
            System.out.println("\nNo carbon footprint data available. Please calculate your footprint first.");
            return;
        }

        System.out.println("\n--- Your Carbon Footprint ---");
        System.out.println("Calculation Date: " + this.calculationDate);
        System.out.println("\nBreakdown by Category (tons of CO2):");
        System.out.println("Home: " + String.format("%.2f", this.homeEmissions) + " tons");
        System.out.println("Transport: " + String.format("%.2f", this.transportEmissions) + " tons");
        System.out.println("Food: " + String.format("%.2f", this.foodEmissions) + " tons");
        System.out.println("Waste: " + String.format("%.2f", this.wasteEmissions) + " tons");
        System.out.println("\nTotal Emissions: " + String.format("%.2f", this.totalEmissions) + " tons of CO2 per year");
        System.out.println("---------------------------");

        // Provide suggestions based on the highest emission category
        provideSuggestions();
    }

    // Provide suggestions for reducing carbon footprint
    private void provideSuggestions() {
        String highestCategory = "";
        double highestValue = 0;

        for (Map.Entry<String, Double> entry : this.detailedBreakdown.entrySet()) {
            if (entry.getValue() > highestValue) {
                highestValue = entry.getValue();
                highestCategory = entry.getKey();
            }
        }

        System.out.println("\nSuggestions to Reduce Your Carbon Footprint:");

        switch (highestCategory) {
            case "Home":
                System.out.println("- Switch to energy-efficient appliances");
                System.out.println("- Install LED light bulbs");
                System.out.println("- Improve home insulation");
                System.out.println("- Consider renewable energy sources");
                break;
            case "Transport":
                System.out.println("- Use public transportation more often");
                System.out.println("- Consider carpooling or ride-sharing");
                System.out.println("- Walk or bike for short distances");
                System.out.println("- Reduce air travel when possible");
                break;
            case "Food":
                System.out.println("- Reduce meat consumption");
                System.out.println("- Buy local and seasonal produce");
                System.out.println("- Reduce food waste");
                System.out.println("- Consider growing your own vegetables");
                break;
            case "Waste":
                System.out.println("- Recycle properly");
                System.out.println("- Compost organic waste");
                System.out.println("- Reduce single-use plastics");
                System.out.println("- Reuse items instead of disposing them");
                break;
        }
    }

    // Getters
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
