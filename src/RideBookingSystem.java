
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class RideBookingSystem {


    public void signup(String name, String email, String password) {

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement("INSERT INTO users(name, email, password) VALUES (?, ?, ?)")) {

            ps.setString(1, name);
            ps.setString(2, email);
            ps.setString(3, password);

            ps.executeUpdate();
            System.out.println("Signup successful");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }




    public user login(String email, String password) {

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement("SELECT * FROM users WHERE email = ? AND password = ?")) {

            ps.setString(1, email);
            ps.setString(2, password);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                user loggedInUser = new user(
                        rs.getInt("user_id"),
                        rs.getString("name"),
                        rs.getString("email"),
                        rs.getString("password")
                );

                System.out.println("Login successful");
                System.out.println(loggedInUser.toString());

                return loggedInUser;
            } else {
                System.out.println("Invalid email or password");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void updateUserDetails(int user_id, String name, String email, String password) {

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement("UPDATE users SET name = ?, email = ?, password = ? WHERE user_id = ?")) {

            ps.setString(1, name);
            ps.setString(2, email);
            ps.setString(3, password);
            ps.setInt(4, user_id);

            int rowsUpdated = ps.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("User details updated successfully");
            } else {
                System.out.println("User not found");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteUserDetails(int user_id){
        for(user u: users) {
            if (u.user_id == user_id) {
                users.remove(u);
                System.out.println("User details removed");
            }
        }

    }

    public List<Ride> rideList = new ArrayList<>();

    public void createRide(String source, String destination,int availableSeats, int totalSeats,double fare, user user) {

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement("INSERT INTO rides(source, destination, available_seats, total_seats, fare, user_id) VALUES (?, ?, ?, ?, ?, ?)")) {

            ps.setString(1, source);
            ps.setString(2, destination);
            ps.setInt(3, availableSeats);
            ps.setInt(4, totalSeats);
            ps.setDouble(5, fare);
            ps.setInt(6, user.user_id);

            ps.executeUpdate();
            System.out.println("Ride created");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Ride> showAllRide(){
        return rideList;
    }

    public List<Ride> searchRide(String source, String destination, int seats) {

        List<Ride> rides = new ArrayList<>();

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement("SELECT * FROM rides WHERE source = ? AND destination = ? AND available_seats >= ?")) {

            ps.setString(1, source);
            ps.setString(2, destination);
            ps.setInt(3, seats);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Ride ride = new Ride(
                        rs.getInt("id"),
                        rs.getString("source"),
                        rs.getString("destination"),
                        rs.getInt("available_seats"),
                        rs.getInt("total_seats"),
                        rs.getDouble("fare"),
                        null
                );
                rides.add(ride);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return rides;
    }


    public void updateRide(int rideId, String newSource, String newDestination, int newTotalSeats, double newFare) {
        for (Ride ride : rideList) {
            if (ride.id == rideId) {
                ride.source = newSource;
                ride.destination = newDestination;
                ride.total_seats = newTotalSeats;
                ride.available_seats = newTotalSeats;
                ride.fare = newFare;
            }
        }
    }

     public void deleteRide(int rideId){
        for (Ride ride : rideList) {
            if (ride.id == rideId) {
                rideList.remove(ride);
                System.out.println("Ride deleted");
            }
        }

    }
    public List<booking> bookedList =new ArrayList<>();

    public void bookRide(Ride ride,user user,int seats_booked ){
        // search ride
        // available seats
        // fare calculate
        // booking create
        // seats update
    }
     public List<Ride> viewRideCreated(user user){
//        rideList->filter-userid
        List<Ride> viewCreated =new ArrayList<>();
        for(Ride ride : rideList){
            if(ride.user.equals(user)){
                viewCreated.add(ride);
            }
        }
        return viewCreated;
    }

    public List<booking> viewRideBooked(user user){
        //booking-> filter-userid
        List<booking> viewBooked  =new ArrayList<>();
        for(booking booked : bookedList) {
            if (booked.user.equals(user)) {
                viewBooked.add(booked);
            }
        }
        return viewBooked;
    }

     public int updateBooking(Ride more,int seats_booked){
        //seats_booked

             if(seats_booked<=more.available_seats){
                 more.available_seats -=seats_booked;
                 System.out.println("Seats is booked");

             }

             else  {
                 System.out.println("Seats not available");
             }
             return more.available_seats;

    }

    public int deletedBooking( Ride book,int seats_booked ){
        book.available_seats +=seats_booked;

        bookedList.remove(book);
        return book.available_seats;
    }

}
//future scope:
//time
//Authentication & Authorization
//admin
//mul location
//price bargain
//AI chatboat & fare recommendation
//review
//chat
