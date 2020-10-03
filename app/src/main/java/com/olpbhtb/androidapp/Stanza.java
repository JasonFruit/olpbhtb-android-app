package com.olpbhtb.androidapp;

import java.util.List;

public class Stanza {
    public final int Number;
    public final List<String> Lines;

    @Override
    public String toString() {
        String out = "";

        for (String line: this.Lines) {
            out += line;
            out += "\n";
        }

        return out;
    }

    public Stanza(int num, List<String> lines) {
        this.Number = num;
        this.Lines = lines;
    }
}
