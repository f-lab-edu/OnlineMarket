USE Market;
CREATE TABLE if not exists `User` (
									  `id` bigint unsigned NOT NULL AUTO_INCREMENT,
									  `email` varchar(30) NOT NULL,
	`password` varchar(80) NOT NULL,
	`name` varchar(20) NOT NULL,
	`tel` varchar(20) NOT NULL,
	`isMembership` tinyint(1) NOT NULL DEFAULT '0',
	`createdAt` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
	`updatedAt` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
	PRIMARY KEY (`id`),
	UNIQUE KEY `email_UNIQUE` (`email`)
	);
CREATE TABLE if not exists `App` (
									 `id` bigint unsigned NOT NULL AUTO_INCREMENT,
									 `name` varchar(45) NOT NULL,
	`isMembership` tinyint NOT NULL DEFAULT '0' COMMENT '멤버쉽이 필요한 앱',
	`updatedAt` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
	`createdAt` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
	PRIMARY KEY (`id`),
	UNIQUE KEY `name_UNIQUE` (`name`)
	);
CREATE TABLE if not exists `Address` (
						   `id` bigint unsigned NOT NULL AUTO_INCREMENT,
						   `name` varchar(45) NOT NULL,
						   `isMain` tinyint NOT NULL DEFAULT '0',
						   `mainAddress` varchar(80) NOT NULL,
						   `subAddress` varchar(80) NOT NULL,
						   `userId` bigint unsigned NOT NULL,
						   `updatedAt` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
						   `createdAt` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
						   PRIMARY KEY (`id`),
						   KEY `userId_idx` (`userId`),
						   CONSTRAINT `address_userId` FOREIGN KEY (`userId`) REFERENCES `User` (`id`)
);


CREATE TABLE if not exists `UserDevice` (
							  `id` bigint unsigned NOT NULL AUTO_INCREMENT,
							  `userId` bigint unsigned NOT NULL,
							  `uuid` varchar(150) NOT NULL,
							  `model` varchar(45) NOT NULL,
							  `ip` varchar(45) NOT NULL,
							  `osVersion` varchar(45) NOT NULL,
							  `createdAt` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
							  `updatedAt` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
							  PRIMARY KEY (`id`),
							  UNIQUE KEY `userId_uuid_model_osVersion_UNIQUE` (`userId`,`uuid`,`model`,`osVersion`),
							  KEY `userDevice_userId_idx` (`userId`),
							  CONSTRAINT `userDevice_userId` FOREIGN KEY (`userId`) REFERENCES `User` (`id`)
);
CREATE TABLE if not exists `UserDeviceApps` (
								  `id` bigint unsigned NOT NULL AUTO_INCREMENT,
								  `userId` bigint unsigned NOT NULL,
								  `deviceId` bigint unsigned NOT NULL,
								  `appId` bigint unsigned NOT NULL,
								  `updatedAt` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
								  `createdAt` timestamp NOT NULL,
								  PRIMARY KEY (`id`),
								  UNIQUE KEY `userId_deviceId_appId_UNIQUE` (`userId`,`deviceId`,`appId`),
								  KEY `userApp_userId_idx` (`userId`),
								  KEY `userApp_appId_idx` (`appId`),
								  KEY `userDeviceApps_deviceId_idx` (`deviceId`),
								  CONSTRAINT `userDeviceApps_appId` FOREIGN KEY (`appId`) REFERENCES `App` (`id`),
								  CONSTRAINT `userDeviceApps_deviceId` FOREIGN KEY (`deviceId`) REFERENCES `UserDevice` (`id`),
								  CONSTRAINT `userDeviceApps_userId` FOREIGN KEY (`userId`) REFERENCES `User` (`id`)
);
