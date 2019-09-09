import java.util.HashMap;
import java.util.HashSet;

class Range{
    private int start, end, sum;
    Range(int s, int e, int sm){
        start = s;
        end = e;
        sum = sm;
    }

    public int getSum(){
        return sum;
    }

    public int getEnd() {
        return end;
    }

    public int getStart() {
        return start;
    }
}

class ex2{
    private int rowStart, rowEnd, colStart, colEnd, sum;

    SubMatrix(int rs, int re, int cs, int ce, int s){
        rowStart = rs;
        rowEnd = re;
        colStart = cs;
        colEnd = ce;
        sum = s;
    }

    public int getSum(){
        return sum;
    }

    public String toString(){
        return "Submatrix [topLeft = (" + rowStart + ", " + colStart + "), bottomRight = (" + rowEnd +", " + colEnd + ")";
    }
}

public class MaxSubmatrix{

    public static SubMatrix MaxSubmatrix(int[][] matrix){
        // validateMatrixDimensions();

        int rowCount = matrix.length;
        int colCount = matrix[0].length;

        SubMatrix best = null;

        for (int rowStart = 0; rowStart < rowCount; rowStart++){
            int[] partialSum = new int[colCount];

            for (int rowEnd=rowStart; rowEnd<rowCount; rowEnd++){
                for(int col=0; col<colCount; col++){
                    partialSum[col] += matrix[rowEnd][col];
                }

                Range bestRange = maxSubArray(partialSum);
                if(best == null || bestRange.getSum() > best.getSum())
                    best = new SubMatrix(rowStart, rowEnd, bestRange.getStart(), bestRange.getEnd(), bestRange.getSum());
            }
        }
        return best;
    }

    public static Range maxSubArray(int[] array){
        Range best = null;
        int sum = 0;
        int start = 0;
        
        for (int i = 0; i < array.length; i++){
            sum += array[i];

            if (best == null || sum > best.getSum())
                best = new Range(start, i, sum);
            
            if (sum < 0){
                start = i + 1;
                sum = 0;
            }  
        }
        return best;
    }

    public static void test(){
        int[][] matrix = new int[][]{{5,-2,-10, 7},{3,-2,10, 3},{8,4,-5, -10}}; 
        printMatrix(matrix);

        SubMatrix sm = MaxSubmatrix(matrix);
        System.out.println("SubMatrix w/ max sum:" + sm.toString());
    }

    public static printMatrix(int[][] matrix){
        System.out.println("Original Matrix:");
        for(int r = 0; r < matrix.length; r++){
            for (int c = 0; c < matrix[0].length; c++){
                System.out.print(matrix[r][c] + " ");
            }
            System.out.println();
        }
    }
}