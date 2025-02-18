
CREATE TABLE IF NOT EXISTS `roles` (
                         `name` varchar(255) DEFAULT NULL,
                         `role_id` bigint NOT NULL AUTO_INCREMENT,
                         PRIMARY KEY (`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE IF NOT EXISTS `sectors` (
                           `id` bigint NOT NULL AUTO_INCREMENT,
                           `name` varchar(255) DEFAULT NULL,
                           PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE IF NOT EXISTS `usuarios` (
                            `id` bigint NOT NULL AUTO_INCREMENT,
                            `username` varchar(255) DEFAULT NULL,
                            `email` varchar(255) DEFAULT NULL,
                            `password` varchar(255) DEFAULT NULL,
                            `zip_code` varchar(255) DEFAULT NULL,
                            `last_modified` datetime(6) DEFAULT NULL,
                            `integrated` int DEFAULT 0,
                            PRIMARY KEY (`id`),
                            UNIQUE KEY `UKm2dvbwfge291euvmk6vkkocao` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE IF NOT EXISTS `usersector` (
                              `userid` bigint NOT NULL,
                              `sectorid` bigint NOT NULL,
                              PRIMARY KEY (`userid`,`sectorid`),
                              KEY `FKnmeiy4dhbulmovd0784vqm6y0` (`sectorid`),
                              CONSTRAINT `FK4qvr5nkkih7dtn54i8nabw579` FOREIGN KEY (`userid`) REFERENCES `usuarios` (`id`),
                              CONSTRAINT `FKnmeiy4dhbulmovd0784vqm6y0` FOREIGN KEY (`sectorid`) REFERENCES `sectors` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE IF NOT EXISTS `usersroles` (
                              `userid` bigint NOT NULL,
                              `roleid` bigint NOT NULL,
                              PRIMARY KEY (`userid`,`roleid`),
                              KEY `FKklpmj6jheeu4k9xab4y3df6r1` (`roleid`),
                              CONSTRAINT `FKklpmj6jheeu4k9xab4y3df6r1` FOREIGN KEY (`roleid`) REFERENCES `roles` (`role_id`),
                              CONSTRAINT `FKlealr1d0c8emtjlb7ihxvmx4n` FOREIGN KEY (`userid`) REFERENCES `usuarios` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE IF NOT EXISTS `tickets` (
                           `id` bigint NOT NULL AUTO_INCREMENT,
                           `analyst` varchar(255) DEFAULT NULL,
                           `userid` bigint DEFAULT NULL,
                           `requester` varchar(255) DEFAULT NULL,
                           `reply` text,
                           `text` text,
                           `type` enum('ANOTHER','FEATURE','NETWORK_ERROR','PC_MAINTENCE') DEFAULT NULL,
                           `finished` tinyint(1) DEFAULT '0',
                           `created_at` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
                           `finished_at` datetime DEFAULT '2000-01-01 00:00:00',
                           PRIMARY KEY (`id`),
                           KEY `FKcd0d9n0i6v09e25ve0dc1tbw0` (`userid`),
                           CONSTRAINT `FKcd0d9n0i6v09e25ve0dc1tbw0` FOREIGN KEY (`userid`) REFERENCES `usuarios` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;