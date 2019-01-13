package dao;

import model.Attraction;
import model.Child;
import util.DaoUtil;

import java.util.List;

public class ChildDao {
    public static Child addChild(String name, Integer age, List<Attraction> attraction) {
        Child child = Child.builder()
                .name(name)
                .age(age)
                .attraction(attraction)
                .build();

        return DaoUtil.save(child);
    }


}
