-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Dec 05, 2024 at 12:39 PM
-- Server version: 10.4.32-MariaDB
-- PHP Version: 8.3.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `datasiswa`
--

-- --------------------------------------------------------

--
-- Table structure for table `cabeka`
--

CREATE TABLE `cabeka` (
  `nis` varchar(5) NOT NULL,
  `tgl` date NOT NULL,
  `permasalahan` varchar(255) NOT NULL,
  `solusi` varchar(255) NOT NULL,
  `kehadiran` enum('sakit','izin','alpha') NOT NULL,
  `petugas_bk` varchar(70) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `cabeka`
--

INSERT INTO `cabeka` (`nis`, `tgl`, `permasalahan`, `solusi`, `kehadiran`, `petugas_bk`) VALUES
('001', '2024-12-01', 'Belajar', 'Belajar', 'izin', '001'),
('001', '2024-12-03', 'rrrrrrrrrrrrrrrrr', 'rrrrrrrrrrrrrrrrrrrrr', 'izin', '001'),
('002', '2024-12-02', 'Belajar', 'Belajar', 'izin', '003'),
('003', '2024-12-02', 'Sering Bohong', 'Berhenti Bohong', 'sakit', '004');

-- --------------------------------------------------------

--
-- Stand-in structure for view `cabekaview`
-- (See below for the actual view)
--
CREATE TABLE `cabekaview` (
`nis` varchar(5)
,`tgl` date
,`permasalahan` varchar(255)
,`solusi` varchar(255)
,`kehadiran` enum('sakit','izin','alpha')
,`petugas_bk` varchar(70)
,`nama_siswa` varchar(60)
,`nama_guru` varchar(70)
);

-- --------------------------------------------------------

--
-- Table structure for table `guru`
--

CREATE TABLE `guru` (
  `nik` varchar(25) NOT NULL,
  `nama` varchar(70) NOT NULL,
  `jenkel` enum('L','P') DEFAULT NULL,
  `tglLahir` date DEFAULT NULL,
  `tmpLahir` varchar(50) DEFAULT NULL,
  `Alamat` varchar(75) DEFAULT NULL,
  `Telpon` varchar(25) DEFAULT NULL,
  `User` varchar(6) DEFAULT NULL,
  `Waktu` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  `photo` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `guru`
--

INSERT INTO `guru` (`nik`, `nama`, `jenkel`, `tglLahir`, `tmpLahir`, `Alamat`, `Telpon`, `User`, `Waktu`, `photo`) VALUES
('001', 'Rian Pioriandana', 'L', '1976-09-26', 'Jakarta', 'Jl. Sudirman', '08888999', 'Rian', '2024-11-15 00:35:39', NULL),
('002', 'Eva Yepriliyanti', 'P', '1978-07-08', 'Bandung', 'Tangerang', '07777', 'Eva', '2024-11-07 13:25:48', ''),
('003', 'Chairul Fajri', 'L', '1997-03-10', 'Jakarta', 'Pekayon', '099999', 'Fajri', '2024-11-15 00:09:06', ''),
('004', 'Nisa', 'P', '2024-11-01', 'jakarta', 'jakarta', '3434543', 'nisa', '2024-11-15 00:28:17', NULL);

-- --------------------------------------------------------

--
-- Table structure for table `jurusan`
--

CREATE TABLE `jurusan` (
  `kdJurusan` varchar(2) NOT NULL,
  `nmJurusan` varchar(50) NOT NULL,
  `kdKajur` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `jurusan`
--

INSERT INTO `jurusan` (`kdJurusan`, `nmJurusan`, `kdKajur`) VALUES
('TE', 'Teknik Elektro', '002'),
('TI', 'Teknik Informatika', '003'),
('TM', 'Teknik Mesin', '001');

-- --------------------------------------------------------

--
-- Table structure for table `matapelajaran`
--

CREATE TABLE `matapelajaran` (
  `kode` varchar(4) NOT NULL,
  `mapel` varchar(40) NOT NULL,
  `kelompok` enum('Muatan Nasional','Muatan Lokal','Produktif') NOT NULL,
  `JmlJam` int(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `matapelajaran`
--

INSERT INTO `matapelajaran` (`kode`, `mapel`, `kelompok`, `JmlJam`) VALUES
('BING', 'B. Inggris', 'Muatan Nasional', 3),
('DPK', 'Pemrograman', 'Produktif', 3),
('MTK', 'Matematika', 'Muatan Lokal', 3),
('SJRH', 'Sejarah', 'Muatan Nasional', 3);

-- --------------------------------------------------------

--
-- Table structure for table `nilai`
--

CREATE TABLE `nilai` (
  `nis` varchar(5) NOT NULL,
  `semester` enum('01','02','03','04','05','06','07','08','09','10') NOT NULL,
  `kode_mapel` varchar(4) NOT NULL,
  `kode_guru` varchar(5) NOT NULL,
  `nilai` decimal(6,2) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `nilai`
--

INSERT INTO `nilai` (`nis`, `semester`, `kode_mapel`, `kode_guru`, `nilai`) VALUES
('001', '01', 'BING', '004', 95.00),
('001', '01', 'DPK', '002', 95.00),
('001', '01', 'MTK', '003', 98.00),
('001', '01', 'SJRH', '001', 90.00),
('001', '02', 'DPK', '001', 98.00),
('002', '01', 'BING', '003', 90.00),
('002', '01', 'DPK', '002', 95.00),
('002', '01', 'MTK', '003', 98.00),
('002', '01', 'SJRH', '001', 90.00),
('003', '01', 'MTK', '001', 95.00);

-- --------------------------------------------------------

--
-- Stand-in structure for view `nilaiview`
-- (See below for the actual view)
--
CREATE TABLE `nilaiview` (
`nis` varchar(5)
,`nama` varchar(60)
,`semester` enum('01','02','03','04','05','06','07','08','09','10')
,`nilai` decimal(6,2)
,`kode_mapel` varchar(4)
,`mapel` varchar(40)
,`kode_guru` varchar(5)
,`Pengampu` varchar(70)
);

-- --------------------------------------------------------

--
-- Table structure for table `siswa`
--

CREATE TABLE `siswa` (
  `nis` varchar(5) NOT NULL,
  `nama` varchar(60) NOT NULL,
  `jenkel` enum('L','P') DEFAULT NULL,
  `tmplahir` varchar(40) DEFAULT NULL,
  `tglLahir` date DEFAULT NULL,
  `alamat` varchar(80) DEFAULT NULL,
  `telepon` varchar(25) DEFAULT NULL,
  `ayah` varchar(60) DEFAULT NULL,
  `ibu` varchar(60) DEFAULT NULL,
  `user` varchar(10) DEFAULT NULL,
  `waktu` timestamp NOT NULL DEFAULT current_timestamp(),
  `photo` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `siswa`
--

INSERT INTO `siswa` (`nis`, `nama`, `jenkel`, `tmplahir`, `tglLahir`, `alamat`, `telepon`, `ayah`, `ibu`, `user`, `waktu`, `photo`) VALUES
('001', 'Iftikhar Azhar', 'L', 'Jakarta', '2007-08-21', 'Jl. Dalang, Munjul, Jakarta Timur', '0819-0547-8802', 'Ayah', 'Ibu', 'azhar', '2024-08-23 01:11:02', ''),
('002', 'Ramadan', 'L', 'Ciracas', '2007-09-26', 'Dukuh', '08888', 'Muhidin', 'Ibu', 'Tabo', '2024-08-30 01:08:19', 'D:\\AWS\\AWS\\AWS\\img-profil\\Ramadan.jpg'),
('003', 'Zaky', 'L', 'Makmur', '2008-07-06', 'Makmur', '1236213', 'Wasito', 'Marfungah', 'j4kyy', '2024-09-06 00:47:25', 'D:\\pp.jpg'),
('004', 'adasdadsa', 'L', 'sadadad', '2024-11-01', 'dsadad', '34244234', 'dsdsdasd', 'adsdsa', 'asddsada', '2024-11-01 00:20:16', 'D:\\Foto Profil.jpg');

-- --------------------------------------------------------

--
-- Structure for view `cabekaview`
--
DROP TABLE IF EXISTS `cabekaview`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `cabekaview`  AS SELECT `c`.`nis` AS `nis`, `c`.`tgl` AS `tgl`, `c`.`permasalahan` AS `permasalahan`, `c`.`solusi` AS `solusi`, `c`.`kehadiran` AS `kehadiran`, `c`.`petugas_bk` AS `petugas_bk`, `s`.`nama` AS `nama_siswa`, `g`.`nama` AS `nama_guru` FROM ((`cabeka` `c` join `siswa` `s`) join `guru` `g`) WHERE `c`.`nis` = `s`.`nis` AND `c`.`petugas_bk` = `g`.`nik` ;

-- --------------------------------------------------------

--
-- Structure for view `nilaiview`
--
DROP TABLE IF EXISTS `nilaiview`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `nilaiview`  AS SELECT `n`.`nis` AS `nis`, `s`.`nama` AS `nama`, `n`.`semester` AS `semester`, `n`.`nilai` AS `nilai`, `n`.`kode_mapel` AS `kode_mapel`, `m`.`mapel` AS `mapel`, `n`.`kode_guru` AS `kode_guru`, `g`.`nama` AS `Pengampu` FROM (((`nilai` `n` join `siswa` `s`) join `guru` `g`) join `matapelajaran` `m`) WHERE `s`.`nis` = `n`.`nis` AND `g`.`nik` = `n`.`kode_guru` AND `m`.`kode` = `n`.`kode_mapel` ;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `cabeka`
--
ALTER TABLE `cabeka`
  ADD PRIMARY KEY (`nis`,`tgl`),
  ADD KEY `fk_guru_bk` (`petugas_bk`);

--
-- Indexes for table `guru`
--
ALTER TABLE `guru`
  ADD PRIMARY KEY (`nik`);

--
-- Indexes for table `jurusan`
--
ALTER TABLE `jurusan`
  ADD PRIMARY KEY (`kdJurusan`);

--
-- Indexes for table `matapelajaran`
--
ALTER TABLE `matapelajaran`
  ADD PRIMARY KEY (`kode`);

--
-- Indexes for table `nilai`
--
ALTER TABLE `nilai`
  ADD PRIMARY KEY (`nis`,`semester`,`kode_mapel`),
  ADD KEY `kode_mapel` (`kode_mapel`);

--
-- Indexes for table `siswa`
--
ALTER TABLE `siswa`
  ADD PRIMARY KEY (`nis`);

--
-- Constraints for dumped tables
--

--
-- Constraints for table `cabeka`
--
ALTER TABLE `cabeka`
  ADD CONSTRAINT `cabeka_ibfk_1` FOREIGN KEY (`nis`) REFERENCES `siswa` (`nis`),
  ADD CONSTRAINT `fk_guru_bk` FOREIGN KEY (`petugas_bk`) REFERENCES `guru` (`nik`),
  ADD CONSTRAINT `fk_nis` FOREIGN KEY (`nis`) REFERENCES `siswa` (`nis`);

--
-- Constraints for table `nilai`
--
ALTER TABLE `nilai`
  ADD CONSTRAINT `nilai_ibfk_1` FOREIGN KEY (`kode_mapel`) REFERENCES `matapelajaran` (`kode`),
  ADD CONSTRAINT `nilai_ibfk_2` FOREIGN KEY (`nis`) REFERENCES `siswa` (`nis`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
