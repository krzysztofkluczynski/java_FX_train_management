package app.pociagi.db_classes_singletons;

import app.pociagi.utils.DatabaseHandler;
import javafx.scene.chart.PieChart;

import java.util.HashMap;
import java.util.Objects;

public class DBObject {
    private DatabaseHandler handler = DatabaseHandler.getInstance();

    private static Integer object_id;

    public HashMap<String, Object> data;
    public String table;
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
                values = String.format("%s, %d", values, data.get(key));
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
