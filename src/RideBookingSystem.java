
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class RideBookingSystem {

    public User signup(String name, String email, String password) {

        User user = null;

        try {
            Connection conn = DBConnection.getConnection();

            String checkSql = "SELECT user_id FROM users WHERE email = ?";
            PreparedStatement checkPs = conn.prepareStatement(checkSql);
            checkPs.setString(1, email);

            ResultSet checkRs = checkPs.executeQuery();
            if (checkRs.next()) {
                System.out.println("Account already exists. Please login.");
                return null;
            }

            String sql = "INSERT INTO users(name,email,pass) VALUES (?,?,?) RETURNING user_id";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, name);
            ps.setString(2, email);
            ps.setString(3, password);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                int id = rs.getInt("user_id");
                user = new User(id, name, email, password);
                System.out.println("Account created successfully");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return user;
    }

    public User login(String email, String password) {

        User user = null;

        try {
            Connection conn = DBConnection.getConnection();

            String sql = "SELECT user_id, name FROM users WHERE email = ? AND pass = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, email);
            ps.setString(2, password);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                int id = rs.getInt("user_id");
                String name = rs.getString("name");
                user = new User(id, name, email, password);
                System.out.println("Login Successful");
            } else {
                System.out.println("Invalid login");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return user;
    }

    public int createRide(String source, String destination, int seats, double fare, User user) {

        int rideId = -1;

        try {
            Connection conn = DBConnection.getConnection();

            String sql = """
                    INSERT INTO ride(source, dest, fare, created_by)
                    VALUES (?, ?, ?, ?)
                    RETURNING ride_id
                    """;

            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, source);
            ps.setString(2, destination);
            ps.setDouble(3, fare);
            ps.setInt(4, user.user_id);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                rideId = rs.getInt("ride_id");
                System.out.println("Ride created with Ride ID: " + rideId);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return rideId;
    }

    public List<Ride> viewAllRidesFromDB() {

        List<Ride> rides = new ArrayList<>();

        try {
            Connection conn = DBConnection.getConnection();

            String sql = """
                    SELECT r.ride_id, r.source, r.dest, r.fare,
                           u.user_id, u.name
                    FROM ride r
                    JOIN users u ON r.created_by = u.user_id
                    ORDER BY r.ride_id
                    """;

            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("ride_id");
                String source = rs.getString("source");
                String dest = rs.getString("dest");
                double fare = rs.getDouble("fare");

                int uid = rs.getInt("user_id");
                String uname = rs.getString("name");

                User owner = new User(uid, uname, null, null);
                Ride ride = new Ride(id, source, dest, 0, fare, owner);
                rides.add(ride);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return rides;
    }

    public void bookRide(int rideId, User user, int seats, int totalFare) {

        try {
            Connection conn = DBConnection.getConnection();

            String sql = """
                    INSERT INTO booking(ride_id, user_id, seats_booked, total_fare)
                    VALUES (?, ?, ?, ?)
                    """;

            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, rideId);
            ps.setInt(2, user.user_id);
            ps.setInt(3, seats);
            ps.setInt(4, totalFare);

            ps.executeUpdate();
            System.out.println("Ride booked successfully");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}