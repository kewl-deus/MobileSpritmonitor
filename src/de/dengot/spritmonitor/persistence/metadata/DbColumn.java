package de.dengot.spritmonitor.persistence.metadata;


public class DbColumn {

    private String name;
    private String type;

    public static DbColumn column(String name, String type) {
        return new DbColumn(name, type);
    }

    public DbColumn(String name, String type) {
        this.name = name;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public String toDDL() {
        return getName() + " " + getType();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DbColumn dbColumn = (DbColumn) o;

        if (name != null ? !name.equals(dbColumn.name) : dbColumn.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return name != null ? name.hashCode() : 0;
    }
}
