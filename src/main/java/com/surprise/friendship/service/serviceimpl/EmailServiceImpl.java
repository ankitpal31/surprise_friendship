package com.surprise.friendship.service.serviceimpl;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.surprise.friendship.service.EmailService;

@Service
public class EmailServiceImpl implements EmailService{

    @Autowired
    private JavaMailSender mailSender;

    @Override
    public void sendPdfEmail(String to, byte[] pdfData) throws MessagingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        helper.setTo(to);
        helper.setSubject("Quiz Answers – 7 Saath Hai");
        helper.setText("Find the attached PDF of your answers. 😊");

        helper.addAttachment("quiz-answers.pdf", new ByteArrayResource(pdfData));

        mailSender.send(message);
    }
}

