package com.jzfactory.jd.dao;

import java.util.List;

import org.hibernate.LockOptions;
import org.hibernate.Query;

import static org.hibernate.criterion.Example.create;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jzfactory.jd.bean.Master;
import com.jzfactory.jd.util.BaseHibernateDAO;

/**
 * A data access object (DAO) providing persistence and search support for
 * Master entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see com.jzfactory.jd.bean.Master
 * @author MyEclipse Persistence Tools
 */
public class MasterDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory.getLogger(MasterDAO.class);
	// property constants
	public static final String NAME = "name";
	public static final String SEX = "sex";

	public void save(Master transientInstance) {
		log.debug("saving Master instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Master persistentInstance) {
		log.debug("deleting Master instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Master findById(java.lang.Integer id) {
		log.debug("getting Master instance with id: " + id);
		try {
			Master instance = (Master) getSession().get(
					"com.jzfactory.jd.bean.Master", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<Master> findByExample(Master instance) {
		log.debug("finding Master instance by example");
		try {
			List<Master> results = (List<Master>) getSession()
					.createCriteria("com.jzfactory.jd.bean.Master")
					.add(create(instance)).list();
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List findByProperty(String propertyName, Object value) {
		log.debug("finding Master instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Master as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<Master> findByName(Object name) {
		return findByProperty(NAME, name);
	}

	public List<Master> findBySex(Object sex) {
		return findByProperty(SEX, sex);
	}

	public List findAll() {
		log.debug("finding all Master instances");
		try {
			String queryString = "from Master";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Master merge(Master detachedInstance) {
		log.debug("merging Master instance");
		try {
			Master result = (Master) getSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Master instance) {
		log.debug("attaching dirty Master instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Master instance) {
		log.debug("attaching clean Master instance");
		try {
			getSession().buildLockRequest(LockOptions.NONE).lock(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
	public static void main(String[] args) {
		MasterDAO dao=new MasterDAO();
		Master master=dao.findById(1);
		System.out.println(master.getClasz());
	}
}