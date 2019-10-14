-- 创建插入所有表的存储过程
-- 一条完整的数据需要分别插入对应的表
-- 包括关系表（因为在查询时会用到）
DELIMITER $$ -- console ; 转换为 $$

-- 定义存储过程
CREATE PROCEDURE classroomInsert
  (IN v_build VARCHAR(50),IN v_buildnumber VARCHAR(5),IN v_buildlevel INT,IN v_day INT,
  IN v_time INT,IN v_week INT,IN v_classroom INT,IN v_organization VARCHAR(50),IN v_name VARCHAR(50)
  ,IN v_telphone VARCHAR(50),IN v_description VARCHAR(250),OUT insert_count INT)
  -- 添加OUT，返回的r_result若为0则没插入（重复插入），若为1则插入成功
  BEGIN
     declare v_build_id INT;
     declare v_build_buildnumber_id INT;
     declare v_buildlevel_classroom_id INT;
     declare v_classroom_id INT;
     declare v_week_day_id INT;
     declare v_time_id INT;
     declare v_user_id INT;
     declare v_query_id INT DEFAULT 0;
     -- 添加返回值，给后端判断到底有没有添加成功
     -- 添加insert_count的值，插入是否成功，重复插入返回0，否则返回非0
--      declare insert_count INT DEFAULT 1;

--     设置事务：一旦设置了事务，在该段代码中要么ROLLBACK（回滚），要么COMMIT（提交），
--     否则只有遇到下一次事务开启时，这次事务才会被迫结束（这就导致了刚才那个问题）
--     -- 问题是：
--     -- 第一次插入：result->1 但不插入
--     -- 第二次插入：result->0 但插入
--     -- 应该是哪里写反了
--        m d z z

    START TRANSACTION;

    INSERT IGNORE into build(build) VALUES(v_build);
    SELECT build_id into v_build_id from build where build=v_build;

    INSERT IGNORE into build_buildnumber(build_id,buildnumber) VALUES (v_build_id,v_buildnumber);
    SELECT build_buildnumber_id into v_build_buildnumber_id from build_buildnumber where build_id=v_build_id and buildnumber=v_buildnumber;

    INSERT IGNORE into buildlevel_classroom (buildlevel,classroom) VALUES (v_buildlevel,v_classroom);
    SELECT buildlevel_classroom_id into v_buildlevel_classroom_id from buildlevel_classroom where buildlevel=v_buildlevel and classroom=v_classroom;

    -- 因为教室已经完全定死了，所以不可能存在新创建的教室
    -- 具体教室和具体时间都定死了，所以只能对这些定死了的教室和时间进行操作，需要变的只有那个query

    -- 如果insert_count=0，证明没插入成功，如果不是0证明插入成功
    -- 问题是：
    -- 第一次插入：result->1 但不插入
    -- 第二次插入：result->0 但插入
    -- 应该是哪里写反了

    -- 问题解决——事务的提交与回滚

--     IF (insert_count = 0) THEN
--       SET r_result = 0;
--     ELSE
--       SET r_result = 1;
--     END IF;

    INSERT IGNORE into classroom_all(build_buildnumber_id,buildlevel_classroom_id) VALUES (v_build_buildnumber_id,v_buildlevel_classroom_id);
    SELECT id into v_classroom_id from classroom_all where build_buildnumber_id=v_build_buildnumber_id and buildlevel_classroom_id=v_buildlevel_classroom_id;

    INSERT IGNORE into week_day(week,day) VALUES (v_week,v_day);
    SELECT id into v_week_day_id from week_day where week=v_week and day=v_day;

    INSERT IGNORE into week_day_time(week_day_id,time) VALUES (v_week_day_id,v_time);
    SELECT id into v_time_id from week_day_time where week_day_id=v_week_day_id and time=v_time;

    SELECT id into v_query_id from query where time_id = v_time_id and classroom_id = v_classroom_id;

    -- 如果select_count=0——证明还没有插入过，所以需要插入数据，如果select_count!=0，证明已经插入过了（已经可以查到信息）
    IF (v_query_id = 0) THEN
      INSERT into query(time_id,classroom_id) VALUES (v_time_id,v_classroom_id);
      -- 如果插入成功，将insert_count设置为1
      SELECT row_count() INTO insert_count;

      -- 只有当前教室没有被预约过才会插入教室信息+（教室信息+社团信息）
      -- 再查一下query_id
      SELECT id into v_query_id from query where time_id = v_time_id and classroom_id = v_classroom_id;

      -- 根据社团信息，查出社团id
      SELECT id into v_user_id from users where organization = v_organization and name = v_name and telphone = v_telphone;

      -- 将query_id和user_id和description插入到对应表中
      INSERT into occupy(query_id,user_id,description) VALUES (v_query_id,v_user_id,v_description);

      -- 事务提交
      COMMIT;
    ELSE
      -- 如果没进行插入，将insert_count设置为0
      SET insert_count = 0;
      -- 已经插入过了，就直接提交事务，否则还会执行下面的插入操作
      COMMIT ;
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
call classroomInsert('哈哈','H',20,99,999,30,55,'校青团','李老师','12345678910','开会',@insert_count);
-- 获取结果
select @insert_count;
