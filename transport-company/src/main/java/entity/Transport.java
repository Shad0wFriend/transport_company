package entity;

import javax.persistence.*;
import java.io.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "transports")
public class Transport implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "transportCompanyTransports")
    private TransportCompany transportCompany;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "type", nullable = false)
    private TransportType transportType;

    @Column(name = "price", nullable = false)
    private BigDecimal price;

    @Column(name = "is_paid")
    private Boolean isPaid;

    @Column(name = "start_point", nullable = false)
    private String startPoint;

    @Column(name = "end_point", nullable = false)
    private String endPoint;

    @Column(name = "start_date", nullable = false)
    private LocalDate startDate;

    @Column(name = "end_date", nullable = false)
    private LocalDate endDate;

    @Column(name = "total_weight")
    private String totalWeight;

    public long getId() {
        return id;
    }

    public Transport(long id, TransportCompany transportCompany, Employee employee, Client client, TransportType transportType, BigDecimal price, Boolean isPaid, String startPoint, String endPoint, LocalDate startDate, LocalDate endDate, String totalWeight) {
        this.id = id;
        this.transportCompany = transportCompany;
        this.employee = employee;
        this.client = client;
        this.transportType = transportType;
        this.price = price;
        this.isPaid = isPaid;
        this.startPoint = startPoint;
        this.endPoint = endPoint;
        this.startDate = startDate;
        this.endDate = endDate;
        this.totalWeight = totalWeight;
    }


    public void serialize() {
        String fileName = this.getId() + ".ser";
        File filePath = new File("./Transports/" + fileName);

        try (
                FileOutputStream fos = new FileOutputStream(filePath);
                ObjectOutputStream oos = new ObjectOutputStream(fos)
        ) {
            oos.writeObject(this);
        } catch (IOException exception) {
            System.out.println(exception);
        }
    }

    public void deserialize() {
        String fileName = this.getId() + ".txt";

        try(
            FileWriter fw = new FileWriter("./Transports/" + fileName, false)
        ) {
            fw.write(this.toString());
        } catch (IOException exception) {
            System.out.println(exception);
        }
    }

    @Override
    public String toString() {
        return "Transport{" +
                "id=" + id +
                ", transportType=" + transportType +
                ", price=" + price +
                ", isPaid=" + isPaid +
                ", startPoint='" + startPoint + '\'' +
                ", endPoint='" + endPoint + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", totalWeight='" + totalWeight + '\'' +
                '}';
    }
}
