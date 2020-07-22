-- phpMyAdmin SQL Dump
-- version 4.9.0.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Nov 26, 2019 at 08:46 AM
-- Server version: 10.3.16-MariaDB
-- PHP Version: 7.3.7

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `cscp2014_assignment_2`
--

-- --------------------------------------------------------

--
-- Table structure for table `movie`
--

CREATE TABLE `movie` (
  `id` int(11) NOT NULL,
  `name` text NOT NULL,
  `type` text NOT NULL,
  `showtime` text NOT NULL,
  `language` text NOT NULL,
  `subtitle` text NOT NULL,
  `classification` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `movie`
--

INSERT INTO `movie` (`id`, `name`, `type`, `showtime`, `language`, `subtitle`, `classification`) VALUES
(2, 'Avenger: Age Of Ultron', 'action', '18:30', 'English', 'Bahasa Malay', '13+'),
(4, 'Frozen 2', 'Animation, Family', '23:00', 'English', 'Bahasa Malay', 'None'),
(3, 'Fast & Furious: Hob & Shaw', 'Action, Adventure', '19:30', 'English', 'Chinese', '13+'),
(1, 'Batman', 'Action, Adventure', '20:30', 'English', 'Chinese', '13+');

-- --------------------------------------------------------

--
-- Table structure for table `seat`
--

CREATE TABLE `seat` (
  `status` text NOT NULL,
  `vip seat` text NOT NULL,
  `row` text NOT NULL,
  `number` int(11) NOT NULL,
  `price` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `seat`
--

INSERT INTO `seat` (`status`, `vip seat`, `row`, `number`, `price`) VALUES
('fixed', 'no', 'A', 1, 2.24),
('fixed', 'no', 'A', 2, 2.24),
('fixed', 'no', 'A', 3, 2.24),
('fixed', 'no', 'A', 4, 2.24),
('fixed', 'no', 'A', 5, 2.24),
('fixed', 'no', 'A', 6, 2.24),
('fixed', 'no', 'A', 7, 2.24),
('fixed', 'no', 'A', 8, 2.24),
('fixed', 'no', 'A', 9, 2.24),
('fixed', 'no', 'A', 10, 2.24),
('fixed', 'no', 'A', 11, 2.24),
('fixed', 'no', 'A', 12, 2.24),
('fixed', 'no', 'B', 1, 2.24),
('fixed', 'no', 'B', 2, 2.24),
('fixed', 'no', 'B', 3, 2.24),
('fixed', 'no', 'B', 4, 2.24),
('fixed', 'yes', 'B', 5, 3.24),
('fixed', 'no', 'B', 6, 2.24),
('fixed', 'no', 'B', 7, 2.24),
('fixed', 'yes', 'B', 8, 3.24),
('fixed', 'no', 'B', 9, 2.24),
('fixed', 'no', 'B', 10, 2.24),
('fixed', 'no', 'B', 11, 2.24),
('fixed', 'no', 'B', 12, 2.24),
('fixed', 'no', 'C', 1, 2.24),
('fixed', 'no', 'C', 2, 2.24),
('fixed', 'no', 'C', 3, 2.24),
('fixed', 'no', 'C', 4, 2.24),
('fixed', 'no', 'C', 5, 2.24),
('fixed', 'no', 'C', 6, 2.24),
('fixed', 'no', 'C', 7, 2.24),
('fixed', 'no', 'C', 8, 2.24),
('fixed', 'no', 'C', 9, 2.24),
('fixed', 'no', 'C', 10, 2.24),
('fixed', 'no', 'C', 11, 2.24),
('fixed', 'no', 'C', 12, 2.24),
('fixed', 'no', 'D', 1, 2.24),
('fixed', 'no', 'D', 2, 2.24),
('fixed', 'no', 'D', 3, 2.24),
('fixed', 'no', 'D', 4, 2.24),
('fixed', 'no', 'D', 5, 2.24),
('fixed', 'no', 'D', 6, 2.24),
('fixed', 'no', 'D', 7, 2.24),
('fixed', 'no', 'D', 8, 2.24),
('fixed', 'no', 'D', 9, 2.24),
('fixed', 'no', 'D', 10, 2.24),
('fixed', 'no', 'D', 11, 2.24),
('fixed', 'no', 'D', 12, 2.24),
('extended', 'no', 'E', 1, 4.48),
('extended', 'no', 'E', 2, 4.48),
('extended', 'no', 'E', 3, 4.48),
('extended', 'no', 'E', 4, 4.48),
('extended', 'yes', 'E', 5, 6.48),
('extended', 'yes', 'E', 6, 6.48),
('extended', 'yes', 'E', 7, 6.48),
('extended', 'yes', 'E', 8, 6.48),
('extended', 'no', 'E', 9, 4.48),
('extended', 'no', 'E', 10, 4.48),
('extended', 'no', 'E', 11, 4.48),
('extended', 'no', 'E', 12, 4.48);

-- --------------------------------------------------------

--
-- Table structure for table `ticket`
--

CREATE TABLE `ticket` (
  `id` text NOT NULL,
  `movie` text NOT NULL,
  `seat` text NOT NULL,
  `price` double NOT NULL,
  `date of purchase` text NOT NULL,
  `phone number` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `ticket`
--

INSERT INTO `ticket` (`id`, `movie`, `seat`, `price`, `date of purchase`, `phone number`) VALUES
('D620145255869', 'Avenger: Age Of Ultron', 'D6', 15.14, 'Tue Nov 26 15:25:23 SGT 2019', '0145255869'),
('A43213121', 'Fast & Furious: Hob & Shaw', 'A4', 15.14, 'Tue Nov 26 15:38:22 SGT 2019', '213121'),
('B103213121', 'Fast & Furious: Hob & Shaw', 'B10', 15.14, 'Tue Nov 26 15:38:30 SGT 2019', '213121'),
('D73213121', 'Fast & Furious: Hob & Shaw', 'D7', 15.14, 'Tue Nov 26 15:38:40 SGT 2019', '213121'),
('D91123121', 'Batman', 'D9', 15.14, 'Tue Nov 26 15:39:52 SGT 2019', '123121');
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
