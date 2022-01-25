package entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "capabilities")
public class Capabilities {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false)
    private CapabilitiesType capabilitiesType;

    @ManyToMany
    private List<Employee> employees;

    public Capabilities(long id, CapabilitiesType capabilitiesType, List<Employee> employees) {
        this.id = id;
        this.capabilitiesType = capabilitiesType;
        this.employees = employees;
    }

    @Override
    public String toString() {
        return "Capabilities{" +
                "id=" + id +
                ", capabilitiesType=" + capabilitiesType +
                '}';
    }
}
