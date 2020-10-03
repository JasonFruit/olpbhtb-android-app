package com.olpbhtb.androidapp;

public class Category implements NamedIdable {
    public int Id;
    public String Name;

    public NamedId toNamedId() {
        return new NamedId(Id, Name);
    }

    public Category(int id, String name) {
        this.Id = id;
        this.Name = name;
    }
}
