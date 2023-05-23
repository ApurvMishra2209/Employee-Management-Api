package com.employee.management.domain;

//import com.employee.management.model.DepartmentName;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.time.OffsetDateTime;
import java.util.Set;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Department {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

//    @Column(nullable = false)
//    @Enumerated(EnumType.STRING)
//    private DepartmentName departmentName;

    @Column(unique = true,nullable = false)
    private String departmentName;

    @OneToMany(mappedBy = "department", cascade = CascadeType.ALL)
    private Set<Employee> employee;

    @Column(unique = true, columnDefinition = "char(36)")
    @Type(type = "uuid-char")
    private UUID managerUuid;

    @Column
    private String managerFirstName;

    @Column
    private String managerLastName;

    @Column
    private OffsetDateTime dateCreated;

    @Column
    private OffsetDateTime lastUpdated;

    @PrePersist
    public void prePersist() {
        dateCreated = OffsetDateTime.now();
        lastUpdated = dateCreated;
    }

    @PreUpdate
    public void preUpdate() {
        lastUpdated = OffsetDateTime.now();
    }
}
