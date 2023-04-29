package repository;


import model.Grade;
import model.GradeIdentifier;
import structure.TreeSet;

public class GradeRepository {
    private static final GradeRepository instance = new GradeRepository();
    private final TreeSet<GradeIdentifier, Grade> grades = new TreeSet<>();

    private GradeRepository() {
    }

    public static GradeRepository getInstance() {
        return instance;
    }

    public void add(Grade grade) {
        GradeIdentifier gradeIdentifier = new GradeIdentifier(grade.getStudentCode(), grade.getCourseCode());
        grades.add(gradeIdentifier, grade);
    }

    public void remove(Grade grade) {
        GradeIdentifier gradeIdentifier = new GradeIdentifier(grade.getStudentCode(), grade.getCourseCode());
        grades.delete(gradeIdentifier);
    }

    public void remove(int studentCode, int courseCode) {
        GradeIdentifier gradeIdentifier = new GradeIdentifier(studentCode, courseCode);
        grades.delete(gradeIdentifier);
    }

    public Grade findByCodes(int studentCode, int courseCode) {
        GradeIdentifier gradeIdentifier = new GradeIdentifier(studentCode, courseCode);

        return grades.find(gradeIdentifier);
    }

}
