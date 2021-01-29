package agileengine.testtask.tests;

import agileengine.testtask.pageObjects.PagesFacade;
import agileengine.testtask.utils.DriverFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public abstract class BaseTest {
    protected PagesFacade pages = PagesFacade.INSTANCE;

    @BeforeMethod
    public void beforeMethod() {
        DriverFactory.INSTANCE.startWebDriver();
    }

    @AfterMethod
    public void afterMethod(){
        DriverFactory.INSTANCE.stopDriver();
    }
}
