
use test_db;

show tables;

-- o2m

desc t_group;

desc t_user;

show create table t_group \G;

show create table t_user \G;

show index from t_group \G;

show index from t_user \G;

select * from t_group;

select * from t_user;

-- m2m


desc t_student;

desc t_teacher;

desc t_s;

show create table t_student \G;

show create table t_teacher \G;

show create table t_s \G;

show index from t_student \G;

show index from t_teacher \G;

show index from t_s \G;

select * from t_student;

select * from t_teacher;

select * from t_s;


-- criteria

desc husband;

desc wife;

desc hibernate_sequences


select * from husband;

select * from wife;

-- hql

desc t_group;

desc t_user;

show create table t_group \G;

show create table t_user \G;

show index from t_group \G;

show index from t_user \G;

select * from t_group;

select * from t_user;


-- annotation

show tables;

drop table if exists husband;

drop table if exists wife;

drop table if exists stu, 
				t_user,
				t_group,
				t_person,
				t_student,
				t_teacher,
				t_s
				;


drop table if exists t_student; 

drop database if exists test_db;

show databases;

create database test_db;

desc t_group;

desc t_user;


-- inherite

desc t_person;

desc t_student;

desc t_teacher;

select * from t_person;

select * from t_student;

select * from t_teacher;






