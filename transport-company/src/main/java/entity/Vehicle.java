package entity;

import javax.persistence.*;

@Entity
@Table(name = "vehicles")
public class Vehicle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @ManyToOne
    @JoinColumn(name = "transportCompanyVehicles")
    private TransportCompany transportCompany;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "type", nullable = false)
    private VehicleType type;

    public Vehicle(long id, String name, TransportCompany transportCompany, VehicleType type) {
        this.id = id;
        this.name = name;
        this.transportCompany = transportCompany;
        this.type = type;
    }


    @Override
    public String toString() {
        return "Vehicle{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", type=" + type +
                '}';
    }
}
