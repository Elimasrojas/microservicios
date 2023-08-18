DROP TABLE IF EXISTS bike ;
CREATE TABLE bike (
                     id int not null AUTO_INCREMENT,
                     brand varchar(255) ,
                     model varchar(255) ,
                     user_id int,
                     PRIMARY KEY (id)
)