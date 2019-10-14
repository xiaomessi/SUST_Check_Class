DELIMITER $$ -- console ; 转换为 $$

-- 定义存储过程
CREATE PROCEDURE userSelect
  (IN v_organization VARCHAR(50),IN v_name VARCHAR(50),IN v_telphone VARCHAR(50),IN v_description VARCHAR(250),
  OUT v_build VARCHAR(50),OUT v_buildnumber VARCHAR(5),OUT v_buildlevel INT,OUT v_day INT,
  OUT v_time INT,OUT v_week INT,OUT v_classroom INT)
  -- 添加OUT，返回的不同表的信息
  BEGIN
     declare v_build_id INT;
     declare v_build_buildnumber_id INT;
     declare v_buildlevel_classroom_id INT;
     declare v_classroom_id INT;
     declare v_week_day_id INT;
     declare v_time_id INT;
     declare v_user_id INT;
     declare v_query_id INT;

    START TRANSACTION;

    -- 根据社团信息，查出社团id
    SELECT id into v_user_id from users where organization = v_organization and name = v_name and telphone = v_telphone;

    -- 根据社团id和description，查出query_id
    SELECT query_id into v_query_id from occupy where user_id = v_user_id and description = v_description;

    -- 根据query_id，查出time_id,classroom_id
    SELECT time_id into v_time_id from query where id = v_query_id;
    SELECT classroom_id into v_classroom_id from query where id = v_query_id;

    -- 根据time_id，查出week_day_id和time，并将查询到的time赋给要返回的v_time
    SELECT week_day_id into v_week_day_id from week_day_time where id = v_time_id;
    SELECT time into v_time from week_day_time where id = v_time_id;

    -- 根据week_day_id，查出week和day，并将查询到的week和day赋给要返回的v_week和v_day
    SELECT week into v_week from week_day where id = v_week_day_id;
    SELECT day into v_day from week_day where id = v_week_day_id;

    -- 根据classroom_id，查出build_buildnumber_id和buildlevel_classroom_id
    SELECT build_buildnumber_id into v_build_buildnumber_id from classroom_all where id = v_classroom_id;
    SELECT buildlevel_classroom_id into v_buildlevel_classroom_id from classroom_all where id = v_classroom_id;

    -- 根据buildlevel_classroom_id，查出buildlevel和classroom，并将查到的buildlevel和classroom赋给v_buildlevel和v_classroom
    SELECT buildlevel into v_buildlevel from buildlevel_classroom where buildlevel_classroom_id = v_buildlevel_classroom_id;
    SELECT classroom into v_classroom from buildlevel_classroom where buildlevel_classroom_id = v_buildlevel_classroom_id;

    -- 根据build_buildnumber_id，查出build_id和buildnumber，并将查到的buildnumber赋给v_buildnumber
    SELECT build_id into v_build_id from build_buildnumber where build_buildnumber_id = v_build_buildnumber_id;
    SELECT buildnumber into v_buildnumber from build_buildnumber where build_buildnumber_id = v_build_buildnumber_id;

    -- 根据build_id，查出build，并将查到的build赋给v_build
    SELECT build into v_build from build where build_id = v_build_id;

    -- 事务提交
    COMMIT;
  END;
$$
-- 存储过程定义结束

DELIMITER ; -- 还原为;

-- 查看创建的存储过程
show create procedure userSelect;
-- 删除存储过程
drop PROCEDURE userSelect;

-- 测试数据
set @v_build='';
set @v_buildnumber='';
set @v_buildlevel=-1;
set @v_day=-1;
set @v_time=-1;
set @v_week=-1;
set @v_classroom=-1;

-- 执行存储过程
call userSelect('晓清潭','王','12345678999','开会',@v_build,@v_buildnumber,@v_buildlevel,@v_day,@v_time,@v_week,@v_classroom);

-- 获取结果
select @v_build;
select @v_buildnumber;
select @v_buildlevel;
select @v_day;
select @v_time;
select @v_week;
select @v_classroom;