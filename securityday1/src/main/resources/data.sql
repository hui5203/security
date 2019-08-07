-- INSERT INTO `user_info` VALUES ('1', '123456', 'ROLE_ADMIN', 'admin');
-- INSERT INTO `user_info` VALUES ('2', '123456', 'ROLE_USER', 'user');

-- 初始化role
insert into role(name,description,rid) value ('ADMIN','管理员','1')
insert into role(name,description,rid) value ('USER','普通用户','2')
--
-- -- 初始化userinfo
insert into user_info(password,username,uid) value ('2','admin','1')
insert into user_info(password,username,uid) value ('3','user','2')


-- 给user配置role
insert into user_role(rid,uid) value ('1','1')
insert into user_role(rid,uid) value ('2','1')
insert into user_role(rid,uid) value ('2','2')