CREATE TABLE `user` (
	`id` INT(10) NOT NULL AUTO_INCREMENT,
	`username` VARCHAR(20) NOT NULL COLLATE 'utf8mb4_0900_ai_ci',
	`email` VARCHAR(50) NOT NULL COLLATE 'utf8mb4_0900_ai_ci',
	`password` VARCHAR(50) NOT NULL COLLATE 'utf8mb4_0900_ai_ci',
	`cep` VARCHAR(8) NOT NULL COLLATE 'utf8mb4_0900_ai_ci',
	`birthday` DATE NOT NULL,
	`biography` VARCHAR(200) NOT NULL COLLATE 'utf8mb4_0900_ai_ci',
	`account_non_expired` TINYINT(1) NULL DEFAULT '1',
	`account_non_locked` TINYINT(1) NULL DEFAULT '1',
	`credentials_non_expired` TINYINT(1) NULL DEFAULT '1',
	`enabled` TINYINT(1) NULL DEFAULT '1',
	`create_at` DATE NULL DEFAULT NULL,
	`update_at` DATE NULL DEFAULT NULL,
	PRIMARY KEY (`id`) USING BTREE,
	UNIQUE INDEX `username` (`username`) USING BTREE,
	UNIQUE INDEX `email` (`email`) USING BTREE
)
COLLATE='utf8mb4_0900_ai_ci'
ENGINE=InnoDB
AUTO_INCREMENT=9
;
