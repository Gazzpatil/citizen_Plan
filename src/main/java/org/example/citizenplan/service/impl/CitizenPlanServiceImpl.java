package org.example.citizenplan.service.impl;

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
            row1.createCell(5).setCellValue(citizenPlan.getPlanStartDate());
            row1.createCell(6).setCellValue(citizenPlan.getPlanEndDate());
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
    public boolean exportToPdf() {
        return false;
    }
}
