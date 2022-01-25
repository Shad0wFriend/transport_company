package entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "employees")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @Column(name = "salary", nullable = false)
    private BigDecimal salary;

    @ManyToOne
    @JoinColumn(name = "transportCompanyId")
    private TransportCompany transportCompany;

    @OneToMany(mappedBy = "employee")
    private List<Transport> transports;

    @ManyToMany
    @Column(name = "capabilities", nullable = false)
    private List<Capabilities> employeeCapabilities;

    public Employee(long id, String name, BigDecimal salary, TransportCompany transportCompany) {
        this.id = id;
        this.name = name;
        this.salary = salary;
        this.transportCompany = transportCompany;
        this.transports = new ArrayList<>();
        this.employeeCapabilities = new ArrayList<>();
    }


    public void addTransport(Transport transport) {
        if (!this.transports.contains(transport)) {
            this.transports.add(transport);
        } else {
            System.out.println("This transport already exists!");
        }
    }

    public void addCapabilities(Capabilities capabilities) {
        if (!this.employeeCapabilities.contains(capabilities)) {
            this.employeeCapabilities.add(capabilities);
        } else {
            System.out.println("This capabilities already exists!");
        }
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", salary=" + salary +
                '}';
    }
}
