ALTER TABLE `share` ADD COLUMN `user_share` INT(10) NOT NULL;
ALTER TABLE `share` ADD CONSTRAINT `user_share` FOREIGN KEY (`user_share`) REFERENCES `user` (`id`);