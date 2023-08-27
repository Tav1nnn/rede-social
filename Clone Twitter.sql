CREATE TABLE `user` (
  `id` int PRIMARY KEY AUTO_INCREMENT,
  `username` varchar(20) UNIQUE NOT NULL,
  `email` varchar(50) UNIQUE NOT NULL,
  `password` varchar(50) NOT NULL,
  `cep` varchar(8) NOT NULL,
  `birthday` date NOT NULL,
  `biography` varchar(200) NOT NULL,
  `account_non_expired` boolean NOT NULL DEFAULT true,
  `credentials_non_expired` boolean NOT NULL DEFAULT true,
  `enabled` boolean NOT NULL DEFAULT true,
  `create_at` date,
  `update_at` date
);

CREATE TABLE `publication` (
  `id` int PRIMARY KEY AUTO_INCREMENT,
  `tweet` varchar(200) NOT NULL,
  `create_at` date
);

CREATE TABLE `like` (
  `id` int PRIMARY KEY AUTO_INCREMENT,
  `create_at` date
);

CREATE TABLE `comment` (
  `id` int PRIMARY KEY AUTO_INCREMENT,
  `text` varchar(100),
  `create_at` date
);

CREATE TABLE `sub_comment` (
  `id` int PRIMARY KEY AUTO_INCREMENT,
  `text` varchar(100),
  `create_at` date
);

ALTER TABLE `publication` ADD CONSTRAINT `user_publication` FOREIGN KEY (`id`) REFERENCES `user` (`id`);

ALTER TABLE `like` ADD CONSTRAINT `user_like` FOREIGN KEY (`id`) REFERENCES `user` (`id`);

ALTER TABLE `comment` ADD CONSTRAINT `user_comment` FOREIGN KEY (`id`) REFERENCES `user` (`id`);

ALTER TABLE `like` ADD CONSTRAINT `publication_like` FOREIGN KEY (`id`) REFERENCES `publication` (`id`);

ALTER TABLE `comment` ADD CONSTRAINT `publication_comment` FOREIGN KEY (`id`) REFERENCES `publication` (`id`);
