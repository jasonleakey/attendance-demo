package edu.utd.ooad.model;

import java.util.List;

/**
 * Author: Jason Huang (yetianhuang.cs@gmail.com)
 */
public class SearchResult {
    List<String> allStudents;
    List<String> missingStudents;

    public List<String> getAllStudents() {
        return allStudents;
    }

    public void setAllStudents(List<String> allStudents) {
        this.allStudents = allStudents;
    }

    public List<String> getMissingStudents() {
        return missingStudents;
    }

    public void setMissingStudents(List<String> missingStudents) {
        this.missingStudents = missingStudents;
    }
}
