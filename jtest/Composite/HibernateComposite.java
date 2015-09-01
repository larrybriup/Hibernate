package Composite;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.tool.hbm2ddl.SchemaExport;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class HibernateComposite {
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

		// args1是否在控制台打印日志文件,args2是否把表删了重新建一个
		se.create(true, true);
	}

	@Test
	public void save() {
		Session session = sf.openSession();
		Transaction tran = session.beginTransaction();

		Person p = new Person();
		Address address = new Address("中国", "郑州");
		PersonPK pk = new PersonPK(1, "tom");

		p.setAge(20);
		p.setAddress(address);
		p.setPk(pk);

		session.save(p);

		tran.commit();

		session.close();
	}

	@Test
	public void query() {
		Session session = sf.openSession();
		Transaction tran = session.beginTransaction();

		PersonPK pk = new PersonPK(1, "tom");
		Person p = (Person) session.get(Person.class, pk);

		System.out.println("id=" + p.getPk().getId());
		System.out.println("name=" + p.getPk().getName());
		System.out.println("age=" + p.getAge());
		System.out.println("address=" + p.getAddress());
		System.out.println("address2s.Country=" + p.getAddress().getCountry());
		System.out.println("address2s.City=" + p.getAddress().getCity());

		 session.delete(p);

		tran.commit();
		session.close();
	}
}
