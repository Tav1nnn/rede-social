CREATE TABLE `comment` (
  `id` int PRIMARY KEY AUTO_INCREMENT,
  `content` varchar(200),
  `id_father` int default 0,
  layer int default 0,
  `create_at` date
);
