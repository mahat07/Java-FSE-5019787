public class FinancialForecasting {

    public static double calculateFutureValueIterative(double presentValue, double rate, int periods) {
        double futureValue = presentValue;
        for (int i = 0; i < periods; i++) {
            futureValue *= (1 + rate);
        }
        return futureValue;
    }

    public static void main(String[] args) {
        double presentValue = 1000.0;  
        double annualRate = 0.05;      
        int years = 10;                

        double futureValue = calculateFutureValueIterative(presentValue, annualRate, years);
        System.out.printf("The future value after %d years is: %.2f%n", years, futureValue);
    }
}
