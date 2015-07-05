package orm;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.tool.hbm2ddl.SchemaExport;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import Bean.Student;

public class HibernateBase {
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
	public void hibernateFirst() {
		try {
			//1创建Configuration对象
			Configuration cfg = new Configuration();
			
			//读取hibernate的配置文件
			//默认去读取src下面的hibernate.cfg.xml
			cfg.configure();
			
			SessionFactory sf = cfg.buildSessionFactory();
			
			//获得session对象
			Session session = sf.openSession();
			
			//用这个session对象开启一个事务,并把这个事务对象返回
			Transaction tran = session.beginTransaction();
			
			//执行一个插入操作
			Student s = new Student(1,"zhangsan",20);
			//会话保存
			session.save(s);
			
			//提交事务
			tran.commit();
			//关闭session和sf
			session.close();
			sf.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@Test
	public void delete() {
		try {
//			Configuration cfg = new Configuration();
//			cfg.configure();
//			SessionFactory sf = cfg.buildSessionFactory();
			Session session = sf.openSession();
			Transaction tran = session.beginTransaction();
			Student s = new Student();
			s.setId(1);
			
			session.delete(s);
			
			tran.commit();
			session.close();
			
		} catch (Exception e) {
		}
	}
	@Test
	public void save() {
		try {
			Session session = sf.openSession();
			Transaction tran = session.beginTransaction();
			
			Student s = new Student(1,"cho",23);
			session.save(s);
			tran.commit();
//			if(1==1) {
//				throw new Exception("test");
//			}
			
			session.close();
		} catch (HibernateException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	@Test
	public void update() {
		try {
			Session session = sf.openSession();
			Transaction tran = session.beginTransaction();
			
			Student s = new Student(1,"choda",23);
			session.update(s);
			
			tran.commit();
			session.close();
		} catch (HibernateException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	@Test
	public void query_get() {
		//不延迟加载,加载类的时候就开始打印查询语句
		try {
			Session session = sf.openSession();
//			Transaction tran = session.beginTransaction();
			Student stu= 
				(Student)session.get(Student.class,1L);
//				(Student)session.getNamedQuery("sname");
			System.out.println("hh");
			System.out.println(stu.getAge());
			System.out.println(stu);
			System.out.println("hh");
//			tran.commit();
			session.close();
		} catch (HibernateException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	@Test
	public void query_load() {
		//延迟加载,查询的时候才开始加载
		try {
			Session session = sf.openSession();
			Transaction tran = session.beginTransaction();
			Student stu= 
				(Student)session.load(Student.class,1L);
			System.out.println(stu.getAge());
			System.out.println("hh");
			System.out.println(stu);
			System.out.println("hh");
			tran.commit();
			session.close();
		} catch (HibernateException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	@Test
	public void session_flush() {
		//flush()方法的作用:
		//冲刷缓存,执行sql语句
		//执行sql语句和提交事务是两件不同是事情
		//tran.commit();会默认的先执行flush()再提交事务
		//flush();之后是可以回滚的,commit()之后不能回滚
		//session.close();在关闭之前把数据提交,但不能执行sql语句flush()
		try {
			Session session = sf.openSession();
			Transaction tran = session.beginTransaction();
			
			Student s1= new Student(1,"lili",22);
//			Student s2= new Student(2,"lili",22);
//			Student s3= new Student(3,"lili",22);
//			Student s4= new Student(4,"lili",22);
			System.out.println("Hello");
			session.save(s1);
//			session.delete(s1);
			
//			session.save(s2);
//			s2.setName("tom");
//			
//			session.save(s3);
//			session.save(s4);
			
			session.flush();
			System.out.println("World");
			
			
			
			
//			Student s1=(Student)session.get(Student.class, 1L);
//			System.out.println("s1="+s1);
//			
//			Student s2=(Student)session.get(Student.class, 1L);
//			System.out.println("s2="+s2);
			
			tran.commit();
			session.close();//在关闭之前把数据提交
		} catch (HibernateException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	@Test
	public void session_clear() {
		//session_clear() 清空session中的缓存内容
		try {
			Session session = sf.openSession();
			Transaction tran = session.beginTransaction();
			
			Student s = new Student(1,"tom",23);
			
			session.save(s);
			session.flush();
			session.clear();
			
			tran.commit();
			session.close();//在关闭之前把数据提交
		} catch (HibernateException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	@Test
	public void session_update_merge() {
		//session_clear() 清空session中的缓存内容
		try {
			Session session = sf.openSession();
			Transaction tran = session.beginTransaction();
			
			Student s = new Student(1,"tom",23);
			session.save(s);
			Student s1 = new Student(1,"lili",25);
			session.merge(s1);
			
			System.out.println("s.getName()="+s.getName());
			System.out.println("s1.getName()="+s1.getName());
			
			tran.commit();
			session.close();//在关闭之前把数据提交
		} catch (HibernateException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	@Test
	public void session_open() {
		//每次调用session方法都会返回一个新的ssession对象
		//open_session方式得到的session对象,在事务提交之后
		//还需要我们手动的把他关闭掉session.close()
		try {
			Session session1 = sf.openSession();
			Session session2 = sf.openSession();
			Session session3 = sf.openSession();
			
			
			System.out.println(session1==session2);
			System.out.println(session2==session3);
			System.out.println(session1==session3);
			
			
			session1.close();
			session2.close();
			session3.close();
		} catch (HibernateException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	@Test
	public void session_current() {
		//使用之前需要在hibernate.hbm.xml文件中进行配置
		//<property name="current_session_context_class">thread</property> 
		//如果当前有session对象,返回这个session对象
		//如果当前没有session对象,则创建一个新的session对象
		//2用getCurrent_session方式得到的session对象,在事务提交的同时
		//会自动的把这个session对象关闭
		try {
//			sf.openSession();
			
			Session session1 = sf.getCurrentSession();
//			Session session2 = sf.getCurrentSession();
//			Session session3 = sf.getCurrentSession();
//			
//			System.out.println(session1==session2);
//			System.out.println(session2==session3);
//			System.out.println(session1==session3);
			Transaction tran = session1.beginTransaction();
			
			tran.commit();
			session1.close();
		} catch (HibernateException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	@Test
	public void idIncrement() {
		//<generator class="assigned"></generator>
		//assigned 表示id的值是由用户设置的,这个是默认值
		//increment 表示id的值是自动增长的,每次表中id值增加1
		//sequence id值由数据库中的一个序列负责生成 默认找叫hibernate_sequence的序列
		//identity 是mysql SQLServer 等数据库中的数据增长策略,oracle不支持
		//native 让本地数据库自己选使用哪一种id增长策略
		//hilo 使用hilo算法(高位数值结合低位数值)生成id值,高位的值在数据库的表中存放
		//seqhilo 同样使用hilo算法获得id的值,但是高位的值是从数据库中的序列取得
		//使用uuid算法生成id的值,一个16进制的数字,但是以字符串的形式表示出来
		//guid 和uuid一样的生成方式,uuid是由hibernate完成的,guid是由oracle完成的
		try {
			Session session = sf.openSession();
			Transaction tran = session.beginTransaction();
			Student s = new Student("tom",23);
			System.out.println("Hello");
			session.save(s);
			System.out.println("World");
			System.out.println("s.getId()="+s.getId());
			
			tran.commit();
//			session.close();
		} catch (HibernateException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	@Test
	public void schemaExport() {
		try {
			SchemaExport se= new SchemaExport(cfg);
			
			//args1是否在控制台打印日志文件,args2是否把表删了重新建一个
			se.create(false, true);
		} catch (HibernateException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
}
