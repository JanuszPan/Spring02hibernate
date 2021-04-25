insert into publishers(name,regon,nip) values ('Helion',337473111,5094884892), ('Atena',370163623,5274209258);
insert into authors(firstName, lastName, pesel,email) values ('Jan', 'Kowalski',73051969411,'jan.kowalski@wp.pl'), ('Anna', 'Nowak',50021177926,'anna.nowak@wp.pl');
insert into books( description,rating, title, pages,publisher_id) values ('powieść historyczna',5,'Ogniem i mieczem',200,1),('podręcznik',6,'Java',300,2);
insert into category(name ) values ('literatura piękna'),('literatura naukowa'),('literatura obca');
insert into users(username, password, enabled, email) values ('admin','$2a$12$JOg9QO7ZOwxjjoU2XzxiZuuKYi0K7asptCQ.ITNFOGmBWFsY9/y7K',true,'admin@abc.pl'), ('user','$2a$12$t2QhTTeMH61bkgtgJZMqQuTf89tCl4fe9pjNa5Ex2ZxxUt8l.pan6 ',false,'user@abc.pl');;
insert into authorities(authority) values ('ADMIN'),('USER');
insert into users_authorities(user_id,authorities_id) values (1,2),(2,1),(2,2);

-- create table  users (username varchar(50) not null primary key, password varchar(500) not null, enabled  bit not null);
-- create table  authorities (username  varchar(50) not null, authority varchar(50) not null, constraint fk_authorities_users foreign key (username) references users (username));
-- create unique index ix_auth_username on authorities (username,authority);

