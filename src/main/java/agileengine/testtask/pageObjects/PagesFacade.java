package agileengine.testtask.pageObjects;

public enum PagesFacade {
    INSTANCE;

    public TallestBuildingsWikiPage wikiPage() {
        return new TallestBuildingsWikiPage();
    }
}
