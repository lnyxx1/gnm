-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jan 19, 2023 at 08:46 AM
-- Server version: 10.4.19-MariaDB
-- PHP Version: 8.0.7

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `g&m_db`
--

-- --------------------------------------------------------

--
-- Table structure for table `ms_cart`
--

CREATE TABLE `ms_cart` (
  `id` int(11) NOT NULL,
  `cust_id` char(5) DEFAULT NULL,
  `product_id` char(5) DEFAULT NULL,
  `quantity` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `ms_cart`
--

INSERT INTO `ms_cart` (`id`, `cust_id`, `product_id`, `quantity`) VALUES
(73, 'US001', 'PR001', 1);

-- --------------------------------------------------------

--
-- Table structure for table `ms_customer`
--

CREATE TABLE `ms_customer` (
  `id` char(5) NOT NULL,
  `name` varchar(255) NOT NULL,
  `phone` varchar(255) NOT NULL,
  `address` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `gender` varchar(255) NOT NULL,
  `dob` date NOT NULL,
  `role` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `ms_customer`
--

INSERT INTO `ms_customer` (`id`, `name`, `phone`, `address`, `email`, `password`, `gender`, `dob`, `role`) VALUES
('CS001', 'admin', '021345678910', 'Anggrek Street', 'admin@binus.ac.id', 'admin123', 'Male', '2003-07-30', 'admin'),
('US001', 'William', '085399966699', 'Alsut Street', 'williamleo@gmail.com', 'b!Nu$101103', 'Male', '2023-01-02', 'user');

-- --------------------------------------------------------

--
-- Table structure for table `ms_product`
--

CREATE TABLE `ms_product` (
  `id` char(5) NOT NULL,
  `type_id` char(5) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `price` int(10) DEFAULT NULL,
  `stock` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `ms_product`
--

INSERT INTO `ms_product` (`id`, `type_id`, `name`, `price`, `stock`) VALUES
('PR001', 'TY001', 'Regular Fit T-Shirt', 179000, 4),
('PR002', 'TY001', 'Oversized Cotton T-Shirt', 199000, 3),
('PR003', 'TY001', 'Printed T-Shirt', 169000, 18),
('PR004', 'TY001', 'Raw-Edge T-Shirt', 199000, 10),
('PR005', 'TY001', 'Premium Cotton Shirt', 379000, 3),
('PR006', 'TY002', 'V-Neck Dress', 479000, 3),
('PR007', 'TY002', 'Tie-Belt Shirt Dress', 549000, 2),
('PR008', 'TY002', 'Tie-belt Crêpe Dress', 549000, 1),
('PR009', 'TY003', 'Regular Fit Teddy Jacket', 549000, 6),
('PR010', 'TY003', 'Oversized Jacket', 699000, 13),
('PR011', 'TY003', 'Running Jacket Regular Fit', 429000, 5),
('PR012', 'TY003', 'Denim Jacket', 599000, 3),
('PR013', 'TY004', 'Pima Cotton Sweatpants', 599000, 7),
('PR014', 'TY004', 'Cargo Joggers', 699000, 10),
('PR015', 'TY004', 'Skinny Fit Cotton Chinos', 479000, 11),
('PR016', 'TY004', 'High-Waisted Leggings', 199000, 12),
('PR017', 'TY004', 'Cigarette Trousers', 399000, 5),
('PR018', 'TY005', 'Regular Jeans', 549000, 18),
('PR019', 'TY005', 'Slim Jeans', 549000, 11),
('PR020', 'TY005', 'Wide High Jeans', 479000, 8),
('PR021', 'TY005', 'Straight High Jeans', 549000, 6),
('PR022', 'TY006', 'Cycling Shorts', 149000, 4),
('PR023', 'TY006', 'Denim Shorts', 399000, 7),
('PR024', 'TY006', 'Cotton Shorts', 429000, 3),
('PR025', 'TY007', 'Pleated Skirt', 549000, 5),
('PR026', 'TY007', 'Sweatshirt Skirt', 349000, 2),
('PR027', 'TY007', 'Crinkled Skirt', 399000, 3);

-- --------------------------------------------------------

--
-- Table structure for table `ms_product_type`
--

CREATE TABLE `ms_product_type` (
  `id` char(5) NOT NULL,
  `name` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `ms_product_type`
--

INSERT INTO `ms_product_type` (`id`, `name`) VALUES
('TY001', 'Shirts'),
('TY002', 'Dresses'),
('TY003', 'Jackets'),
('TY004', 'Trousers'),
('TY005', 'Jeans'),
('TY006', 'Shorts'),
('TY007', 'Skirts');

-- --------------------------------------------------------

--
-- Table structure for table `transaction_detail`
--

CREATE TABLE `transaction_detail` (
  `tr_id` char(5) DEFAULT NULL,
  `product_id` char(5) DEFAULT NULL,
  `quantity` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `transaction_detail`
--

INSERT INTO `transaction_detail` (`tr_id`, `product_id`, `quantity`) VALUES
('TR001', 'PR001', 1);

-- --------------------------------------------------------

--
-- Table structure for table `transaction_header`
--

CREATE TABLE `transaction_header` (
  `id` char(5) NOT NULL,
  `user_id` char(5) DEFAULT NULL,
  `tr_date` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `transaction_header`
--

INSERT INTO `transaction_header` (`id`, `user_id`, `tr_date`) VALUES
('TR001', 'US001', '2023-01-19');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `ms_cart`
--
ALTER TABLE `ms_cart`
  ADD PRIMARY KEY (`id`),
  ADD KEY `cust_id` (`cust_id`),
  ADD KEY `product_id` (`product_id`);

--
-- Indexes for table `ms_customer`
--
ALTER TABLE `ms_customer`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `ms_product`
--
ALTER TABLE `ms_product`
  ADD PRIMARY KEY (`id`),
  ADD KEY `type_id` (`type_id`);

--
-- Indexes for table `ms_product_type`
--
ALTER TABLE `ms_product_type`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `transaction_detail`
--
ALTER TABLE `transaction_detail`
  ADD KEY `tr_id` (`tr_id`),
  ADD KEY `product_id` (`product_id`);

--
-- Indexes for table `transaction_header`
--
ALTER TABLE `transaction_header`
  ADD PRIMARY KEY (`id`),
  ADD KEY `user_id` (`user_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `ms_cart`
--
ALTER TABLE `ms_cart`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=74;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `ms_cart`
--
ALTER TABLE `ms_cart`
  ADD CONSTRAINT `ms_cart_ibfk_1` FOREIGN KEY (`cust_id`) REFERENCES `ms_customer` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `ms_cart_ibfk_2` FOREIGN KEY (`product_id`) REFERENCES `ms_product` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `transaction_detail`
--
ALTER TABLE `transaction_detail`
  ADD CONSTRAINT `transaction_detail_ibfk_1` FOREIGN KEY (`tr_id`) REFERENCES `transaction_header` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `transaction_detail_ibfk_2` FOREIGN KEY (`product_id`) REFERENCES `ms_product` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `transaction_header`
--
ALTER TABLE `transaction_header`
  ADD CONSTRAINT `transaction_header_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `ms_customer` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
