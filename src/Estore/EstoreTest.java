package Estore;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.tool.hbm2ddl.SchemaExport;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

// TODO:
public class EstoreTest {

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
	public void save() {
		Session session = sf.openSession();
		Transaction tran = session.beginTransaction();

		// Order o= new Order(1);
		// Customer c = new Customer(1,"曺圭贤");
		// Book b= new Book(1,"乱世佳人");
		//
		// c.getOrders().add(o);
		// b.getOrders().add(o);
		//
		// o.setCustomer(c);
		// o.getBooks().add(b);
		//
		//
		// session.save(o);

		tran.commit();
		session.close();
	}
}
