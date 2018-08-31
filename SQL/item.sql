CREATE TABLE `item` (
  `id` int PRIMARY KEY AUTO_INCREMENT,
  `name` varchar(256) DEFAULT NULL,
  `detail` text,
  `allergy_egg` bit DEFAULT NULL,
  `allergy_wheat` bit DEFAULT NULL,
  `allergy_milk` bit DEFAULT NULL,
  `allergy_other` text,
  `price` int DEFAULT NULL,
  `file_name` varchar(256) DEFAULT NULL,
  `stock` int DEFAULT NULL,
  `buy_sum` int DEFAULT NULL
)