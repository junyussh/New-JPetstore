package org.csu.jpetstore.bean;

public class Category {
    private Integer id;

    public Integer getCategoryId() {
        return id;
    }

    public void setCategoryId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    private String name;
    private String description;
}
