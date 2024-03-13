package com.hexaware.policymanager.service;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;

import javax.imageio.ImageIO;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;
import org.springframework.stereotype.Component;

import com.hexaware.policymanager.dto.PolicyPaymentsDTO;
import com.hexaware.policymanager.entities.Policies;

@Component
public class PdfGenerator {

    public byte[] generatePdf(String userName, LocalDate startDate, double maturityAmount, LocalDate endDate,
            int durationInYears, Policies policy) throws IOException {
        try (PDDocument document = new PDDocument()) {
            PDPage page = new PDPage();
            document.addPage(page);

            try (PDPageContentStream contentStream = new PDPageContentStream(document, page)) {
                contentStream.setFont(PDType1Font.HELVETICA_BOLD, 12);

                // Add a gap between the image and the header
                float gap = 20;

                // Draw the image
                BufferedImage image = ImageIO
                        .read(new File("C:\\Hexaware\\FSD\\UI\\Angular\\policymanager\\src\\assets\\logo-no-background.png"));
                PDImageXObject pdImage = PDImageXObject.createFromByteArray(document, toByteArray(image), "image");
                float imageWidth = pdImage.getWidth() / 12;
                float imageHeight = pdImage.getHeight() / 12;
                float x = 50; // Adjusted X coordinate for left side
                float y = page.getMediaBox().getHeight() - imageHeight - 50; // Adjusted Y coordinate
                contentStream.drawImage(pdImage, x, y, imageWidth, imageHeight);

                // Move to the next line
                contentStream.beginText();
                contentStream.newLineAtOffset(0, -(imageHeight + gap));
                contentStream.endText();
                // Center the text
                String headerText = "Details of the Policy you have been registered";
                float textWidth = PDType1Font.HELVETICA_BOLD.getStringWidth(headerText) / 1000 * 12; // Font size 12
                float centeredX = (page.getMediaBox().getWidth() - textWidth) / 2;
                contentStream.beginText();
                contentStream.newLineAtOffset(centeredX, 700); // Adjusted Y coordinate
                contentStream.showText(headerText);
                contentStream.endText();
                contentStream.setFont(PDType1Font.HELVETICA, 12);
                // Display the rest of the text
                contentStream.beginText();
                contentStream.newLineAtOffset(50, 680); // Adjusted X coordinate
                contentStream.showText("User Name: " + userName);
                contentStream.newLineAtOffset(0, -20); // Move to the next line
                contentStream.showText("Policy Id: " + policy.getPolicyId());
                contentStream.newLineAtOffset(0, -20); // Move to the next line
                contentStream.showText("Policy Name: " + policy.getPolicyName());
                contentStream.newLineAtOffset(0, -20);
                contentStream.showText("Initial Deposit: " + policy.getInitialDeposit());
                contentStream.newLineAtOffset(0, -20);
                contentStream.showText("Start Date: " + startDate);
                contentStream.newLineAtOffset(0, -20);
                contentStream.showText("End Date: " + endDate);
                contentStream.newLineAtOffset(0, -20);
                contentStream.showText("Term Period: " + policy.getTermPeriod());
                contentStream.newLineAtOffset(0, -20);
                contentStream.showText("Term Amount: " + policy.getTermAmount());
                contentStream.newLineAtOffset(0, -20);
                contentStream.showText("Interest: " + policy.getInterest());
                contentStream.newLineAtOffset(0, -20);
                contentStream.showText("Maturity Amount: " + maturityAmount);
                contentStream.endText();
            }

            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            document.save(outputStream);
            return outputStream.toByteArray();
        }
    }

    public byte[] generatePaymentConfirmationPdf(PolicyPaymentsDTO policyPaymentsDTO) throws IOException {
        try (PDDocument document = new PDDocument()) {
            PDPage page = new PDPage();
            document.addPage(page);

            try (PDPageContentStream contentStream = new PDPageContentStream(document, page)) {
                contentStream.setFont(PDType1Font.HELVETICA_BOLD, 12);

                float gap = 20;

                // Draw the image
                BufferedImage image = ImageIO
                        .read(new File("C:\\Hexaware\\FSD\\UI\\Angular\\policymanager\\src\\assets\\logo-no-background.png"));
                PDImageXObject pdImage = PDImageXObject.createFromByteArray(document, toByteArray(image), "image");
                float imageWidth = pdImage.getWidth() / 12;
                float imageHeight = pdImage.getHeight() / 12;
                float x = 50; // Adjusted X coordinate for left side
                float y = page.getMediaBox().getHeight() - imageHeight - 50; // Adjusted Y coordinate
                contentStream.drawImage(pdImage, x, y, imageWidth, imageHeight);

                // Move to the next line
                contentStream.beginText();
                contentStream.newLineAtOffset(0, -(imageHeight + gap));
                contentStream.endText();
                // Center the text
                String headerText = "Invoice";
                float textWidth = PDType1Font.HELVETICA_BOLD.getStringWidth(headerText) / 1000 * 12; // Font size 12
                float centeredX = (page.getMediaBox().getWidth() - textWidth) / 2;
                contentStream.beginText();
                contentStream.newLineAtOffset(centeredX, 700); // Adjusted Y coordinate
                contentStream.showText(headerText);
                contentStream.endText();
                contentStream.setFont(PDType1Font.HELVETICA, 12);
                // Display payment details
                contentStream.beginText();
                contentStream.newLineAtOffset(50, 680); // Adjusted X coordinate
                contentStream.showText("Payment Date: " + policyPaymentsDTO.getPaymentDate());
                contentStream.newLineAtOffset(0, -20); // Move to the next line
                contentStream.showText("Payment Method: " + policyPaymentsDTO.getPaymentMethod());
                contentStream.newLineAtOffset(0, -20); // Move to the next line
                contentStream.showText("Fine: " + policyPaymentsDTO.getFine());
                contentStream.newLineAtOffset(0, -20); // Move to the next line
                contentStream.showText("Total Amount: " + policyPaymentsDTO.getTotalAmount());
                contentStream.newLineAtOffset(0, -20); // Move to the next line
                contentStream.showText("Payment Status: " + policyPaymentsDTO.getPaymentStatus());
                contentStream.endText();
            }

            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            document.save(outputStream);
            return outputStream.toByteArray();
        }
    }

    private byte[] toByteArray(BufferedImage image) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(image, "png", baos);
        return baos.toByteArray();
    }
}
