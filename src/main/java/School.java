import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.TreeSet;

public class School {
    private TreeSet<Grade> grades;

    public School() {
        grades = new TreeSet<>();
    }

    public void add(String studentName, int gradeNumber) {
        Grade grade = grades.stream().filter(g -> g.equals(gradeNumber)).findFirst().orElse(null);
        if(grade == null)
            grades.add(new Grade(gradeNumber, studentName));
        else
            grade.addStudent(studentName);
    }

    public List<String> grade(int gradeNumber) {
        Grade grade = grades.stream().filter(g -> g.equals(gradeNumber)).findFirst().orElse(null);
        return grade != null ? grade.getStudents() : List.of();
    }

    public List<String> roster() {
        List<String> allStudents = new LinkedList<>();
        for(Grade grade : grades) {
            allStudents.addAll(grade.getStudents());
        }
        return allStudents;
    }
}
class Grade implements Comparable {
    private int gradeNumber;
    private TreeSet<String> students;
    public Grade(int gradeNumber, String studentName) {
        this.gradeNumber = gradeNumber;
        students = new TreeSet<>();
        students.add(studentName);
    }
    public void addStudent(String studentName) {
        students.add(studentName);
    }

    public int getGradeNumber() {
        return gradeNumber;
    }

    public List<String> getStudents() {
        return new LinkedList<>(students);
    }

    public boolean equals(int gradeNumber){
        return this.gradeNumber == gradeNumber;
    }
    @Override
    public int compareTo(Object obj) {
        Grade g2 = (Grade) obj;
        if(gradeNumber > g2.getGradeNumber())
            return 1;
        else if(gradeNumber < g2.getGradeNumber())
            return -1;
        else
            return 0;
    }
}
