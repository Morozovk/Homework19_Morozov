package config;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.util.function.Supplier;

public class WebDriverProvider implements Supplier<WebDriver> {

    private final WebDriverConfig config;

    public WebDriverProvider() {
        this.config = ConfigFactory.create(WebDriverConfig.class, System.getProperties());
    }

    @Override
    public WebDriver get() {
        if (config.isRemote()) {
            return createRemoteDriver();
        } else {
            return createLocalDriver();
        }
    }

    private WebDriver createRemoteDriver() {
        if (config.getRemoteUrl() == null) {
            throw new RuntimeException("Remote URL is not set");
        }
        switch (config.getBrowser()) {
            case CHROME:
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.setBrowserVersion(config.getBrowserVersion());
                return new RemoteWebDriver(config.getRemoteUrl(), chromeOptions);
            case FIREFOX:
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                firefoxOptions.setBrowserVersion(config.getBrowserVersion());
                return new RemoteWebDriver(config.getRemoteUrl(), firefoxOptions);
            default:
                throw new RuntimeException("Unsupported browser for remote: " + config.getBrowser());
        }
    }

    private WebDriver createLocalDriver() {
        switch (config.getBrowser()) {
            case CHROME: {
                WebDriverManager.chromedriver().setup();
                return new ChromeDriver();
            }
            case FIREFOX: {
                WebDriverManager.firefoxdriver().setup();
                return new FirefoxDriver();
            }
            default: {
                throw new RuntimeException("No such driver");
            }
        }
    }
}