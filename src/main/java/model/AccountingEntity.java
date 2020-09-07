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
    @ManyToOne(fetch = FetchType.EAGER)
    private BankEntity bankEntity;
    @ManyToOne(fetch = FetchType.EAGER)
    private PeriodEntity periodEntity;

    public AccountingEntity() { }

    public AccountingEntity(int accountNum, BigDecimal inputBalance, boolean isAsset, BigDecimal turnoverDebit,
                            BigDecimal turnoverCredit, BankEntity bankEntity, PeriodEntity periodEntity) {
        this.accountNum = accountNum;
        this.inputBalance = inputBalance;
        this.isAsset = isAsset;
        this.turnoverDebit = turnoverDebit;
        this.turnoverCredit = turnoverCredit;
        this.bankEntity = bankEntity;
        this.periodEntity = periodEntity;
    }

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

    public boolean isAsset() {
        return isAsset;
    }

    public void setAsset(boolean asset) {
        isAsset = asset;
    }

    public BankEntity getBankEntity() {
        return bankEntity;
    }

    public void setBankEntity(BankEntity bankEntity) {
        this.bankEntity = bankEntity;
    }

    public PeriodEntity getPeriodEntity() {
        return periodEntity;
    }

    public void setPeriodEntity(PeriodEntity periodEntity) {
        this.periodEntity = periodEntity;
    }

    @Override
    public String toString() {
        return "AccountingEntity{" +
                "id=" + id +
                ", accountNum=" + accountNum +
                ", inputBalance=" + inputBalance +
                ", isAsset=" + isAsset +
                ", turnoverDebit=" + turnoverDebit +
                ", turnoverCredit=" + turnoverCredit +
                ", bankEntity=" + bankEntity +
                ", periodEntity=" + periodEntity +
                '}';
    }
}
