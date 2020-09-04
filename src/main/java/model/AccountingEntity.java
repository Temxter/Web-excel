package model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Entity
public class AccountingEntity {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;
    private int accountNum;
    private BigDecimal inputBalance;
    private boolean isAsset;
    private BigDecimal turnoverDebit;
    private BigDecimal turnoverCredit;
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "accountingEntity")
    private List<BankAccountEntity> bankAccountEntityList;

    public AccountingEntity() { }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAccountNum() {
        return accountNum;
    }

    public void setAccountNum(int accountNum) {
        this.accountNum = accountNum;
    }

    public BigDecimal getInputBalance() {
        return inputBalance;
    }

    public void setInputBalance(BigDecimal inputBalance) {
        this.inputBalance = inputBalance;
    }

    public boolean isIsAsset() {
        return isAsset;
    }

    public void setIsAsset(boolean isasset) {
        this.isAsset = isasset;
    }

    public BigDecimal getTurnoverDebit() {
        return turnoverDebit;
    }

    public void setTurnoverDebit(BigDecimal turnoverDebit) {
        this.turnoverDebit = turnoverDebit;
    }

    public BigDecimal getTurnoverCredit() {
        return turnoverCredit;
    }

    public void setTurnoverCredit(BigDecimal turnoverCredit) {
        this.turnoverCredit = turnoverCredit;
    }

    public List<BankAccountEntity> getBankAccountEntityList() {
        return bankAccountEntityList;
    }

    public void setBankAccountEntityList(List<BankAccountEntity> bankAccountEntityList) {
        this.bankAccountEntityList = bankAccountEntityList;
    }
}
