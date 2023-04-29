package repository;

import model.Student;
import structure.HashTable;
import structure.LinkedList;
import structure.TreeSet;

public class StudentsRepository {
    private static final StudentsRepository instance = new StudentsRepository();
    private final LinkedList<Student> students = new LinkedList<>();
    private final HashTable studentHash = new HashTable();
    private final TreeSet<String, Student> studentTreeSet = new TreeSet<>();

    private StudentsRepository() {
    }

    public static StudentsRepository getInstance() {
        return instance;
    }

    public void setHashValue(long a, long b, long p) {
        studentHash.setValues(a, b, p);
    }

    public Student getStudentByCodeFromLinkedList(int studentCode) {
        return students.getByCode(studentCode);
    }

    public Student getStudentByCode(int studentCode) {
        int index = studentHash.findIndex(studentCode);
        if (index != -1) {
            return getStudentByCodeFromLinkedList(studentCode);
        }
        return null;
    }

    public Student getStudentByName(String name) {
        return studentTreeSet.find(name);
    }

    public void add(Student student) {
        students.add(student);
        studentHash.insert(student.getCode());
        studentTreeSet.add(student.getName(), student);
    }

    public boolean exists(Student student) {
        int studentCode = student.getCode();
        return students.get(studentCode) != null;
    }

    public void remove(int studentCode) {
        Student student = this.getStudentByCode(studentCode);
        students.delete(student);
        studentHash.delete(studentCode);
        studentTreeSet.delete(student.getName());
    }

    public long getHash(int code) {
        return studentHash.getHashValue(code);
    }

    public void updateStudent(int studentCode, String newStudentName) {
        Student student = getStudentByCode(studentCode);
        studentTreeSet.delete(student.getName());
        student.setName(newStudentName);
        studentTreeSet.add(newStudentName, student);
    }


//    public void debug() {
//        System.out.println(students);
//////        LinkedList<Student> node = students.getHead();
//////        while (node != null) {
//////            Student student = node.getElement();
//////            System.out.println("List: " + student.toString());
//////            System.out.println("Hash: " + studentHash.getIndex(studentHash.search(student.getCode())).toString());
//////            System.out.println("Tree: " + studentTreeSet.find(student.getName()).toString());
//////            System.out.println();
//////            node = node.getNext();
//////        }
//    }

}