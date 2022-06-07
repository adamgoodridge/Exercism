import java.util.Arrays;

public class PascalsTriangleGenerator {
    public PascalsTriangleGenerator() {

    }
    public int[][] generateTriangle(int rowCount) {
        if(rowCount == 0)
            return new int[0][0];
        int[][] triangle= new int[rowCount][];
        triangle[0] = new int[] {1};
        for(int i = 1; i < triangle.length; i++) {
            triangle[i] = new int[i+1];
            for(int k = 1; k < triangle[i].length - 1; k++) {
                triangle[i][k] = triangle[i - 1][k - 1] + triangle[i - 1][k];
            }
            triangle[i][0] = 1;
            triangle[i][triangle[i].length - 1] = 1;
        }
        return triangle;
    }
}