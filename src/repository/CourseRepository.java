package repository;

import model.Course;
import structure.HashTable;
import structure.LinkedList;
import structure.TreeSet;

public class CourseRepository {
    private static final CourseRepository instance = new CourseRepository();
    private final LinkedList<Course> courses = new LinkedList<>();

    private final HashTable courseHash = new HashTable();

    private final TreeSet<String, Course> courseTreeSet = new TreeSet<>();

    private CourseRepository() {
    }

    public static CourseRepository getInstance() {
        return instance;
    }

    public void setHashValue(long a, long b, long p) {
        courseHash.setValues(a, b, p);
    }

    public Course getCourseByCodeFromLinkedList(int courseCode) {
        return courses.getByCode(courseCode);
    }

    public Course getCourseByCode(int courseCode) {
        int index = courseHash.findIndex(courseCode);
        if (index != -1) {
            return getCourseByCodeFromLinkedList(courseCode);
        }
        return null;
    }

    public Course getCourseByName(String name) {
        return courseTreeSet.find(name);
    }

    public void add(Course course) {
        courses.add(course);
        courseHash.insert(course.getCode());
        courseTreeSet.add(course.getName(), course);
    }

    public void remove(int courseCode) {
        Course course = this.getCourseByCode(courseCode);
        courses.delete(course);
        courseHash.delete(courseCode);
        courseTreeSet.delete(course.getName());
    }

    public long getHash(int code) {
        return courseHash.getHashValue(code);
    }

    public void updateCourse(int courseCode, String newCourseName) {
        Course course = getCourseByCode(courseCode);
        courseTreeSet.delete(course.getName());
        course.setName(newCourseName);
        courseTreeSet.add(newCourseName, course);
    }

}