import dao.*;
import entity.*;
import net.bytebuddy.asm.Advice;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String [] args) {

        TransportCompany transportCompany1 = new TransportCompany(1, "transportCompany1");
        TransportCompany transportCompany2 = new TransportCompany(2, "transportCompany2");
        TransportCompany transportCompany3 = new TransportCompany(3, "transportCompany3");

        List<TransportCompany> transportCompanies = new ArrayList<>();
        transportCompanies.add(transportCompany1);
        transportCompanies.add(transportCompany2);
        transportCompanies.add(transportCompany3);

        TransportCompanyDAO.saveMultiple(transportCompanies);

        Employee employee1 = new Employee(1, "employee1", BigDecimal.valueOf(500), transportCompany1);
        Employee employee2 = new Employee(2, "employee2", BigDecimal.valueOf(5000), transportCompany2);
        Employee employee3 = new Employee(3, "employee3", BigDecimal.valueOf(1000), transportCompany2);

        List<Employee> employeesEdible = new ArrayList<>();
        employeesEdible.add(employee1);
        employeesEdible.add(employee3);

        EmployeeDAO.saveOne(employee1);
        EmployeeDAO.saveOne(employee2);
        EmployeeDAO.saveOne(employee3);

        Client client1 = new Client(1, "client1");
        Client client2 = new Client(2, "client2");
        Client client3 = new Client(3, "client3");
        Client client4 = new Client(4, "client4");
        Client client5 = new Client(5, "client5");

        client1.addTransportCompany(transportCompany1);
        client2.addTransportCompany(transportCompany1);
        client3.addTransportCompany(transportCompany1);
        client4.addTransportCompany(transportCompany2);
        client5.addTransportCompany(transportCompany3);

        List<Client> clients = new ArrayList<>();
        clients.add(client2);
        clients.add(client3);
        clients.add(client4);
        clients.add(client5);

        ClientDAO.saveOne(client1);
        ClientDAO.saveMultiple(clients);

        Vehicle vehicle1 = new Vehicle(1, "vehicle1", transportCompany1, VehicleType.CAR);
        Vehicle vehicle2 = new Vehicle(2, "vehicle2", transportCompany2, VehicleType.AIRPLANE);
        Vehicle vehicle3 = new Vehicle(3, "vehicle3", transportCompany3, VehicleType.MINIVAN);

        List<Vehicle> vehicles = new ArrayList<>();
        vehicles.add(vehicle1);
        vehicles.add(vehicle2);
        vehicles.add(vehicle3);

        VehicleDAO.saveMultiple(vehicles);

        Capabilities capabilities1 = new Capabilities(1, CapabilitiesType.EDIBLE, employeesEdible);

        CapabilitiesDAO.saveOne(capabilities1);

        Transport transport1 = new Transport(1, transportCompany1, employee1, client1, TransportType.PEOPLE, BigDecimal.valueOf(1200), true,
                "Sofia", "Balchik", LocalDate.of(2022, 1, 10), LocalDate.of(2022, 3, 15), "1");

        Transport transport2 = new Transport(2, transportCompany2, employee2, client3, TransportType.WEAPONS, BigDecimal.valueOf(122000), true,
                "zavod Arsenal", "Afrika", LocalDate.of(2022, 1, 1), LocalDate.of(2022, 7, 15), "10");


        TransportDAO.saveOne(transport1);
        TransportDAO.saveOne(transport2);

        client1.addTransport(transport1);
        ClientDAO.saveOne(client1);


        employee1.addCapabilities(capabilities1);
        employee1.addTransport(transport1);

        employee2.addCapabilities(capabilities1);
        employee2.addTransport(transport2);

        EmployeeDAO.saveOne(employee1);
        EmployeeDAO.saveOne(employee2);

        transportCompany1.addTransport(transport1);
        transportCompany1.addEmployee(employee1);
        transportCompany1.addVehicle(vehicle1);
        transportCompany1.addClient(client1);

        TransportCompanyDAO.saveOne(transportCompany1);

        List<Transport> serializeTransports = new ArrayList<>();
        serializeTransports.add(transport1);
        serializeTransports.add(transport2);

        serializeTransports.forEach(Transport::serialize);
        serializeTransports.forEach(Transport::deserialize);

//        TransportCompanyDAO.saveOrUpdateOne(transportCompany3);

//        TransportCompanyDAO.deleteOne(transportCompany3);

    }

}
