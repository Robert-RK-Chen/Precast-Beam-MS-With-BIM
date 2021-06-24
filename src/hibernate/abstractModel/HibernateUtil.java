package hibernate.abstractModel;

import org.hibernate.HibernateException;
import org.hibernate.Metamodel;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import javax.persistence.metamodel.EntityType;

/**
 * @author Robert Chen
 */
public class HibernateUtil {
    private static final SessionFactory OUR_SESSION_FACTORY;

    static {
        try {
            Configuration configuration = new Configuration();
            configuration.configure();
            OUR_SESSION_FACTORY = configuration.buildSessionFactory();
        } catch (Throwable ex) {
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static Session getSession() throws HibernateException {
        return OUR_SESSION_FACTORY.openSession();
    }

    public static void main(final String[] args) {
        try (Session session = getSession()) {
            System.out.println("querying all the managed hibernate.entities...");
            final Metamodel metamodel = session.getSessionFactory().getMetamodel();
            for (EntityType<?> entityType : metamodel.getEntities()) {
                final String entityName = entityType.getName();
                final Query<?> query = session.createQuery("from " + entityName);
                System.out.println("executing: " + query.getQueryString());
                for (Object o : query.list()) {
                    System.out.println("  " + o);
                }
            }
        }
    }
}