package com.olpbhtb.androidapp;

import java.util.List;

public class Hymn {
    public final HymnInfo Info;
    public final List<Stanza> Stanzas;

    @Override
    public String toString() {
        String out = "\n";

        for (Stanza s: Stanzas) {
            out += s.toString();
            out += "\n\n";
        }

        return out;

    }

    public Hymn(HymnInfo info,
                List<Stanza> stanzas) {
        this.Info = info;
        this.Stanzas = stanzas;
    }
}
