package hibernate.abstractModel;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.io.Serializable;
import java.util.List;

/**
 * @author Robert Chen
 * @date 2021-04-18
 */
public abstract class AbstractModel<T> {
    private final Class<T> entity;
    protected SessionFactory sessionFactory = HibernateUtil.getSession().getSessionFactory();

    public AbstractModel(Class<T> entity) {
        this.entity = entity;
    }

    /**
     * 向数据库中插入信息
     *
     * @param entity : 插入数据库中的实体
     */
    public void insert(T entity) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.save(entity);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }

    /**
     * 删除数据中信息
     *
     * @param entity : 删除数据库中的实体
     */
    public void delete(T entity) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.delete(entity);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }

    /**
     * 更新数据库中信息
     *
     * @param entity : 更新数据库的实体信息
     */
    public void update(T entity) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.saveOrUpdate(entity);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }

    /**
     * 查询数据库中信息
     *
     * @param id : 用于查找信息的 id
     * @return T : 查找后返回的实体
     */
    public T findById(Object id) {
        T result = null;
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            result = session.get(entity, (Serializable) id);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            if (transaction != null) {
                transaction.rollback();
            }
        }
        return result;
    }

    /**
     * 用于查询数据库表中所有的信息
     *
     * @return List<T> : 所有返回值的列表
     */
    public List<T> findAll() {
        List<T> result = null;
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            result = session.createQuery("from " + entity.getName()).list();
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            if (transaction != null) {
                transaction.rollback();
            }
        }
        return result;
    }

}
