package model;

import javax.persistence.*;
import java.util.List;

@Entity
public class BankEntity {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;
    private String name;
    @OneToMany(mappedBy = "bankEntity")
    private List<BankAccountEntity> bankAccountEntityList;

    public BankEntity() { }

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

    public List<BankAccountEntity> getBankAccountEntityList() {
        return bankAccountEntityList;
    }

    public void setBankAccountEntityList(List<BankAccountEntity> bankAccountEntityList) {
        this.bankAccountEntityList = bankAccountEntityList;
    }
}
