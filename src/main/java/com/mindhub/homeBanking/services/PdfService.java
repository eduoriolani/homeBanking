package com.mindhub.homeBanking.services;
import com.lowagie.text.Paragraph;
import com.lowagie.text.*;
import com.lowagie.text.Document;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import com.mindhub.homeBanking.models.Account;
import com.mindhub.homeBanking.models.Client;
import com.mindhub.homeBanking.models.Transaction;
import org.springframework.stereotype.Service;

import javax.swing.*;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.List;

@Service
public class PdfService {


    public ByteArrayOutputStream generatePDF(List<Transaction> transactions, Account account, Client client) throws DocumentException, IOException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        List<Transaction> sortedTransactions = new ArrayList<>(transactions);
        Collections.sort(sortedTransactions, Comparator.comparing(Transaction::getDate));

        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, outputStream);

        document.open();

        Paragraph title = new Paragraph("Account Information");
        Font fontTitle = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        Font fontInfo = FontFactory.getFont(FontFactory.TIMES_ROMAN);
        title.setAlignment(Element.ALIGN_CENTER);
        title.setFont(fontTitle);
        document.add(title);

        document.add(new Paragraph("Account holder: " + client.getFirstName()+ " " + client.getLastName(), fontInfo));
        document.add(new Paragraph("Account number: " + account.getNumber(), fontInfo));
        document.add(new Paragraph("Account type: " + account.getType(), fontInfo));
        document.add(new Paragraph("Actual Balance: $" + account.getBalance(), fontInfo));

        PdfPTable table = new PdfPTable(3);
        table.setWidthPercentage(100);
        table.setSpacingBefore(10f);
        table.setSpacingAfter(10f);

        PdfPCell cell;
        cell = new PdfPCell(new Phrase("Date"));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase("Description"));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase("Amount"));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell);

        for (Transaction transaction : sortedTransactions) {
            table.addCell(transaction.getDate().format(DateTimeFormatter.ofPattern("yyyy/MM/dd hh:mm:ss")));
            table.addCell(transaction.getDescription());
            table.addCell((" $" +transaction.getAmount()));
        }
        document.add(table);

        //No entiendo por que me da tantos errores la ruta
        String imagePath = "https://blogger.googleusercontent.com/img/b/R29vZ2xl/AVvXsEgeEhNCLJ-iS6MYWDOV9OubMJn7QuPMYsD4cZv1I0na0aFZx0jO9qe841V2wb27a5Nx5oxH_E70Srzg3AkCfAWPZq5Kz1asszSdbv3NREjHdWjOlkqJeJJ8QmzhoIywCc19_x1xI_M-VowcJ8yv6odqD4K-woUPLj_Lr64pc1wPxMIgN0bvRALvizIpOJjp/s1600/mindHub-icon.png";
        Image image = Image.getInstance(imagePath);
        image.setAlignment(Element.ALIGN_CENTER);

        document.add(image);
        document.close();

        return outputStream;
    }

}
