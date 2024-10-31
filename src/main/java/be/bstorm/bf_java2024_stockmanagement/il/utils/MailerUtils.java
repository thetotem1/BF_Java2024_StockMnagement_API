package be.bstorm.bf_java2024_stockmanagement.il.utils;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

/**
 * Utility class for sending emails, including support for HTML templates using Thymeleaf.
 * This class provides methods to send emails synchronously and create threads for asynchronous sending.
 *
 * <p>Fields:
 * <ul>
 * <li>{@code appEmailAddress} - The application’s email address for the "from" field.</li>
 * <li>{@code mailSender} - Spring’s {@link JavaMailSender} for sending emails.</li>
 * <li>{@code templateEngine} - The Thymeleaf {@link TemplateEngine} for processing HTML templates.</li>
 * </ul>
 * </p>
 *
 * <p>Methods:
 * <ul>
 * <li>{@link #createThread(String, String, Context, String...)} - Creates a {@link MailerThread} for asynchronous email sending.</li>
 * <li>{@link #send(String, String, Context, String...)} - Sends an HTML email with the specified subject, template, and recipients.</li>
 * </ul>
 * </p>
 *
 * @see MailerThread
 * @see JavaMailSender
 * @see TemplateEngine
 */
@Component
@RequiredArgsConstructor
public class MailerUtils {

    /**
     * The application’s email address for the "from" field in emails.
     */
    @Value("${spring.mail.username}")
    private String appEmailAddress;

    private final JavaMailSender mailSender;
    private final TemplateEngine templateEngine;

    /**
     * Creates a new {@link MailerThread} instance for asynchronous email sending.
     *
     * @param subject The subject of the email.
     * @param templateName The name of the email template.
     * @param context The Thymeleaf {@link Context} containing variables for the template.
     * @param to The recipient email addresses.
     * @return A {@link MailerThread} for running the email send operation asynchronously.
     */
    public MailerThread createThread(String subject, String templateName, Context context, String... to) {
        return new MailerThread(this, subject, templateName, context, to);
    }

    /**
     * Sends an HTML email with the specified subject, template, and recipients.
     * Processes the template with Thymeleaf, constructs a MIME message, and sends it via {@code mailSender}.
     *
     * @param subject The subject of the email.
     * @param templateName The name of the email template.
     * @param context The Thymeleaf {@link Context} containing variables for the template.
     * @param to The recipient email addresses.
     * @throws RuntimeException if an error occurs while creating or sending the email.
     */
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
