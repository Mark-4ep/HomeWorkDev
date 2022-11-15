package org.example.descriptionСlasses;

public class MaxProjectsClient {
    private String name;
    private int projectCount;

    public MaxProjectsClient(String name, int project_count) {
        this.name = name;
        this.projectCount = project_count;
    }

    public String getName() {
        return name;
    }

    public int getProjectCount() {
        return projectCount;
    }
}
