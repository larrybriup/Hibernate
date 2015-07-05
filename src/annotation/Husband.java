package annotation;

import javax.annotation.Generated;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import javax.persistence.OneToOne;

@Entity//映射这个实体类 默认映射的表示类名首字母小写
@Table(name="husband")//设置映射表 的名字
public class Husband {
private long id;
private String name;
private Wife wife;


//@Transient//设置这个属性不做任何映射
@OneToOne(cascade=CascadeType.ALL,fetch=FetchType.LAZY)
@JoinColumn(name="wifeid")//设置外间列名字
public Wife getWife() {
	return wife;
}
public void setWife(Wife wife) {
	this.wife = wife;
}
@Id//映射这个属性为组建
@GeneratedValue(strategy=GenerationType.AUTO)//指明ID 的增长策略
public long getId() {
	return id;
}
public void setId(long id) {
	this.id = id;
}
//@Basic
@Column(name="myname")//设置属性映射的列的名字
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
}
