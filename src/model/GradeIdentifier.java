package model;

public class GradeIdentifier implements Comparable<GradeIdentifier> {

    protected int studentCode;
    protected int courseCode;

    public GradeIdentifier(int studentCode, int courseCode) {
        this.studentCode = studentCode;
        this.courseCode = courseCode;
    }

    public GradeIdentifier() {

    }

    public void setCourseCode(int courseCode) {
        this.courseCode = courseCode;
    }
    public void setStudentCode(int studentCode) {
        this.studentCode = studentCode;
    }

    public int getStudentCode() {
        return studentCode;
    }
    public int getCourseCode() {
        return courseCode;
    }

    @Override
    public int compareTo(GradeIdentifier gradeIdentifier) {
        int compare = this.studentCode - gradeIdentifier.studentCode;
        if (compare != 0) {
            return compare;
        }
        compare = this.courseCode - gradeIdentifier.courseCode;
        return compare;
    }

}
