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
        var comparing = switch (header) {
            case NAME -> Comparator.comparing(SkyscraperUIModel::getName);
            case CITY -> Comparator.comparing(SkyscraperUIModel::getCity);
            case COUNTRY -> Comparator.comparing(SkyscraperUIModel::getCountry);
            case HEIGHT_IN_METERS -> Comparator.comparing(SkyscraperUIModel::getHeightInMetersAsDouble);
            case HEIGHT_IN_FT -> Comparator.comparing(SkyscraperUIModel::getHeightInFtAsInt);
            case FLOORS -> Comparator.comparing(SkyscraperUIModel::getFloorsAsInt);
            case YEAR -> Comparator.comparing(SkyscraperUIModel::getYearAsInt);
            default-> Comparator.comparing(SkyscraperUIModel::getRankAsInt);
        };

        return order.equals(SortOrder.asc) ?
                skyscrapers.stream().sorted(comparing).collect(Collectors.toList()) :
                skyscrapers.stream().sorted(comparing.reversed()).collect(Collectors.toList());
    }
}
