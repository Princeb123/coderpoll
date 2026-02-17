public class Booking {
    int id;
    String source;
    String destination;
    int seats;
    double fare;

    public Booking(int id, String source, String destination, int seats, double fare) {
        this.id = id;
        this.source = source;
        this.destination = destination;
        this.seats = seats;
        this.fare = fare;
    }

    public int getId() {
        return id;
    }

    public String getSource() {
        return source;
    }

    public String getDestination() {
        return destination;
    }

    public int getSeats() {
        return seats;
    }

    public double getFare() {
        return fare;
    }
}