package entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="transport_company")
public class TransportCompany {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @OneToMany(mappedBy = "transportCompany", cascade = CascadeType.ALL)
    private List<Transport> companyTransportList;

    @OneToMany(mappedBy = "transportCompany", cascade = CascadeType.ALL)
    private List<Employee> companyEmployeesList;

    @OneToMany(mappedBy = "transportCompany", cascade = CascadeType.ALL)
    private List<Vehicle> companyVehiclesList;

    @ManyToMany()
    private List<Client> companyClientsList;

    public TransportCompany(long id, String name) {
        this.id = id;
        this.name = name;
        this.companyTransportList = new ArrayList<>();
        this.companyEmployeesList = new ArrayList<>();
        this.companyVehiclesList = new ArrayList<>();
        this.companyClientsList = new ArrayList<>();
    }

    public void setName(String name) {
        this.name = name;
    }


    public void addTransport(Transport transport) {
        if (!this.companyTransportList.contains(transport)) {
            this.companyTransportList.add(transport);
        } else {
            System.out.println("This transport already exists!");
        }
    }

    public void addEmployee(Employee employee) {
        if (!this.companyEmployeesList.contains(employee)) {
            this.companyEmployeesList.add(employee);
        } else {
            System.out.println("This employee already exists!");
        }
    }

    public void addVehicle(Vehicle vehicle) {
        if (!this.companyVehiclesList.contains(vehicle)) {
            this.companyVehiclesList.add(vehicle);
        } else {
            System.out.println("This vehicle already exists!");
        }
    }

    public void addClient(Client client) {
        if (!this.companyClientsList.contains(client)) {
            this.companyClientsList.add(client);
        } else {
            System.out.println("This client already exists!");
        }
    }

    @Override
    public String toString() {
        return "TransportCompany{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}