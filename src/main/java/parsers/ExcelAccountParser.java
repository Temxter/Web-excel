package parsers;

import model.AccountingEntity;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;

public class ExcelAccountParser {

    private String bankName;
    private String fileName;
    private String reportName;
    private Date start;
    private Date finish;
    private Date docCreated;
    private static final DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
    private static final Pattern datePattern = Pattern.compile("[\\d]{4}.[\\d]{2}.[\\d]{2}");


    public List<AccountingEntity> parse(String filename) throws IOException, ParseException {
        fileName = filename;
        HSSFWorkbook excelBook = new HSSFWorkbook(new FileInputStream(filename));
        HSSFSheet excelSheet = excelBook.getSheetAt(0);
        bankName = excelSheet.getRow(0).getCell(0).getStringCellValue();
        reportName = excelSheet.getRow(1).getCell(0).getStringCellValue();
        String reportDatesString = excelSheet.getRow(2).getCell(0).getStringCellValue();
        String [] reportDates = datePattern.split(reportDatesString);
        start = dateFormat.parse(reportDates[0]);
        finish = dateFormat.parse(reportDates[1]);
        docCreated = excelSheet.getRow(5).getCell(0).getDateCellValue();

//        for(excelSheet.getLastRowNum())

//        System.out.println("Report name: " + reportName + ". Bank name: " + bankName);
        return null;
    }
}
