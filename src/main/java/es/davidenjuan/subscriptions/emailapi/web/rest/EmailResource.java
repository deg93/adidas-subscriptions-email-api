package es.davidenjuan.subscriptions.emailapi.web.rest;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.davidenjuan.subscriptions.emailapi.service.EmailService;
import es.davidenjuan.subscriptions.emailapi.service.dto.EmailDTO;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping("/api")
public class EmailResource {

    private final Logger log = LoggerFactory.getLogger(EmailResource.class);

    private EmailService emailService;

    public EmailResource(EmailService emailService) {
        this.emailService = emailService;
    }

    @ApiOperation("Sends the specified email")
    @PostMapping("/emails")
    public ResponseEntity<EmailDTO> sendEmail(
        @ApiParam("The email to send")
        @Valid
        @RequestBody
        EmailDTO emailDTO
    ) {
        log.info("REST request to send email");
        emailService.sendEmail(emailDTO);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
