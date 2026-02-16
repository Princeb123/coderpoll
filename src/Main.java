import java.sql.Connection;
import java.util.List;
import java.util.Scanner;

public class Main {

    static RideBookingSystem system = new RideBookingSystem();
    static Scanner sc = new Scanner(System.in);
    static user loggedUser = null;

    public static void main(String[] args) {

        Connection conn = DBConnection.getConnection();
        if (conn == null) return;

        while (true) {

            System.out.println("\n1 Create Account");
            System.out.println("2 Login");
            System.out.println("3 Create Ride");
            System.out.println("4 View Rides");
            System.out.println("5 Book Ride");
            System.out.println("0 Exit");

            System.out.print("Enter choice: ");
            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {

                case 1:
                    System.out.print("Enter name: ");
                    String name = sc.nextLine();

                    System.out.print("Enter email: ");
                    String email = sc.nextLine();

                    System.out.print("Enter password: ");
                    String pass = sc.nextLine();

                    loggedUser = system.signup(name, email, pass);
                    break;

                case 2:
                    System.out.print("Enter email: ");
                    String em = sc.nextLine();

                    System.out.print("Enter password: ");
                    String pw = sc.nextLine();

                    loggedUser = system.login(em, pw);
                    break;

                case 3:
                    if (loggedUser == null) {
                        System.out.println("Login first");
                        break;
                    }

                    System.out.print("Enter source: ");
                    String src = sc.nextLine();

                    System.out.print("Enter destination: ");
                    String dest = sc.nextLine();

                    System.out.print("Enter seats: ");
                    int seats = sc.nextInt();

                    System.out.print("Enter fare: ");
                    double fare = sc.nextDouble();

                    system.createRide(src, dest, seats, fare, loggedUser);
                    break;

                case 4:
                    if (loggedUser == null) {
                        System.out.println("Login first");
                        break;
                    }

                    List<Ride> rides = system.viewAllRidesFromDB();
                    if (rides.isEmpty()) {
                        System.out.println("No rides available");
                    } else {
                        for (Ride r : rides) {
                            System.out.println(
                                    "Ride ID: " + r.id +
                                            " | " + r.source + " -> " + r.destination +
                                            " | Fare: " + r.fare +
                                            " | Owner: " + r.user
                            );
                        }
                    }
                    break;

                case 5:
                    if (loggedUser == null) {
                        System.out.println("Login first");
                        break;
                    }

                    System.out.print("Enter ride id: ");
                    int rid = sc.nextInt();

                    System.out.print("Enter seats: ");
                    int sb = sc.nextInt();

                    System.out.print("Enter total fare: ");
                    int tf = sc.nextInt();

                    system.bookRide(rid, loggedUser, sb, tf);
                    break;

                case 0:
                    System.exit(0);
            }
        }
    }
}

