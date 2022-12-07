package app.pociagi.db_classes_singletons;

import java.util.HashMap;

public class Discount extends DBObject{
    private String name;
    private String value;
    public Discount(Integer id, String name, String value) {
        super(id);
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public String getValue() {
        return value;
    }

    @Override
    public void pushToDB() {
        HashMap<String, Object> dict = new HashMap<>();
        dict.put("ID", this.getID());
        dict.put("NAME", this.name);
        dict.put("VALUE", this.value);
        super.data = dict;
        super.table = "DISCOUNTS";
        super.pushToDB();
    }
}
