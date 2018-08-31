CREATE TABLE `user` (
  `id` int PRIMARY KEY AUTO_INCREMENT,
  `name` varchar(256) DEFAULT NULL,
  `birthday` date DEFAULT NULL,
  `login_id` varchar(256) DEFAULT NULL,
  `login_password` varchar(256) DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  `update_date` datetime DEFAULT NULL
)