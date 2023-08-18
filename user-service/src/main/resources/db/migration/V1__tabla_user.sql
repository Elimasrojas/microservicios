DROP TABLE IF EXISTS user;
CREATE TABLE user (
                      id int not null AUTO_INCREMENT,
                      name varchar(255) ,
                      email varchar(255) ,
                      PRIMARY KEY (id)
)