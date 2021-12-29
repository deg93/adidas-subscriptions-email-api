package es.davidenjuan.subscriptions.emailapi.service.dto;

import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;


@SuppressWarnings("serial")
public class EmailDTO implements Serializable {

    @NotEmpty
    private String from;

    @NotNull
    @Email
    private String to;

    @NotEmpty
    private String subject;

    @NotEmpty
    private String body;

    @NotNull
    private Boolean bodyIsHtml;

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Boolean getBodyIsHtml() {
        return bodyIsHtml;
    }

    public void setBodyIsHtml(Boolean bodyIsHtml) {
        this.bodyIsHtml = bodyIsHtml;
    }
}
