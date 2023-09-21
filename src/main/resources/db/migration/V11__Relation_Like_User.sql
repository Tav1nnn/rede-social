ALTER TABLE `like` ADD COLUMN `user_like` INT(10) NOT NULL;
ALTER TABLE `like` ADD CONSTRAINT `user_like` FOREIGN KEY (`user_like`) REFERENCES `user` (`id`);