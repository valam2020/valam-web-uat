CREATE TABLE IF NOT EXISTS DISPATCHER_DETAILS (
  dispatcher_id int NOT NULL AUTO_INCREMENT,
  first_name varchar(100) NOT NULL,
  last_name varchar(100) NOT NULL,
  created_date date,
  modified_date date,
  address varchar(300) NOT NULL,
  dis_reg_id varchar(300) NOT NULL,
  email varchar(300) NOT NULL,
  ph_num varchar(300) NOT NULL,
  latitude DECIMAL(15,10) ,
  longitude DECIMAL(20,10) ,
  pincode varchar(10),
  image_url varchar(300),
  is_deleted boolean,
  PRIMARY KEY (dispatcher_id)
);


INSERT INTO DISPATCHER_DETAILS(DISPATCHER_ID,first_name,ADDRESS,last_name,DIS_REG_ID,EMAIL,PH_NUM,created_date) VALUES('1','Test','Test','Test','Test','test@gmail.com','+919999999999','2021-02-14');
INSERT INTO DISPATCHER_DETAILS(DISPATCHER_ID,first_name,ADDRESS,last_name,DIS_REG_ID,EMAIL,PH_NUM,created_date) VALUES('2','Ajth','ST STREET','Ram','ARTSDFG34','ajith@gmail.com','+919987455321','2021-02-14');
INSERT INTO DISPATCHER_DETAILS(DISPATCHER_ID,first_name,ADDRESS,last_name,DIS_REG_ID,EMAIL,PH_NUM,created_date) VALUES('3','Akash','JAMES STREET','Jay','A7THEGH234','akash@gmail.com','+919987456322','2021-02-14');
INSERT INTO DISPATCHER_DETAILS(DISPATCHER_ID,first_name,ADDRESS,last_name,DIS_REG_ID,EMAIL,PH_NUM,created_date) VALUES('4','Ram','CINE Square','Abhi','ARCBNVH234','ram@gmail.com','+919987546326','2021-02-14');
INSERT INTO DISPATCHER_DETAILS(DISPATCHER_ID,first_name,ADDRESS,last_name,DIS_REG_ID,EMAIL,PH_NUM,created_date) VALUES('5','Raj','CINE MALL','Rohith','A20CBNVH234','raj@gmail.com','+919987856328','2021-02-14');
INSERT INTO DISPATCHER_DETAILS(DISPATCHER_ID,first_name,ADDRESS,last_name,DIS_REG_ID,EMAIL,PH_NUM,created_date) VALUES('6','Ramu','Park Street','Abhay','A20BNVH234','ramu@gmail.com','+919987666321','2021-02-14');
INSERT INTO DISPATCHER_DETAILS(DISPATCHER_ID,first_name,ADDRESS,last_name,DIS_REG_ID,EMAIL,PH_NUM,created_date) VALUES('7','Honey','James Street','Ram','ARCB55234','honey@gmail.com','+919987556321','2021-02-14');
INSERT INTO DISPATCHER_DETAILS(DISPATCHER_ID,first_name,ADDRESS,last_name,DIS_REG_ID,EMAIL,PH_NUM,created_date) VALUES('8','Kajal','James Street','Abhi','ARCBNVH254','kajal@gmail.com','+919988456321','2021-02-14');
INSERT INTO DISPATCHER_DETAILS(DISPATCHER_ID,first_name,ADDRESS,last_name,DIS_REG_ID,EMAIL,PH_NUM,created_date) VALUES('9','Sam','CINE Square','Abhi','ARCBVBH234','sam@gmail.com','+919987456331','2021-02-14');
INSERT INTO DISPATCHER_DETAILS(DISPATCHER_ID,first_name,ADDRESS,last_name,DIS_REG_ID,EMAIL,PH_NUM,created_date) VALUES('10','Jack','CINE Square','Abhi','ARCBN32434','jack@gmail.com','+919987526321','2021-02-14');




