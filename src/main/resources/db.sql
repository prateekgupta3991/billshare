CREATE TABLE `gang` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '',
  PRIMARY KEY (`id`)
)

CREATE TABLE `user` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '',
  `email` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '',
  `contact` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '',
  PRIMARY KEY (`id`)
)

CREATE TABLE `bill` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '',
  `amount` double NOT NULL,
  PRIMARY KEY (`id`)
)

alter table gang add column admin_user_id bigint;

CREATE TABLE user_gang (
  id bigint NOT NULL AUTO_INCREMENT,
  gang_id bigint NOT NULL,
  user_id bigint NOT NULL,
  PRIMARY KEY (`id`)
)

alter table user_gang add constraint fk1 foreign key (gang_id) references gang(id);
alter table user_gang add constraint fk2 foreign key (user_id) references user(id);