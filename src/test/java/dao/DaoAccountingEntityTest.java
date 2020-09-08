package dao;

import model.AccountingEntity;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import parsers.ExcelAccountParser;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

public class DaoAccountingEntityTest {

    private List<AccountingEntity> accountingEntityList;
    private DaoAccountingEntity dao;

    @Before
    public void init() throws IOException, ParseException {
        accountingEntityList = new ExcelAccountParser().parse("test.xls");
        dao = new DaoAccountingEntity();
    }

    @Test
    public void save() {
        AccountingEntity entity = accountingEntityList.get(0);
        dao.save(entity);
        Assert.assertTrue("Entity has no id",entity.getId() != 0);
        Assert.assertNotNull("Entity not found in database", dao.get(entity.getId()));
        dao.delete(entity);
    }
}
