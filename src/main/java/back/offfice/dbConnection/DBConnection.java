package back.offfice.dbConnection;

import back.offfice.beans.User;
import org.hibernate.Criteria;
import org.hibernate.classic.Session;
import org.hibernate.criterion.Restrictions;

import java.util.List;

public class DBConnection {

    private static final DBConnection dbConnection = new DBConnection();

    public static DBConnection getInstance(){
        return dbConnection;
    }

    public boolean isLoginSuccessful(String userName,String password){
        Session session = HibernateUtil.getSessionFactory().openSession();
        try
        {
            Criteria criteria = session.createCriteria(User.class);
            criteria.add(Restrictions.eq("staffId", userName));
            criteria.add(Restrictions.eq("password", password));
            List retList =  criteria.list();
            session.close();
            return retList != null && !retList.isEmpty();
        } catch (Exception e) {
            session.close();
            return false;
        }
    }
}
