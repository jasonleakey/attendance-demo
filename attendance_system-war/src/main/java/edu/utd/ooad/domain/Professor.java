package edu.utd.ooad.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Author: Jason Huang (yetianhuang.cs@gmail.com)
 */
public class Professor extends Person {
    private List<Clazz> clazzs;

    public Professor() {
        init();
    }

    public void createRecord(String classId, Date date) {
        // dummy
    }

    public AttendanceRecord getDayAttendanceRecord(String classId, Date date) {
        return AttendanceRecord.queryAttendanceRecord(classId, date);
    }

    public List<AttendanceRecord> getAllAttendanceRecords(String classId) {
        return AttendanceRecord.queryAttendanceRecords(classId);
    }

    public ClassRoster getClassRoster(String classId) {
        return Clazz.queryClassRoster(classId);
    }

    public void init() {
        PersistentStorageHandler.reload();
        clazzs = new ArrayList<Clazz>();
        for (String classId : PersistentStorageHandler.classIds) {
            Clazz clazz = new Clazz(classId);
            clazzs.add(clazz);
        }
    }

    public List<Clazz> getMyClasses() {
        return clazzs;
    }
}
