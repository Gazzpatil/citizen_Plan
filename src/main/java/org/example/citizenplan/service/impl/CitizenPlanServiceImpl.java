package org.example.citizenplan.service.impl;


import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.poi.ss.usermodel.*;
import org.example.citizenplan.dto.SearchResult;
import org.example.citizenplan.model.CitizenPlan;
import org.example.citizenplan.repo.CitizenPlanRepo;
import org.example.citizenplan.service.CitizenPlanService;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;


import java.io.*;
import java.util.List;
@Service
public class CitizenPlanServiceImpl implements CitizenPlanService {

    @Autowired
    private CitizenPlanRepo citizenPlanRepo;

    @Override
    public List<String> getPlanName() {
        System.out.println(
                citizenPlanRepo.getPlanName()+" citizenPlanRepo.getPlanName()"
        );
        return citizenPlanRepo.getPlanName();
    }

    @Override
    public List<String> getPlanStatus() {
        return citizenPlanRepo.getPlanStatus();
    }

    @Override
    public List<CitizenPlan> getCitizenPlan(SearchResult searchResult) {
        System.out.println(citizenPlanRepo.findAll()+" ************************ all data sevice **********************");
        CitizenPlan citizenPlan = new CitizenPlan();
        if(null != searchResult.getPlanStatus() && !"".equals(searchResult.getPlanStatus())) {
            citizenPlan.setPlanStatus(searchResult.getPlanStatus());
        }
        if(null != searchResult.getPlanName() && !"".equals(searchResult.getPlanName())) {
            citizenPlan.setPlanName(searchResult.getPlanName());
        }
        if(null != searchResult.getGender() && !"".equals(searchResult.getGender())) {
            citizenPlan.setGender(searchResult.getGender());
        }
        return  citizenPlanRepo.findAll(Example.of(citizenPlan)); // the Example.of(T) method is used to create a query-by-example (QBE) object. It allows you to construct queries dynamically without writing explicit query logic.
    }

    @Override
    public boolean exportToExcel(HttpServletResponse response) throws IOException {
        List<CitizenPlan> citizenPlanRepoAll = citizenPlanRepo.findAll();



//        XSSFWorkbook is used to export ".xlsh" file
//        Workbook workbook = new XSSFWorkbook();


//      HSSFWorkbook is used to create '.xls' file
        Workbook workbook=new HSSFWorkbook();
        Sheet sheet= workbook.createSheet("CitizenPlan");
        Row rowHeader = sheet.createRow(0);



        // Create a custom font for the header
        Font headerFont = workbook.createFont();
        headerFont.setFontName("Arial");
        headerFont.setBold(true);
        headerFont.setFontHeightInPoints((short) 15);
        headerFont.setColor(IndexedColors.WHITE.getIndex());

        // Create a CellStyle and apply the font
        CellStyle headerStyle = workbook.createCellStyle();
        headerStyle.setFont(headerFont);
        headerStyle.setFillForegroundColor(IndexedColors.DARK_BLUE.getIndex());
        headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        headerStyle.setAlignment(HorizontalAlignment.CENTER);
        headerStyle.setVerticalAlignment(VerticalAlignment.CENTER);

//Storing the row  header in an array and and iterating throw to change the each cell style
        String[] headers = {
                "Id", "Citizen Name", "Gender", "Plan Name",
                "Plan Status", "Plan Start Date", "Plan End Date", "Beneficial Amount"
        };

        for (int i = 0; i < headers.length; i++) {
            Cell cell = rowHeader.createCell(i);
            cell.setCellValue(headers[i]);
            cell.setCellStyle(headerStyle);
        }

//        rowHeader.createCell(8).setCellValue("Denial reason");
//        rowHeader.createCell(9).setCellValue("Termination date");
//        rowHeader.createCell(10).setCellValue("Termination reason");

        int row=1;
        for (CitizenPlan citizenPlan : citizenPlanRepoAll) {
            Row row1 = sheet.createRow(row);
            row1.createCell(0).setCellValue(citizenPlan.getCitizenId());
            row1.createCell(1).setCellValue(citizenPlan.getCitizenName());
            row1.createCell(2).setCellValue(citizenPlan.getGender());
            row1.createCell(3).setCellValue(citizenPlan.getPlanName());
            row1.createCell(4).setCellValue(citizenPlan.getPlanStatus());
            if(null != citizenPlan.getPlanStartDate()) {
                row1.createCell(5).setCellValue(citizenPlan.getPlanStartDate());
            }else
                row1.createCell(5).setCellValue("N/A");
           if(null != citizenPlan.getPlanEndDate()) {
               row1.createCell(6).setCellValue(citizenPlan.getPlanEndDate());
           }
           else
               row1.createCell(6).setCellValue("N/A");
            if(citizenPlan.getBenefitAmt() != null)
                row1.createCell(7).setCellValue(citizenPlan.getBenefitAmt());
            else
                row1.createCell(7).setCellValue("N/A");

            row++;
        }


        ServletOutputStream outputStream = response.getOutputStream();
        workbook.write(outputStream);
        workbook.close();

        return true;
    }

    @Override
    public boolean exportToPdf(HttpServletResponse response) throws IOException {

        // Set response properties for PDF
        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "attachment; filename=citizen_plans.pdf");

        // Initialize document and writer
        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, response.getOutputStream());
        document.open();

        // Add title to the document
        Paragraph paragraph = new Paragraph("Citizen Plans");
        paragraph.setAlignment(Element.ALIGN_CENTER);
        paragraph.setSpacingAfter(10);
        document.add(paragraph);

        // Create table with 8 columns
        PdfPTable table = new PdfPTable(8);
        table.setWidthPercentage(100);
        table.setSpacingBefore(10);

        // Add headers
        table.addCell("Id");
        table.addCell("Citizen Name");
        table.addCell("Gender");
        table.addCell("Plan Name");
        table.addCell("Plan Status");
        table.addCell("Plan Start Date");
        table.addCell("Plan End Date");
        table.addCell("Beneficial Amount");

        // Populate rows
        for (CitizenPlan c : citizenPlanRepo.findAll()) {
            table.addCell(String.valueOf(c.getCitizenId()));
            table.addCell(c.getCitizenName());
            table.addCell(c.getGender());
            table.addCell(c.getPlanName());
            table.addCell(c.getPlanStatus());

            if (c.getPlanStartDate() != null) {
                table.addCell(String.valueOf(c.getPlanStartDate()));
            } else {
                table.addCell("N/A");
            }

            if (c.getPlanEndDate() != null) {
                table.addCell(String.valueOf(c.getPlanEndDate()));
            } else {
                table.addCell("N/A");
            }

            if (c.getBenefitAmt() != null) {
                table.addCell(String.valueOf(c.getBenefitAmt()));
            } else {
                table.addCell("N/A");
            }
        }

        // Add the table to the document
        document.add(table);

        // Close the document
        document.close();

        return true;
    }



}
