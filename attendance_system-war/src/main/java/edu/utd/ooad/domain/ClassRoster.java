package edu.utd.ooad.domain;

import java.util.List;

/**
 * Author: Jason Huang (yetianhuang.cs@gmail.com)
 */
public class ClassRoster {
    List<Student> students;

    public List<Student> getStudents() {
        return students;
    }

    @Override
    public String toString() {
        StringBuilder buf = new StringBuilder();
        buf.append("[");
        for (Student stu : students) {
            buf.append(stu.name + ", ");
        }
        buf.append("]");
        return buf.toString();
    }
}
