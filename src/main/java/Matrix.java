import java.util.*;
import java.util.stream.IntStream;

class Matrix {
    private Set<MatrixCoordinate> saddlePoints;
    Matrix(List<List<Integer>> values) {
        saddlePoints = new HashSet<MatrixCoordinate>();
        for(int i = 0; i < values.size();i++){
            List<Integer> row = values.get(i);
            int maxValue = Collections.max(row);
            int[] indexes = IntStream.range(0,row.size()).filter(searchIndex -> row.get(searchIndex).equals(maxValue)).toArray();
            nextSuitable:
            for(int index : indexes) {
                for(int k = 0; k < values .size(); k++) {
                    int value = values.get(k).get(index);
                    if(value == maxValue)
                        continue;
                    //max must be smaller therefore is greater, it isn't a saddle point
                    if(maxValue > value)
                        continue nextSuitable;
                }
                //only gets here if it compared values in the same column in the same column
                MatrixCoordinate mc = new MatrixCoordinate(i+1,index+1);
                saddlePoints.add(mc);
            }
        }
    }


    Set<MatrixCoordinate> getSaddlePoints() {
        return saddlePoints;
    }
}
