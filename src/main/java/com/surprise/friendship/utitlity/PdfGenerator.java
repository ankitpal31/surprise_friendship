package com.surprise.friendship.utitlity;

import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.kernel.pdf.PdfDocument;

import java.io.ByteArrayOutputStream;
import java.util.Map;

public class PdfGenerator {

    public static byte[] generateQuizPdf(Map<Integer, String> answers) throws Exception {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PdfWriter writer = new PdfWriter(baos);
        PdfDocument pdf = new PdfDocument(writer);
        Document document = new Document(pdf);

        document.add(new Paragraph("Hum 7 Saath Hai Quiz – Answers"));
        document.add(new Paragraph("--------------------------"));

        for (Map.Entry<Integer, String> entry : answers.entrySet()) {
            document.add(new Paragraph("Q" + entry.getKey() + ": " + entry.getValue()));
        }

        document.close();
        return baos.toByteArray();
    }
}

