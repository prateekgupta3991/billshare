CREATE TABLE `gang` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(64) NOT NULL,
  `admin_user_id` bigint NOT NULL,
  PRIMARY KEY (`id`)
)

CREATE TABLE `user` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(256) NOT NULL,
  `email` varchar(256) NOT NULL,
  `contact` varchar(10) NOT NULL,
  PRIMARY KEY (`id`)
)

CREATE TABLE `bill` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(64) NOT NULL,
  `amount` double NOT NULL,
  `gang_id` bigint,
  PRIMARY KEY (`id`)
)

CREATE TABLE user_gang (
  id bigint NOT NULL AUTO_INCREMENT,
  gang_id bigint NOT NULL,
  user_id bigint NOT NULL,
  PRIMARY KEY (`id`)
)

alter table user_gang add constraint fk1 foreign key (gang_id) references gang(id);
alter table user_gang add constraint fk2 foreign key (user_id) references user(id);
alter table bill add constraint fk3 foreign key (gang_id) references gang(id);