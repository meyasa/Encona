-- phpMyAdmin SQL Dump
-- version 4.7.4
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: 18 Mar 2020 pada 15.12
-- Versi Server: 10.1.28-MariaDB
-- PHP Version: 7.1.11

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `enconadb`
--

-- --------------------------------------------------------

--
-- Struktur dari tabel `barang`
--

CREATE TABLE `barang` (
  `kd_barang` varchar(20) NOT NULL,
  `nama_barang` varchar(100) NOT NULL,
  `category` varchar(100) NOT NULL,
  `tarif` int(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `barang`
--

INSERT INTO `barang` (`kd_barang`, `nama_barang`, `category`, `tarif`) VALUES
('A101', 'Laptop', 'Peralatan Kantor', 100000),
('A103', 'Mesin Potong kayu meja 8\" ( Table Saw - Makita 2702 ) ', 'Peralatan Teknis', 400000),
('A104', 'Circular saw 6\" -Bosch GKS 600', 'Peralatan Teknis', 750000),
('A102', 'Rebar', 'Material', 300000),
('A105', 'HT', 'Komunikasi', 30000);

-- --------------------------------------------------------

--
-- Struktur dari tabel `bckpo`
--

CREATE TABLE `bckpo` (
  `rowid` int(100) NOT NULL DEFAULT '0',
  `nama_project` varchar(100) NOT NULL,
  `id_project` varchar(100) NOT NULL,
  `no_po` varchar(100) NOT NULL,
  `nama_vendor` varchar(100) NOT NULL,
  `id_vendor` varchar(100) NOT NULL,
  `pic_vendor` varchar(100) NOT NULL,
  `category` varchar(100) NOT NULL,
  `nama_barang` varchar(100) NOT NULL,
  `jumlah` int(10) NOT NULL,
  `tarif` int(10) NOT NULL,
  `tgl_po` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `bckpo`
--

INSERT INTO `bckpo` (`rowid`, `nama_project`, `id_project`, `no_po`, `nama_vendor`, `id_vendor`, `pic_vendor`, `category`, `nama_barang`, `jumlah`, `tarif`, `tgl_po`) VALUES
(1, 'Pembangunan Instalasi Jaringan', '8000001', 'PO01-20200220-1001', 'PT. LUCKY ABADI JAYA', '4004', 'Irwansyah', 'Peralatan Kantor', 'Laptop', 10, 3000000, '2020-02-20'),
(2, 'Pembangunan TMA Rooftop Cafe', '8000002', 'PO01-20200318-1002', 'PT. LUCKY ABADI JAYA', '4004', 'Irwansyah', 'Peralatan Kantor', 'Laptop', 5, 500000, '2020-03-18'),
(5, 'Pembuatan Drainase', '8000003', 'PO01-20200318-1003', 'PT. TECH MAYANTARA', '4002', 'Irene Nuzuliah', 'Komunikasi', 'HT', 10, 300000, '2020-03-18');

-- --------------------------------------------------------

--
-- Struktur dari tabel `bckproject`
--

CREATE TABLE `bckproject` (
  `id_project` varchar(100) DEFAULT NULL,
  `nama_project` varchar(100) DEFAULT NULL,
  `nama_client` varchar(100) DEFAULT NULL,
  `id_client` varchar(100) DEFAULT NULL,
  `tgl_mulai` date DEFAULT NULL,
  `tgl_selesai` date DEFAULT NULL,
  `nilai_project` int(100) DEFAULT NULL,
  `jml_vendor` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `bckproject`
--

INSERT INTO `bckproject` (`id_project`, `nama_project`, `nama_client`, `id_client`, `tgl_mulai`, `tgl_selesai`, `nilai_project`, `jml_vendor`) VALUES
('8000001', 'Pembangunan Instalasi Jaringan', 'PT. SURYA KENCANA BAHARI', '1001', '2020-02-14', '2020-04-14', 50000000, '5'),
('8000002', 'Pembangunan TMA Rooftop Cafe', 'PT. TECH MAYANTARA ASIA', '1002', '2020-03-10', '2020-04-20', 175000000, '4'),
('8000003', 'Pembuatan Drainase', 'PT. MELIA SEHAT SEJAHTERA', '1003', '2020-03-18', '2020-03-29', 75000000, '3');

-- --------------------------------------------------------

--
-- Struktur dari tabel `client`
--

CREATE TABLE `client` (
  `id_client` int(11) NOT NULL,
  `nama_client` varchar(100) NOT NULL,
  `alamat` varchar(200) NOT NULL,
  `no_telp` varchar(20) NOT NULL,
  `pic` varchar(100) NOT NULL,
  `tgl_join` date NOT NULL,
  `npwp` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `client`
--

INSERT INTO `client` (`id_client`, `nama_client`, `alamat`, `no_telp`, `pic`, `tgl_join`, `npwp`) VALUES
(1001, 'PT. SURYA KENCANA BAHARI', 'Tanjung Priok, Jakarta Utara', '085612345678', 'Nia Daniati', '2020-03-10', '8932742817171234'),
(1003, 'PT. MELIA SEHAT SEJAHTERA', 'Manggarai, Jakarta Pusat', '087787652516', 'Elvan Boer', '2020-03-07', '8932742817128371'),
(1002, 'PT. TECH MAYANTARA ASIA', 'Japati, Bandung', '085612341234', 'Irena Marchelina', '2020-03-03', '8932742817177777');

-- --------------------------------------------------------

--
-- Struktur dari tabel `deleted_po`
--

CREATE TABLE `deleted_po` (
  `no_po` varchar(100) NOT NULL,
  `delete_time` datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Struktur dari tabel `pembayaran`
--

CREATE TABLE `pembayaran` (
  `rowid` int(100) NOT NULL,
  `no_po` varchar(100) NOT NULL,
  `status_bayar` varchar(100) NOT NULL,
  `tgl_bayar` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Struktur dari tabel `po`
--

CREATE TABLE `po` (
  `rowid` int(100) NOT NULL,
  `nama_project` varchar(100) NOT NULL,
  `id_project` varchar(100) NOT NULL,
  `no_po` varchar(100) NOT NULL,
  `nama_vendor` varchar(100) NOT NULL,
  `id_vendor` varchar(100) NOT NULL,
  `pic_vendor` varchar(100) NOT NULL,
  `category` varchar(100) NOT NULL,
  `nama_barang` varchar(100) NOT NULL,
  `jumlah` int(10) NOT NULL,
  `tarif` int(10) NOT NULL,
  `tgl_po` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Trigger `po`
--
DELIMITER $$
CREATE TRIGGER `after_delete_po` AFTER DELETE ON `po` FOR EACH ROW BEGIN
    declare
    max_row, var_no_po varchar(100);
   	
   	select max(rowid) into max_row from po;
    select no_po into var_no_po from po where rowid = max_row;
    
    delete from pembayaran where no_po = var_no_po;
end
$$
DELIMITER ;
DELIMITER $$
CREATE TRIGGER `after_insert_po` AFTER INSERT ON `po` FOR EACH ROW BEGIN
    declare
    max_row, var_no_po varchar(100);
   	
   	select max(rowid) into max_row from po;
    select no_po into var_no_po from po where rowid = max_row;
    
    insert into pembayaran (no_po, status_bayar)
    select var_no_po, 'Belum Terbayar' as status_bayar from dual;
end
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Struktur dari tabel `po2`
--

CREATE TABLE `po2` (
  `nama_project` varchar(100) DEFAULT NULL,
  `id_project` varchar(100) DEFAULT NULL,
  `no_po` varchar(100) DEFAULT NULL,
  `nama_vendor` varchar(100) DEFAULT NULL,
  `id_vendor` varchar(100) DEFAULT NULL,
  `pic_vendor` varchar(100) DEFAULT NULL,
  `category` varchar(100) DEFAULT NULL,
  `nama_barang` varchar(100) DEFAULT NULL,
  `jumlah` int(10) DEFAULT NULL,
  `tarif` int(10) DEFAULT NULL,
  `tgl_po` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `po2`
--

INSERT INTO `po2` (`nama_project`, `id_project`, `no_po`, `nama_vendor`, `id_vendor`, `pic_vendor`, `category`, `nama_barang`, `jumlah`, `tarif`, `tgl_po`) VALUES
('Pembangunan TMA Rooftop Cafe', '8000002', 'PO01-20200318-1003', 'PT. TECH MAYANTARA', '4002', 'Irene Nuzuliah', 'Komunikasi', 'HT', 8, 240000, '2020-03-21'),
('Pembangunan TMA Rooftop Cafe', '8000002', 'PO01-20200318-1003', 'PT. TECH MAYANTARA', '4002', 'Irene Nuzuliah', 'Komunikasi', 'HT', 8, 240000, '2020-03-21'),
('Pembangunan Instalasi Jaringan', '8000001', 'PO01-20200220-1001', 'PT. LUCKY ABADI JAYA', '4004', 'Irwansyah', 'Peralatan Kantor', 'Laptop', 10, 3000000, '2020-02-20');

-- --------------------------------------------------------

--
-- Struktur dari tabel `po4`
--

CREATE TABLE `po4` (
  `nama_project` varchar(100) DEFAULT NULL,
  `id_project` varchar(100) DEFAULT NULL,
  `no_po` varchar(100) DEFAULT NULL,
  `nama_vendor` varchar(100) DEFAULT NULL,
  `id_vendor` varchar(100) DEFAULT NULL,
  `pic_vendor` varchar(100) DEFAULT NULL,
  `category` varchar(100) DEFAULT NULL,
  `nama_barang` varchar(100) DEFAULT NULL,
  `jumlah` int(10) DEFAULT NULL,
  `tarif` int(10) DEFAULT NULL,
  `tgl_po` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `po4`
--

INSERT INTO `po4` (`nama_project`, `id_project`, `no_po`, `nama_vendor`, `id_vendor`, `pic_vendor`, `category`, `nama_barang`, `jumlah`, `tarif`, `tgl_po`) VALUES
('Pembangunan Instalasi Jaringan', '8000001', 'PO01-20200220-1001', 'PT. LUCKY ABADI JAYA', '4004', 'Irwansyah', 'Peralatan Kantor', 'Laptop', 10, 3000000, '2020-02-20');

-- --------------------------------------------------------

--
-- Struktur dari tabel `project`
--

CREATE TABLE `project` (
  `id_project` varchar(100) DEFAULT NULL,
  `nama_project` varchar(100) DEFAULT NULL,
  `nama_client` varchar(100) DEFAULT NULL,
  `id_client` varchar(100) DEFAULT NULL,
  `tgl_mulai` date DEFAULT NULL,
  `tgl_selesai` date DEFAULT NULL,
  `nilai_project` int(100) DEFAULT NULL,
  `jml_vendor` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `project`
--

INSERT INTO `project` (`id_project`, `nama_project`, `nama_client`, `id_client`, `tgl_mulai`, `tgl_selesai`, `nilai_project`, `jml_vendor`) VALUES
('8000001', 'Pembangunan Instalasi Jaringan', 'PT. SURYA KENCANA BAHARI', '1001', '2020-03-18', '2020-03-21', 75000000, '2');

-- --------------------------------------------------------

--
-- Struktur dari tabel `project2`
--

CREATE TABLE `project2` (
  `id_project` varchar(100) DEFAULT NULL,
  `nama_project` varchar(100) DEFAULT NULL,
  `nama_client` varchar(100) DEFAULT NULL,
  `id_client` varchar(100) DEFAULT NULL,
  `tgl_mulai` date DEFAULT NULL,
  `tgl_selesai` date DEFAULT NULL,
  `no_po` varchar(100) DEFAULT NULL,
  `nilai_project` int(100) DEFAULT NULL,
  `jml_vendor` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `project2`
--

INSERT INTO `project2` (`id_project`, `nama_project`, `nama_client`, `id_client`, `tgl_mulai`, `tgl_selesai`, `no_po`, `nilai_project`, `jml_vendor`) VALUES
('8000001', 'Pembangunan Instalasi Jaringan', 'PT. SURYA KENCANA BAHARI', '1001', '2020-02-14', '2020-04-14', 'PO01-20200214-1001', 50000000, '5');

-- --------------------------------------------------------

--
-- Struktur dari tabel `t_admin`
--

CREATE TABLE `t_admin` (
  `rowid` int(10) NOT NULL,
  `username` varchar(100) NOT NULL,
  `password` varchar(100) NOT NULL,
  `role` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `t_admin`
--

INSERT INTO `t_admin` (`rowid`, `username`, `password`, `role`) VALUES
(1, 'admin', 'password', 'Admin'),
(2, 'deni', 'bscfdgeg', 'Admin');

-- --------------------------------------------------------

--
-- Struktur dari tabel `t_admin3`
--

CREATE TABLE `t_admin3` (
  `username` varchar(100) NOT NULL,
  `password` varchar(100) NOT NULL,
  `role` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `t_admin3`
--

INSERT INTO `t_admin3` (`username`, `password`, `role`) VALUES
('admin', 'password', 'Admin');

-- --------------------------------------------------------

--
-- Struktur dari tabel `vendor`
--

CREATE TABLE `vendor` (
  `id_vendor` varchar(5) DEFAULT NULL,
  `nama_vendor` varchar(100) DEFAULT NULL,
  `pic` varchar(100) DEFAULT NULL,
  `contact` varchar(100) DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL,
  `category` varchar(100) DEFAULT NULL,
  `alamat` varchar(100) DEFAULT NULL,
  `status` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `vendor`
--

INSERT INTO `vendor` (`id_vendor`, `nama_vendor`, `pic`, `contact`, `email`, `category`, `alamat`, `status`) VALUES
('4001', 'PT. MASTER STEEL', 'Agung Gumelar', '089687485578', 'mastersteel@business.com', 'Material', 'Lebak, Banten', 'Aktif'),
('4002', 'PT. TECH MAYANTARA', 'Irene Nuzuliah', '083877465578', 'tma@web.com', 'Komunikasi', 'Japati, Bandung', 'Aktif'),
('4003', 'PT. SWAMEDIA JAYA', 'Anggi Perdana', '02187714567', 'swamedia@telkom.co.id', 'Material', 'Karawang, Jawa Barat', 'Aktif'),
('4004', 'PT. LUCKY ABADI JAYA', 'Irwansyah', '087877525123', 'lucky.abadi@gmail.com', 'Peralatan Kantor', 'Ujung Aspal, Pondok Gede', 'Aktif');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `pembayaran`
--
ALTER TABLE `pembayaran`
  ADD PRIMARY KEY (`rowid`);

--
-- Indexes for table `po`
--
ALTER TABLE `po`
  ADD PRIMARY KEY (`rowid`);

--
-- Indexes for table `t_admin`
--
ALTER TABLE `t_admin`
  ADD PRIMARY KEY (`rowid`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `pembayaran`
--
ALTER TABLE `pembayaran`
  MODIFY `rowid` int(100) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT for table `po`
--
ALTER TABLE `po`
  MODIFY `rowid` int(100) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- AUTO_INCREMENT for table `t_admin`
--
ALTER TABLE `t_admin`
  MODIFY `rowid` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
