package org.design.patternpatients.healthCareModule.entities;
import jakarta.persistence.*;
import lombok.Data;
import org.design.patternpatients.AccountsReconciliation.entities.AccountItem;

@Entity
@Data
public class Patients {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String patient_name;
    private Integer age;
    private Integer weight;
    private String treatment;
    private String email;
    private Integer amount;
    private String patientType;
    @ManyToOne(cascade = CascadeType.ALL)
    private AccountItem bill;
}

