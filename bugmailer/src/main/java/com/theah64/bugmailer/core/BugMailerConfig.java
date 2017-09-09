package com.theah64.bugmailer.core;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by theapache64 on 9/9/17.
 */

public class BugMailerConfig {

    private static final String DEFAULT_GMAIL_USERNAME = "mymailer64@gmail.com";
    private static final String DEFAULT_GMAIL_PASSWORD = "mypassword64";

    private final Context context;
    private final String emailUsername, emailPassword;
    private String themeColor;
    private final List<String> recipients = new ArrayList<>();

    public BugMailerConfig(Context context, String emailUsername, String emailPassword, String primaryRecipient) {
        this.context = context;
        this.emailUsername = emailUsername;
        this.emailPassword = emailPassword;
        recipients.add(primaryRecipient);
    }

    public BugMailerConfig(Context context, String primaryRecipient) {
        this(context, DEFAULT_GMAIL_USERNAME, DEFAULT_GMAIL_PASSWORD, primaryRecipient);
    }


    public BugMailerConfig setThemeColor(String themeColor) {
        this.themeColor = themeColor;
        return this;
    }

    public BugMailerConfig addRepientEmail(String email) {
        recipients.add(email);
        return this;
    }

    public Context getContext() {
        return context;
    }

    public String getEmailUsername() {
        return emailUsername;
    }

    public String getEmailPassword() {
        return emailPassword;
    }

    public String getThemeColor() {
        return themeColor;
    }

    public List<String> getRecipients() {
        return recipients;
    }
}