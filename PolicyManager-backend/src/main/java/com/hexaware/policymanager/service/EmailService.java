package com.hexaware.policymanager.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Component
public class EmailService {

    @Autowired
    private JavaMailSender javaMailSender;

    public void sendEmailWithAttachment(String userEmail, byte[] pdfBytes) throws MessagingException {
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        helper.setTo(userEmail);
        helper.setSubject("Policy Registration Confirmation");
        helper.setText("Please find attached your policy registration confirmation.");

        // Attach the PDF
        helper.addAttachment("policy_confirmation.pdf", new ByteArrayResource(pdfBytes));

        javaMailSender.send(message);
    }
    public void sendEmailForPayment(String userEmail, byte[] pdfBytes) throws MessagingException {
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        helper.setTo(userEmail);
        helper.setSubject("Payment Successful");
        helper.setText("Please find payment details in the below attached pdf.");

        helper.addAttachment("policy_payment.pdf", new ByteArrayResource(pdfBytes));

        javaMailSender.send(message);
    }
}