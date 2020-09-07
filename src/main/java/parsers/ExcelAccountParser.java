package parsers;

import dao.DaoAccountingEntity;
import model.*;
import org.apache.poi.hpsf.Decimal;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellType;

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
    private Date start;
    private Date finish;
    private Date docCreated;
    private static final DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
    private static final Pattern datePattern = Pattern.compile("\\d{2}.\\d{2}.\\d{4}");
    private static final Pattern anyNumberPattern = Pattern.compile("\\d+");
    private DaoAccountingEntity daoAccountingEntity = new DaoAccountingEntity();

    public List<AccountingEntity> parse(String filename) throws IOException, ParseException {
        fileName = filename;
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

        PeriodEntity periodEntity = new PeriodEntity(start, finish, docCreated, reportName, fileName);
        BankEntity bankEntity = new BankEntity(bankName);

        int lastRowNum = excelSheet.getLastRowNum();

        List<AccountingEntity> accountingEntityList = new ArrayList<>();

        for (int i = 9; i < lastRowNum; i++) {
            HSSFRow row = excelSheet.getRow(i);

            if (row.getCell(0).getCellType() == CellType.NUMERIC) {

                if (row.getCell(0).getCellType() == CellType.STRING
                        && row.getCell(0).getStringCellValue().length() > 4) { // class name
                    classStringParseRow(row, periodEntity);
                }
                else { // row with numbers
                    accountingParseRow(row, bankEntity, periodEntity, accountingEntityList);
                }
            } else if (row.getCell(0).getCellType() == CellType.STRING) {
                if (row.getCell(0).getStringCellValue().length() > 4) {
                    classStringParseRow(row, periodEntity);
                } else {
                    accountingParseRow(row, bankEntity, periodEntity, accountingEntityList);
                }
            }
        }

        return accountingEntityList;
    }

    private void classStringParseRow(HSSFRow row, PeriodEntity periodEntity) {
        String className = row.getCell(0).getStringCellValue();
        String classNumString;

        Matcher matcherNumber = anyNumberPattern.matcher(className);
        if (matcherNumber.find()) {
            classNumString = matcherNumber.group();
        }
        else {
            return;
        }

        int classNum = Integer.parseInt(classNumString);
        ClassNameEntity classNameEntity = new ClassNameEntity(classNum, className, periodEntity);
        // TODO save to DB?
        periodEntity.getClassNameEntityList().add(classNameEntity);
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
                turnoverDebit, turnoverCredit, bankEntity, periodEntity);
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
