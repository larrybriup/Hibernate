package criteria;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.FetchMode;
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
		cfg = new Configuration();
		cfg.configure();
		sf = cfg.buildSessionFactory();
	}

	@After
	public void After() {
		sf.close();
	}

	@Test
	public void schemaExport() {
		SchemaExport se = new SchemaExport(cfg);

		se.create(true, true);
	}

	@Test
	public void o2o_bi_save() {
		Session session = sf.openSession();
		Transaction tran = session.beginTransaction();

		Husband h = new Husband();
		h.setName("Tom");

		Wife w = new Wife();
		w.setName("lili");

		w.setHusband(h);
		h.setWife(w);

		session.save(w);

		tran.commit();
		session.close();
	}

	@SuppressWarnings("unchecked")
	@Test
	public void criteria_query() {
		// QBC Query By Criteria
		Session session = sf.openSession();
		Transaction tran = session.beginTransaction();

		Criteria criteria = session.createCriteria(Husband.class);
		List<Husband> list1 = criteria.list();

		List<Husband> list2 = criteria.add(Restrictions.gt("id", 5L)).list();
		
		List list3 = criteria.add(Restrictions.gt("id", 3L))
						.add(Restrictions.lt("id", 10L))
						.add(Restrictions.between("id", 4L, 8L))
						.add(Restrictions.disjunction().add(Restrictions.eq("name", "Tom"))
										.add(Restrictions.like("name", "T%"))).list();
		
		List list4 = criteria.add(Restrictions.between("id", 4L, 8L)).list();
		
		criteria.createCriteria("wife");
		criteria.setFetchMode("wife", FetchMode.JOIN);
		criteria.addOrder(Order.asc("id"));
		List list5 = criteria.list();

		tran.commit();
		session.close();
	}
}
