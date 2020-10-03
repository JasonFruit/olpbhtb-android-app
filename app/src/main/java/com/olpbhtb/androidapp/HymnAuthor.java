package com.olpbhtb.androidapp;

import java.util.jar.Attributes;

public class HymnAuthor implements NamedIdable {
    public String Name;
    public int Id;

    public NamedId toNamedId() {
        return new NamedId(Id, Name);
    }

    public HymnAuthor(int id, String name) {
        this.Id = id;
        this.Name = name;
    }
}
