package be.bstorm.bf_java2024_stockmanagement.il.utils;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@Component
@RequiredArgsConstructor
public class MailerUtils {

    @Value("${spring.mail.username}")
    private String appEmailAddress;

    private final JavaMailSender mailSender;
    private final TemplateEngine templateEngine;

    public MailerThread createThread(String subject, String templateName,Context context, String... to) {
        return new MailerThread(this, subject, templateName, context, to);
    }

    public void send(String subject, String templateName, Context context, String... to) {

        String html = templateEngine.process("emails/" + templateName, context);

        try {
            MimeMessage mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
            helper.setFrom(appEmailAddress);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(html, true);

            mailSender.send(mimeMessage);

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}
