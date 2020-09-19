public class StandardDeviation {

    public static void main(String[] args) {
    	// { 30, 25, 20, 15} // GOAL
//        double[] numArray = { 29, 23, 8, 9};
//        double SD = calculateSD(numArray);
//        
//        double[] numArrayGap = { 1, 2, 12, 6};
//        double SDGap = calculateSD(numArrayGap);
//
//        System.out.format("Standard Deviation Overall skill= %.2f", SD);
//        System.out.format("\nStandard Deviation Overall gap= %.2f", SDGap);
    }

    public static double calculateSD(double numArray[])
    {
        double sum = 0.0, standardDeviation = 0.0;
        int length = numArray.length;

        for(double num : numArray) {	
            sum += num;
        }

        double mean = sum/length;

        for(double num: numArray) {
            standardDeviation += Math.pow(num - mean, 2);
        }

        return Math.sqrt(standardDeviation/length);
    }
}