/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.travelling.dao;

import com.travelling.entity.CbrCase;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;
import java.util.LinkedList;
import java.util.List;
import java.util.Map.Entry;
import java.util.logging.Logger;
import javax.persistence.Column;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.Table;
import javax.transaction.TransactionManager;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Deroude
 */
public abstract class AbstractDAOImpl<T> implements AbstractDAO<T> {

    private Class<T> type;
    protected EntityManagerFactory entityManagerFactory;

    public AbstractDAOImpl() {
        Type t = getClass().getGenericSuperclass();
        ParameterizedType pt = (ParameterizedType) t;
        type = (Class) pt.getActualTypeArguments()[0];
        entityManagerFactory = Persistence.createEntityManagerFactory("travelling");
    }

    @Override
    @Transactional
    public T create(final T t) {
        EntityManager manager = entityManagerFactory.createEntityManager();
        EntityTransaction tx = manager.getTransaction();
        T newObject = t;
        try {
            tx.begin();
            newObject = manager.merge(t);
            tx.commit();
        } catch (RuntimeException e) {
            tx.rollback();
            throw e;
        } finally {
            manager.close();
        }
        return newObject;
    }

    @Transactional
    public void createAll(final List<T> l) {
        EntityManager manager = entityManagerFactory.createEntityManager();
        EntityTransaction tx = manager.getTransaction();
        try {
            tx.begin();

            if (l.isEmpty()) {
                return;
            }

            if (l.size() > 300) {
                createAllIgnoreNullColumns(l.subList(0, 300));
                createAllIgnoreNullColumns(l.subList(300, l.size()));
                return;
            }

            String tableName = type.getAnnotation(Table.class).name();

            String queryString = "insert into " + tableName + " (";
            LinkedList<Field> fields = new LinkedList<Field>();
            for (Field f : type.getDeclaredFields()) {
                if (f.getAnnotation(Id.class) != null) {
                    continue;
                }
                String column = null;
                Column c = f.getAnnotation(Column.class);
                if (c != null) {
                    column = c.name();
                } else {
                    JoinColumn join = f.getAnnotation(JoinColumn.class);
                    if (join != null) {
                        column = join.name();
                    }
                }
                if (column != null) {
                    queryString += column + ", ";
                    fields.add(f);
                }

            }
            if (fields.size() == 0) {
                return;
            }
            queryString = queryString.substring(0, queryString.length() - 2) + ") values ";
            for (int i = 0; i < l.size(); i++) {
                queryString += "(";
                for (int j = 0; j < fields.size() - 1; j++) {
                    queryString += "?, ";
                }
                queryString += "?), ";
            }
            queryString = queryString.substring(0, queryString.length() - 2);
            Query query = manager.createNativeQuery(queryString);
            int i = 1;
            for (T t : l) {
                for (Field f : fields) {
                    String fieldName = f.getName();
                    String methodName = "get" + fieldName.toUpperCase().charAt(0) + fieldName.substring(1);
                    try {
                        Object arg = type.getMethod(methodName, new Class[0]).invoke(t);
                        if (arg == null) {
                            query.setParameter(i++, null);
                            continue;
                        }
                        JoinColumn jc = f.getAnnotation(JoinColumn.class);
                        if (jc != null) {
                            String id = jc.referencedColumnName();
                            methodName = "get" + id.toUpperCase().charAt(0) + id.substring(1);
                            arg = arg.getClass().getMethod(methodName, new Class[0]).invoke(arg);
                        }
                        query.setParameter(i++, arg);
                    } catch (Exception e) {
                        e.printStackTrace();
                        return;
                    }
                }

            }
            query.executeUpdate();
        } catch (RuntimeException e) {
            tx.rollback();
            throw e;
        } finally {
            manager.close();
        }

        /*for (T t : l)
         {
         this.entityManager.persist(t);
         }*/
    }

