
CREATE TABLE `user_role` (
  `user_id` int,
  `role_id` int,
  PRIMARY KEY (`user_id`, `role_id`)
);

ALTER TABLE `user_role` ADD FOREIGN KEY (`user_id`) REFERENCES `user` (`id`);