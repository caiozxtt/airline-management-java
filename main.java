// Airline Management System - United Airlines Example
// Author: Caio Carvalho

import java.util.*;

class Flight {
    private String flightNumber;
    private String origin;
    private String destination;
    private int availableSeats;

    public Flight(String flightNumber, String origin, String destination, int availableSeats) {
        this.flightNumber = flightNumber;
        this.origin = origin;
        this.destination = destination;
        this.availableSeats = availableSeats;
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public int getAvailableSeats() {
        return availableSeats;
    }

    public void bookSeat() {
        if (availableSeats > 0) {
            availableSeats--;
        }
    }

    @Override
    public String toString() {
        return "Flight " + flightNumber + " | " + origin + " -> " + destination +
               " | Seats: " + availableSeats;
    }
}

class Passenger {
    private String name;
    private String passport;

    public Passenger(String name, String passport) {
        this.name = name;
        this.passport = passport;
    }

    public String getName() {
        return name;
    }
}

class Reservation {
    private Passenger passenger;
    private Flight flight;

    public Reservation(Passenger passenger, Flight flight) {
        this.passenger = passenger;
        this.flight = flight;
    }

    @Override
    public String toString() {
        return "Reservation: " + passenger.getName() + " | " + flight.getFlightNumber();
    }
}

public class Main {

    private static List<Flight> flights = new ArrayList<>();
    private static List<Reservation> reservations = new ArrayList<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        initializeFlights();

        int option;

        do {
            showMenu();
            option = scanner.nextInt();
            scanner.nextLine();

            switch (option) {
                case 1:
                    listFlights();
                    break;
                case 2:
                    makeReservation(scanner);
                    break;
                case 3:
                    listReservations();
                    break;
                case 0:
                    System.out.println("Exiting system...");
                    break;
                default:
                    System.out.println("Invalid option.");
            }

        } while (option != 0);

        scanner.close();
    }

    private static void showMenu() {
        System.out.println("\n=== United Airlines Management System ===");
        System.out.println("1. View Flights");
        System.out.println("2. Make Reservation");
        System.out.println("3. View Reservations");
        System.out.println("0. Exit");
        System.out.print("Choose an option: ");
    }

    private static void initializeFlights() {
        flights.add(new Flight("UA101", "New York", "Chicago", 120));
        flights.add(new Flight("UA202", "Los Angeles", "Miami", 90));
        flights.add(new Flight("UA303", "Houston", "San Francisco", 75));
        flights.add(new Flight("UA404", "Denver", "Seattle", 60));
    }

    private static void listFlights() {
        System.out.println("\nAvailable Flights:");
        for (Flight flight : flights) {
            System.out.println(flight);
        }
    }

    private static void makeReservation(Scanner scanner) {
        System.out.print("Passenger Name: ");
        String name = scanner.nextLine();

        System.out.print("Passport Number: ");
        String passport = scanner.nextLine();

        System.out.print("Flight Number: ");
        String flightNumber = scanner.nextLine();

        Flight selectedFlight = null;

        for (Flight flight : flights) {
            if (flight.getFlightNumber().equalsIgnoreCase(flightNumber)) {
                selectedFlight = flight;
                break;
            }
        }

        if (selectedFlight == null) {
            System.out.println("Flight not found.");
            return;
        }

        if (selectedFlight.getAvailableSeats() == 0) {
            System.out.println("No seats available.");
            return;
        }

        Passenger passenger = new Passenger(name, passport);
        selectedFlight.bookSeat();

        Reservation reservation = new Reservation(passenger, selectedFlight);
        reservations.add(reservation);

        System.out.println("Reservation completed successfully!");
    }

    private static void listReservations() {
        System.out.println("\nReservations:");

        if (reservations.isEmpty()) {
            System.out.println("No reservations found.");
            return;
        }

        for (Reservation r : reservations) {
            System.out.println(r);
        }
    }
}
