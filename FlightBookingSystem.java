package day8;
import java.util.*;
import java.util.concurrent.*;

// Flight class representing flight details
class Flight {
    private final String flightNumber;
    private final String source;
    private final String destination;
    private int availableSeats;

    public Flight(String flightNumber, String source, String destination, int seats) {
        this.flightNumber = flightNumber;
        this.source = source;
        this.destination = destination;
        this.availableSeats = seats;
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public String getSource() {
        return source;
    }

    public String getDestination() {
        return destination;
    }

    public synchronized boolean bookSeat() {
        if (availableSeats > 0) {
            availableSeats--;
            return true;
        }
        return false;
    }

    public int getAvailableSeats() {
        return availableSeats;
    }

    @Override
    public String toString() {
        return "Flight{" +
                "flightNumber='" + flightNumber + '\'' +
                ", source='" + source + '\'' +
                ", destination='" + destination + '\'' +
                ", availableSeats=" + availableSeats +
                '}';
    }
}

// FlightSearchTask simulates fetching available flights asynchronously
class FlightSearchTask implements Callable<List<Flight>> {
    private final String source;
    private final String destination;
    private final List<Flight> allFlights;

    public FlightSearchTask(String source, String destination, List<Flight> allFlights) {
        this.source = source;
        this.destination = destination;
        this.allFlights = allFlights;
    }

    @Override
    public List<Flight> call() {
        List<Flight> availableFlights = new ArrayList<>();
        for (Flight flight : allFlights) {
            if (flight.getSource().equalsIgnoreCase(source) &&
                flight.getDestination().equalsIgnoreCase(destination)) {
                availableFlights.add(flight);
            }
        }
        return availableFlights;
    }
}

// FlightBookingSystem handles user requests
public class FlightBookingSystem {
    private static final List<Flight> flights = new ArrayList<>();
    private static final ExecutorService executor = Executors.newFixedThreadPool(5);

    static {
        // Sample flight data
        flights.add(new Flight("AI101", "New York", "Los Angeles", 3));
        flights.add(new Flight("BA202", "New York", "Chicago", 2));
        flights.add(new Flight("CX303", "Los Angeles", "San Francisco", 4));
        flights.add(new Flight("DL404", "Chicago", "New York", 5));
    }

    public static Future<List<Flight>> searchFlights(String source, String destination) {
        return executor.submit(new FlightSearchTask(source, destination, flights));
    }

    public static void bookTicket(String flightNumber) {
        for (Flight flight : flights) {
            if (flight.getFlightNumber().equalsIgnoreCase(flightNumber)) {
                synchronized (flight) {
                    if (flight.bookSeat()) {
                        System.out.println("Ticket booked for flight: " + flightNumber);
                    } else {
                        System.out.println("No available seats on flight: " + flightNumber);
                    }
                }
                return;
            }
        }
        System.out.println("Flight not found: " + flightNumber);
    }

    public static void shutdown() {
        executor.shutdown();
        try {
            if (!executor.awaitTermination(5, TimeUnit.SECONDS)) {
                executor.shutdownNow();
            }
        } catch (InterruptedException e) {
            executor.shutdownNow();
        }
        System.out.println("Flight Booking System shut down.");
    }

    public static void main(String[] args) {
        try {
            // User 1 searching for flights
            Future<List<Flight>> futureFlights1 = searchFlights("New York", "Los Angeles");

            // User 2 searching for flights
            Future<List<Flight>> futureFlights2 = searchFlights("Los Angeles", "San Francisco");

            // Fetching flight results
            List<Flight> flights1 = futureFlights1.get();
            List<Flight> flights2 = futureFlights2.get();

            System.out.println("Available flights for User 1: " + flights1);
            System.out.println("Available flights for User 2: " + flights2);

            // User 3 and User 4 booking tickets simultaneously
            Thread user3 = new Thread(() -> bookTicket("AI101"));
            Thread user4 = new Thread(() -> bookTicket("AI101"));
            Thread user5 = new Thread(() -> bookTicket("BA202"));

            user3.start();
            user4.start();
            user5.start();

            user3.join();
            user4.join();
            user5.join();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            shutdown();
        }
    }
}