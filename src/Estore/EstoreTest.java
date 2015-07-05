package Estore;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.tool.hbm2ddl.SchemaExport;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class EstoreTest {
	private SessionFactory sf;
	private Configuration cfg;
	@Before
	public void Before() {
		try {
			 cfg = new Configuration();
			cfg.configure();
			sf=cfg.buildSessionFactory();
			
			
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
			 SchemaExport se= new SchemaExport(cfg);
			
			se.create(false, true);
		} catch (final HibernateException e) {
			e.printStackTrace();
		} catch (final Exception e) {
			e.printStackTrace();
		}
		
	}
	@Test
	public void save() {
		try {
			Session session = sf.openSession();
			Transaction tran = session.beginTransaction();
			
//			Order o= new Order(1);
//			Customer c = new Customer(1,"曺圭贤");
//			Book b= new Book(1,"乱世佳人");
//			
//			c.getOrders().add(o);
//			b.getOrders().add(o);
//			
//			o.setCustomer(c);
//			o.getBooks().add(b);
//			
//			
//			session.save(o);
			
			tran.commit();
			session.close();
		} catch (HibernateException e) {
			e.printStackTrace();
		}
	}
}
