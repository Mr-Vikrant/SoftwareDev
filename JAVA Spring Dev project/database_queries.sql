create database FileTrack;

use FileTrack;

create table LoginTable(UserName char(15) primary key,Password varchar(50),isAdmin varchar(1));
use FileTrack;
desc LoginTable;

create table EmployeeTable(UserName char(15),EmpId char(15) not null,EmpName varchar(30) not null,EmpDesignation char(20) not null,EmpEmail varchar(30) not null, EmpAge int not null, EmpAddress varchar(30));
select * from EmployeeTable;
desc EmployeeTable;

create table FileTable(FileId int auto_increment primary key,FileRefNo char(30) not null,Filename char(30),FilePriority int,FileLanguage int,FileLastEdit datetime not null,FilePath varchar(200),FileCreatedOn datetime,unique(FileRefNo));
insert into FileTable(FileRefNo,Filename,FilePriority,FileLanguage,FileLastEdit,FilePath,FileCreatedOn)values('152','abc',1,1,'samadhan','wgvebgrt',now());
truncate table filetable;
select * from filetable;



create table MasterPriority(value int not null, Description char(30));
create table MasterLanguage(value int not null, Description char(30));
show tables;


create table FileTrackTable(FIleId int not null,FileFrom char(30) not null,FileTo char(30) not null,FileAllocatedDate date,FileIsSubmitted boolean not null default False,FileSubmittedOn date,FileInputRemarks char(75), FileOutputRemarks char(75),foreign key (FileId) references filetable(FileId));
desc filetracktable;
select * from filetracktable;

create table FileTrackTable(FIleId int not null,FileFrom char(30) not null,FileTo char(30) not null,FileAllocatedDate date,FileIsSubmitted boolean not null default False,FileSubmittedOn date,FileInputRemarks char(75), FileOutputRemarks char(75),foreign key (FileId) references filetable(FileId));
----------------------------------------------------------------------------------------------------------------
UPDATE filetracktable set FileIsSubmitted='F'

