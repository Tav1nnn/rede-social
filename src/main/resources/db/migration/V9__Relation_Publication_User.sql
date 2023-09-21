ALTER TABLE `publication` ADD COLUMN `user_publication` INT(10) NOT NULL;
ALTER TABLE `publication` ADD CONSTRAINT `user_publication` FOREIGN KEY (`user_publication`) REFERENCES `user` (`id`);
