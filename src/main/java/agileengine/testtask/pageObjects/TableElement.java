package agileengine.testtask.pageObjects;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.NoSuchElementException;

import java.util.List;

public class TableElement {
    private SelenideElement table;
    public TableElement(SelenideElement element) {
        table = element;
    }

    private ElementsCollection getHeaders() {
        return table.$("thead").$$("th");
    }

    public SelenideElement getHeaderWithTitle(String title) {
        return getHeaders().stream()
                .filter(it -> it.text().equals(title))
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("Unable to find table header with title: " + title));
    }

    public SelenideElement getTableBody() {
        return table.$("tbody");
    }

    private ElementsCollection getColumnElements(int headerIndex) {
        return getTableBody().$$x(".//td[" + headerIndex + "]");
    }

    public List<String> getColumnElementsTexts(int headerIndex) {
        return getColumnElements(headerIndex).texts();
    }
}
