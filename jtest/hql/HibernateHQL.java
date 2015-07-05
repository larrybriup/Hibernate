package hql;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.jdbc.Work;
import org.hibernate.tool.hbm2ddl.SchemaExport;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class HibernateHQL {
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

	@Test
	public void session_JDBC() {
		try {
			Session session = sf.openSession();
			Transaction tran = session.beginTransaction();

			// 1 Connection connection = session.connection();
			// 2 通过spring和hibernate关联,然后通过spring的一个类直接拿到Connection
			// 3 session.doWork();hibernate推荐
			session.doWork(new Work() {

				public void execute(Connection conn) throws SQLException {

				}
			});

			tran.commit();
			session.close();

		} catch (final HibernateException e) {
			e.printStackTrace();
		} catch (final Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void o2m_bi_save() {
		try {
			Session session = sf.openSession();
			Transaction tran = session.beginTransaction();

			Group g = new Group();
			g.setName("g1");

			User u1 = new User();
			u1.setName("zhangsan");

			User u2 = new User();
			u2.setName("lisi");

			User u3 = new User();
			u3.setName("wangwu");

			g.getUsers().add(u1);
			g.getUsers().add(u2);
			g.getUsers().add(u3);

			u1.setGroup(g);
			u2.setGroup(g);
			u3.setGroup(g);

			session.save(g);

			tran.commit();
			session.close();

		} catch (HibernateException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Test
	public void hql_first() {
		// hql是hibernate提供的一种查询语句
		// 需要使用org.hibernate.query接口的实现类对象去执行sql语句
		// 通过session获得query接口的实现类对象
		// hql语句关注的是类和类中的属性
		// hql语句不能出现表或者列的名字
		try {
			Session session = sf.openSession();
			Transaction tran = session.beginTransaction();

			String hql = "from Group";
			Query query = session.createQuery(hql);
			// 查询结果放到list中返回
			
			List<Group> list = query.list();
			
//			System.out.println(list.size());
//			System.out.println(list.get(0).getClass());
//			System.out.println(list.get(0).getId());
			for (Group g : list) {
				System.out.println(g.getId());
				System.out.println(g.getName());
				System.out.println(g.getUsers());
				System.out.println(g);
			}

			tran.commit();
			session.close();

		} catch (final HibernateException e) {
			e.printStackTrace();
		} catch (final Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void hql_select() {
		try {
			Session session = sf.openSession();
			Transaction tran = session.beginTransaction();

			String hql = "select g.id,g.name from Group g";
			Query query = session.createQuery(hql);
			// 这个时候list中放的是Object类型的数组
			// 数组中放的是我们放的每一条值
			// 每一条包括id,name
			List<Object[]> list = query.list();

			System.out.println(list.size());
			System.out.println(list.get(0).getClass());
			// System.out.println(list.get(1).getClass());

			for (Object[] obj : list) {
				for (Object o : obj) {

					System.out.print(o + " ");
				}
				System.out.println();
			}

			tran.commit();
			session.close();

		} catch (final HibernateException e) {
			e.printStackTrace();
		} catch (final Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void hql_select_new() {
		try {
			Session session = sf.openSession();
			Transaction tran = session.beginTransaction();

			// Group类中有一个集合存在
			String hql = "select new hql.Group(g.id,g.name) from Group g";
			// String hql="select from new Group(g.id,g.name from Group g) ";
			Query query = session.createQuery(hql);

			List list = query.list();

			System.out.println(list.get(0).getClass());
			// for(Group g:list) {
			// System.out.println(g.getId());
			// System.out.println(g.getName());
			// }

			tran.commit();
			session.close();

		} catch (final HibernateException e) {
			e.printStackTrace();
		} catch (final Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void hql_select_new2() {
		try {
			Session session = sf.openSession();
			Transaction tran = session.beginTransaction();

			// String hql="from User u";
			String hql = "select new User(u.id,u.name) from User u";
			Query query = session.createQuery(hql);

			List<User> list = query.list();

			for (User u : list) {
				System.out.println("=====================");
				System.out.print("u.id=" + u.getId() + " ");
				System.out.print("u.name=" + u.getName() + " ");
				System.out.println("u.group=" + u.getGroup());
				System.out.println("=====================");
			}

			tran.commit();
			session.close();

		} catch (final HibernateException e) {
			e.printStackTrace();
		} catch (final Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void hql_select_join() {
		try {
			Session session = sf.openSession();
			Transaction tran = session.beginTransaction();

			// join
			// join fetch
			String hql = "from User u join  u.group";
			// String hql="select new User(u.id,u.name) from User u";
			Query query = session.createQuery(hql);

			List<User> list = query.list();

//			 System.out.println(list.size());
//			 System.out.println(list.get(0));
//			 Object[] obj = (Object[])list.get(0);
//			 System.out.println(obj.length);
//			 System.out.println(obj[0].getClass());
//			 System.out.println(obj[1].getClass());
			for (User u : list) {
				System.out.println("=====================");
				System.out.print("u.id=" + u.getId() + " ");
				System.out.print("u.name=" + u.getName() + " ");
				System.out.println("u.group=" + u.getGroup());
				System.out.println("group.set=" + u.getGroup().getUsers());
				System.out.println();
				System.out.println("=====================");
			}
			//			

			tran.commit();
			session.close();

		} catch (final HibernateException e) {
			e.printStackTrace();
		} catch (final Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void hql_select_join_fetch() {
		// join fetch 语句
		// 如果我们设置了延迟加载,但是在查询的时候
		// 我们又不想用延迟加载了,那么就可以使用join fetch
		// 这样会让hibernate立刻发出sql语句,去把相关联的对象也查询出来
		// 同时也只会发出一条sql语句,解决了1+n问题
		// 1+n问题:当我们发出一条sql语句去查询数据的时候,由于级联查询的问题,hibernate
		// 会在发出额外的n条sql语句,去把要查询的和数据关联的其他表中的数据也查询出来

		// join 和join fetch的区别:
		// join也能查询,但是不会把查询的对象建立关联关系
		// join fetch不仅能连接查询,还能帮我们把查询出来的对象建立起关联关系
		//
		try {
			Session session = sf.openSession();
			Transaction tran = session.beginTransaction();

			String hql = "from User u join fetch u.group";
			// String hql="select new User(u.id,u.name) from User u";
			Query query = session.createQuery(hql);

			List<User> list = query.list();

			for (User u : list) {
				System.out.println("=====================");
				System.out.print("u.id=" + u.getId() + " ");
				System.out.print("u.name=" + u.getName() + " ");
				System.out.println("u.group=" + u.getGroup());
				System.out.println("group.set=" + u.getGroup().getUsers());
				System.out.println();
				System.out.println("=====================");
			}
			tran.commit();
			session.close();
		} catch (final HibernateException e) {
			e.printStackTrace();
		} catch (final Exception e) {
			e.printStackTrace();
		}
	}
	@Test
	public void hql_select_where() {
		try {
			Session session = sf.openSession();
			Transaction tran = session.beginTransaction();
			
//			String hql = "from User u join fetch u.group";
//			 String hql="from User u where u.id=1";
//			 String hql="from User u where id=:sid";
//			 String hql="from User u where id=?";
			 String hql="from User u where name=?";
			Query query = session.createQuery(hql);
			//从左到右,0开始,第一个?的下标是0
//			query.setLong(0, 1L);
			query.setString(0, "zhangsan");
			User u = (User)query.uniqueResult();
//			
//			System.out.println(u.getId());
//			System.out.println(u.getName());
			tran.commit();
			session.close();
		} catch (final HibernateException e) {
			e.printStackTrace();
		} catch (final Exception e) {
			e.printStackTrace();
		}
	}
	@Test
	public void hql_delete() {
		try {
			Session session = sf.openSession();
			Transaction tran = session.beginTransaction();
			
			//不能级联删除
			String hql="delete from User u where u.name='zhangsan'";
			Query query = session.createQuery(hql);
			query.executeUpdate();
			
			tran.commit();
			session.close();
		} catch (final HibernateException e) {
			e.printStackTrace();
		} catch (final Exception e) {
			e.printStackTrace();
		}
	}
	@Test
	public void hql_update() {
		try {
			Session session = sf.openSession();
			Transaction tran = session.beginTransaction();
			
			String hql="update User u set u.name='tommy' where u.name=?";
		
			Query query = session.createQuery(hql);
			query.setString(0, "tom");
			query.executeUpdate();
			
			tran.commit();
			session.close();
		} catch (final HibernateException e) {
			e.printStackTrace();
		} catch (final Exception e) {
			e.printStackTrace();
		}
	}
}
