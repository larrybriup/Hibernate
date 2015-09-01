package cache;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.tool.hbm2ddl.SchemaExport;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class HibernateCache {

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

		se.create(false, true);
	}

	@Test
	public void o2o__save() {
		Session session = sf.openSession();
		Transaction tran = session.beginTransaction();

		Husband h = new Husband();
		h.setName("Tom");

		Wife w = new Wife();
		w.setName("lili");
		
		w.setHusband(h);
		h.setWife(w);

		session.save(h);

		tran.commit();
		session.close();
	}

	@Test
	public void cache_query() {
		Session session = sf.openSession();
		Transaction tran = session.beginTransaction();

		System.out.println("第一次查询");
		Husband h = (Husband) session.get(Husband.class, 1L);
		System.out.println("第二次查询");
		String hql = "from Husband where id=1";
		Query query = session.createQuery(hql);
		query.setCacheable(true).list();
		System.out.println(h.getId());
		System.out.println(h.getName());

		tran.commit();
		session.close();

		System.out.println("==================跨两个session============================");

		Session session2 = sf.openSession();
		Transaction tran2 = session2.beginTransaction();
		System.out.println("第三次查询在缓存中");
		Husband h2 = (Husband) session2.get(Husband.class, 1L);
		System.out.println(h2.getId());
		System.out.println(h2.getName());

		tran2.commit();
		session2.close();
	}

	@Test
	public void cache_hql_query() {
		Session session = sf.openSession();
		Transaction tran = session.beginTransaction();
		String hql = "from Husband where id=1";
		Query query = session.createQuery(hql);
		// 注意设置查询的时候使用查询缓存setCacheable(true).list()
		query.setCacheable(true).list();
		tran.commit();
		session.close();

		System.out.println("==================跨两个session============================");

		Session session2 = sf.openSession();
		Transaction tran2 = session2.beginTransaction();

		// Husband h3=(Husband)session2.get(Husband.class, 1L);
		String hql2 = "from Husband where id=1";
		Query query2 = session2.createQuery(hql2);
		// 注意设置查询的时候使用查询缓存setCacheable(true).list()
//		query2.setCacheable(true).list();
		query2.list();

		tran2.commit();
		session2.close();
	}
}
