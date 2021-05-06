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
  `bauthorid` VARCHAR(25) NOT NULL,
  `bdate` timestamp default now(),
  PRIMARY KEY (`bno`),
  FOREIGN KEY (`bauthorid`) REFERENCES `user` (`uid`));
  
  CREATE TABLE IF NOT EXISTS `reply` (
  `rno` INT NOT NULL auto_increment,
  `bno` INT NOT NULL,
  `uid` VARCHAR(25) NOT NULL,
  `rcontent` TEXT default NULL,
  `rdate` timestamp default now(),
  PRIMARY KEY (`rno`),
  FOREIGN KEY (`uid`) REFERENCES `user` (`uid`),
  FOREIGN KEY (`bno`) REFERENCES `board` (`bno`));
  