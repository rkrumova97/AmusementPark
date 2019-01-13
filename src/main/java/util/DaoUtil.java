package util;

import javafx.scene.control.Alert;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class DaoUtil {


    public static <T> T save(T entity) {
        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            session.save(entity);
            tx.commit();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Successful Dialog");
            alert.setHeaderText("SAVED");
            alert.setContentText("YOUR DATA WAS SAVED!");
            alert.showAndWait();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setHeaderText("SOMETHING IS WRONG");
            alert.setContentText("SORRY, YOUR DATA WAS NOT SAVED!");
            alert.showAndWait();
        }
        return entity;
    }

    public static <T> T findByName(String table, String nam) {
        T entity = null;
        Transaction tx = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            entity = (T) session.createQuery("FROM " + table + " t where t.name = '" + nam + "'").getSingleResult();
            tx.commit();
        } catch (HibernateException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setHeaderText("NOT FOUND");
            alert.setContentText("SORRY, YOUR DATA IS NOT IN THE TABLE!");
            alert.showAndWait();
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();

        }
        return entity;
    }

    public static <T> List<T> findAll(String table) {
        Transaction tx = null;
        List o = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            o = session.createQuery("FROM " + table).list();
            tx.commit();
        } catch (HibernateException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setHeaderText("THEY ARE NOT FOUND");
            alert.setContentText("SORRY, THERE IS NO DATA IN THE TABLE!");
            alert.showAndWait();

            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();

        }
        return o;
    }


}
