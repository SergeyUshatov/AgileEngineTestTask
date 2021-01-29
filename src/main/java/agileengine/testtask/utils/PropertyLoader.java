package agileengine.testtask.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public enum PropertyLoader {
    INSTANCE;

    private Properties properties;

    PropertyLoader() {
        this.properties = getEnvConfig();
    }

    private Properties getEnvConfig() {
        Properties props = new Properties();
        String property = System.getProperty("propFile", "application.properties");
        if (property == null) {
            throw new RuntimeException("Missing property 'propFile'");
        }

        try(InputStream is = PropertyLoader.class.getClassLoader().getResourceAsStream(property)) {
            props.load(is);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return props;
    }

    public String getRunEnv() {
        return properties.getProperty("run.env");
    }

    String getSeleniumGridHubUrl() {
        return properties.getProperty("selenium.grid.url");
    }

    public String getWikiUrl() {
        return properties.getProperty("test.wiki.url");
    }

    public String getBrowser() {
        return properties.getProperty("browser.name");
    }

    public String getBrowserVersion() {
        return properties.getProperty("browser.version");
    }
}
