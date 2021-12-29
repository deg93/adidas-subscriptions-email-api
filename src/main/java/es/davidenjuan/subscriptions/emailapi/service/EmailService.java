package es.davidenjuan.subscriptions.emailapi.service;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.env.Environment;
import org.springframework.core.env.Profiles;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;

import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;

import es.davidenjuan.subscriptions.emailapi.configuration.ApplicationProperties;
import es.davidenjuan.subscriptions.emailapi.exception.EmailException;
import es.davidenjuan.subscriptions.emailapi.service.dto.EmailDTO;

@Service
public class EmailService {

    private Logger log = LoggerFactory.getLogger(EmailService.class);

    private final boolean isProductionEnvironment;

    private final String sendGridApiKey;

    public EmailService(Environment environment, ApplicationProperties applicationProperties) {
        this.isProductionEnvironment = environment.acceptsProfiles(Profiles.of("prod"));
        this.sendGridApiKey = applicationProperties.getSendGrid().getApiKey();
    }

    /**
     * Sends the given email.
     * @param emailDTO email to send.
     */
    public void sendEmail(EmailDTO emailDTO) {
        log.info("Request to send email");

        // Send email only in production environment
        if (!isProductionEnvironment) {
            log.warn("Email will not be sent: current environment is not PROD");
            return;
        }

        // Prepare email for SendGrid
        Email from = new Email(emailDTO.getFrom());
        Email to = new Email(emailDTO.getTo());
        String bodyMediaType = emailDTO.getBodyIsHtml() ? MediaType.TEXT_HTML_VALUE : MediaType.TEXT_PLAIN_VALUE;
        Content content = new Content(bodyMediaType, emailDTO.getBody());
        Mail mail = new Mail(from, emailDTO.getSubject(), to, content);

        // Send email with SendGrid
        SendGrid sendGrid = new SendGrid(sendGridApiKey);
        Request request = new Request();
        try {
            request.setMethod(Method.POST);
            request.setEndpoint("mail/send");
            request.setBody(mail.build());
            Response response = sendGrid.api(request);
            if (!HttpStatus.valueOf(response.getStatusCode()).is2xxSuccessful()) {
                throw new IOException("SendGrid API returned error code " + response.getStatusCode() + " (error body: " + response.getBody() + ")");
            }
        } catch (IOException e) {
            log.error("Error sending email", e);
            throw new EmailException("Error sending email", e);
        }
    }
}
