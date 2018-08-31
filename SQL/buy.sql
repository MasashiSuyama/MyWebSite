CREATE TABLE `buy` (
  `id` int PRIMARY KEY AUTO_INCREMENT,
  `user_id` int NOT NULL,
  `total_price` int NOT NULL,
  `arrival_date` date NOT NULL,
  `create_date` date NOT NULL
)