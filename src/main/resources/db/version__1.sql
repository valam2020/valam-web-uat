DROP TABLE IF EXISTS USER_DETAILS;
CREATE TABLE IF NOT EXISTS USER_DETAILS (
  user_id int NOT NULL AUTO_INCREMENT,
  first_name varchar(100) NOT NULL,
  last_name varchar(100) NOT NULL,
  email_id varchar(100) NOT NULL,
  ph_num varchar(20) DEFAULT NULL,
  PRIMARY KEY (user_id)
);

DROP TABLE IF EXISTS DRIVER_DETAILS;
CREATE TABLE IF NOT EXISTS DRIVER_DETAILS (
  driver_id int NOT NULL AUTO_INCREMENT,
  first_name varchar(100) NOT NULL,
  last_name varchar(100) NOT NULL,
  email_id varchar(100) NOT NULL,
  ph_num varchar(20) NOT NULL,
  dl_num varchar(30) NOT NULL,
  address varchar(300) NOT NULL,
  PRIMARY KEY (driver_id)
);

DROP TABLE IF EXISTS USER_DRIVER_RIDES;
CREATE TABLE IF NOT EXISTS USER_DRIVER_RIDES (
  ride_id int NOT NULL AUTO_INCREMENT,
  driver_id int NOT NULL,
  user_id int NOT NULL,
  from_address varchar(100) NOT NULL,
  to_address varchar(100) NOT NULL,
  fare decimal(10,2) NOT NULL,
  ride_date datetime NOT NULL,
  address varchar(300) NOT NULL,
  FOREIGN KEY (driver_id) REFERENCES DRIVER_DETAILS (driver_id) ON DELETE RESTRICT ON UPDATE CASCADE,
  FOREIGN KEY (user_id) REFERENCES USER_DETAILS (user_id) ON DELETE RESTRICT ON UPDATE CASCADE,
  PRIMARY KEY (ride_id)
)


