package com.amirbahadoramiri.androidlearning.views.solid.DependencyInversion;

public class Chatter {

    private Database database;

    public void setDatabase(Database database) {
        this.database = database;
    }

    public void insert() {
        database.insert();
    }

    public void read() {
        database.read();
    }

    public void update() {
        database.update();
    }
}
