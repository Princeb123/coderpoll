
import java.util.ArrayList;
import java.util.List;

public class RideBookingSystem {
    public List<user> users = new ArrayList<>();

    public List<user> signup(int user_id, String name, String email, String password){
        if(email == null || email.trim().isEmpty() || password == null || password.isEmpty()) {
            System.out.println("Your email or password cannot be empty");
        }
        for(user u : users) {
            if (u.email.equals(email) && u.password.equals(password)) {
                System.out.println("Email and password already exists");
            }
        }

        user user = new user(user_id, name , email, password);
        users.add(user);
        System.out.println("Signup successful for ");
        return users;

    }


    public user login(String email, String password){

        if(email == null || email.trim().isEmpty() || password == null || password.isEmpty()) {
            System.out.println("Your email or password cannot be empty");
        }
       else if(email != null || !email.trim().isEmpty() && password != null || !password.isEmpty()){
           for(user u : users){
                if(u.email.equals(email) && u.password.equals(password)){
                    System.out.println("login successful");
                    return u;
                }

           }

        }
       else {
           System.out.println("Enter correct Email and password");
        }
        return null;
    }
     public void updateUserDetails(int user_id, String name, String email, String password){
        for(user u: users) {
            if (u.user_id == user_id) {
                u.name = name;
                u.email = email;
                u.password = password;
                System.out.println("details updated");
            }
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

    public void createRide(int id, String source, String destination, int available_seats,int total_seats, double fare ,user user){
        Ride ride = new Ride(id,source,destination,available_seats,total_seats,fare,user);
        rideList.add(ride);
    }
    public List<Ride> showAllRide(){
        return rideList;
    }

    public List<Ride> searchRide(String source, String destination, int available_seats){
        List<Ride> availableRide = new ArrayList<>();
        for (Ride ride: rideList ){
            if(ride.source.equals(source) && ride.destination.equals(destination) && ride.available_seats >= available_seats){
                availableRide.add(ride);
            }

        }
        return availableRide;
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
//futur scope:
//time
//Authentication & Authorization
//admin
//mul location
//price bargain
//AI chatboat & fare recommendation
//review
//chat
