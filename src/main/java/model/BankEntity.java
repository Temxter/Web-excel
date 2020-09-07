package model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class BankEntity {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;
    private String name;
    @OneToMany(mappedBy = "bankEntity")
    private List<AccountingEntity> accountingEntityList;

    public BankEntity() { }

    public BankEntity(String name) {
        this.name = name;
        accountingEntityList = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<AccountingEntity> getAccountingEntityList() {
        return accountingEntityList;
    }

    public void setAccountingEntityList(List<AccountingEntity> accountingEntityList) {
        this.accountingEntityList = accountingEntityList;
    }
}
