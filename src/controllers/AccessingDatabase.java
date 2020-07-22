package controllers;

import models.*;

import java.util.*;
import java.sql.*;

abstract public class AccessingDatabase {
    final static String URL = "jdbc:mysql://localhost:3306/cscp2014_assignment_2";
    final static String USER = "root";
    final static String PASSWORD = "";
    static Connection connection;
    static Statement stmt;

    static {
        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            stmt = connection.createStatement();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

class AccessingMovieDatabase extends AccessingDatabase {
    public static int insertNewRecords(Movie movie) throws Exception {
        String sql = String.format("insert into movie (id, name, type, showtime, language, subtitle, classification) " +
                " values( %d, '%s' ,'%s', '%s', '%s', '%s', '%s')", movie.getId(), movie.getName(), movie.getType(), movie.getTimeSlot(), movie.getLanguage(), movie.getSubtitle(), movie.getClassification());
        return stmt.executeUpdate(sql);
    }

    public static int isExistedMovie(Movie movie) throws Exception {
        String sql = String.format("select * from movie where showtime = '%s'", movie.getTimeSlot());
        ResultSet rs = stmt.executeQuery(sql);
        if (rs.next())
            return 1;
        else
            return 0;
    }

    public static int isExistMovie(Movie movie) throws Exception {
        String sql = String.format("select * from movie where id = %d ", movie.getId());
        ResultSet rs = stmt.executeQuery(sql);
        if (rs.next())
            return 1 + isExistMovie2(movie) + isExistedMovie(movie);
        else
            return isExistMovie2(movie) + isExistedMovie(movie);
    }

    public static int isExistMovie2(Movie movie) throws Exception {
        String sql = String.format("select * from movie where name = '%s' ", movie.getName());
        ResultSet rs = stmt.executeQuery(sql);
        if (rs.next())
            return 1;
        else
            return 0;
    }

    public static List<Movie> retrieveAllRecords() throws Exception {
        List<Movie> movies = new ArrayList<Movie>();
        String sql = "select * from movie order by id, name";
        ResultSet rs = stmt.executeQuery(sql);

        while (rs.next()) {
            Movie movie = new Movie(rs.getInt("id"), rs.getString("name"), rs.getString("type"),
                    rs.getString("showtime"), rs.getString("language"), rs.getString("subtitle"), rs.getString("classification"));
            movies.add(movie);
        }
        return movies;
    }

    public static Movie retrieveOneRecord(Movie movie) throws Exception {
        String sql = String.format("select * from movie where name = '%s'", movie.getName());
        ResultSet rs = stmt.executeQuery(sql);
        while (rs.next()) {
            movie = new Movie(rs.getInt("id"), rs.getString("name"), rs.getString("type"),
                    rs.getString("showtime"), rs.getString("language"), rs.getString("subtitle"), rs.getString("classification"));

        }
        return movie;
    }

    public static int updateRecords(Movie movie) throws Exception {
        String sql = String.format("update movie set showtime = '%s' where name = '%s'", movie.getTimeSlot(), movie.getName());
        return stmt.executeUpdate(sql);
    }

    public static int deleteRecords(Movie movie) throws Exception {
        String sql = String.format("delete from movie where name = '%s'", movie.getName());
        return stmt.executeUpdate(sql);
    }
}

class AccessingSeatDatabase extends AccessingDatabase {
    public static int insertNewRecords(Seat seat) throws Exception {
        String sql = String.format("insert into seat (status, `vip seat`, row, number, price) " +
                " values('%s', '%s', '%s', %d, '%.2f')", seat.getStatus(), seat.getVipSeat(), seat.getSeatRow(), seat.getSeatNumber(), seat.getPrice());
        return stmt.executeUpdate(sql);
    }

    public static List<Seat> retrieveAllRecords() throws Exception {
        List<Seat> seats = new ArrayList<Seat>();
        String sql = "select * from seat order by `row`";
        ResultSet rs = stmt.executeQuery(sql);
        while (rs.next()) {
            Seat seat = new Seat(rs.getString("vip seat"), rs.getString("row"), rs.getInt("number"));
            seats.add(seat);
        }
        return seats;
    }

    public static Seat retrieveOneRecord(Seat seat) throws Exception {
        String sql = String.format("select * from seat where `row` = '%s' and number = %d", seat.getSeatRow(), seat.getSeatNumber());
        ResultSet rs = stmt.executeQuery(sql);
        while (rs.next()) {
            seat.setVipSeat(rs.getString("vip seat"));
            seat.setStatus();
            seat.setPrice();
        }
        return seat;
    }

    public static boolean isExistSeat() throws Exception {
        String sql = String.format("select * from seat where `row` = '%s'", "E");
        return stmt.executeQuery(sql).next();
    }

    public static int deleteExtendedSeat() throws Exception {
        String sql = String.format("delete from seat where `row` = '%s'", "E");
        return stmt.executeUpdate(sql);
    }

    public static int updateSeat(Seat seat) throws Exception {
        String sql = String.format("update seat set `vip seat` = '%s', price = %.2f where `row` = '%s' and number = %d", seat.getVipSeat(), seat.getPrice(), seat.getSeatRow(), seat.getSeatNumber());
        return stmt.executeUpdate(sql);
    }

}

class AccessingTicketDatabase extends AccessingDatabase {
    public static int insertNewRecords(Ticket ticket) throws Exception {
        String sql = String.format("insert into ticket(id, movie, seat, price, `date of purchase`, `phone number`)" +
                " values ('%s','%s','%s', %.2f , '%s' , '%s')", ticket.getId(), ticket.getMovie().getName(), ticket.getSeat().getSeatRow() + ticket.getSeat().getSeatNumber(), ticket.getPrice(), ticket.getDate(), ticket.getCustomerPhoneNumber());
        return stmt.executeUpdate(sql);
    }

    public static List<Ticket> retrieveAllRecords(Movie movie) throws Exception {
        String sql = String.format("select * from ticket where movie = '%s' order by seat", movie.getName());
        ResultSet rs = stmt.executeQuery(sql);
        List<Ticket> tickets = new ArrayList<Ticket>();
        while (rs.next()) {
            Ticket ticket = new Ticket(rs.getString("id"), rs.getDouble("price"), rs.getString("phone number"), rs.getString("date of purchase"));
            ticket.setSeat2(rs.getString("seat"));
            tickets.add(ticket);
        }
        return tickets;
    }

    public static Ticket retrieveOneRecords(Ticket ticket) throws Exception {
        String sql = String.format("select * from ticket where id = '%s'", ticket.getId());
        ResultSet rs = stmt.executeQuery(sql);
        while (rs.next()) {
            ticket.setCustomerPhoneNumber(rs.getString("phone number"));
            ticket.setSeat2(rs.getString("seat"));
        }
        return ticket;
    }

    public static boolean isExistMovie(Movie movie) throws Exception {
        String sql = String.format("select * from ticket where movie = '%s'", movie.getName());
        ResultSet rs = stmt.executeQuery(sql);
        if (rs.next())
            return true;
        else
            return false;
    }

    public static boolean isExistTicket(Ticket ticket) throws Exception {
        String sql = String.format("select * from ticket where id = '%s'", ticket.getId());
        ResultSet rs = stmt.executeQuery(sql);
        if (rs.next()) {
            return true;
        } else
            return false;
    }

    public static boolean isExistSeat(String seatLocation) throws Exception {
        String sql = String.format("select * from ticket where seat = '%s'", seatLocation);
        ResultSet rs = stmt.executeQuery(sql);
        if (rs.next())
            return true;
        else
            return false;
    }

    public static int updateTicket(Ticket ticket, String newSeat) throws Exception {
        String sql = String.format("update ticket set id = '%s', seat = '%s', price = %.2f where seat = '%s'", ticket.getId(), newSeat, ticket.getPrice(), ticket.getSeat2());
        return stmt.executeUpdate(sql);
    }

    public static int deleteTicket(String id) throws Exception {
        String sql = String.format("delete from ticket where id = '%s'", id);
        return stmt.executeUpdate(sql);
    }


}
