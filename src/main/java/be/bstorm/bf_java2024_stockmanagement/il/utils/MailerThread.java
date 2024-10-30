package be.bstorm.bf_java2024_stockmanagement.il.utils;

import org.thymeleaf.context.Context;

import java.util.List;

public class MailerThread implements Runnable {

    private final MailerUtils mailerUtils;
    private final String subject;
    private final String templateName;
    private final Context context;
    private final List<String> to;

    public MailerThread(MailerUtils mailerUtils, String subject, String templateName, Context context, String... to) {
        this.mailerUtils = mailerUtils;
        this.subject = subject;
        this.templateName = templateName;
        this.context = context;
        this.to = List.of(to);
    }

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
