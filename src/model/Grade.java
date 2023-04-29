package model;

import java.util.Objects;

public class Grade extends GradeIdentifier {

    private int semester;
    private double grade;


    public Grade(int semester, double grade, int studentCode, int courseCode){
        super(studentCode, courseCode);
        this.semester = semester;
        this.grade = grade;
    }



    public void setGrade(double grade) {
        this.grade = grade;
    }

    public void setSemester(int semester) {
        this.semester = semester;
    }


    public double getGrade() {
        return grade;
    }

    public int getSemester() {
        return semester;
    }




}
