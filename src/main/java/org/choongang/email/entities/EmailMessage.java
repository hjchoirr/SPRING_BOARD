package org.choongang.email.entities;

public record EmailMessage(
        String to,
        String subject,
        String message
) {}
