
 connect 'jdbc:derby:RunnerDB;create=true';
 DROP TABLE RunnerDetails;
 
 CREATE TABLE RunnerDetails
 (
 	RunnerName VARCHAR(20),
 	RunnersMoveIncrement INT,
 	RestPercentage INT
 );

INSERT INTO RunnerDetails VALUES ('Tortoise',10,0);
INSERT INTO RunnerDetails VALUES ('Hare',100,90);
INSERT INTO RunnerDetails VALUES ('Dog',50,40);
INSERT INTO RunnerDetails VALUES ('Cat',30,75);
INSERT INTO RunnerDetails VALUES ('Horse',100,50);

SELECT * FROM RunnerDetails;