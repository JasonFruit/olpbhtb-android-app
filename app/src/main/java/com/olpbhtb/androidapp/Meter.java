package com.olpbhtb.androidapp;

public class Meter implements NamedIdable {
    public int Id;
    public String Description;

    public NamedId toNamedId() {
        return new NamedId(Id, Description);
    }

    public Meter(int id, String dsc) {
        this.Id = id;
        this.Description = dsc;
    }
}
