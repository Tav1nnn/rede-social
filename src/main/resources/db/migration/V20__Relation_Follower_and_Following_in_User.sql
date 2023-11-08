ALTER TABLE `follower` ADD COLUMN `user_follower` INT(10) NOT NULL;
ALTER TABLE `follower` ADD CONSTRAINT `user_follower` FOREIGN KEY (`user_follower`) REFERENCES `user` (`id`);

ALTER TABLE `following` ADD COLUMN `user_following` INT(10) NOT NULL;
ALTER TABLE `following` ADD CONSTRAINT `user_following` FOREIGN KEY (`user_following`) REFERENCES `user` (`id`);