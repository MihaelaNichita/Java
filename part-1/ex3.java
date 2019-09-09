public class Scheduler{

    public static int maxHours(int[] requests){
        if (requests==null || requests.length==0) 
            return -1;
        int[] memo = new int[requests.length]; /** Biggest number of hours w/ or w/o request at index */
        return maxHours(requests, 0, memo);
    }

    private static int maxHours(int[] requests, int index, int[] memo){
        if (index >= requests.length)
            return 0;
        if (memo[index] != 0)
            return memo[index];

        int sumWith = maxHours(requests, index+2, memo) + requests[index];
        int sumWithout = maxHours(requests, index+1, memo);

        memo[index] = Math.max(sumWith, sumWithout);
        return memo[index];
    }

    public static int maxHoursIterative(int[] requests) {

        int oneAway = 0;
        int twoAway = 0;

        for (int i = requests.length-1; i>=0; i--){
            int bestWith = requests[i] + twoAway;
            int bestWithout = oneAway;
            int best = Math.max(bestWith, bestWithout);
            twoAway = oneAway;
            oneAway = best;
        }

        return oneAway;
    }

    public static void test(){
        int[] requests = new int[]{15,30,75,45,45};
        int hours = maxHours(requests);
        int hours_it = maxHoursIterative(requests);

        System.out.println("[RECURSIVE] max hours = "+hours);
        System.out.println("[ITERATIVE] max hours = "+hours_it);
    }
}