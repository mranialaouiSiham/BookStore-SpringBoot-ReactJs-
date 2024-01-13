-- MySQL dump 10.13  Distrib 8.0.27, for Win64 (x86_64)
--
-- Host: localhost    Database: bookstore
-- ------------------------------------------------------
-- Server version	8.0.27

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Dumping data for table `author`
--

LOCK TABLES `author` WRITE;
/*!40000 ALTER TABLE `author` DISABLE KEYS */;
INSERT INTO `author` VALUES (1,'Gertrude Spinka'),(2,'Sheron Tillman'),(3,'Abdul Greenholt'),(4,'Mrs. Milton Metz'),(5,'Ms. Charolette Kling'),(6,'Titus Berge'),(7,'Mr. Kit Koss'),(8,'Shenika Mosciski'),(9,'Ethan Vandervort'),(10,'Bruno Boyer');
/*!40000 ALTER TABLE `author` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `book`
--

LOCK TABLES `book` WRITE;
/*!40000 ALTER TABLE `book` DISABLE KEYS */;
INSERT INTO `book` VALUES (14,'Ut ratione sequi unde qui eos enim. Reprehenderit qui voluptatem nemo vitae. Quisquam iste enim. Eveniet autem qui. Consectetur quo accusamus tempora animi aut odit.','https://images.pexels.com/photos/5886041/pexels-photo-5886041.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=650&w=940',49.13,'The Doors of Perception2121',9,2,9),(15,'Est et non aut tenetur optio est voluptatem. Rem doloribus commodi aut laudantium delectus possimus reiciendis. Doloribus et ipsa dicta ducimus. Voluptatem et nostrum nisi fugit perferendis est. Expedita blanditiis unde.','http://lorempixel.com/g/640/480/business/',22.37,'Ego Dominus Tuus',9,2,2),(16,'Magni aspernatur omnis. Aut aut et aut similique. Eum accusamus vero ut. Quasi quisquam rerum et.','http://lorempixel.com/g/1024/768/animals/',49.33,'Brandy of the Damned',9,10,3),(17,'Voluptatem qui est labore. Beatae molestiae assumenda quos. Pariatur corporis voluptatibus unde veniam hic neque expedita.','http://lorempixel.com/g/1920/1200/sports/',80.96,'By Grand Central Station I Sat Down and Wept',5,3,10),(18,'Quae repudiandae necessitatibus quo est error est ex. Non neque ducimus. Et ex est repellendus ab omnis et. Necessitatibus qui molestiae dolor eveniet. Veritatis omnis magni aut consequatur repellat quae.','http://lorempixel.com/g/320/200/animals/',15.77,'Little Hands Clapping',9,9,8),(19,'Dolor nesciunt et ipsum. Molestiae beatae id repellendus. Dolor quia soluta. Officiis dignissimos incidunt consequatur iure et.','http://lorempixel.com/g/1024/768/business/',55.8,'Vanity Fair',10,9,9),(20,'Sed minus nisi eveniet modi et. Sed qui sunt. Vel voluptas qui molestiae. Aut tempora a est ab rem numquam exercitationem.','http://lorempixel.com/1024/768/fashion/',2.6,'Tender Is the Night',7,6,3),(21,'Vel ducimus voluptatum id voluptatum repellendus consequatur. Adipisci molestiae omnis odit ab corrupti. Vitae voluptates porro consectetur aut ad. Aliquid rem harum dolores fugiat eos reiciendis. Illo alias enim maxime.','http://lorempixel.com/1680/1050/business/',76.95,'A Catskill Eagle',8,10,5),(22,'ssadsds',NULL,10,'juned',NULL,NULL,NULL),(23,'ssadsds',NULL,10,'juned',NULL,NULL,NULL),(24,'ssadsds','sasa',0,'juned',4,9,4),(25,'ssadsds','sasa',0,'juned',4,9,4),(26,'ssadsds',NULL,0,'juned',4,9,4),(27,'ssadsds',NULL,0,'juned',4,9,4),(28,'ssadsds',NULL,0,'juned',4,9,4),(29,'ssadsds',NULL,0,'juned',4,9,4),(30,'ssadsds',NULL,0,'juned',4,9,4),(31,'ssadsds',NULL,10,'juned',4,9,4),(32,'ssadsds',NULL,10,'juned',4,9,4),(33,'ssadsds',NULL,10,'juned',4,9,4),(34,'ssadsds',NULL,10,'juned',4,NULL,NULL),(35,'ssadsds',NULL,10,'juned',4,9,4),(36,'ssadsds',NULL,10,'juned',4,9,4),(37,'ssadsds','sasa',10,'juned',4,9,4),(38,'ssadsds','sasa',10,'juned',4,9,4);
/*!40000 ALTER TABLE `book` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `category`
--

LOCK TABLES `category` WRITE;
/*!40000 ALTER TABLE `category` DISABLE KEYS */;
INSERT INTO `category` VALUES (1,'Fanfiction'),(2,'Mythology'),(3,'Metafiction'),(4,'Fanfiction'),(5,'Biography/Autobiography'),(6,'Suspense/Thriller'),(7,'Legend'),(8,'Mystery'),(9,'Fanfiction'),(10,'Historical fiction');
/*!40000 ALTER TABLE `category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `language`
--

LOCK TABLES `language` WRITE;
/*!40000 ALTER TABLE `language` DISABLE KEYS */;
INSERT INTO `language` VALUES (1,'expedita'),(2,'aut'),(3,'aut'),(4,'officia'),(5,'debitis'),(6,'et'),(7,'itaque'),(8,'maxime'),(9,'delectus'),(10,'accusamus');
/*!40000 ALTER TABLE `language` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `order_item`
--

LOCK TABLES `order_item` WRITE;
/*!40000 ALTER TABLE `order_item` DISABLE KEYS */;
INSERT INTO `order_item` VALUES (3,'com.bookstore.main.models.Category@de40aa1','http://lorempixel.com/g/640/480/business/','com.bookstore.main.models.Language@61b76a7d',22.37,2,'Ego Dominus Tuus',1),(4,'com.bookstore.main.models.Category@4d66cb','http://lorempixel.com/g/640/200/abstract/','com.bookstore.main.models.Language@7c79f2cf',49.13,1,'The Doors of Perception',1),(5,'com.bookstore.main.models.Category@496a9df9','http://lorempixel.com/g/640/480/business/','com.bookstore.main.models.Language@2e656f32',22.37,3,'Ego Dominus Tuus',2),(6,'com.bookstore.main.models.Category@2b1f699','http://lorempixel.com/g/640/480/business/','com.bookstore.main.models.Language@1a3e4134',22.37,3,'Ego Dominus Tuus',3),(7,'com.bookstore.main.models.Category@11c5a038','http://lorempixel.com/g/640/480/business/','com.bookstore.main.models.Language@583ead69',22.37,3,'Ego Dominus Tuus',4),(8,'com.bookstore.main.models.Category@c315910','http://lorempixel.com/g/1024/768/animals/','com.bookstore.main.models.Language@644f1bb1',49.33,3,'Brandy of the Damned',4),(9,'com.bookstore.main.models.Category@1377623a','http://lorempixel.com/g/640/480/business/','com.bookstore.main.models.Language@38fa0480',22.37,3,'Ego Dominus Tuus',5),(10,'com.bookstore.main.models.Category@189ae37a','http://lorempixel.com/g/1024/768/animals/','com.bookstore.main.models.Language@594ae506',49.33,3,'Brandy of the Damned',5),(11,'com.bookstore.main.models.Category@2acef823','http://lorempixel.com/g/1024/768/animals/','com.bookstore.main.models.Language@1e1d2295',49.33,1,'Brandy of the Damned',6),(12,'com.bookstore.main.models.Category@613822a7','http://lorempixel.com/g/1024/768/animals/','com.bookstore.main.models.Language@3b4adfa',49.33,2,'Brandy of the Damned',7),(13,'com.bookstore.main.models.Category@4b5b39b4','https://images.pexels.com/photos/5886041/pexels-photo-5886041.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=650&w=940','com.bookstore.main.models.Language@581476c3',49.13,1,'The Doors of Perception2121',8),(14,'com.bookstore.main.models.Category@d097670','https://images.pexels.com/photos/5886041/pexels-photo-5886041.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=650&w=940','com.bookstore.main.models.Language@6e6c9792',49.13,3,'The Doors of Perception2121',9),(15,'com.bookstore.main.models.Category@1b966571','http://lorempixel.com/g/1920/1200/sports/','com.bookstore.main.models.Language@6d0a422a',80.96,1,'By Grand Central Station I Sat Down and Wept',9),(16,'com.bookstore.main.models.Category@22ca7855','http://lorempixel.com/g/320/200/animals/','com.bookstore.main.models.Language@20d4a2a4',15.77,1,'Little Hands Clapping',10),(17,'com.bookstore.main.models.Category@e07258e','http://lorempixel.com/g/640/480/business/','com.bookstore.main.models.Language@797c97a2',22.37,1,'Ego Dominus Tuus',10),(18,'com.bookstore.main.models.Category@3ed62681','http://lorempixel.com/1024/768/fashion/','com.bookstore.main.models.Language@5faac7d6',2.6,1,'Tender Is the Night',11),(19,'com.bookstore.main.models.Category@3888ab93','http://lorempixel.com/g/320/200/animals/','com.bookstore.main.models.Language@5fc4db6e',15.77,2,'Little Hands Clapping',11);
/*!40000 ALTER TABLE `order_item` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `orders`
--

LOCK TABLES `orders` WRITE;
/*!40000 ALTER TABLE `orders` DISABLE KEYS */;
INSERT INTO `orders` VALUES (1,'123, ABC Street, XYZ City','2022-05-27',71.5,1,'DELIVERED'),(2,'123455454','2022-06-08',22.37,1,'DELIVERED'),(3,'123456','2022-06-08',22.37,3,'DELIVERED'),(4,'123456','2022-06-08',71.7,3,'DELIVERED'),(5,'123456','2022-06-08',71.7,3,'PROCESSING'),(6,'12354455','2022-06-08',49.33,3,'PROCESSING'),(7,'fafasdfas','2022-06-08',49.33,3,'PROCESSING'),(8,'5534342321','2022-06-08',49.13,3,'PROCESSING'),(9,'my second address','2022-06-08',228.35000000000002,4,'DELIVERED'),(10,'juned second address','2022-06-08',38.14,1,'PROCESSING'),(11,'123456','2022-06-08',34.14,1,'PROCESSING');
/*!40000 ALTER TABLE `orders` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `roles`
--

LOCK TABLES `roles` WRITE;
/*!40000 ALTER TABLE `roles` DISABLE KEYS */;
INSERT INTO `roles` VALUES (1,'ROLE_ADMIN'),(2,'ROLE_USER');
/*!40000 ALTER TABLE `roles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `user_roles`
--

LOCK TABLES `user_roles` WRITE;
/*!40000 ALTER TABLE `user_roles` DISABLE KEYS */;
INSERT INTO `user_roles` VALUES (1,1),(2,2),(3,2),(4,2);
/*!40000 ALTER TABLE `user_roles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'admin@gmail.com','$2a$10$s1inPY4iVhIOk.tttvhMhe03HdNUln7b9Nr.Mu46LLS/qI8of3P7W')
,(2,'user@gmail.com','$2a$10$TTKG1oYRMuVmL.PjtZ5ZIegj8Qpqe2vYsreS.eTUfkMklwvJHRwGy')
,(3,'juned@gmail.com','$2a$10$PgTI9knNpxepObN2UG.o3eMyyf0LQr6z1EK3gYnV2mWn4Gw8Jx55q'),
(4,'juned1@gmail.com','$2a$10$.sD.dpW2X6TdGCB8e5pMouFJV8Xy5uYCEgotfH7orlQySJkg7M2na');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-06-08 22:21:14
