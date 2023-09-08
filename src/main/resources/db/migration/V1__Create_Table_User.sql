CREATE TABLE `user` (
  `id` int PRIMARY KEY AUTO_INCREMENT,
  `username` varchar(20) UNIQUE NOT NULL,
  `email` varchar(50) UNIQUE NOT NULL,
  `password` varchar(50) NOT NULL,
  `cep` varchar(8) NOT NULL,
  `birthday` date NOT NULL,
  `biography` varchar(200) NOT NULL
  );

