package com.example.admin_cc_questionback.service.candidate.impl;

import com.example.admin_cc_questionback.entities.candidate.Candidate;
import com.example.admin_cc_questionback.entities.candidate.CandidateType;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.springframework.stereotype.Service;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

@Service
public class ExcelExporter {
    private SXSSFWorkbook workbook;
    private SXSSFSheet sheet;
    private List<Candidate> candidates;

    public ExcelExporter(List<Candidate> candidates){
        this.candidates = candidates;
        workbook = new SXSSFWorkbook();
    }

    public String formatDateToString(LocalDateTime dateTime){
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        if (dateTime != null){
            return dateTime.format(dateTimeFormatter);
        }
        return "";
    }
    private void writeHeaderLine(){
        sheet = workbook.createSheet("Candidates");
        Row row = sheet.createRow(0);
        CellStyle style = workbook.createCellStyle();

        createCell(row, 0, "Имя", style);
        createCell(row, 1, "Фамилия", style);
        createCell(row, 2, "Почта", style);
        createCell(row, 3, "День рождения", style);
        createCell(row, 4, "Дата регистрации", style);
        createCell(row, 5, "Номер", style);
        createCell(row, 6, "Статус", style);
        createCell(row, 7, "Вакансия", style);
    }

    private void createCell(Row row, int columnCount, Object value, CellStyle style){
        Cell cell = row.createCell(columnCount);
        if (value instanceof Integer){
            cell.setCellValue((Integer)value);
        } else if (value instanceof Boolean){
            cell.setCellValue((Boolean) value);
        }
        else if (value instanceof LocalDateTime){
            cell.setCellValue((LocalDateTime) value);
        }
        else if (value instanceof LocalDate){
            cell.setCellValue((LocalDate) value);
        }
        else if (value instanceof Double){
            cell.setCellValue((Double) value);
        }
        else if (value instanceof Long){
            cell.setCellValue((Long) value);
        }
        else if (value instanceof Date){
            cell.setCellValue((Date) value);
        }
        else {
            cell.setCellStyle(style);
            cell.setCellValue((String) value);
        }
    }
    private void writeDataLine() {
        int rowCount = 1;
        CellStyle style = workbook.createCellStyle();

        for (Candidate candidate : candidates) {
                Row row = sheet.createRow(rowCount++);
                int columnCount = 0;
                createCell(row, columnCount++, candidate.getName(), style);
                createCell(row, columnCount++, candidate.getSurname(), style);
                createCell(row, columnCount++, candidate.getEmail(), style);
                createCell(row, columnCount++, candidate.getBirthday(), style);
                createCell(row, columnCount++, formatDateToString(candidate.getRegistrationDate()), style);
                createCell(row, columnCount++, candidate.getPhoneNumber(), style);
                createCell(row, columnCount++, candidate.getStatus(), style);
                createCell(row, columnCount++, candidate.getCandidateType().getCandidateType(), style);

        }
    }


    public HttpServletResponse export(HttpServletResponse response) throws IOException {
        writeHeaderLine();
        writeDataLine();

        ServletOutputStream outputStream = response.getOutputStream();
        workbook.write(outputStream);
        workbook.close();
        outputStream.close();
        return response;
    }

    public String booleanCheck(boolean param){
        if (param){
            return "Да";
        }
        return  "";
    }

}
