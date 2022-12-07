package app.pociagi.db.Utils;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Finder {
    public static String find(String table, String column, String whereColumn, String whereValue) throws SQLException {
        DatabaseHandler handler = DatabaseHandler.getInstance();
        String sql = String.format("SELECT %s FROM %s WHERE %s = '%s'",
                column, table, whereColumn, whereValue);
        System.out.println(sql);
        ResultSet rs = handler.executeQuery(sql);
        ArrayList<String> arr = handler.returnDataArray(rs, 1);
        if (arr.size() == 1) return arr.get(0);
        else throw new SQLException("Returned too much data!");
    }
}
