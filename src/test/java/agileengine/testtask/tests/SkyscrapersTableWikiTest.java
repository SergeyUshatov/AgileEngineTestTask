package agileengine.testtask.tests;

import agileengine.testtask.models.skyscraper.SkyscraperUIModels;
import agileengine.testtask.models.skyscraper.SkyscrapersTableHeader;
import agileengine.testtask.models.common.SortOrder;
import agileengine.testtask.pageObjects.TallestBuildingsWikiPage;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class SkyscrapersTableWikiTest extends BaseTest {
    private TallestBuildingsWikiPage wikiPage;

    @BeforeMethod
    private void init() {
        wikiPage = pages.wikiPage().open();
    }

    @Test
    public void oldestBuildingIsEmpireStateBuilding(){
        wikiPage.clickOnHeader(SkyscrapersTableHeader.RANK);
        var skyscrapers = wikiPage.getListOfSkyscrapers();
        var oldestBuilding = skyscrapers.findOldestSkyscraper();

        assertThat(oldestBuilding.getName(), equalTo("Empire State Building"));
    }

    @Test
    public void chinaHasBiggestAmountOfSkyscrapers(){
        wikiPage.clickOnHeader(SkyscrapersTableHeader.RANK);
        var skyscrapers = wikiPage.getListOfSkyscrapers();
        var country = skyscrapers.getCountryWithBiggestAmountOfSkyscrapers();

        assertThat(country, equalTo("China"));
    }

    @Test(dataProvider = "sortByField")
    public void tableCanBeSortedBy(SkyscrapersTableHeader header){
        wikiPage.clickOnHeader(header);
        SkyscraperUIModels skyscrapers = wikiPage.getListOfSkyscrapers();
        var actualSkyscrapersSortedAsc = skyscrapers.getAll();

        wikiPage.clickOnHeader(header);
        var actualSkyscrapersSortedDesc = wikiPage.getListOfSkyscrapers().getAll();

        var expectedListSortedAsc = skyscrapers.sortBy(header, SortOrder.asc);
        var expectedListSortedDesc = skyscrapers.sortBy(header, SortOrder.desc);

        assertThat(actualSkyscrapersSortedAsc, equalTo(expectedListSortedAsc));
        assertThat(actualSkyscrapersSortedDesc, equalTo(expectedListSortedDesc));
    }

    @DataProvider(name = "sortByField")
    public Object[][] createData1() {
        return new Object[][] {
                { SkyscrapersTableHeader.RANK },
                { SkyscrapersTableHeader.NAME },
                { SkyscrapersTableHeader.CITY },
                { SkyscrapersTableHeader.COUNTRY },
                { SkyscrapersTableHeader.HEIGHT_IN_METERS },
                { SkyscrapersTableHeader.HEIGHT_IN_FT },
                { SkyscrapersTableHeader.FLOORS },
                { SkyscrapersTableHeader.YEAR },
        };
    }

}
