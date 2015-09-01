package annotation;

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
}
