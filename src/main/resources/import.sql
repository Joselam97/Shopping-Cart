CREATE TABLE `categorias` (
  `id` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

INSERT INTO `categorias` VALUES (1,'Deporte'),(2,'Tecnologia'),(3,'Computacion'),(4,'Electrohogar'),(5,'Iluminacion');



CREATE TABLE `productos` (
  `id` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(45) DEFAULT NULL,
  `precio` int DEFAULT NULL,
  `fecha_registro` datetime DEFAULT NULL,
  `categoria_id` int DEFAULT NULL,
  `sku` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `sku_UNIQUE` (`sku`),
  KEY `productos_categorias_idx` (`categoria_id`),
  CONSTRAINT `productos_categorias` FOREIGN KEY (`categoria_id`) REFERENCES `categorias` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

INSERT INTO `productos` VALUES (1,'Bicicleta',350,'2020-12-10 00:00:00',1,'zxc'),(2,'Samsung TV ',300,'2021-05-29 00:00:00',2,NULL),(3,'Teclado Corsair k95 mecanico',400,'2023-05-07 00:00:00',3,'jkl'),(4,'Teclado Red Dragon mecanico',350,'2024-10-27 00:00:00',3,NULL),(5,'Refrigerador Samsung',750,'2023-12-13 00:00:00',4,NULL),(6,'Lampara Led Escritorio',850,'2024-12-24 00:00:00',5,NULL),(7,'Monitor Asus Tuf',850,'2024-10-28 00:00:00',3,'123'),(9,'Monitor HP',450,'2024-10-22 00:00:00',3,'abc'),(11,'Monitor Gigabyte',700,'2024-10-29 00:00:00',3,'456'),(13,'Lavadora GE',1250,'2024-10-31 00:00:00',4,'plm'),(14,'Ram 8Gb',95,'2024-11-03 00:00:00',3,'789'),(15,'CPU Intel',450,'2024-11-14 00:00:00',3,'a1b1');



CREATE TABLE `usuarios` (
  `id` int NOT NULL AUTO_INCREMENT,
  `username` varchar(12) DEFAULT NULL,
  `password` varchar(60) DEFAULT NULL,
  `email` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

INSERT INTO `usuarios` VALUES (1,'admin','12345','admin@hotmail.com'),(2,'joselam','12345','jlam97@hotmail.es'),(3,'ymorales','12345','ymorales13@hotmail.com');
