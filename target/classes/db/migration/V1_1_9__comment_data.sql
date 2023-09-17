CREATE TABLE IF NOT EXISTS comments_table (
  comment_id int NOT NULL AUTO_INCREMENT,
  comment varchar(600) ,
  user_id int,
  driver_id int,
  created_date date,
  PRIMARY KEY (comment_id)
);
