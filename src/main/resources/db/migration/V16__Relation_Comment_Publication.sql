ALTER TABLE `comment` ADD COLUMN `publication_comment` INT(10) NOT NULL;
ALTER TABLE `comment` ADD CONSTRAINT `publication_comment` FOREIGN KEY (`publication_comment`) REFERENCES `publication` (`id`);