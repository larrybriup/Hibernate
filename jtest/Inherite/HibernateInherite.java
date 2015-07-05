package Inherite;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.classic.Session;
import org.hibernate.tool.hbm2ddl.SchemaExport;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class HibernateInherite {
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
			
			//args1是否在控制台打印日志文件,args2是否把表删了重新建一个
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
			
			PersonPK pk= new PersonPK(2,"宋承宪");
			Address address= new Address("韩国","首尔");
			Person p1 = new Student(pk,30,address,90);
			Person p2= new Teacher(pk,34,address,10001);
			Person p3= new Person(pk,35,address);
			
			session.save(p2);
			
			tran.commit();
			session.close();
		} catch (HibernateException e) {
			e.printStackTrace();
		}
	}
	@Test
	public void query() {
		try {
			Session session = sf.openSession();
			Transaction tran = session.beginTransaction();
			
			PersonPK pk= new PersonPK(1,"宋承宪老师");
			Address address= new Address("韩国","首尔");
			Person p1 = new Student(pk,30,address,90);
			
			Student s3=(Student)session.get(Student.class, pk);
			
			
			tran.commit();
			session.close();
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Test
	public void delete() {
		try {
			Session session = sf.openSession();
			Transaction tran = session.beginTransaction();
			
			PersonPK pk= new PersonPK(1,"宋承宪老师");
			Address address= new Address("韩国","首尔");
			Person p1 = new Student(pk,30,address,90);
			
			Student s3=(Student)session.get(Student.class, pk);
			session.delete(s3);
			
			tran.commit();
			session.close();
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
