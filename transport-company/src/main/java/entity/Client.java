package entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "clients")
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name")
    private String name;

    @ManyToMany
    private List<TransportCompany> transportCompanies;

    @OneToMany(mappedBy = "client")
    private List<Transport> transports;

    public Client(long id, String name) {
        this.id = id;
        this.name = name;
        this.transportCompanies = new ArrayList<>();
        this.transports = new ArrayList<>();
    }

    public void addTransportCompany(TransportCompany transportCompany) {
        if (!this.transportCompanies.contains(transportCompany)) {
            this.transportCompanies.add(transportCompany);
        } else {
            System.out.println("This company already exists!");
        }
    }

    public void addTransport(Transport transport) {
        if (!this.transports.contains(transport)) {
            this.transports.add(transport);
        } else {
            System.out.println("This transport already exists!");
        }

    }

    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
