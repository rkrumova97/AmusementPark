package dao;

import model.Attraction;
import util.DaoUtil;

public class AttractionDao {
    public static Attraction addAttraction(String name, Integer age) {
        Attraction attraction = Attraction.builder()
                .name(name)
                .minAge(age)
                .build();
        return DaoUtil.save(attraction);
    }
}
