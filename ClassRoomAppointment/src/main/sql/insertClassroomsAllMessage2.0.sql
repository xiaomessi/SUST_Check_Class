CREATE PROCEDURE classroomInsert
  (IN v_build VARCHAR(50),IN v_buildnumber VARCHAR(5),IN v_buildlevel INT,IN v_day INT,
  IN v_time INT,IN v_week INT,IN v_classroom INT,OUT insert_count INT)
  BEGIN
     declare v_build_id INT;
     declare v_build_buildnumber_id INT;
     declare v_buildlevel_classroom_id INT;
     declare v_classroom_id INT;
     declare v_week_day_id INT;
     declare v_time_id INT;
    DECLARE select_count INT DEFAULT 0;
    START TRANSACTION;
    INSERT IGNORE into build(build) VALUES(v_build);
    SELECT build_id into v_build_id from build where build=v_build;
    INSERT IGNORE into build_buildnumber(build_id,buildnumber) VALUES (v_build_id,v_buildnumber);
    SELECT build_buildnumber_id into v_build_buildnumber_id from build_buildnumber where build_id=v_build_id and buildnumber=v_buildnumber;
    INSERT IGNORE into buildlevel_classroom (buildlevel,classroom) VALUES (v_buildlevel,v_classroom);
    SELECT buildlevel_classroom_id into v_buildlevel_classroom_id from buildlevel_classroom where buildlevel=v_buildlevel and classroom=v_classroom;
    INSERT IGNORE into classroom_all(build_buildnumber_id,buildlevel_classroom_id) VALUES (v_build_buildnumber_id,v_buildlevel_classroom_id);
    SELECT id into v_classroom_id from classroom_all where build_buildnumber_id=v_build_buildnumber_id and buildlevel_classroom_id=v_buildlevel_classroom_id;
    INSERT IGNORE into week_day(week,day) VALUES (v_week,v_day);
    SELECT id into v_week_day_id from week_day where week=v_week and day=v_day;
    INSERT IGNORE into week_day_time(week_day_id,time) VALUES (v_week_day_id,v_time);
    SELECT id into v_time_id from week_day_time where week_day_id=v_week_day_id and time=v_time;
    SELECT id into select_count from query where time_id = v_time_id and classroom_id = v_classroom_id;
    IF (select_count = 0) THEN
      INSERT into query(time_id,classroom_id) VALUES (v_time_id,v_classroom_id);
      -- 如果插入成功，将insert_count设置为1
      SELECT row_count() INTO insert_count;
      COMMIT ;
    ELSE
      -- 如果没进行插入，将insert_count设置为0
      SET insert_count = 0;
      COMMIT;
    END IF;

  END;
$$
-- 存储过程定义结束

DELIMITER ; -- 还原为;

-- 查看创建的存储过程
show create procedure classroomInsert;
-- 删除存储过程
drop PROCEDURE classroomInsert;

-- 测试数据
set @insert_count=-3;
-- 执行存储过程
call classroomInsert('哈哈','H',20,99,999,30,55,@insert_count);
-- 获取结果
select @insert_count;
