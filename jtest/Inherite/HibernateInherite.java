package Inherite;

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

		PersonPK pk = new PersonPK(1, "宋承宪");
		Address address = new Address("韩国", "首尔");
		Person p1 = new Student(pk, 30, address, 90);
		
		PersonPK pk2 = new PersonPK(3, "陈浩泽");
		Address address2 = new Address("中国", "上海");
		Person p2 = new Teacher(pk2, 25, address2, 10001);
		
		
		PersonPK pk3 = new PersonPK(4, "无名氏");
		Address address3 = new Address("中国", "不定");
		Person p3 = new Person(pk3, 90, address3);

		session.save(p1);
		session.save(p2);
		session.save(p3);

		tran.commit();
		session.close();
	}

	@Test
	public void query() {
		Session session = sf.openSession();
		Transaction tran = session.beginTransaction();

		PersonPK pk = new PersonPK(2, "宋承宪");
		Address address = new Address("韩国", "首尔");
		Person p1 = new Student(pk, 30, address, 90);

		Student s3 = (Student) session.get(Student.class, pk);
		System.out.println(s3);

		tran.commit();
		session.close();
	}

	@Test
	public void delete() {
		Session session = sf.openSession();
		Transaction tran = session.beginTransaction();

		PersonPK pk = new PersonPK(2, "宋承宪");
		Address address = new Address("韩国", "首尔");
		Person p1 = new Student(pk, 30, address, 90);

		Student s3 = (Student) session.get(Student.class, pk);
		session.delete(s3);

		tran.commit();
		session.close();
	}
}
