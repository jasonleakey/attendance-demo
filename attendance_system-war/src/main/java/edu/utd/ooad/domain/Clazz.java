package edu.utd.ooad.domain;

/**
 * Author: Jason Huang (yetianhuang.cs@gmail.com)
 */
public class Clazz {
    String classId;

    public Clazz() {

    }

    public Clazz(String classId) {
        this.classId = classId;
    }

    public static ClassRoster queryClassRoster(String classId) {
        return PersistentStorageHandler.queryClassRoster(classId);
    }

    public final String getClassId() {
        return classId;
    }
}
