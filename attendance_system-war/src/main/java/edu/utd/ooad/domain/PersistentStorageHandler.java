package edu.utd.ooad.domain;

import org.apache.commons.io.FilenameUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Author: Jason Huang (yetianhuang.cs@gmail.com)
 */
public class PersistentStorageHandler {
    private static final String DATA_STORE_PATH = "/data/";
    static List<String> classIds = new ArrayList<String>();
    static Map<String, List<Date>> attendances = new HashMap<String, List<Date>>();
    static final String DATE_FORMAT = "yyyy_mm_dd";

    public static ClassRoster queryClassRoster(String classId) {
        reload();
        List<Student> allStudents = readRosterCSV(classId);
        ClassRoster classRoster = new ClassRoster();
        classRoster.students = allStudents;
        return classRoster;
    }

    public static List<AttendanceRecord> queryAttendanceRecords(String classId) {
        reload();
        List<AttendanceRecord> recs = new ArrayList<AttendanceRecord>();
        for (Date date : attendances.get(classId)) {
            List<Student> presentStudents = readAttendStudentNamesCSV(classId, date);
            List<Student> allStudents = readRosterCSV(classId);
            AttendanceRecord rec = new AttendanceRecord(classId, date);
            for (Student stu : allStudents) {
                if (!presentStudents.contains(stu)) {
                    rec.addMissingStudent(stu);
                }
            }
            recs.add(rec);
        }
        return recs;
    }

    public static AttendanceRecord queryAttendanceRecord(String classId, Date date) {
        reload();
        List<Student> missingStudents = readAttendStudentNamesCSV(classId, date);
        List<Student> allStudents = readRosterCSV(classId);
        AttendanceRecord rec = new AttendanceRecord(classId, date);
        for (Student stu : allStudents) {
            if (missingStudents.contains(stu)) {
                rec.addMissingStudent(stu);
            }
        }
        return rec;
    }

    public static void writeAttendanceRecord(AttendanceRecord record) {
        // dummy.
    }

    static void reload() {
        URI dirURL = null;
        try {
            dirURL = PersistentStorageHandler.class.getResource(DATA_STORE_PATH).toURI();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        File dir = new File(dirURL);
        if (!dir.exists()) {
            return;
        }
        File[] files = dir.listFiles();
        for (File file : files) {
            if (file.isFile() && FilenameUtils.isExtension(file.getName(), "csv")) {
                String nameWithoutExt = FilenameUtils.removeExtension(file.getName());
                if (nameWithoutExt.startsWith("ClassRoster-")) {
                    String classId = nameWithoutExt.split("-")[1];
                    if (!classIds.contains(classId)) {
                        classIds.add(classId);
                    }
                } else if (nameWithoutExt.startsWith("attendRec")) {
                    String[] temp = nameWithoutExt.split("-");
                    String classId = temp[0].replace("attendRec", "");
                    Date date;
                    try {
                        date = new SimpleDateFormat(DATE_FORMAT).parse(temp[1]);
                    } catch (ParseException e) {
                        System.err.println(e.toString());
                        return;
                    }
                    if (null == date) {
                        return;
                    }
                    if (attendances.containsKey(classId)) {
                        if (!attendances.get(classId).contains(date)) {
                            attendances.get(classId).add(date);
                        }
                    } else {
                        List<Date> dates = new ArrayList<Date>();
                        dates.add(date);
                        attendances.put(classId, dates);
                    }
                }
            }
        }
    }

    private static List<Student> readAttendStudentNamesCSV(String classId, Date date) {
        String csvFile = DATA_STORE_PATH + "attendRec" + classId
                + "-" + new SimpleDateFormat(DATE_FORMAT).format(date) + ".csv";
        List<Student> lists = new ArrayList<Student>();
        List objs = readCSV(csvFile);
        for (int i = 2; i < objs.size(); i++) {
            Student stu = new Student();
            stu.name = (String) objs.get(i);
            lists.add(stu);
        }
        return lists;
    }

    private static List<Student> readRosterCSV(String classId) {
        String csvFile = DATA_STORE_PATH + "ClassRoster-" + classId + ".csv";
        List<Student> lists = new ArrayList<Student>();
        List objs = readCSV(csvFile);
        for (Object obj : objs) {
            Student stu = new Student();
            stu.name = (String) obj;
            lists.add(stu);
        }
        return lists;
    }

    private static List readCSV(String csvFile) {
        String[] lists = null;
        BufferedReader br = null;
        String line = "";
        String cvsSplitBy = ",";

        try {
            br = new BufferedReader(new InputStreamReader(PersistentStorageHandler.class.getResourceAsStream(csvFile)));
            while ((line = br.readLine()) != null) {
                // use comma as separator
                lists = line.split(cvsSplitBy);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return Arrays.asList(lists);
    }
}
