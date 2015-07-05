package criteria;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.classic.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.hibernate.tool.hbm2ddl.SchemaExport;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

//Criteria查询
public class HibernateCriteria {
	
	private SessionFactory sf;
	private Configuration cfg;

	@Before
	public void Before() {
		try {
			cfg = new Configuration();
			cfg.configure();
			sf = cfg.buildSessionFactory();

		} catch (Exception e) {
		}
	}

	@After
	public void After() {
		try {
			sf.close();
		} catch (Exception e) {
		}
	}

	@Test
	public void schemaExport() {
		try {
			SchemaExport se = new SchemaExport(cfg);

			se.create(false, true);
		} catch (final HibernateException e) {
			e.printStackTrace();
		} catch (final Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void o2o_bi_save() {
		try {
			Session session = sf.openSession();
			Transaction tran = session.beginTransaction();
			
			
			Husband h= new Husband();
			h.setName("Tom");
			
			Wife w = new Wife();
			w.setName("lili");
			w.setHusband(h);
			h.setWife(w);
			
			session.save(w);
			
			tran.commit();
			session.close();
			
		} catch (HibernateException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	@Test
	public void criteria() {
		//QBC Query By Criteria
		try {
			Session session = sf.openSession();
			Transaction tran = session.beginTransaction();
			
			Criteria criteria =
				session.createCriteria(Husband.class);
			
//			List<Husband> list = criteria.list();
			
//			List<Husband> list =
//				criteria.add(Restrictions.gt("id", 5L)).list();
//			criteria.add(Restrictions.gt("id", 3L)).
//					 add(Restrictions.lt("id", 10L)).
//					 add(Restrictions.between("id", 4L, 8L)).
//					 add(Restrictions.disjunction().
//							 add(Restrictions.eq("name", "Tom")).
//							 add(Restrictions.like("name", "T%"))
//						).
//						list();
//			criteria.add(Restrictions.between("id", 4L, 8L)).list();
//			criteria.createCriteria("wife");
			criteria.setFetchMode("wife", FetchMode.JOIN);
			criteria.addOrder(Order.asc("id"));
			criteria.list();
//			for(Husband h:list) {
//				System.out.print(h.getId()+" ");
//				System.out.println(h.getName());
//			}
			
			tran.commit();
			session.close();
			
		} catch (final HibernateException e) {
			e.printStackTrace();
		} catch (final Exception e) {
			e.printStackTrace();
		}
	}
}
