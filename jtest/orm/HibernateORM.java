package orm;

import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.classic.Session;
import org.hibernate.tool.hbm2ddl.SchemaExport;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import orm.m2m.Student;
import orm.m2m.Teacher;
import orm.o2m.Group;
import orm.o2m.User;
import orm.o2o.Husband;
import orm.o2o.Wife;

public class HibernateORM {
	private Configuration cfg;
	private SessionFactory sf;

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
		// 自动建表
		SchemaExport se = new SchemaExport(cfg);

		// args1是否在控制台打印日志文件,args2是否把表删了重新建一个
		se.create(true, true);
	}

	@Test
	public void o2o__save() {
		Session session = sf.openSession();
		Transaction tran = session.beginTransaction();

		Husband h = new Husband();
		h.setName("Tom");

		Wife w = new Wife();
		w.setName("lili");

		session.save(h);
		session.save(w);

		tran.commit();
		session.close();
	}

	@Test
	public void o2o_bi_save() {
		Session session = sf.openSession();
		Transaction tran = session.beginTransaction();

		Husband h = new Husband();
		h.setName("Tom");

		Wife w = new Wife();
		w.setName("lili");

		// 建立两个实体类对象之间的关系
		w.setHusband(h);
		h.setWife(w);

		// save anyone will save another one cascade
		session.save(h);
		// session.save(w);

		tran.commit();
		session.close();
	}

	@Test
	public void o2o_uni_query() {
		Session session = sf.openSession();
		Transaction tran = session.beginTransaction();

		Husband h = (Husband) session.get(Husband.class, 1L);
		Wife w = (Wife) session.get(Wife.class, 1L);

		System.out.println(h.getWife().getId());
		System.out.println(w.getHusband().getId());

		tran.commit();
		session.close();
	}

	@Test
	public void o2o_uni_delete() {
		Session session = sf.openSession();
		Transaction tran = session.beginTransaction();

		Husband h = (Husband) session.get(Husband.class, 1L);
		session.delete(h);

		tran.commit();
		session.close();
	}

	// unidirectional单向
	// bidirectional多向
	@Test
	public void o2m_uni_save() {
		Session session = sf.openSession();
		Transaction tran = session.beginTransaction();

		Group g = new Group();
		g.setName("g1");

		User u1 = new User();
		u1.setName("Larry1");
		User u2 = new User();
		u2.setName("Larry2");
		User u3 = new User();
		u3.setName("Larry3");

		g.getUsers().add(u1);
		g.getUsers().add(u2);
		g.getUsers().add(u3);

		// u1.setGroup(g);

		session.save(g);

		tran.commit();
		session.close();
	}

	@Test
	public void o2m_uni_query() {
		Session session = sf.openSession();
		Transaction tran = session.beginTransaction();
		
		Group g = (Group) session.get(Group.class, 1L);
		System.out.println(g.getId());
		System.out.println(g.getUsers().size());

		tran.commit();
		session.close();
	}

	@Test
	public void o2m_uni_delete() {
		Session session = sf.openSession();
		Transaction tran = session.beginTransaction();

		Group g = (Group) session.get(Group.class, 1L);
		session.delete(g);
		
		tran.commit();
		session.close();
	}

	@Test
	public void o2m_bi_save() {
		Session session = sf.openSession();
		Transaction tran = session.beginTransaction();

		Group g = new Group();
		g.setName("g1");

		User u1 = new User();
		u1.setName("Larry1");

		User u2 = new User();
		u2.setName("Larry2");

		User u3 = new User();
		u3.setName("Larry3");

		g.getUsers().add(u1);
		g.getUsers().add(u2);
		g.getUsers().add(u3);

		u1.setGroup(g);
		u2.setGroup(g);
		u3.setGroup(g);

		session.save(g);

		tran.commit();
		session.close();
	}

	@Test
	public void m2m_uni_save() {
		Session session = sf.openSession();
		Transaction tran = session.beginTransaction();

		Teacher t = new Teacher();
		t.setName("Terry");

		Student s1 = new Student();
		s1.setName("Larry1");

		Student s2 = new Student();
		s2.setName("Larry2");

		Student s3 = new Student();
		s3.setName("Larry3");

		t.getStudents().add(s1);
		t.getStudents().add(s2);
		t.getStudents().add(s3);

		session.save(t);

		tran.commit();
		session.close();
	}

	@Test
	public void m2m_uni_query() {
		Session session = sf.openSession();
		Transaction tran = session.beginTransaction();

		Teacher t = (Teacher) session.get(Teacher.class, 1L);

		System.out.println(t.getId());
		System.out.println(t.getStudents().size());
		
		tran.commit();
		session.close();
	}

	@Test
	public void m2m_uni_delete() {
		Session session = sf.openSession();
		Transaction tran = session.beginTransaction();

//		Teacher t = (Teacher) session.get(Teacher.class, 1L);
//		session.delete(t);
		
		Student s = (Student)session.get(Student.class, 1L);
		session.delete(s);

		tran.commit();
		session.close();
	}

	@Test
	public void m2m_bi_save() {
		Session session = sf.openSession();
		Transaction tran = session.beginTransaction();

		Teacher t = new Teacher();
		t.setName("Terry");

		Student s1 = new Student();
		s1.setName("Larry1");

		Student s2 = new Student();
		s2.setName("Larry2");

		Student s3 = new Student();
		s3.setName("Larry3");

		t.getStudents().add(s1);
		t.getStudents().add(s2);
		t.getStudents().add(s3);

		s1.getTeachers().add(t);
		s2.getTeachers().add(t);
		s3.getTeachers().add(t);

		session.save(t);

		tran.commit();
		session.close();
	}
}
