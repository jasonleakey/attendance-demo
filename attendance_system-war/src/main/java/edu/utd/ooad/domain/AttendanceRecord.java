package edu.utd.ooad.domain;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Author: Jason Huang (yetianhuang.cs@gmail.com)
 */
public class AttendanceRecord {
    private List<Student> missingStudents = new ArrayList<Student>();
    private Date date;
    private String classId;

    public AttendanceRecord(String classId, Date date) {
        this.classId = classId;
        this.date = date;
    }

    public void addMissingStudent(Student student) {
        if (!missingStudents.contains(student)) {
            missingStudents.add(student);
        }
    }

    public void delMissingStudent(Student student) {
        if (missingStudents.contains(student)) {
            missingStudents.remove(student);
        }
    }

    public List<Student> getMissingStudents() {
        return missingStudents;
    }

    public static List<AttendanceRecord> queryAttendanceRecords(String classId) {
        return PersistentStorageHandler.queryAttendanceRecords(classId);
    }

    public static AttendanceRecord queryAttendanceRecord(String classId, Date date) {
        return PersistentStorageHandler.queryAttendanceRecord(classId, date);
    }

    public void save() {
        // dummy.
    }

    @Override
    public String toString() {
        StringBuilder buf = new StringBuilder();
        buf.append("[Class: " + classId + "; ");
        buf.append("Date: " +
                new SimpleDateFormat(PersistentStorageHandler.DATE_FORMAT).format(date) + "; ");
        buf.append("Missing students: [");
        for (Student stu : missingStudents) {
            buf.append(stu.name + ",");
        }
        buf.append("]");
        buf.append("]");
        return buf.toString();
    }
}
