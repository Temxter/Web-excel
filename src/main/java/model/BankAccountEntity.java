package model;

import javax.persistence.*;

@Entity
public class BankAccountEntity {
    @Id
    @ManyToOne(fetch = FetchType.EAGER)
    private BankEntity bankEntity;
    @Id
    @ManyToOne(fetch = FetchType.EAGER)
    private PeriodEntity periodEntity;
    @Id
    @ManyToOne
    private AccountingEntity accountingEntity;

    public BankAccountEntity() { }

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

    public AccountingEntity getAccountingEntity() {
        return accountingEntity;
    }

    public void setAccountingEntity(AccountingEntity accountingEntity) {
        this.accountingEntity = accountingEntity;
    }
}
