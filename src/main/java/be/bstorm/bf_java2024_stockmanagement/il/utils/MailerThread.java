package be.bstorm.bf_java2024_stockmanagement.il.utils;

import org.thymeleaf.context.Context;

import java.util.List;

/**
 * Represents a thread for sending an email using a specified template and context data.
 * This class implements {@link Runnable} to enable asynchronous email sending.
 *
 * <p>Fields:
 * <ul>
 * <li>{@code mailerUtils} - Utility for sending emails.</li>
 * <li>{@code subject} - The subject of the email.</li>
 * <li>{@code templateName} - The name of the email template to use.</li>
 * <li>{@code context} - The Thymeleaf {@link Context} containing variables for the template.</li>
 * <li>{@code to} - A list of recipient email addresses.</li>
 * </ul>
 * </p>
 *
 * <p>Methods:
 * <ul>
 * <li>{@link #run()} - Executes the email-sending task.</li>
 * </ul>
 * </p>
 *
 * @see MailerUtils
 * @see Runnable
 */
public class MailerThread implements Runnable {

    private final MailerUtils mailerUtils;
    private final String subject;
    private final String templateName;
    private final Context context;
    private final List<String> to;

    /**
     * Constructs a {@code MailerThread} instance with the specified email details.
     *
     * @param mailerUtils The {@link MailerUtils} utility for sending emails.
     * @param subject The subject of the email.
     * @param templateName The name of the email template to use.
     * @param context The Thymeleaf {@link Context} containing variables for the template.
     * @param to The recipient email addresses.
     */
    public MailerThread(MailerUtils mailerUtils, String subject, String templateName, Context context, String... to) {
        this.mailerUtils = mailerUtils;
        this.subject = subject;
        this.templateName = templateName;
        this.context = context;
        this.to = List.of(to);
    }

    /**
     * Executes the email-sending task using {@link MailerUtils}.
     * This method is called when the thread is started.
     */
    @Override
    public void run() {
        mailerUtils.send(
                subject,
                templateName,
                context,
                to.toArray(new String[0])
        );
    }
}
