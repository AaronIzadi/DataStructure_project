package model;

import structure.LinkedList;
import structure.Pair;

public class Course extends Entity {
    private final LinkedList<Pair<Student, Grade>> newStudents = new LinkedList<>();

    private final LinkedList<Student> students = new LinkedList<>();
    public Course(int code, String name) {
        this.code = code;
        this.name = name;
    }

    public LinkedList<Pair<Student, Grade>> getNewStudents() {
        return newStudents;
    }

    public LinkedList<Student> getStudents() {
        return students;
    }
}
