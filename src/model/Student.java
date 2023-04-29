package model;

import structure.LinkedList;
import structure.Pair;
import structure.TreeSet;

public class Student extends Entity {
    private final LinkedList<Pair<Course, Grade>> newCourses = new LinkedList<>();

    private final LinkedList<Course> courses = new LinkedList<>();

    public Student(int code, String name) {
        this.code = code;
        this.name = name;
    }

    public LinkedList<Pair<Course, Grade>> getNewCourses() {
        return newCourses;
    }

    public LinkedList<Course> getCourses() {
        return courses;
    }
}
