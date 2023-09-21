ALTER TABLE `like` ADD COLUMN `publication_like` INT(10) NOT NULL;
ALTER TABLE `like` ADD CONSTRAINT `publication_like` FOREIGN KEY (`publication_like`) REFERENCES `publication` (`id`);