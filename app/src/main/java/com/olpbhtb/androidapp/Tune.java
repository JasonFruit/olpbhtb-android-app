package com.olpbhtb.androidapp;

public class Tune implements NamedIdable {

    public int Id;
    public String Name;

    @Override
    public NamedId toNamedId() {
        return new NamedId(Id, Name);
    }

    public Tune(int id, String name) {
        this.Id = id;
        this.Name = name;
    }
}