    @Transactional
    public void createAllIgnoreNullColumns(final List<T> l) {

        EntityManager manager = entityManagerFactory.createEntityManager();
        EntityTransaction tx = manager.getTransaction();
        try {
            tx.begin();
            if (l.isEmpty()) {
                return;
            }
            if (l.size() > 300) {
                createAllIgnoreNullColumns(l.subList(0, 300));
                createAllIgnoreNullColumns(l.subList(300, l.size()));
                return;
            }

            LinkedList<Field> fields = new LinkedList<Field>();
            for (Field f : type.getDeclaredFields()) {
                if (f.getAnnotation(Id.class) != null) {
                    continue;
                }
                String column = null;
                Column c = f.getAnnotation(Column.class);
                if (c != null) {
                    column = c.name();
                } else {
                    JoinColumn join = f.getAnnotation(JoinColumn.class);
                    if (join != null) {
                        column = join.name();
                    }
                }
                if (column != null) {
                    fields.add(f);
                }

            }
            if (fields.size() == 0) {
                return;
            }

            List<Object> parameters = new LinkedList<Object>();
            for (T t : l) {
                for (Field f : fields) {
                    String fieldName = f.getName();
                    String methodName = "get" + fieldName.toUpperCase().charAt(0) + fieldName.substring(1);
                    try {
                        Object arg = type.getMethod(methodName, new Class[0]).invoke(t);
                        if (arg == null) {
                            parameters.add(null);
                            continue;
                        }
                        JoinColumn jc = f.getAnnotation(JoinColumn.class);
                        if (jc != null) {
                            String id = jc.referencedColumnName();
                            methodName = "get" + id.toUpperCase().charAt(0) + id.substring(1);
                            arg = arg.getClass().getMethod(methodName, new Class[0]).invoke(arg);
                        }
                        parameters.add(arg);
                    } catch (Exception e) {
                        e.printStackTrace();
                        return;
                    }
                }

            }


            String tableName = type.getAnnotation(Table.class).name();

            String queryString = "insert into " + tableName + " (";
            int cnt = 0;
            for (Field f : fields) {
                if (parameters.get(cnt) == null) {
                    cnt++;
                    continue;
                }
                if (f.getAnnotation(Id.class) != null) {
                    continue;
                }
                String column = null;
                Column c = f.getAnnotation(Column.class);
                if (c != null) {
                    column = c.name();
                } else {
                    JoinColumn join = f.getAnnotation(JoinColumn.class);
                    if (join != null) {
                        column = join.name();
                    }
                }
                if (column != null) {
                    queryString += column + ", ";
                }
                cnt++;
            }

            queryString = queryString.substring(0, queryString.length() - 2) + ") values ";
            for (int i = 0; i < l.size(); i++) {
                queryString += "(";
                for (int j = 0; j < fields.size(); j++) {
                    if (parameters.get(i * fields.size() + j) != null) {
                        queryString += "?, ";
                    }
                }
                queryString = queryString.substring(0, queryString.length() - 2);
                queryString += "), ";
            }
            queryString = queryString.substring(0, queryString.length() - 2);
            Query query = manager.createNativeQuery(queryString);
            int i = 1;
            for (Object arg : parameters) {
                if (arg != null) {
                    query.setParameter(i++, arg);
                }
            }
            query.executeUpdate();
        } catch (RuntimeException e) {
            tx.rollback();
            throw e;
        } finally {
            manager.close();
        }
        /*for (T t : l)
         {
         this.entityManager.persist(t);
         }*/
    }

    @Override
    @Transactional
    public void delete(final Object id) {
        EntityManager manager = entityManagerFactory.createEntityManager();
        EntityTransaction tx = manager.getTransaction();
        try {
            tx.begin();
            manager.remove(manager.getReference(type, id));
        } catch (RuntimeException e) {
            tx.rollback();
            throw e;
        } finally {
            manager.close();
        }
    }

    @Override
    public T find(final Object id) {
        EntityManager manager = entityManagerFactory.createEntityManager();
        try {
            if (id == null) {
                return null;
            } else {
                return (T) manager.find(type, id);
            }
        } catch (RuntimeException e) {
            return null;
        } finally {
            manager.close();
        }

    }

    @Override
    @Transactional
    public T update(final T t) {
        EntityManager manager = entityManagerFactory.createEntityManager();
        EntityTransaction tx = manager.getTransaction();
        T newObject = t;
        try {
            tx.begin();
            newObject = manager.merge(t);
            tx.commit();
        } catch (RuntimeException e) {
            tx.rollback();
            throw e;
        } finally {
            manager.close();
        }
        return newObject;
    }

    @Override
    public List<T> findAll() {
        final StringBuffer queryString = new StringBuffer("SELECT a from ");
        queryString.append(type.getSimpleName());
        queryString.append(" a");
        EntityManager manager = entityManagerFactory.createEntityManager();
        try {
            final Query query = manager.createQuery(queryString.toString(), type);
            return query.getResultList();
        } catch (RuntimeException e) {
            return new LinkedList<T>();
        } finally {
            manager.close();
        }
        
    }

    @Override
    public List<T> findByNamedQuery(String qry, HashMap<String, Object> params) {
        EntityManager manager = entityManagerFactory.createEntityManager();
        try {
            final Query query = manager.createNamedQuery(qry, type);
            for (Entry<String, Object> param : params.entrySet()) {
                query.setParameter(param.getKey(), param.getValue());
            }
            return query.getResultList();
        } catch (Exception e) {
            return new LinkedList<T>();
        }
        finally {
            manager.close();
        }
    }

    @Override
    public T findOneByNamedQuery(String qry, HashMap<String, Object> params) {
        EntityManager manager = entityManagerFactory.createEntityManager();
        try {
            final Query query = manager.createNamedQuery(qry, type);
            for (Entry<String, Object> param : params.entrySet()) {
                query.setParameter(param.getKey(), param.getValue());
            }
            return (T) query.getSingleResult();
        } catch (Exception e) {
            return null;
        }
        finally {
            manager.close();
        }
    }
}
