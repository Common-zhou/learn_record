DROP PROCEDURE IF EXISTS insert_user;

DELIMITER ;;
CREATE PROCEDURE insert_user()
BEGIN
	DECLARE i INT DEFAULT 1;
WHILE i<1000000 DO
	set autocommit = 1;
insert into user(name,password,salt,birthday,create_time,update_time) values(concat('name',i), concat('password', i),'salt',now(),now(),now());
SET i=i+1;
END WHILE ;
commit;
END ;;

DELIMITER ;
call insert_user();
