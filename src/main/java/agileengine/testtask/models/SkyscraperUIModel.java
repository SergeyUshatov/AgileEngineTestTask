package agileengine.testtask.models;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.Objects;

@Getter
@Builder
@ToString
public class SkyscraperUIModel {
    private String name;
    private String rank;
    private String city;
    private String image;
    private String country;
    private String heightInMeters;
    private String heightInFt;
    private String floors;
    private String year;
    private String notes;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SkyscraperUIModel)) return false;
        SkyscraperUIModel that = (SkyscraperUIModel) o;
        return name.equals(that.name)
                && rank.equals(that.rank)
                && city.equals(that.city)
                && country.equals(that.country)
                && heightInMeters.equals(that.heightInMeters)
                && heightInFt.equals(that.heightInFt)
                && floors.equals(that.floors)
                && year.equals(that.year)
                && notes.equals(that.notes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, rank, city, country, heightInMeters, heightInFt, floors, year, notes);
    }

    public  int getYearAsInt() {
        return Integer.parseInt(year);
    }

    public  int getRankAsInt() {
        return Integer.parseInt(rank);
    }

    public  int getFloorsAsInt() {
        return Integer.parseInt(floors);
    }

    public double getHeightInMetersAsDouble() {
        return Double.parseDouble(heightInMeters);
    }

    public int getHeightInFtAsInt() {
        return Integer.parseInt(heightInFt.replaceAll(",", ""));
    }
}
