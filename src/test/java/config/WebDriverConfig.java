package config;

import org.aeonbits.owner.Config;

import java.net.URL;

public interface WebDriverConfig extends Config {

    @Key("browser")
    @DefaultValue("CHROME")
    Browser getBrowser();

    @Key("browserVersion")
    @DefaultValue("122.0")
    String getBrowserVersion();

    @Key("remoteUrl")
    @DefaultValue("https://user1:1234@selenoid.autotests.cloud/wd/hub")
    URL getRemoteUrl();

    @Key("isRemote")
    @DefaultValue("false")
    boolean isRemote();
}