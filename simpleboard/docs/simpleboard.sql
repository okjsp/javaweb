GRANT ALL PRIVILEGES ON devcamp.* TO javauser@localhost
 IDENTIFIED BY 'javadude' WITH GRANT OPTION;

create database devcamp;

create table devcamp.simpleboard (
  id int not null auto_increment primary key,
  writer varchar(50),
  title varchar(100),
  content text);

insert into devcamp.simpleboard (writer, writer, content) values ('kenu', 'kenu.heo@gmail.com', '게시판 내용입니다.');
