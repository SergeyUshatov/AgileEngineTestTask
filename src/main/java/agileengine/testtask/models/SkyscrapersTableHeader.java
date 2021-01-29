package agileengine.testtask.models;

public enum SkyscrapersTableHeader {
    RANK(1, "Rank"),
    NAME(2, "Name"),
    CITY(4, "City"),
    COUNTRY(5, "Country"),
    HEIGHT_IN_METERS(6, "m"),
    HEIGHT_IN_FT(7, "ft"),
    FLOORS(8, "Floors"),
    YEAR(9, "Year"),
    NOTES(10, "Notes");

    private int index;
    private String columnName;

    SkyscrapersTableHeader(int index, String name) {
        this.index = index;
        this.columnName = name;
    }

    public int getIndex() {
        return index;
    }

    public String getName() {
        return columnName;
    }
}
