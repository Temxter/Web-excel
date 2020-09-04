package parsers;

import org.junit.Test;

import java.io.IOException;
import java.text.ParseException;

public class ExcelAccountParserTest {

    @Test
    public void parse() throws IOException, ParseException {
        ExcelAccountParser parser = new ExcelAccountParser();

        parser.parse("test.xls");
    }
}
