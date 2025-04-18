SET NAMES utf8mb4;
DROP TABLE IF EXISTS students;
DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS schools;
DROP TABLE IF EXISTS applications;
DROP TABLE IF EXISTS admins;
-- 用户表
CREATE TABLE users (
  id INT PRIMARY KEY ,
  acount VARCHAR(50) NOT NULL UNIQUE,
  password VARCHAR(255) NOT NULL,
  role ENUM('STUDENT', 'SCHOOL', 'ADMIN') NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- 学生详细信息表
CREATE TABLE students (
  id INT PRIMARY KEY ,
  student_id INT NOT NULL,
  name VARCHAR(50) NOT NULL,
  gender ENUM('MALE', 'FEMALE', 'OTHER'),
  birth_date DATE,
  contact_phone VARCHAR(20),
  contact_email VARCHAR(100),
  address TEXT
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- 学校信息表
CREATE TABLE schools (
  id INT PRIMARY KEY ,
  school_id INT NOT NULL,
  name VARCHAR(100) NOT NULL,
  location VARCHAR(100),
  description TEXT,
  contact_phone VARCHAR(20),
  contact_email VARCHAR(100),
  website VARCHAR(255)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- 申请记录表
CREATE TABLE applications (
  id INT PRIMARY KEY ,
  student_id INT NOT NULL,
  school_id INT NOT NULL,
  status ENUM('PENDING', 'APPROVED', 'REJECTED') DEFAULT 'PENDING',
  application_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  processed_date TIMESTAMP NULL,
  notes TEXT,
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- 管理员信息表
CREATE TABLE admins (
  id INT PRIMARY KEY ,
  admin_id INT NOT NULL,
  name VARCHAR(50) NOT NULL,
  contact_phone VARCHAR(20),
  contact_email VARCHAR(100)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;