package org.example.descriptionСlasses;

public class LongestProject {
    private int project_id ;
    private int monthCount;


    public LongestProject(int project_id, int monthCount) {
        this.monthCount = monthCount;
        this.project_id = project_id;
    }


    public int getProject_id() {
        return project_id;
    }

    public int getMonthCount() {
        return monthCount;
    }
}
