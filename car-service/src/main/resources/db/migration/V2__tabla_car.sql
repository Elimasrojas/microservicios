DROP TABLE IF EXISTS car;
CREATE TABLE car (
                     id int not null AUTO_INCREMENT,
                     brand varchar(255) ,
                     model varchar(255) ,
                     user_id int,
                     PRIMARY KEY (id)
)