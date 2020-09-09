package model;

import javax.persistence.*;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;

@Entity
public class PeriodEntity {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;
    private Date startDate;
    private Date finishDate;
    private Date createDocDate;
    private String docName;
    private String fileName;
    private String currency;
    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private BankEntity bankEntity;
    @OneToMany(mappedBy = "periodEntity", fetch = FetchType.EAGER)
    private List<AccountingEntity> accountingEntityList;

    public PeriodEntity() { }

    public PeriodEntity(Date startDate, Date finishDate, Date createDocDate, String docName, String fileName,
                        String currency, BankEntity bankEntity) {
        this.startDate = startDate;
        this.finishDate = finishDate;
        this.createDocDate = createDocDate;
        this.docName = docName;
        this.fileName = fileName;
        this.currency = currency;
        this.bankEntity = bankEntity;
        accountingEntityList = new ArrayList<>();
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getFinishDate() {
        return finishDate;
    }

    public void setFinishDate(Date finishDate) {
        this.finishDate = finishDate;
    }

    public Date getCreateDocDate() {
        return createDocDate;
    }

    public void setCreateDocDate(Date createDocDate) {
        this.createDocDate = createDocDate;
    }

    public String getDocName() {
        return docName;
    }

    public void setDocName(String docName) {
        this.docName = docName;
    }

    public List<AccountingEntity> getAccountingEntityList() {
        return accountingEntityList;
    }

    public void setAccountingEntityList(List<AccountingEntity> accountingEntityList) {
        this.accountingEntityList = accountingEntityList;
    }

    public BankEntity getBankEntity() {
        return bankEntity;
    }

    public void setBankEntity(BankEntity bankEntity) {
        this.bankEntity = bankEntity;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }
}
