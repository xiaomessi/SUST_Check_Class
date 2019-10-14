--根据指定的教学楼号+楼栋+楼层确定该层的所有教室
select classroom from buildlevel_classroom where buildlevel=2 and buildlevel_classroom_id in
(select buildlevel_classroom_id from classroom_all where build_buildnumber_id in
(select build_buildnumber_id from build_buildnumber where buildnumber='E' and build_id in
(select build_id from build where build='一号教学楼')))

--根据指定的教学楼号+楼栋+楼层确定该层的上课教室
SELECT classroom from buildlevel_classroom where buildlevel=3 and buildlevel_classroom_id in
(SELECT buildlevel_classroom_id from classroom_all where build_buildnumber_id in
(SELECT build_buildnumber_id from build_buildnumber where buildnumber='E' and build_id in
(SELECT build_id from build where build='一号教学楼') and id in
(SELECT classroom_id from `query` where time_id in
(SELECT id from week_day_time where time=34 and week_day_id in
(SELECT id from week_day where week=7 and day=5)))))