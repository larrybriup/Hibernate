package Component;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.tool.hbm2ddl.SchemaExport;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import Component.Address;
import Component.Person;

public class HibernateComponet {
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
		p.setName("choda");
		p.setAge(20);
		Address address = new Address("中国", "昆山");
		p.setAddress(address);

		session.save(p);
		tran.commit();

		session.close();
	}

	@Test
	public void query() {
		Session session = sf.openSession();
		Transaction tran = session.beginTransaction();

		Person p = (Person) session.get(Person.class, 1L);

		System.out.println("id=" + p.getId());
		System.out.println("name=" + p.getName());
		System.out.println("age=" + p.getAge());
		System.out.println("address=" + p.getAddress());
		System.out.println("address2s.Country=" + p.getAddress().getCountry());
		System.out.println("address2s.City=" + p.getAddress().getCity());

		tran.commit();
		session.close();

	}
}
