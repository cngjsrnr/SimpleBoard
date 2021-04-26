drop schema simpleboard;
CREATE SCHEMA IF NOT EXISTS `simpleboard` DEFAULT CHARACTER SET utf8 ;
use simpleboard;

CREATE TABLE IF NOT EXISTS `user` (
  `uid` VARCHAR(25) NOT NULL,
  `upass` VARCHAR(25) NOT NULL,
  `uname` VARCHAR(25) NOT NULL,
  PRIMARY KEY (`uid`));
  
  CREATE TABLE IF NOT EXISTS `board` (
  `bno` INT NOT NULL auto_increment,
  `btitle` VARCHAR(50) NOT NULL,
  `bcontent` TEXT default NULL,
  `bauthor` VARCHAR(25) NOT NULL,
  `bdate` timestamp default now(),
  PRIMARY KEY (`bno`));
  