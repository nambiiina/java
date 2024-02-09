create table if not exists users(username varchar(50) not null primary key,password varchar(500) not null,enabled boolean not null);
create table if not exists authorities (username varchar(50) not null,authority varchar(50) not null,constraint fk_authorities_users foreign key(username) references users(username));

set @x := (select count(*) from information_schema.statistics where table_schema = 'jdbc-auth-db' and table_name = 'authorities' and index_name = 'ix_auth_username');
set @sql := if(@x > 0, 'select ''Index exists.''', 'Alter Table authorities add index ix_auth_username (username, authority)');
prepare stmt from @sql;
execute stmt;

--create unique index ix_auth_username on authorities (username,authority);