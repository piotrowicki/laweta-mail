package pl.com.laweta.service;

import java.util.Arrays;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import org.eclipse.microprofile.config.inject.ConfigProperty;

import io.quarkus.mailer.Mail;
import io.quarkus.mailer.Mailer;
import pl.com.laweta.dto.MailDto;

@ApplicationScoped
public class MailService {
    private static final String EMAIL_SUBJECT = "Wiadomość od pomocdrogowaostroda.com.pl";
    private static final String EMAIL_BODY = "Dostałeś wiadomość od %s [%s]\n\n%s";

    private final Mailer mailer;

    @ConfigProperty(name = "laweta.admin.mail-receivers")
    String mailReceivers;

    @Inject
    public MailService(Mailer mailer) {
        this.mailer = mailer;
    }

    public void sendEmail(MailDto mailDto) {
        Arrays.stream(mailReceivers.split(",")).forEach(mail -> {
            mailer.send(Mail.withText(mail, EMAIL_SUBJECT,
                    String.format(EMAIL_BODY, mailDto.getName(), mailDto.getEmail(), mailDto.getText())));
        });
    }
}