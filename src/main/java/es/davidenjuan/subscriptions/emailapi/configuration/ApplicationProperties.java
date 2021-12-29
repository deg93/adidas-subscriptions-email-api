package es.davidenjuan.subscriptions.emailapi.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "application", ignoreUnknownFields = false)
public class ApplicationProperties {

    private BasicAuth basicAuth;

    private SendGrid sendGrid;

    public BasicAuth getBasicAuth() {
        return basicAuth;
    }

    public void setBasicAuth(BasicAuth basicAuth) {
        this.basicAuth = basicAuth;
    }

    public SendGrid getSendGrid() {
        return sendGrid;
    }

    public void setSendGrid(SendGrid sendGrid) {
        this.sendGrid = sendGrid;
    }

    public static class BasicAuth {

        private String user;

        private String password;

        public String getUser() {
            return user;
        }

        public void setUser(String user) {
            this.user = user;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }
    }

    public static class SendGrid {

        private String apiKey;

        public String getApiKey() {
            return apiKey;
        }

        public void setApiKey(String apiKey) {
            this.apiKey = apiKey;
        }
    }
}
