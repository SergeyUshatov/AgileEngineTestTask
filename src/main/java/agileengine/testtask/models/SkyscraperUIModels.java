package agileengine.testtask.models;

import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

public class SkyscraperUIModels {
    private List<SkyscraperUIModel> skyscrapers;

    public SkyscraperUIModels(List<SkyscraperUIModel> skyscraperUIModels) {
        this.skyscrapers = skyscraperUIModels;
    }

    public List<SkyscraperUIModel> getAll() {
        return skyscrapers;
    }

    public SkyscraperUIModel findOldestSkyscraper() {
        return skyscrapers.stream()
                .min(Comparator.comparing(SkyscraperUIModel::getYearAsInt))
                .orElseThrow(NoSuchElementException::new);
    }

    public String getCountryWithBiggestAmountOfSkyscrapers() {
        return skyscrapers.stream()
                .collect(Collectors.groupingBy(SkyscraperUIModel::getCountry))
                .entrySet()
                .stream()
                .max(Comparator.comparing(e -> e.getValue().size()))
                .orElseThrow(NoSuchElementException::new)
                .getKey()
                .trim();
    }

    public List<SkyscraperUIModel> sortBy(SkyscrapersTableHeader header, SortOrder order) {
        Comparator<SkyscraperUIModel> comparing;
        switch (header) {
            case NAME : comparing = Comparator.comparing(SkyscraperUIModel::getName); break;
            case CITY: comparing = Comparator.comparing(SkyscraperUIModel::getCity); break;
            case COUNTRY: comparing = Comparator.comparing(SkyscraperUIModel::getCountry); break;
            case HEIGHT_IN_METERS: comparing = Comparator.comparing(SkyscraperUIModel::getHeightInMetersAsDouble); break;
            case HEIGHT_IN_FT: comparing = Comparator.comparing(SkyscraperUIModel::getHeightInFtAsInt); break;
            case FLOORS: comparing = Comparator.comparing(SkyscraperUIModel::getFloorsAsInt); break;
            case YEAR: comparing = Comparator.comparing(SkyscraperUIModel::getYearAsInt); break;
            default: comparing = Comparator.comparing(SkyscraperUIModel::getRankAsInt); break;
        }

        return order.equals(SortOrder.asc) ?
                skyscrapers.stream().sorted(comparing).collect(Collectors.toList()) :
                skyscrapers.stream().sorted(comparing.reversed()).collect(Collectors.toList());
    }
}
