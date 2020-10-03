package com.olpbhtb.androidapp;

public class HymnInfo {
    public final int Id;
    public final int OlpbhtbNum;
    public final Meter Meter;
    public final HymnAuthor Author;
    public final Category Category;
    public final String TuneName;
    public final String FirstLine;

    @Override
    public String toString() {
        String out = String.valueOf(this.OlpbhtbNum);
        out += " (" + Meter.Description + ") ";
        out += Author.Name;
        return out;
    }

    public String imageFilename() {
        return this.TuneName.replace(" ", "-").toLowerCase() + ".gif";
    }

    public HymnInfo(int id,
                    int olpbhtbNum,
                    Meter meter,
                    HymnAuthor author,
                    Category cat,
                    String tuneName,
                    String firstLine) {
        this.Id = id;
        this.OlpbhtbNum = olpbhtbNum;
        this.Meter = meter;
        this.Author = author;
        this.Category = cat;
        this.TuneName = tuneName;
        this.FirstLine = firstLine;
    }
}
