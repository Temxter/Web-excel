package parsers;

import model.AccountingEntity;
import model.BankEntity;
import model.PeriodEntity;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellType;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ExcelAccountParser {

    private String bankName;
    private String fileName;
    private String reportName;
    private String currency;
    private Date start;
    private Date finish;
    private Date docCreated;
    private static final DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
    private static final Pattern datePattern = Pattern.compile("\\d{2}.\\d{2}.\\d{4}");


    public List<AccountingEntity> parse(String filename) throws IOException, ParseException {
        this.fileName = filename.substring(filename.lastIndexOf(File.separator) + 1); //file name without path
        HSSFWorkbook excelBook = new HSSFWorkbook(new FileInputStream(filename));
        HSSFSheet excelSheet = excelBook.getSheetAt(0);
        bankName = excelSheet.getRow(0).getCell(0).getStringCellValue();
        reportName = excelSheet.getRow(1).getCell(0).getStringCellValue();
        String reportDatesString = excelSheet.getRow(2).getCell(0).getStringCellValue();
        Matcher matcherReportDates = datePattern.matcher(reportDatesString);
        if (matcherReportDates.find()) {
            start = dateFormat.parse(matcherReportDates.group());
        }
        if (matcherReportDates.find()) {
            finish = dateFormat.parse(matcherReportDates.group());
        }
        docCreated = excelSheet.getRow(5).getCell(0).getDateCellValue();

        currency = excelSheet.getRow(5).getCell(6).getStringCellValue();

        BankEntity bankEntity = new BankEntity(bankName);
        PeriodEntity periodEntity = new PeriodEntity(start, finish, docCreated,
                reportName, fileName, currency, bankEntity);

        int lastRowNum = excelSheet.getLastRowNum();

        List<AccountingEntity> accountingEntityList = new ArrayList<>();

        for (int i = 9; i < lastRowNum; i++) {
            HSSFRow row = excelSheet.getRow(i);

            if (row.getCell(0).getCellType() == CellType.NUMERIC) {
                accountingParseRow(row, bankEntity, periodEntity, accountingEntityList);
            } else if (row.getCell(0).getCellType() == CellType.STRING) {
                if (row.getCell(0).getStringCellValue().length() <= 4) {
                    accountingParseRow(row, bankEntity, periodEntity, accountingEntityList);
                } // else class string info
            }
        }

        return accountingEntityList;
    }

    private void accountingParseRow(HSSFRow row, BankEntity bankEntity, PeriodEntity periodEntity,
                                    List<AccountingEntity> accountingEntityList) {
        int accountNum = (int) parseNumericCell(row.getCell(0));
        if (accountNum / 100 == 0) { // sum info
            return;
        }
        BigDecimal inputBalance = new BigDecimal(row.getCell(1).getNumericCellValue());
        boolean isAsset = true;
        if (inputBalance.intValue() == 0) {
            isAsset = false;
            inputBalance = new BigDecimal(row.getCell(2).getNumericCellValue());
        }
        BigDecimal turnoverDebit = new BigDecimal(row.getCell(3).getNumericCellValue());
        BigDecimal turnoverCredit = new BigDecimal(row.getCell(4).getNumericCellValue());
        AccountingEntity accountingEntity = new AccountingEntity(accountNum, inputBalance, isAsset,
                turnoverDebit, turnoverCredit, periodEntity);
        accountingEntityList.add(accountingEntity);
//                daoAccountingEntity.save(accountingEntity);
    }

    private double parseNumericCell(HSSFCell cell) {
        if (cell.getCellType() == CellType.NUMERIC) {
            return cell.getNumericCellValue();
        } else if (cell.getCellType() == CellType.STRING) {
            return Double.parseDouble(cell.getStringCellValue());
        }
        return 0.0D;
    }
}
