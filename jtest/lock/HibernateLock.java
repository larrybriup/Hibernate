package lock;

import org.hibernate.HibernateException;
import org.hibernate.LockMode;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.classic.Session;
import org.hibernate.tool.hbm2ddl.SchemaExport;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;


public class HibernateLock {
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
	public void o2o__save() {
		try {
			Session session = sf.openSession();
			Transaction tran = session.beginTransaction();
			
			
			Husband h= new Husband();
			h.setName("Tom");
			
			Wife w = new Wife();
			w.setName("lili");
			w.setHusband(h);
			
			session.save(w);
			session.save(h);
			
			tran.commit();
			session.close();
			
		} catch (HibernateException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	//悲观锁
	@Test
	public void lock_pessimistic() {
		try {
			Session session = sf.openSession();
			Session session2 = sf.openSession();
			
			//第一个session开启事务.,查询事务并加锁
			Transaction tran = session.beginTransaction();
			Husband h=
				(Husband)session.get(Husband.class, 1L,LockMode.UPGRADE);
			System.out.println("rgrg");
			
			//第二个session开启事务并去查询相同的一条数据
			Transaction tran2 = session2.beginTransaction();
			Husband h2=
				(Husband)session2.get(Husband.class,1L,LockMode.UPGRADE_NOWAIT);
			//第二个事务修改事务并且提交
			h2.setName("zhangsan");
			session2.update(h2);
			
			tran2.commit();
			session2.close();
			
			tran.commit();
			session.close();
			
		} catch (HibernateException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	//乐观锁
	@Test
	public void lock_optimistic() {
		try {
			Session session = sf.openSession();
			Session session2 = sf.openSession();
			//第一个session开启事务.,查询事务并加锁
			Transaction tran = session.beginTransaction();
			
			//第二个事务中查出数据 并做出更新 假设version=0 更新后version=1
			Wife w=
				(Wife)session.get(Wife.class, 1L);
			w.setName("吉泽");
			session.update(w);
			
			
			//第二个事务中查出数据 并做出更新 假设version=1更新后version=2
			Transaction tran2 = session2.beginTransaction();
			Wife w2=
				(Wife)session2.get(Wife.class, 1L);
			System.out.println(w2.getName());
			w2.setName("如花");
			session2.update(w2);
			
			
			//第一个事务提交事务 
			tran.commit();
			session.close();
			
			//第二个事务提交事务
			tran2.commit();
			session2.close();
			
		} catch (HibernateException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
