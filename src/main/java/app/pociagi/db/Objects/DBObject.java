package app.pociagi.db.Objects;

import app.pociagi.db.Utils.DatabaseHandler;

import java.util.HashMap;

/**
 * <h2> DBObject </h2>
 * Representation of data from a row from any database table.
 * Database Object is base class for all database objects
 * <p></p>
 * <b>Note:</b> Object is created (by constructors) only locally independently of Database.
 * It can get data from Database but it does not have to. To push object to Database use:
 * object.pushToDB();
 * <p></p>
 * Objects are immutable (only change of nullable attributes is possible)
 * @author rafal
 * @since 2022-12-07
 */
public class DBObject {
    private final DatabaseHandler handler = DatabaseHandler.getInstance();

    private final Integer object_id;

    public HashMap<String, Object> data;
    public String table;

    public DBObject() {object_id = null;}
    public DBObject(Integer id) {
        object_id = id;
    }

    /**
     * <h2> get ID </h2>
     * Every object, that its table has unique id column, should have unique id
     * <p></p>
     * @return ID of object in their table or null if no id in the table
     * Objects are immutable (only change of nullable attributes is possible)
     * @author rafal
     * @since 2022-12-07
     */
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
