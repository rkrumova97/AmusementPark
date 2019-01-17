package dao;

import model.Attraction;
import model.Park;
import model.Ticket;
import util.DaoUtil;

import java.util.ArrayList;
import java.util.List;

public class ParkDao {
    public static Park addPark(String name, String manager, Double tp, List<String> attractions) {
        List<Attraction> attractionList = new ArrayList<>();
        for (String attraction : attractions) {
            attractionList.add(DaoUtil.findByName("Attraction", attraction));
        }
        Park park = Park.builder()
                .name(name)
                .manager(DaoUtil.findByName("Manager", manager))
                .ticketPrice(tp)
                .attractions(attractionList)
                .build();
        return DaoUtil.save(park);
    }

    public static Double getAvgAge(Park park) {
        double avg = 0;
        for (Ticket ticket : park.getTickets()) {
            avg += ticket.getChild().getAge();
        }
        return avg / park.getTickets().size();
    }

    public static Double getAvgAgeForAllParks() {
        List<Park> parks = DaoUtil.findAll("Park");
        double avg = 0;
        for (Park park : parks) {
            avg += getAvgAge(park);
        }
        return avg / parks.size();
    }
}
