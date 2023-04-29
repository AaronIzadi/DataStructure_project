package logic;

import model.*;
import repository.CourseRepository;
import repository.GradeRepository;
import repository.StudentsRepository;
import structure.LinkedList;
import structure.Pair;

public class LogicalAgent {

    StudentsRepository studentsRepository = StudentsRepository.getInstance();
    CourseRepository courseRepository = CourseRepository.getInstance();
    GradeRepository gradeRepository = GradeRepository.getInstance();

    public void addStudent(int studentCode, String studentName) {
        Student student = new Student(studentCode, studentName);
        studentsRepository.add(student);
    }

    public void addCourse(int courseCode, String courseName) {
        Course course = new Course(courseCode, courseName);
        courseRepository.add(course);
    }

    public void addGrade(int studentCode, int courseCode, int semester, double grade) {

        Grade grd = new Grade(semester, grade, studentCode, courseCode);

        Course course = courseRepository.getCourseByCodeFromLinkedList(courseCode);
        Student student = studentsRepository.getStudentByCodeFromLinkedList(studentCode);

        gradeRepository.add(grd);

        course.getNewStudents().add(new Pair<>(student, grd));
        course.getStudents().add(student);

        student.getNewCourses().add(new Pair<>(course, grd));
        student.getCourses().add(course);

    }

    public void editStudent(int studentCode, String newStudentName) {
        studentsRepository.updateStudent(studentCode, newStudentName);
    }

    public void editCourse(int courseCode, String newCourseName) {
        courseRepository.updateCourse(courseCode, newCourseName);
    }

    public void editGrade(int studentCode, int courseCode, double grade) {
        Grade grd = gradeRepository.findByCodes(studentCode, courseCode);
        grd.setGrade(grade);
    }

    public void deleteStudent(int studentCode) {
        Student student = studentsRepository.getStudentByCodeFromLinkedList(studentCode);

        if (student == null) return;


        Object[] pairs = student.getNewCourses().toArray();

        for (Object obj : pairs) {
            Pair<Course, Grade> pair = (Pair<Course, Grade>) obj;
            Course course = pair.getFirstObject();
            Grade grade = pair.getSecondObject();
            gradeRepository.remove(grade);
            course.getNewStudents().delete(new Pair<>(student, grade));
            course.getStudents().delete(student);
        }

        studentsRepository.remove(studentCode);
    }

    public void deleteCourse(int courseCode) {
        Course course = courseRepository.getCourseByCodeFromLinkedList(courseCode);

        if (course == null) return;

        Object[] pairs = course.getNewStudents().toArray();

        for (Object obj : pairs) {
            Pair<Student, Grade> pair = (Pair<Student, Grade>) obj;
            Student student = pair.getFirstObject();
            Grade grade = pair.getSecondObject();
            gradeRepository.remove(grade);
            student.getNewCourses().delete(new Pair<>(course, grade));
            student.getCourses().delete(course);
        }

        courseRepository.remove(courseCode);
    }

    public void deleteGrade(int studentCode, int courseCode) {
        Course course = courseRepository.getCourseByCodeFromLinkedList(courseCode);
        Student student = studentsRepository.getStudentByCodeFromLinkedList(studentCode);
        Grade grade = gradeRepository.findByCodes(studentCode, courseCode);

        student.getNewCourses().delete(new Pair<>(course, grade));
        student.getCourses().delete(course);
        course.getNewStudents().delete(new Pair<>(student, grade));
        course.getStudents().delete(student);
        gradeRepository.remove(studentCode, courseCode);
    }

    public long numberOfCourses(int studentCode) {
        return studentsRepository.getStudentByCodeFromLinkedList(studentCode).getNewCourses().getSize();
    }

    public long numberOfStudents(int courseCode) {
        return courseRepository.getCourseByCodeFromLinkedList(courseCode).getNewStudents().getSize();
    }

    public void printStudentInfoByName(String studentName) {

        Student student = studentsRepository.getStudentByName(studentName);

        printStudentInfo(student);
    }

    public void printStudentInfoAndHashByCode(int studentCode) {

        Student student = studentsRepository.getStudentByCode(studentCode);

        System.out.println(getStudentHash(studentCode));

        printStudentInfo(student);
    }

    private void printStudentInfo(Student student) {

        System.out.println(student.getCode() + " " + student.getName() + " " + student.getNewCourses().getSize());

        //prints courses taken in order of time:
        Object[] pairs = student.getNewCourses().toArray();
        printGradeInfo(pairs);

    }

    public void printCourseInfoByName(String courseName) {

        Course course = courseRepository.getCourseByName(courseName);

        printCourseInfo(course);
    }

    public void printCourseInfoAndHashByCode(int courseCode) {

        Course course = courseRepository.getCourseByCode(courseCode);

        System.out.println(getCourseHash(courseCode));

        printCourseInfo(course);
    }

    private void printCourseInfo(Course course) {

        System.out.println(course.getCode() + " " + course.getName() + " " + course.getNewStudents().getSize());

        //prints students taken this course in order of time:
        Object[] pairs = course.getNewStudents().toArray();
        printGradeInfo(pairs);
    }

    private void printGradeInfo(Object[] pairs) {
        for (Object obj : pairs) {
            Pair<Entity, Grade> pair = (Pair<Entity, Grade>) obj;
            Grade grade = pair.getSecondObject();
            Entity entity = pair.getFirstObject();
            if (Math.floor(grade.getGrade()) == grade.getGrade()) {
                System.out.println(entity.getCode() + " " + grade.getSemester() + " " + (int) (Math.floor(grade.getGrade())));
            } else {
                System.out.println(entity.getCode() + " " + grade.getSemester() + " " + grade.getGrade());
            }
        }
    }

    public void setHashValues(long a, long b, long p) {
        studentsRepository.setHashValue(a, b, p);
        courseRepository.setHashValue(a, b, p);
    }

    private long getCourseHash(int code) {
        return courseRepository.getHash(code);
    }

    private long getStudentHash(int code) {
        return studentsRepository.getHash(code);
    }

    public String isRelated(int code1, int code2) {
        LinkedList<Student> students1 = courseRepository.getCourseByCode(code1).getStudents();
        LinkedList<Student> students2 = courseRepository.getCourseByCode(code2).getStudents();

        double duplicate = (students1.getSize() + students2.getSize()) - students1.getMergeWithoutDuplicationSize(students2);

        if (students1.getSize() < duplicate * 2 && students2.getSize() < duplicate * 2) {
            return "yes";
        }
        return "no";
    }
}