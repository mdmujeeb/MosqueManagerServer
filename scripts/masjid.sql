-- --------------------------------------------------------
-- Host:                         mujeeb.mooo.com
-- Server version:               5.7.19-0ubuntu0.16.04.1 - (Ubuntu)
-- Server OS:                    Linux
-- HeidiSQL Version:             9.3.0.4984
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

-- Dumping database structure for masjid
CREATE DATABASE IF NOT EXISTS `masjid` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `masjid`;


-- Dumping structure for table masjid.masjid_master
CREATE TABLE IF NOT EXISTS `masjid_master` (
  `ID` int(11) NOT NULL,
  `DESCRIPTION` varchar(500) NOT NULL,
  `MASJID_ID` int(11) NOT NULL,
  `PASSWORD` varchar(500) NOT NULL,
  `GPS_LATITUDE` double DEFAULT NULL,
  `GPS_LONGITUDE` double DEFAULT NULL,
  `DATE_CREATED` datetime NOT NULL,
  `DATE_UPDATED` datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Details of Masjid IDs';

-- Dumping data for table masjid.masjid_master: ~2 rows (approximately)
DELETE FROM `masjid_master`;
/*!40000 ALTER TABLE `masjid_master` DISABLE KEYS */;
INSERT INTO `masjid_master` (`ID`, `DESCRIPTION`, `MASJID_ID`, `PASSWORD`, `GPS_LATITUDE`, `GPS_LONGITUDE`, `DATE_CREATED`, `DATE_UPDATED`) VALUES
	(0, 'Abdullah Bin Masood Masjid', 0, 'Mummy@123', 13.0679798, 77.6349816, '2017-08-15 08:22:27', '2017-08-15 08:22:29'),
	(1, 'Gujarat Madrasa', 1, 'einstein', 23.0201818, 72.4396547, '2017-08-15 08:22:52', '2017-08-15 08:22:54');
/*!40000 ALTER TABLE `masjid_master` ENABLE KEYS */;


-- Dumping structure for table masjid.namaz_times
CREATE TABLE IF NOT EXISTS `namaz_times` (
  `ID` int(11) NOT NULL,
  `NAME` varchar(200) NOT NULL,
  `TIME` varchar(200) NOT NULL,
  `MASJID_ID` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table masjid.namaz_times: ~20 rows (approximately)
DELETE FROM `namaz_times`;
/*!40000 ALTER TABLE `namaz_times` DISABLE KEYS */;
INSERT INTO `namaz_times` (`ID`, `NAME`, `TIME`, `MASJID_ID`) VALUES
	(0, 'FAJR', '5:30', 0),
	(1, 'ISHRAQ', '6:36', 0),
	(2, 'ZOHOR', '1:30', 0),
	(3, 'ASR', '5:15', 0),
	(4, 'MAGRIB', '6:48', 0),
	(5, 'ISHA', '8:30', 0),
	(6, 'JUMA', '1:40', 0),
	(7, 'REFRESH_REQUIRED', 'false', 0),
	(8, 'HIJRI_ADJUSTMENT', '0', 0),
	(9, 'FAJR', '5:30', 1),
	(10, 'ISHRAQ', '6:36', 1),
	(11, 'ZOHOR', '1:30', 1),
	(12, 'ASR', '5:15', 1),
	(13, 'MAGRIB', '6:23', 1),
	(14, 'ISHA', '7:14', 1),
	(15, 'JUMA', '1:40', 1),
	(16, 'REFRESH_REQUIRED', 'false', 1),
	(17, 'HIJRI_ADJUSTMENT', '0', 1),
	(18, 'AUDIO_PLAY_TYPE', 'LOCAL', 0),
	(19, 'AUDIO_PLAY_TYPE', 'BROWSER', 1);
/*!40000 ALTER TABLE `namaz_times` ENABLE KEYS */;


-- Dumping structure for table masjid.notices
CREATE TABLE IF NOT EXISTS `notices` (
  `ID` int(11) NOT NULL,
  `NOTICE` varchar(300) NOT NULL,
  `MASJID_ID` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table masjid.notices: ~8 rows (approximately)
DELETE FROM `notices`;
/*!40000 ALTER TABLE `notices` DISABLE KEYS */;
INSERT INTO `notices` (`ID`, `NOTICE`, `MASJID_ID`) VALUES
	(0, 'Mahana mashwara on 12 April after Fajr salaah at Abdullah Bin Mas\'ood Masjid.\r', 0),
	(1, 'Fajr salaah time has been changed to 5:40am.\r', 0),
	(2, 'Summer camp enrollment is on at Zohra Masjid.\r', 0),
	(3, 'Yearly Istima at RT Nagar on 9 April from Fajr till Isha time.\r', 0),
	(4, 'Mahana mashwara on 12 April after Fajr salaah at Abdullah Bin Mas\'ood Masjid.', 1),
	(5, 'Fajr salaah time has been changed to 5:40am.', 1),
	(6, 'Summer camp enrollment is on at Zohra Masjid.', 1),
	(7, 'Yearly Istima at RT Nagar on 9 April from Fajr till Isha time.', 1);
/*!40000 ALTER TABLE `notices` ENABLE KEYS */;


-- Dumping structure for table masjid.occasions
CREATE TABLE IF NOT EXISTS `occasions` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `DESCRIPTION` varchar(500) NOT NULL,
  `OCCASION_DATE` date NOT NULL,
  `MASJID_ID` int(11) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8 COMMENT='List of Islamic Occasions.';

-- Dumping data for table masjid.occasions: ~4 rows (approximately)
DELETE FROM `occasions`;
/*!40000 ALTER TABLE `occasions` DISABLE KEYS */;
INSERT INTO `occasions` (`ID`, `DESCRIPTION`, `OCCASION_DATE`, `MASJID_ID`) VALUES
	(7, 'Shab e Baraat', '2017-05-11', 0),
	(9, 'Shab e Baraat', '2017-05-11', 1),
	(12, 'Eid Ul Adha', '2017-09-02', 0),
	(13, 'Eid Al Adha', '2017-09-02', 1);
/*!40000 ALTER TABLE `occasions` ENABLE KEYS */;


-- Dumping structure for table masjid.quran_ayats
CREATE TABLE IF NOT EXISTS `quran_ayats` (
  `ID` int(11) NOT NULL,
  `AYAT` varchar(300) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table masjid.quran_ayats: ~45 rows (approximately)
DELETE FROM `quran_ayats`;
/*!40000 ALTER TABLE `quran_ayats` DISABLE KEYS */;
INSERT INTO `quran_ayats` (`ID`, `AYAT`) VALUES
	(0, 'The Most Beneficent (Allah)!\r'),
	(1, 'Has taught (you mankind) the Quran (by His Mercy).\r'),
	(2, 'He created man.\r'),
	(3, 'He taught him eloquent speech.\r'),
	(4, 'The sun and the moon run on their fixed courses (exactly) calculated with measured out stages for each (for reckoning, etc.).\r'),
	(5, 'And the herbs (or stars) and the trees both prostrate.\r'),
	(6, 'And the heaven He has raised high, and He has set up the Balance.\r'),
	(7, 'In order that you may not transgress (due) balance.\r'),
	(8, 'And observe the weight with equity and do not make the balance deficient.\r'),
	(9, 'And the earth He has put for the creatures.\r'),
	(10, 'Therein are fruits, date-palms producing sheathed fruit-stalks (enclosing dates).\r'),
	(11, 'And also corn, with (its) leaves and stalk for fodder, and sweet-scented plants.\r'),
	(12, 'Then which of the Blessings of your Lord will you both (jinns and men) deny?\r'),
	(13, 'He created man (Adam) from sounding clay like the clay of pottery.\r'),
	(14, 'And the jinns did He create from a smokeless flame of fire.\r'),
	(15, 'Then which of the Blessings of your Lord will you both (jinns and men) deny?\r'),
	(16, '(He is) the Lord of the two easts (places of sunrise during early summer and early winter) and the Lord of the two wests (places of sunset during early summer and early winter).\r'),
	(17, 'Then which of the Blessings of your Lord will you both (jinns and men) deny?\r'),
	(18, 'He has let loose the two seas (the salt water and the sweet) meeting together.\r'),
	(19, 'Between them is a barrier which none of them can transgress.\r'),
	(20, 'Then which of the Blessings of your Lord will you both (jinns and men) deny?\r'),
	(21, 'Out of them both come out pearl and coral.\r'),
	(22, 'Then which of the Blessings of your Lord will you both (jinns and men) deny?\r'),
	(23, 'And His are the ships going and coming in the seas, like mountains.\r'),
	(24, 'Then which of the Blessings of your Lord will you both (jinns and men) deny?\r'),
	(25, 'Whatsoever is on it (the earth) will perish.\r'),
	(26, 'And the Face of your Lord full of Majesty and Honour will abide forever.\r'),
	(27, 'Then which of the Blessings of your Lord will you both (jinns and men) deny?\r'),
	(28, 'Whosoever is in the heavens and on earth begs of Him (its needs from Him). Every day He has a matter to bring forth (such as giving honour to some, disgrace to some, life to some, death to some, etc.)!\r'),
	(29, 'Then which of the Blessings of your Lord will you both (jinns and men) deny?\r'),
	(30, 'We shall attend to you, O you two classes (jinns and men)!\r'),
	(31, 'Then which of the Blessings of your Lord will you both (jinns and men) deny?\r'),
	(32, 'O assembly of jinns and men! If you have power to pass beyond the zones of the heavens and the earth, then pass (them)! But you will never be able to pass them, except with authority (from Allah)!\r'),
	(33, 'Then which of the Blessings of your Lord will you both (jinns and men) deny?\r'),
	(34, 'There will be sent against you both, smokeless flames of fire and (molten) brass, and you will not be able to defend yourselves.\r'),
	(35, 'Then which of the Blessings of your Lord will you both (jinns and men) deny?\r'),
	(36, 'Then when the heaven is rent asunder, and it becomes rosy or red like red-oil, or red hide.\r'),
	(37, 'Then which of the Blessings of your Lord will you both (jinns and men) deny?\r'),
	(38, 'So on that Day no question will be asked of man or jinn as to his sin, (because they have already been known from their faces either white or black).\r'),
	(39, 'Then which of the Blessings of your Lord will you both (jinns and men) deny?\r'),
	(40, 'The Mujrimun (polytheists, criminals, sinners, etc.) will be known by their marks (black faces), and they will be seized by their forelocks and their feet.\r'),
	(41, 'Then which of the Blessings of your Lord will you both (jinns and men) deny?\r'),
	(42, 'This is Hell which the Mujrimun (polytheists, criminals, sinners, etc.) denied.\r'),
	(43, 'They will go between it (Hell) and the boiling hot water!\r'),
	(44, 'Then which of the Blessings of your Lord will you both (jinns and men) deny?\r');
/*!40000 ALTER TABLE `quran_ayats` ENABLE KEYS */;


-- Dumping structure for table masjid.ramzan_timetable
CREATE TABLE IF NOT EXISTS `ramzan_timetable` (
  `RAMZAN_DATE` int(11) NOT NULL,
  `SEHERI` varchar(100) NOT NULL,
  `IFTAR` varchar(100) NOT NULL,
  `MASJID_ID` int(11) NOT NULL,
  PRIMARY KEY (`RAMZAN_DATE`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table masjid.ramzan_timetable: ~60 rows (approximately)
DELETE FROM `ramzan_timetable`;
/*!40000 ALTER TABLE `ramzan_timetable` DISABLE KEYS */;
INSERT INTO `ramzan_timetable` (`RAMZAN_DATE`, `SEHERI`, `IFTAR`, `MASJID_ID`) VALUES
	(1, '4:30', '6:43', 0),
	(2, '4:30', '6:43', 0),
	(3, '4:29', '6:43', 0),
	(4, '4:29', '6:44', 0),
	(5, '4:29', '6:44', 0),
	(6, '4:29', '6:44', 0),
	(7, '4:29', '6:45', 0),
	(8, '4:29', '6:45', 0),
	(9, '4:29', '6:45', 0),
	(10, '4:29', '6:45', 0),
	(11, '4:29', '6:45', 0),
	(12, '4:29', '6:46', 0),
	(13, '4:29', '6:46', 0),
	(14, '4:29', '6:46', 0),
	(15, '4:29', '6:46', 0),
	(16, '4:29', '6:46', 0),
	(17, '4:30', '6:47', 0),
	(18, '4:30', '6:48', 0),
	(19, '4:30', '6:48', 0),
	(20, '4:30', '6:48', 0),
	(21, '4:31', '6:48', 0),
	(22, '4:31', '6:49', 0),
	(23, '4:31', '6:49', 0),
	(24, '4:31', '6:49', 0),
	(25, '4:31', '6:49', 0),
	(27, '4:31', '6:41', 0),
	(28, '4:31', '6:41', 0),
	(29, '4:30', '6:41', 0),
	(30, '4:30', '6:41', 0),
	(31, '4:30', '6:42', 0),
	(41, '4:30', '6:43', 1),
	(42, '4:30', '6:43', 1),
	(43, '4:29', '6:43', 1),
	(44, '4:29', '6:44', 1),
	(45, '4:29', '6:44', 1),
	(46, '4:29', '6:44', 1),
	(47, '4:29', '6:45', 1),
	(48, '4:29', '6:45', 1),
	(49, '4:29', '6:45', 1),
	(50, '4:29', '6:45', 1),
	(51, '4:29', '6:45', 1),
	(52, '4:29', '6:46', 1),
	(53, '4:29', '6:46', 1),
	(54, '4:29', '6:46', 1),
	(55, '4:29', '6:46', 1),
	(56, '4:29', '6:46', 1),
	(57, '4:30', '6:47', 1),
	(58, '4:30', '6:48', 1),
	(59, '4:30', '6:48', 1),
	(60, '4:30', '6:48', 1),
	(61, '4:31', '6:48', 1),
	(62, '4:31', '6:49', 1),
	(63, '4:31', '6:49', 1),
	(64, '4:31', '6:49', 1),
	(65, '4:31', '6:49', 1),
	(67, '4:31', '6:41', 1),
	(68, '4:31', '6:41', 1),
	(69, '4:30', '6:41', 1),
	(70, '4:30', '6:41', 1),
	(71, '4:30', '6:42', 1);
/*!40000 ALTER TABLE `ramzan_timetable` ENABLE KEYS */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
