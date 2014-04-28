package edu.utd.ooad.controller;

import edu.utd.ooad.domain.AttendanceRecord;
import edu.utd.ooad.domain.ClassRoster;
import edu.utd.ooad.domain.Professor;
import edu.utd.ooad.domain.Student;
import edu.utd.ooad.model.SearchResult;
import edu.utd.ooad.model.Selection;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.support.SessionStatus;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Author: Jason Huang (yetianhuang.cs@gmail.com)
 */
@Controller
@RequestMapping("/")
public class DropDownBoxController {

    @RequestMapping(method = RequestMethod.GET)
    public String printWelcome(ModelMap model) {
        model.addAttribute("classAndDateForm", new Selection());
        return "index";
    }

    @RequestMapping(value = "/search", method = RequestMethod.POST,
        produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    protected SearchResult processSearch(@RequestBody Selection selection) throws Exception {
        Professor prof = new Professor();
        ClassRoster classRoster = prof.getClassRoster(selection.getClassId());
        String date = selection.getDate().split("-")[1];
        AttendanceRecord rec = prof.getDayAttendanceRecord(selection.getClassId(),
                new SimpleDateFormat("yyyy_mm_dd").parse(date));

        // push class roster.
        SearchResult resp = new SearchResult();
        List<String> names = new ArrayList<String>();
        for (Student stu : classRoster.getStudents()) {
            names.add(stu.name);
        }
        resp.setAllStudents(names);

        // push missing students;
        List<String> miss = new ArrayList<String>();
        for (Student stu : rec.getMissingStudents()) {
            miss.add(stu.name);
        }
        resp.setMissingStudents(miss);

        return resp;
    }

    @ModelAttribute("selectionList")
    protected Map populateClassIdsAndDates() throws Exception {

        Map selectionList = new HashMap();

        Map<String, String> classIdMap = new LinkedHashMap<String, String>();
        classIdMap.put("001", "001");
        classIdMap.put("002", "002");
        selectionList.put("classIds", classIdMap);

        Map<String, String> dateMap = new LinkedHashMap<String, String>();
        dateMap.put("001-2014_02_11", "001-2014_02_11");
        dateMap.put("001-2014_04_16", "001-2014_04_16");
        dateMap.put("002-2014_03_26", "002-2014_03_26");
        dateMap.put("002-2014_05_12", "002-2014_05_12");
        selectionList.put("dates", dateMap);

        return selectionList;
    }
}
