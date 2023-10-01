ALTER TABLE `comment` ADD COLUMN `user_comment` INT(10) NOT NULL;
ALTER TABLE `comment` ADD CONSTRAINT `user_comment` FOREIGN KEY (`user_comment`) REFERENCES `user` (`id`);