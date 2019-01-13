package dao;

import model.Attraction;
import model.Park;
import model.Ticket;
import util.DaoUtil;

import java.util.ArrayList;
import java.util.List;

public class TicketDao {
    private static int i = 0;
    private static String number = "ticket" + i;

    public static Ticket addTicket(String name, Integer age, String park, List<String> attractions) {
        List<Attraction> attractionList = new ArrayList<>();
        for(String s : attractions) {
            Attraction attraction1 = DaoUtil.findByName("Attraction", s);
            if(attraction1.getMinAge() < age){
                attractionList.add(attraction1);
            }
        }

        Park park1 = DaoUtil.findByName("Park",park);
        Ticket ticket = Ticket.builder()
                .child(ChildDao.addChild(name,age,attractionList))
                .nameOfTicket(number)
                .park(park1)
                .build();
        i++;
        park1.setProfit();
        return DaoUtil.save(ticket);
    }
}
