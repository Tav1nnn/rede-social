ALTER TABLE `share` ADD COLUMN `publication_share` INT(10) NOT NULL;
ALTER TABLE `share` ADD CONSTRAINT `publication_share` FOREIGN KEY (`publication_share`) REFERENCES `publication` (`id`);