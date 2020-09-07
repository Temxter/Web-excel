package model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class ClassNameEntity {
    @Id
    private int id;
    private int classNum;
    private String className;
    @ManyToOne
    private PeriodEntity periodEntity;

    public ClassNameEntity() {
    }

    public ClassNameEntity(int classNum, String className, PeriodEntity periodEntity) {
        this.classNum = classNum;
        this.className = className;
        this.periodEntity = periodEntity;
    }
}
