package lt.luminor.auth.conf;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties
@ConfigurationProperties(prefix = "lmnr-api")
public class AppProperties {

    private String host;
    private String api_url;
    private String port;
    private String logout_url;

    public String getUrl(){
        return "http://"+getHost()+":"+getPort()+getApi_url();
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getApi_url() {
        return api_url;
    }

    public void setApi_url(String api_url) {
        this.api_url = api_url;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public String getLogout_url() {
        return logout_url;
    }

    public void setLogout_url(String logout_url) {
        this.logout_url = logout_url;
    }
}
