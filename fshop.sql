-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Aug 19, 2025 at 02:39 PM
-- Server version: 10.4.32-MariaDB
-- PHP Version: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `fshop`
--

-- --------------------------------------------------------

--
-- Table structure for table `addresses`
--

CREATE TABLE `addresses` (
  `id` int(11) NOT NULL,
  `district_id` int(11) NOT NULL,
  `full_name` varchar(255) DEFAULT NULL,
  `is_default` bit(1) NOT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `province_id` int(11) NOT NULL,
  `street` varchar(255) DEFAULT NULL,
  `ward_id` int(11) NOT NULL,
  `user_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `addresses`
--

INSERT INTO `addresses` (`id`, `district_id`, `full_name`, `is_default`, `phone`, `province_id`, `street`, `ward_id`, `user_id`) VALUES
(2, 144, 'quag le', b'0', '0777700008', 12, '147 Đường Elm', 2180, 2),
(4, 159, 'quag le', b'1', '0777700009', 14, '147 Đường Elm', 2465, 2);

-- --------------------------------------------------------

--
-- Table structure for table `attributes`
--

CREATE TABLE `attributes` (
  `id` int(11) NOT NULL,
  `name` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `attributes`
--

INSERT INTO `attributes` (`id`, `name`) VALUES
(1, 'Hương vị'),
(2, 'Loại bánh');

-- --------------------------------------------------------

--
-- Table structure for table `attribute_options`
--

CREATE TABLE `attribute_options` (
  `id` int(11) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `attribute_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `attribute_options`
--

INSERT INTO `attribute_options` (`id`, `name`, `attribute_id`) VALUES
(1, 'Khác', 1),
(2, 'Vani', 1),
(3, 'Dâu tây', 1),
(4, 'Socola', 1),
(5, 'Tôm cay', 1),
(6, 'Phô mai', 1),
(7, 'Bò nướng', 1),
(8, 'Khác', 2),
(9, 'Bánh sinh nhật', 2),
(10, 'Bánh kem', 2),
(11, 'Bánh Oishi', 2),
(12, 'Bánh Snack\r\n', 2);

-- --------------------------------------------------------

--
-- Table structure for table `attribute_option_products`
--

CREATE TABLE `attribute_option_products` (
  `id` int(11) NOT NULL,
  `attribute_option_id` int(11) DEFAULT NULL,
  `product_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `attribute_option_products`
--

INSERT INTO `attribute_option_products` (`id`, `attribute_option_id`, `product_id`) VALUES
(35, 8, 8),
(36, 1, 8),
(37, 8, 7),
(38, 1, 7),
(39, 8, 6),
(40, 1, 6),
(41, 8, 4),
(42, 1, 4),
(43, 8, 5),
(44, 1, 5),
(45, 8, 1),
(46, 1, 1),
(47, 8, 2),
(48, 1, 2),
(49, 8, 3),
(50, 1, 3),
(51, 9, 9),
(52, 3, 9);

-- --------------------------------------------------------

--
-- Table structure for table `cart_items`
--

CREATE TABLE `cart_items` (
  `id` int(11) NOT NULL,
  `quantity` int(11) NOT NULL,
  `product_id` int(11) DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `cart_items`
--

INSERT INTO `cart_items` (`id`, `quantity`, `product_id`, `user_id`) VALUES
(28, 1, 4, 12),
(29, 4, 3, 12);

-- --------------------------------------------------------

--
-- Table structure for table `favorites`
--

CREATE TABLE `favorites` (
  `id` int(11) NOT NULL,
  `product_id` int(11) DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `favorites`
--

INSERT INTO `favorites` (`id`, `product_id`, `user_id`) VALUES
(1, 8, 2),
(2, 6, 2),
(3, 9, 8);

-- --------------------------------------------------------

--
-- Table structure for table `orders`
--

CREATE TABLE `orders` (
  `id` int(11) NOT NULL,
  `cancel_order` varchar(255) DEFAULT NULL,
  `create_at` date DEFAULT NULL,
  `payment_method` int(11) NOT NULL,
  `payment_status` varchar(255) DEFAULT NULL,
  `status` int(11) NOT NULL,
  `user_info` varchar(255) DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `orders`
--

INSERT INTO `orders` (`id`, `cancel_order`, `create_at`, `payment_method`, `payment_status`, `status`, `user_info`, `user_id`) VALUES
(11, NULL, '2025-06-18', 0, 'Chưa thanh toán', 1, 'Trần Thị B -  0987654321 - 456 Nguyễn Huệ, TP.HCM', 2),
(12, NULL, '2025-06-18', 0, 'Đã thanh toán', 3, 'Trần Thị Binh -  0987654321 - 456 Nguyễn Huệ, TP.HCM', 2),
(13, 'Đặt nhầm sản phẩm', '2025-06-18', 0, 'Chưa thanh toán', -1, 'Trần Thị B -  0987654321 - 456 Nguyễn Huệ, TP.HCM', 2),
(14, 'Tìm thấy sản phẩm rẻ hơn', '2025-08-01', 0, 'Chưa thanh toán', -1, 'Binh NGUYEN -  0919704134 - OKE, PHƯỜNG KHUÊ MỸ, QUẬN NGŨ HÀNH SƠN, Đà Nẵng', 8),
(15, 'Đặt nhầm sản phẩm', '2025-08-01', 1, 'Chưa thanh toán', -1, 'Binh Pham -  0915876498 - OK2, PHƯỜNG ĐÔNG HẢI, QUẬN LÊ CHÂN, Hải Phòng', 8),
(16, NULL, '2025-08-01', 1, 'Đã thanh toán', 3, 'Binh Pham -  0915876498 - OK2, PHƯỜNG ĐÔNG HẢI, QUẬN LÊ CHÂN, Hải Phòng', 8),
(17, 'Đặt nhầm sản phẩm', '2025-08-08', 0, 'Chưa thanh toán', -1, 'quag le -  0777700008 - 147 Đường Elm, XÃ THÁI HỌC, HUYỆN BÌNH GIANG, Hải Dương', 2),
(18, NULL, '2025-08-08', 1, 'Đã thanh toán', 3, 'quag le -  0777700008 - 147 Đường Elm, XÃ THÁI HỌC, HUYỆN BÌNH GIANG, Hải Dương', 2);

-- --------------------------------------------------------

--
-- Table structure for table `order_items`
--

CREATE TABLE `order_items` (
  `id` int(11) NOT NULL,
  `price` double NOT NULL,
  `quantity` int(11) NOT NULL,
  `order_id` int(11) DEFAULT NULL,
  `product_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `order_items`
--

INSERT INTO `order_items` (`id`, `price`, `quantity`, `order_id`, `product_id`) VALUES
(16, 35000, 2, 11, 3),
(17, 10000, 1, 12, 8),
(18, 10000, 2, 13, 7),
(19, 10000, 2, 13, 9),
(20, 10000, 4, 14, 9),
(21, 10000, 1, 15, 8),
(22, 10000, 2, 16, 9),
(23, 120000, 2, 17, 4),
(24, 10000, 1, 18, 7);

-- --------------------------------------------------------

--
-- Table structure for table `products`
--

CREATE TABLE `products` (
  `id` int(11) NOT NULL,
  `create_at` datetime(6) DEFAULT NULL,
  `descriptions` varchar(255) DEFAULT NULL,
  `image` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `price` double NOT NULL,
  `quantity` int(11) NOT NULL,
  `weight` double NOT NULL,
  `is_active` bit(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `products`
--

INSERT INTO `products` (`id`, `create_at`, `descriptions`, `image`, `name`, `price`, `quantity`, `weight`, `is_active`) VALUES
(1, '2025-06-02 04:11:00.000000', '🎂 Bánh kem sinh nhật socola trang trí hoa quả\nBánh bông lan mềm mịn, phủ kem socola đậm vị, trang trí hoa quả tươi mát như dâu, kiwi, việt quất. Hoàn hảo cho sinh nhật & tiệc kỷ niệm.', 'https://i.ibb.co/tT24bxgC/a-1.jpg', 'Bánh Sinh Nhật Socola', 350000, 20, 50, b'0'),
(2, '2025-06-02 04:11:00.000000', '🍤 Snack Oishi vị tôm cay\nSnack giòn tan, đậm vị tôm cay hấp dẫn. Ăn vặt cực đã khi xem phim, đi chơi hay làm việc.', 'https://i.ibb.co/Xx52CLdg/a-2.jpg', 'Snack Oishi Tôm Cay', 20000, 500, 50, b'0'),
(3, '2025-06-02 04:11:00.000000', '🥐 Croissant trà xanh\nBánh sừng bò phủ matcha thơm lừng. Lớp vỏ giòn rụm, ruột mềm xốp, nhẹ nhàng tinh tế.', 'https://i.ibb.co/WWyGZhfG/a-3.jpg', 'Croissant Trà Xanh', 35000, 147, 50, b'0'),
(4, '2025-06-02 04:11:00.000000', '🍪 Bánh quy vani giòn tan\nBánh quy vàng giòn, thơm ngát hương vani. Ăn kèm sữa nóng hay trà chiều đều tuyệt.\n\n', 'https://i.ibb.co/tTkTvj0k/a-4.jpg', 'Bánh Quy Vani', 120000, 80, 50, b'0'),
(5, '2025-06-18 01:43:04.000000', '🍰 Bánh thát ngon\nMột món bánh đơn giản nhưng gây nghiện bởi vị ngọt dịu, mềm mịn trong từng miếng cắn.', 'https://i.ibb.co/WWK7LmCn/a-5.jpg', 'Bánh Ngon', 10000, 8, 50, b'0'),
(6, '2025-06-18 01:45:01.000000', '🔥 Bánh ăn là ghiền\nCắn một lần là ghiền! Vị ngọt béo hoàn hảo, phù hợp mọi lứa tuổi.', 'https://i.ibb.co/7mybD70/a-6.jpg\n', 'Bánh Ghiền', 10000, 10, 50, b'0'),
(7, '2025-06-18 01:50:55.000000', '🍱 Ăn bánh xong lú luôn\nTên vui nhưng đúng thật: quá ngon, ăn xong chỉ muốn ăn tiếp! Vị bánh lạ miệng, hài hước & thú vị.\\', 'https://i.ibb.co/N2t6ksPH/a-7.jpg', 'Bánh Mạnh Bà 1', 10000, 9, 50, b'0'),
(8, '2025-06-18 12:43:18.000000', 'Bánh mạnh bà ăn xong quên tất cả', 'https://i.ibb.co/21MVzmrt/a-8.jpg', 'Bánh Mạnh Bà 2', 10000, 9, 50, b'0'),
(9, '2025-06-18 17:13:55.000000', 'Bánh của gen z', 'https://i.ibb.co/gZD0QMZq/a-9.jpg', 'Bánh Gen Z', 10000, 8, 50, b'0');

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `id` int(11) NOT NULL,
  `address` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `full_name` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `role` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`id`, `address`, `email`, `full_name`, `password`, `phone`, `role`) VALUES
(1, '123 Đường Láng, Hà Nội', 'lehqpc07896@fpt.edu.vn', 'Nguyễn Văn A', '$2a$10$AUOW.ZZHjkasdLQ3Io0CSeVIyYBSaxM3dhSn2AO6DqIhv0.kAhkVe', '0914445678', 1),
(2, '456 Nguyễn Huệ, TP.HCM', 'huynhquangle007@gmail.com', 'Trần Thị B', '$2a$10$8xRCEFfXevY4M3egAvqr7O1ieSC/NesSVDpW1mdtL/9.cS5trG5ly', '0987654344', 0),
(6, NULL, 'user0044@gmail.com', 'ONE', '$2a$10$x8NT2ibdU5PgAhfAsAhXmOnIuLWQxmkdnM6a95NDcnWSV9n8d5cQ2', NULL, 0),
(7, NULL, 'nhomone20044@gmail.com', 'Tran Thị B', '$2a$10$cCPY/9aQubi5XcXwvPXLXeNL7r7jP1sE1T6wwdZ5Cnfu8nYC3WwMG', NULL, 0),
(8, 'CMC', 'binhphampro@gmail.com', 'Pham Binh', '$2a$10$3Bac.Hd3gK9AliaZzQJ9V.bj7iUFQiyal33IobipdnP2ZpTkYKakq', '0901909123', 0),
(10, NULL, 'adminpro@gmail.com', 'Admin Pro', '$2a$10$aRWSbZRWAIS4iilwvm9q0.XLXOUxlEz7uL0gNjY3v9vkrYdt6eDOy', NULL, 1),
(11, NULL, 'adminpro1@gmail.com', 'Nhom One', '$2a$10$.xBRc9ESJE218bOnmCvmGuTLI6uiNC/JX0kMSE7p1piZWaGqfsxiK', NULL, 1),
(12, NULL, 'binhpntpc07856@fpt.edu.vn', 'Binh Fpt', '$2a$10$jQPziY0c3insaDXxC5vQDusV7.Csrxv4qTPLCYPxOpftJFfJlEuF6', NULL, 0);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `addresses`
--
ALTER TABLE `addresses`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK1fa36y2oqhao3wgg2rw1pi459` (`user_id`);

--
-- Indexes for table `attributes`
--
ALTER TABLE `attributes`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `attribute_options`
--
ALTER TABLE `attribute_options`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKo2hpoh278o091gr6uvd9l8iyb` (`attribute_id`);

--
-- Indexes for table `attribute_option_products`
--
ALTER TABLE `attribute_option_products`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKojc7gtvon02dic039fadntt85` (`attribute_option_id`),
  ADD KEY `FKpbolcpdiy9e2lv5dr8vuvm617` (`product_id`);

--
-- Indexes for table `cart_items`
--
ALTER TABLE `cart_items`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK1re40cjegsfvw58xrkdp6bac6` (`product_id`),
  ADD KEY `FK709eickf3kc0dujx3ub9i7btf` (`user_id`);

--
-- Indexes for table `favorites`
--
ALTER TABLE `favorites`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK6sgu5npe8ug4o42bf9j71x20c` (`product_id`),
  ADD KEY `FKk7du8b8ewipawnnpg76d55fus` (`user_id`);

--
-- Indexes for table `orders`
--
ALTER TABLE `orders`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK32ql8ubntj5uh44ph9659tiih` (`user_id`);

--
-- Indexes for table `order_items`
--
ALTER TABLE `order_items`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKbioxgbv59vetrxe0ejfubep1w` (`order_id`),
  ADD KEY `FKocimc7dtr037rh4ls4l95nlfi` (`product_id`);

--
-- Indexes for table `products`
--
ALTER TABLE `products`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `addresses`
--
ALTER TABLE `addresses`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `attributes`
--
ALTER TABLE `attributes`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `attribute_options`
--
ALTER TABLE `attribute_options`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;

--
-- AUTO_INCREMENT for table `attribute_option_products`
--
ALTER TABLE `attribute_option_products`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=57;

--
-- AUTO_INCREMENT for table `cart_items`
--
ALTER TABLE `cart_items`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=33;

--
-- AUTO_INCREMENT for table `favorites`
--
ALTER TABLE `favorites`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- AUTO_INCREMENT for table `orders`
--
ALTER TABLE `orders`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=19;

--
-- AUTO_INCREMENT for table `order_items`
--
ALTER TABLE `order_items`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=25;

--
-- AUTO_INCREMENT for table `products`
--
ALTER TABLE `products`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;

--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `addresses`
--
ALTER TABLE `addresses`
  ADD CONSTRAINT `FK1fa36y2oqhao3wgg2rw1pi459` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`);

--
-- Constraints for table `attribute_options`
--
ALTER TABLE `attribute_options`
  ADD CONSTRAINT `FKo2hpoh278o091gr6uvd9l8iyb` FOREIGN KEY (`attribute_id`) REFERENCES `attributes` (`id`);

--
-- Constraints for table `attribute_option_products`
--
ALTER TABLE `attribute_option_products`
  ADD CONSTRAINT `FKojc7gtvon02dic039fadntt85` FOREIGN KEY (`attribute_option_id`) REFERENCES `attribute_options` (`id`),
  ADD CONSTRAINT `FKpbolcpdiy9e2lv5dr8vuvm617` FOREIGN KEY (`product_id`) REFERENCES `products` (`id`);

--
-- Constraints for table `cart_items`
--
ALTER TABLE `cart_items`
  ADD CONSTRAINT `FK1re40cjegsfvw58xrkdp6bac6` FOREIGN KEY (`product_id`) REFERENCES `products` (`id`),
  ADD CONSTRAINT `FK709eickf3kc0dujx3ub9i7btf` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`);

--
-- Constraints for table `favorites`
--
ALTER TABLE `favorites`
  ADD CONSTRAINT `FK6sgu5npe8ug4o42bf9j71x20c` FOREIGN KEY (`product_id`) REFERENCES `products` (`id`),
  ADD CONSTRAINT `FKk7du8b8ewipawnnpg76d55fus` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`);

--
-- Constraints for table `orders`
--
ALTER TABLE `orders`
  ADD CONSTRAINT `FK32ql8ubntj5uh44ph9659tiih` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`);

--
-- Constraints for table `order_items`
--
ALTER TABLE `order_items`
  ADD CONSTRAINT `FKbioxgbv59vetrxe0ejfubep1w` FOREIGN KEY (`order_id`) REFERENCES `orders` (`id`),
  ADD CONSTRAINT `FKocimc7dtr037rh4ls4l95nlfi` FOREIGN KEY (`product_id`) REFERENCES `products` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
