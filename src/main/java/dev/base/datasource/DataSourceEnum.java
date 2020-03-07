package dev.base.datasource;

public enum DataSourceEnum {
    MASTER("master"),
    SLAVE("slave");
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

     DataSourceEnum(String name) {
        this.name = name;
    }
}
