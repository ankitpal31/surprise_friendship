package com.surprise.friendship.service;

import jakarta.mail.MessagingException;

public interface EmailService {

	void sendPdfEmail(String string, byte[] pdfData) throws MessagingException;

}
