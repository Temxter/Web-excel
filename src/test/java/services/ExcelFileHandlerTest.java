package services;

import org.junit.Test;

import java.io.IOException;
import java.text.ParseException;

public class ExcelFileHandlerTest {


    @Test
    public void saveFileToDatabaseTest() throws IOException, ParseException {
        ExcelFileHandler.saveFileToDatabase("test.xls");
    }

}
