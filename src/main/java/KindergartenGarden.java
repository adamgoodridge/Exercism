import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class KindergartenGarden {
    private static final List<String> NAMES = Arrays.asList("Alice", "Bob", "Charlie", "David", "Eve", "Fred", "Ginny", "Harriet", "Ileana", "Joseph", "Kincaid", "Larry");

    private String[] rows;

    KindergartenGarden(String garden) {
        this.rows = garden.split("\n");
    }

    List<Plant> getPlantsOfStudent(String student) {
        List<Plant> plants = new ArrayList<Plant>();
        //*2 as each student has 2 before this student
        int studentIndex = NAMES.indexOf(student) * 2;
        for (int i = 0; i < rows.length; i++) {
            //the plants will be @ i and i+1 at each row
            plants.add(Plant.getPlant(rows[i].charAt(studentIndex)));
            plants.add(Plant.getPlant(rows[i].charAt(studentIndex + 1)));
        }
        return plants;
    }
}