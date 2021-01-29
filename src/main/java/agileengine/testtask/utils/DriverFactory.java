package agileengine.testtask.utils;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;
import org.openqa.selenium.MutableCapabilities;

public enum DriverFactory {
    INSTANCE;
    private static final PropertyLoader PROPERTY_LOADER = PropertyLoader.INSTANCE;

    public void startWebDriver() {
        Configuration.timeout = 30;
        Configuration.browser = PROPERTY_LOADER.getBrowser();
        Configuration.browserVersion = PROPERTY_LOADER.getBrowserVersion();
        MutableCapabilities browserCapabilities = new MutableCapabilities();
        String runEnv = PROPERTY_LOADER.getRunEnv();
        switch (runEnv) {
            case "remote":
                browserCapabilities.setCapability("enableVNC", true);
                browserCapabilities.setCapability("enableVideo", false);
                Configuration.remote = PROPERTY_LOADER.getSeleniumGridHubUrl();
                break;

            default:
                break;
        }
        Configuration.browserCapabilities = browserCapabilities;
//        Configuration.startMaximized = true;
    }

    public void stopDriver() {
        WebDriverRunner.closeWebDriver();
    }
}
