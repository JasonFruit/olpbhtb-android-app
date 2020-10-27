package com.olpbhtb.androidapp;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DatabaseAccess {
    private SQLiteOpenHelper openHelper;
    private SQLiteDatabase database;
    private static DatabaseAccess instance;
    private static String hymnInfoSelectStart = "select h.id, " +
            "olpbhtb_number, " +
            "meter_id, " +
            "m.description as meter_dsc, " +
            "author_id, " +
            "a.name as author_name, " +
            "category_id, " +
            "c.name as category_name, " +
            "t.name as tune_name, " +
            "fl.first_line " +
            "from hymn h " +
            "inner join meter m " +
            "on h.meter_id = m.id " +
            "inner join author a " +
            "on h.author_id = a.id " +
            "inner join category c " +
            "on h.category_id = c.id " +
            "inner join first_line fl " +
            "on h.id = fl.id " +
            "inner join tune t " +
            "on h.tune_id = t.id ";

    /**
     * Private constructor to aboid object creation from outside classes.
     *
     * @param context
     */
    public DatabaseAccess(Context context) {
        this.openHelper = new OlpbhtbDatabase(context);
    }

    /**
     * Return a singleton instance of DatabaseAccess.
     *
     * @param context the Context
     * @return the instance of DabaseAccess
     */
    public static DatabaseAccess getInstance(Context context) {
        if (instance == null) {
            instance = new DatabaseAccess(context);
        }
        return instance;
    }

    /**
     * Open the database connection.
     */
    public void open() {
        this.database = openHelper.getWritableDatabase();
    }

    /**
     * Close the database connection.
     */
    public void close() {
        if (database != null) {
            this.database.close();
        }
    }

    public List<HymnInfo> SearchForWords(String q) {
        for (Character c: "'\".,?!&-".toCharArray()) {
            q = q.replace(c, '%');
        }
        String[] words = q.split("\\s");
        List<String> queryWords = new ArrayList<>();

        String sql = hymnInfoSelectStart + "where 1 = 1 ";

        if (words.length == 0) {
            return new ArrayList<>();
        } else {
            for (String word: words) {
                sql += "and h.id in (select hymn_id from line where content like ?) ";
                queryWords.add("%" + word + "%");
            }
            sql += " order by sortable";
        }

        Cursor cur = database.rawQuery(sql, queryWords.toArray(new String[]{}));
        return this.hymnInfoList(cur);
    }

    public List<HymnInfo> SearchForPhrase(String q) {
        String cleanLineSql = "lower(replace(replace(replace(replace(replace(content, ',', ''), '-', ' '), ';', ''), '.', ''), ':', ''))";
        q = q.replace(",", "").replace(".", "").replace(";", "").replace("-", " ").replace(":", "").replace("%", "").toLowerCase();
        String sql = hymnInfoSelectStart + "where h.id in (select hymn_id from line where " + cleanLineSql + " like ?)";
        Cursor cur = database.rawQuery(sql, new String[]{"%" + q + "%"});
        return this.hymnInfoList(cur);
    }

    public List<Tune> getTunes() {
        List<Tune> tunes = new ArrayList<>();
        Cursor cur = database.rawQuery("select id, name from tune order by name", null);
        cur.moveToFirst();
        while (!cur.isAfterLast()) {
            tunes.add(new Tune(cur.getInt(0),
                    cur.getString(1)));
            cur.moveToNext();
        }
        cur.close();
        return tunes;
    }

    public List<HymnAuthor> getAuthors() {
        List<HymnAuthor> authors = new ArrayList<>();
        Cursor cur = database.rawQuery("select id, name from author order by name", null);
        cur.moveToFirst();
        while (!cur.isAfterLast()) {
            authors.add(new HymnAuthor( cur.getInt(0),
                                        cur.getString(1)));
            cur.moveToNext();
        }
        cur.close();
        return authors;
    }

    public List<Meter> getMeters() {
        List<Meter> meters = new ArrayList<>();
        Cursor cur = database.rawQuery("select m.id, description from meter m inner join hymn h on m.id = h.meter_id group by m.id, description order by count(h.id) desc", null);
        cur.moveToFirst();
        while (!cur.isAfterLast()) {
            meters.add(new Meter(cur.getInt(0), cur.getString(1)));
            cur.moveToNext();
        }
        cur.close();
        return meters;
    }

    public List<Category> getCategories() {
        List<Category> cats = new ArrayList<>();
        Cursor cur = database.rawQuery("select id, name from category order by id", null);
        cur.moveToFirst();
        while (!cur.isAfterLast()) {
            cats.add(new Category(cur.getInt(0), cur.getString(1)));
            cur.moveToNext();
        }
        cur.close();
        return cats;
    }

    private List<HymnInfo> hymnInfoList(Cursor cur) {
        List<HymnInfo> hymns = new ArrayList<>();

        cur.moveToFirst();

        while (!cur.isAfterLast()) {
            Meter m = new Meter(cur.getInt(2), cur.getString(3));
            HymnAuthor a = new HymnAuthor(cur.getInt(4), cur.getString(5));
            Category c = new Category(cur.getInt(6), cur.getString(7));
            HymnInfo hi = new HymnInfo( cur.getInt(0),
                    cur.getInt(1),
                    m,
                    a,
                    c,
                    cur.getString(8),
                    cur.getString(9));
            hymns.add(hi);
            cur.moveToNext();
        }

        return hymns;
    }

    public List<HymnInfo> getHymns(HymnAuthor author) {

        String[] args = new String[]{String.valueOf(author.Id)};
        Cursor cur = database.rawQuery(hymnInfoSelectStart + "where author_id = ? order by sortable", args);
        return hymnInfoList(cur);
    }

    public List<HymnInfo> getHymns(Meter meter) {
        String[] args = new String[]{String.valueOf(meter.Id)};
        Cursor cur = database.rawQuery(hymnInfoSelectStart + "where meter_id = ? order by sortable", args);
        return hymnInfoList(cur);
    }

    public List<HymnInfo> getHymns(Category cat) {
        String[] args = new String[]{String.valueOf(cat.Id)};
        Cursor cur = database.rawQuery(hymnInfoSelectStart + "where category_id = ? order by sortable", args);
        return hymnInfoList(cur);
    }

    public List<HymnInfo> getHymns(Tune t) {
        String[] args = new String[]{String.valueOf(t.Id)};
        Cursor cur = database.rawQuery(hymnInfoSelectStart + "where tune_id = ? order by sortable", args);
        return hymnInfoList(cur);
    }

    public List<HymnInfo> getHymns() {
        String[] args = new String[]{};
        Cursor cur = database.rawQuery(hymnInfoSelectStart + "order by sortable", args);
        return hymnInfoList(cur);
    }

    public Hymn getHymn(int id) {

        String[] args = new String[]{String.valueOf(id)};

        // get the header info
        Cursor cur = database.rawQuery(hymnInfoSelectStart + "where h.id = ?", args);
        HymnInfo info = hymnInfoList(cur).get(0); //there can only be one
        cur.close();

        // create hymn with empty stanzas
        Hymn out = new Hymn(info, new ArrayList<Stanza>());

        cur = database.rawQuery("select stanza_num, content from line where hymn_id = ? order by stanza_num, ordinal", args);
        cur.moveToFirst();

        // keep track of current stanza number to know when to add lines from accumulator as a single stanza
        int curStanza = 0;
        List<String> acc = new ArrayList<>(); // Java forces useless initialization

        while (!cur.isAfterLast()) {
            int num = cur.getInt(0);
            String line = cur.getString(1);

            if (num > curStanza) {
                // if there has been a stanza built, add it to the hymn
                if (curStanza > 0) {
                    out.Stanzas.add(new Stanza(curStanza, acc));
                }

                // start a new stanza
                acc = new ArrayList<>();
                curStanza = num;
            }

            acc.add(line);
            cur.moveToNext();
        }

        cur.close();

        if (curStanza > 0) {
            out.Stanzas.add(new Stanza(curStanza, acc));
        }

        return out;
    }

}