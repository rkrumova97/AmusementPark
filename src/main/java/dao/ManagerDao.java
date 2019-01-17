package dao;

import model.Manager;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.DaoUtil;
import util.HibernateUtil;

public class ManagerDao {
    public static Manager addManager(String name, Double salary) {
        Manager manager = Manager.builder()
                .name(name)
                .salary(salary)
                .build();
        return DaoUtil.save(manager);
    }

    public static Double promote(Double profit, Double percent, String managerName) {
        Manager manager = DaoUtil.findByName("Manager", managerName);
        double newSalary = 0;
        if (manager.getPark().getProfit() > profit) {
            newSalary += manager.getSalary() * (percent/100);
        }
        else{
            newSalary = manager.getSalary();
        }
        updateSalary(manager, newSalary);
        return newSalary;
    }

    private static void updateSalary(Manager manager, double salary) {
        Transaction tx = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            Manager newSalary = session.get(Manager.class, manager.getId());
            newSalary.setSalary(salary);
            session.update(newSalary);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        }
    }
}
