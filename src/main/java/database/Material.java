package database;

import java.util.ArrayList;

public class Material {
    private Integer id;
    private String name;
    private Float unitPrice;

    public Material(Integer id, String name, Float unitPrice) {
        this.id = id;
        this.name = name;
        this.unitPrice = unitPrice;

    }

    public static boolean create(Material material){
        return Database.getInstance().createMaterial(material);
    }

    public static ArrayList getAll() {
        return Database.getInstance().getMaterials();
    }

    public static boolean delete(Material material){
        return Database.getInstance().deleteMaterial(material);
    }

    public boolean save(){
        return Database.getInstance().updateMaterial(this);
    }

    public ArrayList getFoods() {
        return Database.getInstance().getFoods(this);
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Float getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(Float unitPrice) {
        this.unitPrice = unitPrice;
    }
}
