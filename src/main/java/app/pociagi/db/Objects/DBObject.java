package app.pociagi.db.Objects;

import app.pociagi.db.Utils.DatabaseHandler;

import java.util.HashMap;

public class DBObject {
    private final DatabaseHandler handler = DatabaseHandler.getInstance();

    private final Integer object_id;

    public HashMap<String, Object> data;
    public String table;

    public DBObject() {object_id = null;}
    public DBObject(Integer id) {
        object_id = id;
    }

    public Integer getID() {
        return object_id;
    }

    public void pushToDB() {
        String columnNames = "";
        for (String key : data.keySet()) {
            columnNames = String.format("%s, %s", columnNames, key);
        }
        columnNames = columnNames.substring(2);

        String values = "";
        for (String key : data.keySet()) {
            if (data.get(key) instanceof Integer) {
                values = String.format("%s, %d", values, (Integer)data.get(key));
            } else values = String.format("%s, '%s'", values, data.get(key));
        }
        values = values.substring(2);
        String sql = String.format("INSERT INTO %s (%s) VALUES (%s)", table, columnNames, values);
        System.out.println(columnNames);
        System.out.println(values);
        System.out.println(sql);
        handler.executeQuery(sql);
    }

    public void removeFromDB(String table, HashMap<String, Object> data) {

    }
}
