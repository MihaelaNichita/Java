
class Range{
    private int start, end;
    
    public Range(int s, int e){
        start = s;
        end = e;
    }

    public int getEnd() {
        return end;
    }

    public int getStart() {
        return start;
    }

    public int getLength(){
        return end-start+1;
    }

    @Override
    public String toString() {
        return "Range [start = " + start + ", end = " + end + ", length = " + getLength() + "]";
    }
}

public class LongestNonDecreasingSequence{

    public static Range longestIncreasingSeq(int[] array){
        if (array == null || array.length == 0) return null;

        int[] longestSeqLengths = new int[array.length];
        Range longestRange = new Range(0, 0);

        longestSeqLengths[0] = 1;

        for(int i = 1; i<array.length; i++){
            int iTermLongestSeqLen = 0;

            for(int prev_i = 0; prev_i < i; prev_i++){
                if(array[prev_i] < array[i]){
                    iTermLongestSeqLen = Math.max(iTermLongestSeqLen,longestSeqLengths[prev_i]+1);
                }
            }

            longestSeqLengths[i] = iTermLongestSeqLen;
            if (longestRange == null || iTermLongestSeqLen > longestRange.getLength())
                longestRange = new Range(i - iTermLongestSeqLen + 1, i);
        }

        return longestRange;
    }

    public static void test(){
        testDecreasing();
        testIncreasing();
        testEmpty();
        testDups();
    }

    public static void testDecreasing(){
        int[] array = new int[]{6,5,4,3,2,1};
        Range longestNonDecreasing =  longestIncreasingSeq(array);

        System.out.println("Test for only decreasing:");
        System.out.println(longestNonDecreasing);
    }

    public static void testIncreasing(){
        int[] array = new int[]{1,2,3,4,5};
        Range longestNonDecreasing =  longestIncreasingSeq(array);

        System.out.println("Test for only increasing:");
        System.out.println(longestNonDecreasing);
    }

    public static void testEmpty(){
        int[] array = new int[]{};
        Range longestNonDecreasing =  longestIncreasingSeq(array);

        System.out.println("Test for only empty array:");
        System.out.println(longestNonDecreasing);
    }

    public static void testDups(){
        int[] array = new int[]{1,1,1,1};
        Range longestNonDecreasing =  longestIncreasingSeq(array);

        System.out.println("Test for only dups:");
        System.out.println(longestNonDecreasing);
    }

}