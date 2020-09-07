package parsers;

import model.AccountingEntity;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

public class ExcelAccountParserTest {

    @Test
    public void parse() throws IOException, ParseException {
        ExcelAccountParser parser = new ExcelAccountParser();

        List<AccountingEntity> accountingEntityList = parser.parse("test.xls");

        Assert.assertTrue(accountingEntityList.size() > 0);
    }
}
