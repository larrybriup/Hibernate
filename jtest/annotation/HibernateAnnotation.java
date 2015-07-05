package annotation;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.tool.hbm2ddl.SchemaExport;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * @author king
 * 
 */
public class HibernateAnnotation {
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
}
