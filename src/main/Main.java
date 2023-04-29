package main;

import logic.LogicalAgent;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        LogicalAgent logicalAgent = new LogicalAgent();
        Scanner scanner = new Scanner(System.in);

        //Number of orders:
        long t = scanner.nextLong();
        //Hash formula values:
        long a = scanner.nextLong();
        long b = scanner.nextLong();
        long p = scanner.nextLong();

        logicalAgent.setHashValues(a, b, p);
        long loopRepetition = 0;

        while (loopRepetition < t) {

            String order = scanner.next();

            String name;
            int studentCode;
            int courseCode, semester;
            double grade;

            switch (order) {
                case "ADDS":
                    studentCode = scanner.nextInt();
                    name = scanner.next();

                    logicalAgent.addStudent(studentCode, name);

                    break;
                case "ADDC":
                    courseCode = scanner.nextInt();
                    name = scanner.next();

                    logicalAgent.addCourse(courseCode, name);

                    break;
                case "ADDG":
                    studentCode = scanner.nextInt();
                    courseCode = scanner.nextInt();
                    semester = scanner.nextInt();
                    grade = scanner.nextDouble();

                    logicalAgent.addGrade(studentCode, courseCode, semester, grade);

                    break;
                case "EDITS":
                    studentCode = scanner.nextInt();
                    name = scanner.next();

                    logicalAgent.editStudent(studentCode, name);

                    break;
                case "EDITC":
                    courseCode = scanner.nextInt();
                    name = scanner.next();

                    logicalAgent.editCourse(courseCode, name);

                    break;
                case "EDITG":
                    studentCode = scanner.nextInt();
                    courseCode = scanner.nextInt();
                    grade = scanner.nextDouble();

                    logicalAgent.editGrade(studentCode, courseCode, grade);

                    break;
                case "DELETES":
                    studentCode = scanner.nextInt();

                    logicalAgent.deleteStudent(studentCode);

                    break;

                case "DELETEC":
                    courseCode = scanner.nextInt();

                    logicalAgent.deleteCourse(courseCode);

                    break;

                case "DELETEG":
                    studentCode = scanner.nextInt();
                    courseCode = scanner.nextInt();

                    logicalAgent.deleteGrade(studentCode, courseCode);

                    break;

                case "NUMBERC":
                    studentCode = scanner.nextInt();

                    System.out.println(logicalAgent.numberOfCourses(studentCode));

                    break;

                case "NUMBERS":
                    courseCode = scanner.nextInt();

                    System.out.println(logicalAgent.numberOfStudents(courseCode));

                    break;

                case "SEARCHSN":
                    name = scanner.next();

                    logicalAgent.printStudentInfoByName(name);

                    break;

                case "SEARCHCN":
                    name = scanner.next();

                    logicalAgent.printCourseInfoByName(name);

                    break;

                case "SEARCHSC":
                    studentCode = scanner.nextInt();

                    logicalAgent.printStudentInfoAndHashByCode(studentCode);

                    break;

                case "SEARCHCC":
                    courseCode = scanner.nextInt();

                    logicalAgent.printCourseInfoAndHashByCode(courseCode);

                    break;

                case "ISRELATIVE":
                    courseCode = scanner.nextInt();
                    int courseCode1 = scanner.nextInt();

                    System.out.println(logicalAgent.isRelated(courseCode, courseCode1));

                    break;

                default:
                    System.out.println("INVALID INPUT!!!");
                    break;
            }
            loopRepetition++;
        }
    }
}