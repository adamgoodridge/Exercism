import java.util.Arrays;

class Triangle {
    private Boolean equilateral, isosceles, scalene;
    Triangle(double side1, double side2, double side3) throws TriangleException {
        if(side1 <= 0 || side2 <= 0 || side3 <= 0)
            throw new TriangleException();
        //could put two if-statements on the same line, the effort of sortting might not need if the first pre-condition fails
        Double[] sides = {side1, side2, side3};
        Arrays.sort(sides);
        if((sides[0] + sides[1]) < sides[2])
            throw new TriangleException();
        equilateral = false;
        isosceles = false;
        scalene = false;
        if(side1 == side2 && side1 == side3){
          equilateral = true;
          isosceles = true;
        } else if (side1 == side2 || side1 == side3 || side2 == side3) {
          isosceles = true;
        } else {
          scalene = true;
        }
    }

    boolean isEquilateral() {
      return this.equilateral;
    }

    boolean isIsosceles() {
       return this.isosceles;
    }

    boolean isScalene() {
        return this.scalene;
    }

}
