package hiber.dao;

import hiber.model.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {
   private final String sqlGetUserByCar = "select u from User u inner join u.car " +
           "where u.car.model = : model and u.car.series =: series";
   @Autowired
   private SessionFactory sessionFactory;
   @Autowired
   private UserDao userDao;

   @Override
   public void add(User user) {
      sessionFactory.getCurrentSession().save(user);
   }

   @Override
   @SuppressWarnings("unchecked")
   public List<User> listUsers() {
      TypedQuery<User> query=sessionFactory.getCurrentSession().createQuery("from User");
      return query.getResultList();
   }

   @Override
   @SuppressWarnings("unchecked")
   public List<User> getUserByCar(String model, int series){
      TypedQuery<User> query=sessionFactory.getCurrentSession().createQuery(sqlGetUserByCar)
              .setParameter("model", model)
              .setParameter("series", series);
      return query.getResultList();
   }
}
