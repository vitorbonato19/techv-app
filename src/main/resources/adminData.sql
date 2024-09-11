CREATE TABLE `tickets` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `finished` int DEFAULT NULL,
  `type_ticket` int DEFAULT NULL,
  `created_at` datetime(6) DEFAULT NULL,
  `finished_at` datetime(6) DEFAULT NULL,
  `id_user` bigint DEFAULT NULL,
  `analyst` varchar(255) DEFAULT NULL,
  `reply` text,
  `requester` varchar(255) DEFAULT NULL,
  `text` text,
  PRIMARY KEY (`id`),
  KEY `FKapd15f0nuk9sn4f3u0sqry0ts` (`id_user`),
  CONSTRAINT `FKapd15f0nuk9sn4f3u0sqry0ts` FOREIGN KEY (`id_user`) REFERENCES `usuarios` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci

