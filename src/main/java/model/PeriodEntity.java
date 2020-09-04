package model;

import javax.persistence.*;
import java.sql.Date;
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
    @OneToMany
    private List<BankAccountEntity> bankAccountEntityList;

    public PeriodEntity() { }

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

    public List<BankAccountEntity> getBankAccountEntityList() {
        return bankAccountEntityList;
    }

    public void setBankAccountEntityList(List<BankAccountEntity> bankAccountEntityList) {
        this.bankAccountEntityList = bankAccountEntityList;
    }
}
