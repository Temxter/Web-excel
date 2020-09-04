package model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class ClassNameEntity {
    @Id
    private int id;
    private String className;
    @ManyToOne
    private PeriodEntity periodEntity;
}
