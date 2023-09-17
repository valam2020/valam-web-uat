CREATE TABLE IF NOT EXISTS RIDE_STATUS (
     sts_id int NOT NULL AUTO_INCREMENT,
     status_name varchar(100) NOT NULL,
      PRIMARY KEY (sts_id)
);

INSERT INTO RIDE_STATUS(STS_ID,STATUS_NAME) VALUES('1','Ride Request');
INSERT INTO RIDE_STATUS(STS_ID,STATUS_NAME) VALUES('2','Driver Available');
INSERT INTO RIDE_STATUS(STS_ID,STATUS_NAME) VALUES('3','No Driver Available');
INSERT INTO RIDE_STATUS(STS_ID,STATUS_NAME) VALUES('4','Accepted');
INSERT INTO RIDE_STATUS(STS_ID,STATUS_NAME) VALUES('5','Arriving');
INSERT INTO RIDE_STATUS(STS_ID,STATUS_NAME) VALUES('6','In Progress');
INSERT INTO RIDE_STATUS(STS_ID,STATUS_NAME) VALUES('7','Driver Cancelled');
INSERT INTO RIDE_STATUS(STS_ID,STATUS_NAME) VALUES('8','Rider Cancelled');
INSERT INTO RIDE_STATUS(STS_ID,STATUS_NAME) VALUES('10','Completed');
INSERT INTO RIDE_STATUS(STS_ID,STATUS_NAME) VALUES('11','InActive');
INSERT INTO RIDE_STATUS(STS_ID,STATUS_NAME) VALUES('12','Active');
INSERT INTO RIDE_STATUS(STS_ID,STATUS_NAME) VALUES('13','Available');
INSERT INTO RIDE_STATUS(STS_ID,STATUS_NAME) VALUES('14','Not Available');


