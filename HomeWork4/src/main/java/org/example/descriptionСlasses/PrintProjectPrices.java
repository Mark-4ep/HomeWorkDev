package org.example.descriptionСlasses;

public class PrintProjectPrices {
    private int projectId;
    private int price;

    public PrintProjectPrices(int projectId, int price) {
        this.projectId = projectId;
        this.price = price;
    }

    public int getProjectId() {
        return projectId;
    }

    public int getPrice() {
        return price;
    }
}
