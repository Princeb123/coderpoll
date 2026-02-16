public class Ride {

    int id;
    String source;
    String destination;
    int seats;
    double fare;
    user user;

    public Ride(int id, String source, String destination, int seats, double fare, user user) {
        this.id = id;
        this.source = source;
        this.destination = destination;
        this.seats = seats;
        this.fare = fare;
        this.user = user;
    }

    public String toString() {
        return source + " -> " + destination + " seats=" + seats + " fare=" + fare;
    }
}