package services;

import dao.DaoAccountingEntity;
import model.AccountingEntity;
import parsers.ExcelAccountParser;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.List;

public class ExcelFileHandler {

    private static String folderForProcessing = "loaded_files";
    private static String folderFailProcessed = "loaded_files" + File.separator + "fail_processed";
    private static String folderSuccessProcessed = "loaded_files" + File.separator + "success_processed";

    private ExcelFileHandler() { }

    /**
     *
     * @param fileName - only file name without path to this file.
     * @throws IOException
     * @throws ParseException
     */
    public static void saveFileToDatabase(String fileName) throws IOException, ParseException {
        ExcelAccountParser parser = new ExcelAccountParser(); //change fileName
        fileName = renameFile(fileName);
        String fileNameWithFolder = folderForProcessing + File.separator + fileName;
        List<AccountingEntity> accountingEntityList = parser.parse(fileNameWithFolder);
        DaoAccountingEntity dao = new DaoAccountingEntity();
        dao.saveList(accountingEntityList);
        // moving to success_processed folder
        new File(fileNameWithFolder).renameTo(new File(folderSuccessProcessed + File.separator + fileName));
    }

    private static String renameFile(String fileName) throws IOException {
        String newFileName = fileName;

        String[] fileNamePointSplit = fileName.split("\\.");

        new File(folderForProcessing).mkdir();
        File folder = new File(folderSuccessProcessed);
        folder.mkdir();
        int counter = 0;

        for (File file : folder.listFiles()) {
            if (file.getName().equals(newFileName)) {
                counter++;
                if (fileNamePointSplit.length > 0) {
                    newFileName = String.format("%s_%d.%s",
                            fileNamePointSplit[0], counter, fileNamePointSplit[1]);
                } else {
                    newFileName = String.format("%s_%d",
                            fileName, counter);
                }
            }
        }

        boolean isRenamed = new File(folderForProcessing, fileName).renameTo(new File(folderForProcessing, newFileName));
        if (isRenamed) {
            return newFileName;
        } else {
            return fileName;
        }
    }

    /**
     *
     * @param fileName
     * @return new pathname for the named file or old pathname if file have not moved.
     */
    public static String moveToFailProcessedFolder(String fileName) throws IOException {
        new File(folderFailProcessed).mkdir();
        String newFileName = folderFailProcessed + File.separator + fileName;
        boolean successMoved = new File(fileName).renameTo(new File(newFileName));
        return successMoved ? newFileName : fileName;
    }


    public static void getSuccessProcessedFile(String fileName) {

    }
}
