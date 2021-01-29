package agileengine.testtask.pageObjects;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import agileengine.testtask.models.SkyscraperUIModel;
import agileengine.testtask.models.SkyscraperUIModels;
import agileengine.testtask.models.SkyscrapersTableHeader;
import org.openqa.selenium.By;
import agileengine.testtask.utils.PropertyLoader;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static com.codeborne.selenide.Selenide.$$;
import static agileengine.testtask.models.SkyscrapersTableHeader.*;

public class TallestBuildingsWikiPage {

    private ElementsCollection allTables = $$(By.className("wikitable"));

    public TallestBuildingsWikiPage open() {
        Selenide.open(PropertyLoader.INSTANCE.getWikiUrl());
        allTables.shouldBe(CollectionCondition.sizeGreaterThanOrEqual(1));
        return this;
    }

    private TableElement getTallestBuildingsTable() {
        var table = allTables.get(1);
        return new TableElement(table);
    }

    public SkyscraperUIModels getListOfSkyscrapers() {
        var tallestBuildingsTable = getTallestBuildingsTable();
        var ranks = tallestBuildingsTable.getColumnElementsTexts(RANK.getIndex());
        var names = tallestBuildingsTable.getColumnElementsTexts(NAME.getIndex());
        var cities = tallestBuildingsTable.getColumnElementsTexts(CITY.getIndex());
        var countries = tallestBuildingsTable.getColumnElementsTexts(COUNTRY.getIndex());
        var heightsInMeters = tallestBuildingsTable.getColumnElementsTexts(HEIGHT_IN_METERS.getIndex());
        var heightsInFt = tallestBuildingsTable.getColumnElementsTexts(HEIGHT_IN_FT.getIndex());
        var floors = tallestBuildingsTable.getColumnElementsTexts(FLOORS.getIndex());
        var years = tallestBuildingsTable.getColumnElementsTexts(YEAR.getIndex());
        var notes = tallestBuildingsTable.getColumnElementsTexts(NOTES.getIndex());
        var skyscraperUIModels = IntStream.range(0, names.size())
                .mapToObj(index -> SkyscraperUIModel.builder()
                        .rank(ranks.get(index))
                        .name(names.get(index))
                        .city(cities.get(index))
                        .country(countries.get(index))
                        .heightInMeters(heightsInMeters.get(index))
                        .heightInFt(heightsInFt.get(index))
                        .floors(floors.get(index))
                        .year(years.get(index))
                        .notes(notes.get(index))
                        .build()
                ).collect(Collectors.toList());
        return new SkyscraperUIModels(skyscraperUIModels);
    }

    public void clickOnHeader(SkyscrapersTableHeader header) {
        SelenideElement headerWithTitle = getTallestBuildingsTable().getHeaderWithTitle(header.getName());
        headerWithTitle.click();
    }
}
