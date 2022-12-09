create table login(
sno int IDENTITY(1,1) PRIMARY KEY,
fullname varchar(100),
username varchar(100) not null,
password1 varchar(100)not null,
email varchar(100)not null,
contactno varchar(11)not null,
)
select * from grave


create table PermanentGrave(
generalGraveId int not null check(generalGraveId<2000),
generalGraveSize varchar(50) not null,
generalCorpseId int identity(1,1) primary key,
corpseName varchar(100)  not null,
corpseGender varchar(100) not null,
corpseFatherName varchar(100) not null,
corpseMotherName varchar(100) not null,
corpseDateOfBirth date not null,
corpseDateOfDeath date not null,
corpseTimeOfDeath varchar(50) not null,
corpseCauseOfDeath varchar(500) not null,
guardianContactNo varchar(11) not null,
guardianRelation varchar(100) not null,
guardianAddress varchar(500) not null,
guardianName varchar(200) not null,
corpseNidNo varchar(17) not null,
corpseBirthCertificateNo varchar(12) not null,
burriedDate date not null,
RecycleDate date not null,
fee int not null,
OwnerId int not null,
ownerNid varchar(200) not null
);
select * from GeneralGrave1

create table PermanentGrave(
permanentGraveId int not null check(permanentGraveId<2000),
permanentGraveSize varchar(50) not null,
permanentCorpseId int identity(1,1) primary key,
corpseName varchar(100)  not null,
corpseGender varchar(100) not null,
corpseFatherName varchar(100) not null,
corpseMotherName varchar(100) not null,
corpseDateOfBirth date not null,
corpseDateOfDeath date not null,
corpseTimeOfDeath varchar(22) not null,
corpseCauseOfDeath varchar(500) not null,
corpseNidNo varchar(17) not null,
corpseBirthCertificateNo varchar(12) not null,
burriedDate date not null,
RecycleDate date not null,
fee int not null,
OwnerId int unique not null,
ownerName varchar(100) not null,
ownerRelation varchar(200) not null,
ownerPhoneNumber int not null,
ownerNid varchar(200) not null,
ownerAddress varchar(500) not null
);


select * from PermanentGrave
create table GeneralGrave1(
generalGraveId int not null check(generalGraveId<2000),
generalGraveSize varchar(50) not null,
generalCorpseId int identity(1,1) primary key,
corpseType varchar(100) not null,
corpseName varchar(100)  null,
corpseGender varchar(100) not null,
corpseFatherName varchar(100) null,
corpseMotherName varchar(100) null,
corpseDateOfBirth date null,
corpseDateOfDeath date null,
corpseTimeOfDeath varchar(50) null,
corpseCauseOfDeath varchar(500) null,
guardianContactNo varchar(11) null,
guardianRelation varchar(100) null,
guardianAddress varchar(500) null,
guardianName varchar(200) null,
corpseNidNo varchar(17) null,
corpseBirthCertificateNo varchar(12) null,
burriedDate date not null,
RecycleDate date not null,
fee int DEFAULT '0'
);
select * from GeneralGrave1
create table OwnerInfo(
serialNo int identity(1,1) primary key,
ownerId int not null,
ownerName varchar(100) not null,
ownerPhoneNumber int not null,
ownerNidOrBirthCertificate varchar(200) not null,
ownerAddress varchar(500) not null,
SellingStatus varchar(50) DEFAULT 'Available',
generalGraveId int  check(generalGraveId>2000),
availablility varchar(50) DEFAULT 'empty'
);
select * from OwnerInfo

create table paymentInfo(
paymentid int identity(1,1) primary key,
serialNo int FOREIGN KEY REFERENCES OwnerInfo(serialNo) not null,
ownerId int not null,
generalGraveId int  check(generalGraveId>2000),
fee int not null
);
select * from paymentInfo

create table Permanent_CorpseInfo(
permanentGraveId int not null check(permanentGraveId>2000),
permanentGraveSize varchar(50) not null,
serialNo int FOREIGN KEY REFERENCES OwnerInfo(serialNo) not null,
ownerId int not null, 
RelationWithOwner varchar(200),
availablility varchar(50) not null,
permanentCorpseId int identity(2001,1) primary key,
corpseType varchar(100) DEFAULT 'Identified',
corpseName varchar(100)  not null,
corpseGender varchar(100) not null,
corpseFatherName varchar(100) not null,
corpseMotherName varchar(100) not null,
corpseDateOfBirth date not null,
corpseDateOfDeath date not null,
corpseTimeOfDeath varchar(22) not null,
corpseCauseOfDeath varchar(500) not null,
corpseNidOrBirthCertificate varchar(17) not null,
burriedDate date not null,
RecycleDate date not null,

);
select * from Permanent_CorpseInfo




select * from PermanentGrave
where RecycleDate < Convert(date, getdate()) 


SELECT (2000-COUNT(generalGraveId))
FROM GeneralGrave1;
SELECT (2000-COUNT (DISTINCT generalGraveId))
  FROM PermanentGrave



 create table OwnerInfo(
serialNo int identity(1,1) primary key,
ownerId int not null,
ownerName varchar(100) not null,
ownerPhoneNumber int not null,
ownerNidOrBirthCertificate varchar(200) not null,
ownerAddress varchar(500) not null,
SellingStatus varchar(50) DEFAULT 'Available',
generalGraveId int  check(generalGraveId>2000),
availablility varchar(50) DEFAULT 'empty'
);
select * from OwnerInfo

create table paymentInfo(
paymentid int identity(1,1) primary key,
serialNo int FOREIGN KEY REFERENCES OwnerInfo(serialNo) not null,
ownerId int not null,
generalGraveId int  check(generalGraveId>2000),
fee int not null
);
select * from paymentInfo



create table Permanent_CorpseInfo(
permanentGraveId int not null check(permanentGraveId>2000),
permanentGraveSize varchar(50) not null,
serialNo int FOREIGN KEY REFERENCES OwnerInfo(serialNo) not null,
ownerId int not null, 
RelationWithOwner varchar(200),
availablility varchar(50) not null,
permanentCorpseId int identity(2001,1) primary key,
corpseType varchar(100) DEFAULT 'Identified',
corpseName varchar(100)  not null,
corpseGender varchar(100) not null,
corpseFatherName varchar(100) not null,
corpseMotherName varchar(100) not null,
corpseDateOfBirth date not null,
corpseDateOfDeath date not null,
corpseTimeOfDeath varchar(22) not null,
corpseCauseOfDeath varchar(500) not null,
corpseNidOrBirthCertificate varchar(17) not null,
burriedDate date not null,
RecycleDate date not null,

);
select * from Permanent_CorpseInfo

UPDATE t1
   SET t1.availablility = t2.availablility
   FROM Permanent_CorpseInfo  t2
   INNER JOIN OwnerInfo t1 ON           --changed to table1
   t2.availablility = 'Occupied' AND t2.serialNo=t1.serialNo AND t2.RecycleDate >= CAST(CURRENT_TIMESTAMP AS DATE)
   --where <some condition>

UPDATE t1 SET t1.availablility ='empty' 
FROM Permanent_CorpseInfo  t2 
INNER JOIN OwnerInfo t1
 ON t2.RecycleDate <= CAST(CURRENT_TIMESTAMP AS DATE) AND t2.serialNo=t1.serialNo
  
UPDATE Permanent_CorpseInfo 
SET availablility ='empty' 
WHERE RecycleDate <= CAST(CURRENT_TIMESTAMP AS DATE);
select * from Permanent_CorpseInfo


UPDATE t2
   SET t2.availablility = t1.availablility
   FROM Permanent_CorpseInfo  t2
   INNER JOIN OwnerInfo t1 ON           --changed to table1
   t1.availablility = 'Occupied' AND t2.serialNo=t1.serialNo 
   --where <some conditiselect * from OwnerInfoon>

BEGIN TRANSACTION
update A
set A.ownerId =  2,
A.ownerName='tonima Islam',
A.ownerPhoneNumber=01840753284,
A.ownerNidOrBirthCertificate='147852369',
A.ownerAddress='mohakhali dhaka',
A.SellingStatus='sold',
A.availablility='empty'
from OwnerInfo A inner join paymentInfo B
on B.serialNo = A.serialNo
and A.serialNo = 2
update B
set B.fee = 5000
from paymentInfo B inner join OwnerInfo A
    on B.serialNo = A.serialNo
    and A.serialNo = 2
update C
set C.permanentGraveId = 1000,
C.permanentGraveSize='SMALL',
C.RelationWithOwner='FATHER',
C.corpseName='Mukta Akter',
C.corpseGender='female',
C.corpseFatherName='Anisul Islam',
C.corpseMotherName='Munia Akter',
C.corpseDateOfBirth='2013-02-12',
C.corpseDateOfDeath='2013-02-12',
C.corpseTimeOfDeath='03.49',
C.corpseCauseOfDeath='Natural',
C.corpseNidOrBirthCertificate='123741852',
C.burriedDate='2013-02-12',
C.RecycleDate='2013-02-12'
from Permanent_CorpseInfo C inner join OwnerInfo A
    on C.serialNo = A.serialNo
    and C.serialNo = 2
	and C.permanentCorpseId=3
COMMIT


---ownerId ownerName ownerPhoneNumber ownerNidOrBirthCertificate ownerAddress SellingStatus availablility
---permanentGraveId permanentGraveSize RelationWithOwner corpseName corpseGender
---corpseFatherName corpseName corpseDateOfBirth corpseDateOfDeath
---corpseTimeOfDeath corpseCauseOfDeath corpseNidOrBirthCertificate
---burriedDate RecycleDate

SELECT OwnerInfo.serialNo,
OwnerInfo.ownerId,
OwnerInfo.ownerName,
OwnerInfo.ownerPhoneNumber,
OwnerInfo.ownerNidOrBirthCertificate,
OwnerInfo.ownerAddress,
OwnerInfo.SellingStatus,
OwnerInfo.availablility,
Permanent_CorpseInfo.permanentCorpseId,
Permanent_CorpseInfo.permanentGraveId,
Permanent_CorpseInfo.permanentGraveSize,
Permanent_CorpseInfo.RelationWithOwner,
Permanent_CorpseInfo.corpseName,
Permanent_CorpseInfo.corpseGender,
Permanent_CorpseInfo.corpseFatherName,
Permanent_CorpseInfo.corpseMotherName,
Permanent_CorpseInfo.corpseDateOfBirth,
Permanent_CorpseInfo.corpseDateOfDeath,
Permanent_CorpseInfo.corpseTimeOfDeath,
Permanent_CorpseInfo.corpseCauseOfDeath,
Permanent_CorpseInfo.corpseNidOrBirthCertificate,
Permanent_CorpseInfo.burriedDate,
Permanent_CorpseInfo.RecycleDate,
paymentInfo.fee,
paymentInfo.paymentid
FROM paymentInfo  
INNER JOIN OwnerInfo 
ON paymentInfo.generalGraveId = OwnerInfo.generalGraveId
LEFT JOIN Permanent_CorpseInfo
ON Permanent_CorpseInfo.permanentGraveId=OwnerInfo.generalGraveId 
;



BEGIN TRANSACTION
 DELETE FROM  
 Permanent_CorpseInfo 
 WHERE serialNo = 1009 AND permanentCorpseId=1002;
 DELETE FROM paymentInfo 
 WHERE   serialNo = 1009 ;
DELETE FROM    OwnerInfo 
WHERE   serialNo = 1009; 
COMMIT;

---update page e owner er info jate permanent_corpseInfo page eo change hoy 
UPDATE t2 SET t2.ownerId = t1.ownerId,
 t2.availablility = t1.availablility
   FROM Permanent_CorpseInfo  t2
   INNER JOIN OwnerInfo t1 ON          
   t2.serialNo=t1.serialNo 
---update page e permanent_corpseInfo er plot_id jate owner er plot_id 
 UPDATE t2
  SET t2.generalGraveId = t1.permanentGraveId
   FROM OwnerInfo t2
   INNER JOIN  Permanent_CorpseInfo  t1 ON          
   t2.serialNo=t1.serialNo 
 ---update page e payment info er ownerId and plotNo change hobe    
 UPDATE t2 
 SET t2.ownerId = t1.ownerId,
 t2.generalGraveId = t1.generalGraveId 
 FROM paymentInfo t2 
 INNER JOIN OwnerInfo t1 ON t2.serialNo=t1.serialNo 





 ---add page
 UPDATE t1   
 SET t1.availablility = t2.availablility
 FROM Permanent_CorpseInfo t2 
 INNER JOIN OwnerInfo t1 
 ON  t2.availablility = 'Occupied' 
 AND t2.serialNo=t1.serialNo 
 AND t2.RecycleDate >= CAST(CURRENT_TIMESTAMP AS DATE);

 UPDATE t1 
 SET t1.availablility =t2.availablility
 FROM Permanent_CorpseInfo  t2 
 INNER JOIN OwnerInfo t1 
 ON t2.RecycleDate <= CAST(CURRENT_TIMESTAMP AS DATE) 
 AND t2.serialNo=t1.serialNo

UPDATE Permanent_CorpseInfo 
 SET availablility ='empty' 
 WHERE RecycleDate <= CAST(CURRENT_TIMESTAMP AS DATE)


 ----Search by name

 SELECT a.serialNo,
a.ownerId,
a.ownerName,
a.ownerPhoneNumber,
a.ownerNidOrBirthCertificate,
a.ownerAddress,
a.SellingStatus,
a.availablility,
b.permanentCorpseId,
b.permanentGraveId,
b.permanentGraveSize,
b.RelationWithOwner,
b.corpseName,
b.corpseGender,
b.corpseFatherName,
b.corpseMotherName,
b.corpseDateOfBirth,
b.corpseDateOfDeath,
b.corpseTimeOfDeath,
b.corpseCauseOfDeath,
b.corpseNidOrBirthCertificate,
b.burriedDate,
b.RecycleDate,
c.fee,
c.paymentid  
FROM OwnerInfo AS a  
INNER JOIN Permanent_CorpseInfo AS b  
ON b.permanentGraveId=a.generalGraveId 
AND corpseName LIKE '%Mahiha mahi%'
INNER JOIN paymentInfo AS c  
ON c.generalGraveId = a.generalGraveId

---search by permanentGraveId
 SELECT a.serialNo,
a.ownerId,
a.ownerName,
a.ownerPhoneNumber,
a.ownerNidOrBirthCertificate,
a.ownerAddress,
a.SellingStatus,
a.availablility,
b.permanentCorpseId,
b.permanentGraveId,
b.permanentGraveSize,
b.RelationWithOwner,
b.corpseName,
b.corpseGender,
b.corpseFatherName,
b.corpseMotherName,
b.corpseDateOfBirth,
b.corpseDateOfDeath,
b.corpseTimeOfDeath,
b.corpseCauseOfDeath,
b.corpseNidOrBirthCertificate,
b.burriedDate,
b.RecycleDate,
c.fee,
c.paymentid  
FROM OwnerInfo AS a  
INNER JOIN Permanent_CorpseInfo AS b  
ON b.permanentGraveId=a.generalGraveId 
AND permanentGraveId =21
INNER JOIN paymentInfo AS c  
ON c.generalGraveId = a.generalGraveId

---search by Burried Date
 SELECT a.serialNo,
a.ownerId,
a.ownerName,
a.ownerPhoneNumber,
a.ownerNidOrBirthCertificate,
a.ownerAddress,
a.SellingStatus,
a.availablility,
b.permanentCorpseId,
b.permanentGraveId,
b.permanentGraveSize,
b.RelationWithOwner,
b.corpseName,
b.corpseGender,
b.corpseFatherName,
b.corpseMotherName,
b.corpseDateOfBirth,
b.corpseDateOfDeath,
b.corpseTimeOfDeath,
b.corpseCauseOfDeath,
b.corpseNidOrBirthCertificate,
b.burriedDate,
b.RecycleDate,
c.fee,
c.paymentid  
FROM OwnerInfo AS a  
INNER JOIN Permanent_CorpseInfo AS b  
ON b.permanentGraveId=a.generalGraveId 
AND burriedDate ='"+burried_date+"'
INNER JOIN paymentInfo AS c  
ON c.generalGraveId = a.generalGraveId

---search by grave size
 SELECT a.serialNo,
a.ownerId,
a.ownerName,
a.ownerPhoneNumber,
a.ownerNidOrBirthCertificate,
a.ownerAddress,
a.SellingStatus,
a.availablility,
b.permanentCorpseId,
b.permanentGraveId,
b.permanentGraveSize,
b.RelationWithOwner,
b.corpseName,
b.corpseGender,
b.corpseFatherName,
b.corpseMotherName,
b.corpseDateOfBirth,
b.corpseDateOfDeath,
b.corpseTimeOfDeath,
b.corpseCauseOfDeath,
b.corpseNidOrBirthCertificate,
b.burriedDate,
b.RecycleDate,
c.fee,
c.paymentid  
FROM OwnerInfo AS a  
INNER JOIN Permanent_CorpseInfo AS b  
ON b.permanentGraveId=a.generalGraveId 
AND permanentGraveSize ='"+burried_date+"'
INNER JOIN paymentInfo AS c  
ON c.generalGraveId = a.generalGraveId

---search by Owner name
 SELECT a.serialNo,
a.ownerId,
a.ownerName,
a.ownerPhoneNumber,
a.ownerNidOrBirthCertificate,
a.ownerAddress,
a.SellingStatus,
a.availablility,
b.permanentCorpseId,
b.permanentGraveId,
b.permanentGraveSize,
b.RelationWithOwner,
b.corpseName,
b.corpseGender,
b.corpseFatherName,
b.corpseMotherName,
b.corpseDateOfBirth,
b.corpseDateOfDeath,
b.corpseTimeOfDeath,
b.corpseCauseOfDeath,
b.corpseNidOrBirthCertificate,
b.burriedDate,
b.RecycleDate,
c.fee,
c.paymentid  
FROM OwnerInfo AS a  
INNER JOIN Permanent_CorpseInfo AS b  
ON b.permanentGraveId=a.generalGraveId 
AND ownerName LIKE '%Mahiha mahi%'
INNER JOIN paymentInfo AS c  
ON c.generalGraveId = a.generalGraveId

---search by Corpse Gender
 SELECT a.serialNo,
a.ownerId,
a.ownerName,
a.ownerPhoneNumber,
a.ownerNidOrBirthCertificate,
a.ownerAddress,
a.SellingStatus,
a.availablility,
b.permanentCorpseId,
b.permanentGraveId,
b.permanentGraveSize,
b.RelationWithOwner,
b.corpseName,
b.corpseGender,
b.corpseFatherName,
b.corpseMotherName,
b.corpseDateOfBirth,
b.corpseDateOfDeath,
b.corpseTimeOfDeath,
b.corpseCauseOfDeath,
b.corpseNidOrBirthCertificate,
b.burriedDate,
b.RecycleDate,
c.fee,
c.paymentid  
FROM OwnerInfo AS a  
INNER JOIN Permanent_CorpseInfo AS b  
ON b.permanentGraveId=a.generalGraveId 
AND corpseGender ='"+burried_date+"'
INNER JOIN paymentInfo AS c  
ON c.generalGraveId = a.generalGraveId
---search by availability
   SELECT a.serialNo,
a.ownerId,
a.ownerName,
a.ownerPhoneNumber,
a.ownerNidOrBirthCertificate,
a.ownerAddress,
a.SellingStatus,
a.availablility,
b.permanentCorpseId,
b.permanentGraveId,
b.permanentGraveSize,
b.RelationWithOwner,
b.corpseName,
b.corpseGender,
b.corpseFatherName,
b.corpseMotherName,
b.corpseDateOfBirth,
b.corpseDateOfDeath,
b.corpseTimeOfDeath,
b.corpseCauseOfDeath,
b.corpseNidOrBirthCertificate,
b.burriedDate,
b.RecycleDate,
c.fee,
c.paymentid  
FROM OwnerInfo AS a  
INNER JOIN Permanent_CorpseInfo AS b  
ON b.permanentGraveId=a.generalGraveId 
AND a.availablility ='empty'
INNER JOIN paymentInfo AS c  
ON c.generalGraveId = a.generalGraveId

---search by relation
 SELECT a.serialNo,
a.ownerId,
a.ownerName,
a.ownerPhoneNumber,
a.ownerNidOrBirthCertificate,
a.ownerAddress,
a.SellingStatus,
a.availablility,
b.permanentCorpseId,
b.permanentGraveId,
b.permanentGraveSize,
b.RelationWithOwner,
b.corpseName,
b.corpseGender,
b.corpseFatherName,
b.corpseMotherName,
b.corpseDateOfBirth,
b.corpseDateOfDeath,
b.corpseTimeOfDeath,
b.corpseCauseOfDeath,
b.corpseNidOrBirthCertificate,
b.burriedDate,
b.RecycleDate,
c.fee,
c.paymentid  
FROM OwnerInfo AS a  
INNER JOIN Permanent_CorpseInfo AS b  
ON b.permanentGraveId=a.generalGraveId 
AND b.RelationWithOwner ='Father'
INNER JOIN paymentInfo AS c  
ON c.generalGraveId = a.generalGraveId
---search by Cause of Death

 SELECT a.serialNo,
a.ownerId,
a.ownerName,
a.ownerPhoneNumber,
a.ownerNidOrBirthCertificate,
a.ownerAddress,
a.SellingStatus,
a.availablility,
b.permanentCorpseId,
b.permanentGraveId,
b.permanentGraveSize,
b.RelationWithOwner,
b.corpseName,
b.corpseGender,
b.corpseFatherName,
b.corpseMotherName,
b.corpseDateOfBirth,
b.corpseDateOfDeath,
b.corpseTimeOfDeath,
b.corpseCauseOfDeath,
b.corpseNidOrBirthCertificate,
b.burriedDate,
b.RecycleDate,
c.fee,
c.paymentid  
FROM OwnerInfo AS a  
INNER JOIN Permanent_CorpseInfo AS b  
ON b.permanentGraveId=a.generalGraveId 
AND b.corpseCauseOfDeath ='Disease'
INNER JOIN paymentInfo AS c  
ON c.generalGraveId = a.generalGraveId

---search by owner Id


 SELECT a.serialNo,
a.ownerId,
a.ownerName,
a.ownerPhoneNumber,
a.ownerNidOrBirthCertificate,
a.ownerAddress,
a.SellingStatus,
a.availablility,
b.permanentCorpseId,
b.permanentGraveId,
b.permanentGraveSize,
b.RelationWithOwner,
b.corpseName,
b.corpseGender,
b.corpseFatherName,
b.corpseMotherName,
b.corpseDateOfBirth,
b.corpseDateOfDeath,
b.corpseTimeOfDeath,
b.corpseCauseOfDeath,
b.corpseNidOrBirthCertificate,
b.burriedDate,
b.RecycleDate,
c.fee,
c.paymentid  
FROM OwnerInfo AS a  
INNER JOIN Permanent_CorpseInfo AS b  
ON b.permanentGraveId=a.generalGraveId 
AND b.ownerId =1
INNER JOIN paymentInfo AS c  
ON c.generalGraveId = a.generalGraveId
 ---search by burried date

 SELECT a.serialNo,
a.ownerId,
a.ownerName,
a.ownerPhoneNumber,
a.ownerNidOrBirthCertificate,
a.ownerAddress,
a.SellingStatus,
a.availablility,
b.permanentCorpseId,
b.permanentGraveId,
b.permanentGraveSize,
b.RelationWithOwner,
b.corpseName,
b.corpseGender,
b.corpseFatherName,
b.corpseMotherName,
b.corpseDateOfBirth,
b.corpseDateOfDeath,
b.corpseTimeOfDeath,
b.corpseCauseOfDeath,
b.corpseNidOrBirthCertificate,
b.burriedDate,
b.RecycleDate,
c.fee,
c.paymentid  
FROM OwnerInfo AS a  
INNER JOIN Permanent_CorpseInfo AS b  
ON b.permanentGraveId=a.generalGraveId 
AND b.burriedDate ='19-05-2020'
INNER JOIN paymentInfo AS c  
ON c.generalGraveId = a.generalGraveId

---making bar chart query ekdine e joto gula kobor deya hoise
select burried,sum(t.NumberOfPerson) as NumberOfPerson from  
 ((SELECT burriedDate as burried,COUNT(burriedDate) AS NumberOfPerson FROM Permanent_CorpseInfo GROUP BY BurriedDate)
 union all
(SELECT burriedDate as burried,COUNT(burriedDate) AS NumberOfPerson FROM GeneralGrave1 GROUP BY burriedDate)) t
 GROUP BY burried
---joto gula manush eki cause e mara geche
select cause,sum(t.NumberOfPerson) as NumberOfPerson from  
 ((SELECT corpseCauseOfDeath as cause ,COUNT (corpseCauseOfDeath) AS NumberOfPerson 
 FROM Permanent_CorpseInfo GROUP BY corpseCauseOfDeath )
 union all
(SELECT corpseCauseOfDeath as cause,COUNT (corpseCauseOfDeath) AS NumberOfPerson
 FROM GeneralGrave1 GROUP BY corpseCauseOfDeath)) t
 GROUP BY cause
---same gender er joto jon mara geche
select gender,sum(t.NumberOfPerson) as NumberOfPerson from  
((SELECT corpseGender as gender,COUNT (corpseGender) AS NumberOfPerson FROM Permanent_CorpseInfo GROUP BY corpseGender)
 union all
(SELECT corpseGender as gender,COUNT (corpseGender) AS NumberOfPerson FROM GeneralGrave1 GROUP BY corpseGender)) t
 GROUP BY gender

---same size e joto jon ke kobor deya hoyeche
select graveSize,sum(t.NumberOfPerson) as NumberOfPerson from  ((SELECT permanentGraveSize as graveSize ,COUNT (permanentGraveSize) AS NumberOfPerson
 FROM Permanent_CorpseInfo GROUP BY permanentGraveSize)
 union all
(SELECT generalGraveSize as graveSize ,COUNT (generalGraveSize) AS NumberOfPerson
 FROM GeneralGrave1 GROUP BY generalGraveSize )) t
 GROUP BY graveSize
---ekjon owner joto gulo plot kineche
SELECT ownerId,COUNT (ownerId) AS NumberOfPerson FROM OwnerInfo GROUP BY ownerId ORDER BY ownerId;
SELECT ownerId ,ownerName,COUNT (ownerId) AS NumberOfPerson FROM OwnerInfo GROUP BY ownerId,ownerName ORDER BY ownerId;
--ekta plot e koyjon ke kobor deya hoyeche (generalgrave ar permanentgrave miliy)
(SELECT permanentGraveId,COUNT (permanentGraveId) AS NumberOfPerson
  FROM Permanent_CorpseInfo GROUP BY permanentGraveId )
  UNION ALL
  (SELECT generalGraveId,COUNT (generalGraveId) AS NumberOfPerson
  FROM GeneralGrave1 GROUP BY generalGraveId)
  ---sudhu permanent e
SELECT permanentGraveId,COUNT (permanentGraveId) AS NumberOfPerson FROM Permanent_CorpseInfo GROUP BY permanentGraveId ORDER BY max(permanentGraveId);


---ek year e joto jon kobor deya hoyeche
 select Year,sum(t.NumberOfPerson) as NumberOfPerson from  
 ((SELECT DATEPART(YEAR,burriedDate) As Year ,COUNT(DATEPART(YEAR,burriedDate)) AS NumberOfPerson FROM Permanent_CorpseInfo GROUP BY DATEPART(YEAR,burriedDate))
 union all
(SELECT DATEPART(YEAR,burriedDate) As Year ,COUNT(DATEPART(YEAR,burriedDate)) AS NumberOfPerson FROM GeneralGrave1 GROUP BY DATEPART(YEAR,burriedDate))) t
 GROUP BY Year


 /*Select plot,sum(t.NumberOfPerson) as NumberOfPerson from((SELECT permanentGraveId as plot,COUNT (permanentGraveId) AS NumberOfPerson
  FROM Permanent_CorpseInfo GROUP BY permanentGraveId )
  UNION ALL
  (SELECT generalGraveId as plot,COUNT (generalGraveId) AS NumberOfPerson
  FROM GeneralGrave1 GROUP BY generalGraveId)) t  
  GROUP BY plot ORDER BY max(NumberOfPerson);*/
  select serialNo from OwnerInfo where ownerId=10
---payment er serial no bosano
  select o.serialNo
   from OwnerInfo o inner join paymentInfo p
   on o.serialNo=p.serialNo and o.ownerId=8 and p.fee is null

---permanent_coprse info er value bosabo plot no e
select generalGraveId from OwnerInfo where ownerId=11 and availablility='empty'
---permanet_coprse info er value bosabo purchaseId te
select serialNo from OwnerInfo where generalGraveId=24 and availablility='empty'
---payment e value asbe purchase id theke plot id te
select generalGraveId from OwnerInfo where serialNo=1 and availablility='empty'
  



/*SELECT p.permanentGraveSize,COUNT (p.permanentGraveSize)+COUNT(g.generalGraveSize) as NumberOfPeople
FROM Permanent_CorpseInfo p,GeneralGrave1 g
GROUP BY p.permanentGraveSize,g.generalGraveSize
having p.permanentGraveSize=g.generalGraveSize
ORDER BY p.permanentGraveSize;

SELECT p.permanentGraveSize ,COUNT(p.permanentGraveSize +(select g.generalGraveSize from GeneralGrave1 g where p.permanentGraveSize=g.generalGraveSize)) as NumberOfPeople
FROM Permanent_CorpseInfo p
GROUP BY p.permanentGraveSize
ORDER BY p.permanentGraveSize;*/





---Union
(select 
generalGraveId as plotNo,
generalGraveSize as plotSize,
generalCorpseId as corpseId,
corpseType as type,
corpseName as name,
corpseGender as gender,
corpseFatherName as father,
corpseMotherName as mother,
corpseDateOfBirth as dob,
corpseDateOfDeath as dod,
corpseTimeOfDeath as tod,
corpseCauseOfDeath as cod,
guardianContactNo as phn,
guardianRelation as relation,
guardianAddress as address,
guardianName as relativeName,
corpseNidNo as nid,
burriedDate as burried,
RecycleDate as recylce,
fee as fee
 from GeneralGrave1)
UNION all
(SELECT
Permanent_CorpseInfo.permanentGraveId as plotNo,
Permanent_CorpseInfo.permanentGraveSize as plotSize,
Permanent_CorpseInfo.permanentCorpseId as corpseId,
Permanent_CorpseInfo.corpseType as type,
Permanent_CorpseInfo.corpseName as name,
Permanent_CorpseInfo.corpseGender as gender,
Permanent_CorpseInfo.corpseFatherName as father,
Permanent_CorpseInfo.corpseMotherName as mother,
Permanent_CorpseInfo.corpseDateOfBirth as dob,
Permanent_CorpseInfo.corpseDateOfDeath as dod,
Permanent_CorpseInfo.corpseTimeOfDeath as tod,
Permanent_CorpseInfo.corpseCauseOfDeath as cod,
OwnerInfo.ownerPhoneNumber as phn,
Permanent_CorpseInfo.RelationWithOwner as relation,
OwnerInfo.ownerAddress as address,
OwnerInfo.ownerName relativeName,
Permanent_CorpseInfo.corpseNidOrBirthCertificate as nid,
Permanent_CorpseInfo.burriedDate as burried,
Permanent_CorpseInfo.RecycleDate as recylce,
paymentInfo.fee as fee
FROM OwnerInfo
INNER JOIN  Permanent_CorpseInfo
ON Permanent_CorpseInfo.permanentGraveId=OwnerInfo.generalGraveId
INNER JOIN paymentInfo
ON OwnerInfo.generalGraveId=paymentInfo.generalGraveId
 )





select * from Permanent_CorpseInfo


---owner receipt
select  
o.serialNo,
o.ownerId, 
o.ownerName,
o.ownerPhoneNumber,
o.ownerNidOrBirthCertificate ,
o.ownerAddress ,
o.SellingStatus, 
o.generalGraveId ,
o.availablility, 
p.paymentid ,
p.fee 
from OwnerInfo o  inner join paymentInfo p
on o.serialNo=p.serialNo and o.ownerId=4;


select * from OwnerInfo 

select * from Permanent_CorpseInfo;

UPDATE Permanent_CorpseInfo SET permanentGraveId=2001 where ownerId=1




---counting available grave for general and permanent 
SELECT SUM(t.available) from ((SELECT(2000-COUNT(DISTINCT generalGraveId)) as available FROM GeneralGrave1 where RecycleDate>GETDATE())
union all
(SELECT(2000-COUNT(DISTINCT permanentCorpseId)) as available FROM Permanent_CorpseInfo where RecycleDate>GETDATE())) t


SELECT(2000-COUNT(DISTINCT generalGraveId)) as available FROM OwnerInfo;


SELECT generalGraveId,Max(recycleDate) AS recycleDate FROM GeneralGrave1 where recycleDate>GETDATE() group by generalGraveId;
SELECT generalGraveId FROM OwnerInfo order by generalGraveId



select 
   o.serialNo,
   o.ownerId, 
   o.ownerName,
   o.ownerPhoneNumber,
   o.ownerNidOrBirthCertificate ,
   o.ownerAddress ,
   o.SellingStatus, 
   o.generalGraveId ,
   o.availablility, 
   p.paymentid ,
   p.fee
   from OwnerInfo o  inner join paymentInfo p
   on o.serialNo=p.serialNo and o.ownerId=12





   All Complex query



-------------------------------------------------------------------------------------------------------------------------------------------------------------
---1.updating permanent_CorpseInfo,ownerInfo and paymentInfo Table useing Inner join

BEGIN TRANSACTION
update A
set A.ownerId =  2,
A.ownerName='tonima Islam',
A.ownerPhoneNumber=01840753284,
A.ownerNidOrBirthCertificate='147852369',
A.ownerAddress='mohakhali dhaka',
A.SellingStatus='sold',
A.availablility='empty'
from OwnerInfo A inner join paymentInfo B
on B.serialNo = A.serialNo
and A.serialNo = 2
update B
set B.fee = 5000
from paymentInfo B inner join OwnerInfo A
    on B.serialNo = A.serialNo
    and A.serialNo = 2
update C
set C.permanentGraveId = 1000,
C.permanentGraveSize='SMALL',
C.RelationWithOwner='FATHER',
C.corpseName='Mukta Akter',
C.corpseGender='female',
C.corpseFatherName='Anisul Islam',
C.corpseMotherName='Munia Akter',
C.corpseDateOfBirth='2013-02-12',
C.corpseDateOfDeath='2013-02-12',
C.corpseTimeOfDeath='03.49',
C.corpseCauseOfDeath='Natural',
C.corpseNidOrBirthCertificate='123741852',
C.burriedDate='2013-02-12',
C.RecycleDate='2013-02-12'
from Permanent_CorpseInfo C inner join OwnerInfo A
    on C.serialNo = A.serialNo
    and C.serialNo = 2
	and C.permanentCorpseId=3
COMMIT
-------------------------------------------------------------------------------------------------------------------------------------------------------------
---2.Selecting permanent_CorpseInfo,ownerInfo and paymentInfo Table

SELECT OwnerInfo.serialNo,
OwnerInfo.ownerId,
OwnerInfo.ownerName,
OwnerInfo.ownerPhoneNumber,
OwnerInfo.ownerNidOrBirthCertificate,
OwnerInfo.ownerAddress,
OwnerInfo.SellingStatus,
OwnerInfo.availablility,
Permanent_CorpseInfo.permanentCorpseId,
Permanent_CorpseInfo.permanentGraveId,
Permanent_CorpseInfo.permanentGraveSize,
Permanent_CorpseInfo.RelationWithOwner,
Permanent_CorpseInfo.corpseName,
Permanent_CorpseInfo.corpseGender,
Permanent_CorpseInfo.corpseFatherName,
Permanent_CorpseInfo.corpseMotherName,
Permanent_CorpseInfo.corpseDateOfBirth,
Permanent_CorpseInfo.corpseDateOfDeath,
Permanent_CorpseInfo.corpseTimeOfDeath,
Permanent_CorpseInfo.corpseCauseOfDeath,
Permanent_CorpseInfo.corpseNidOrBirthCertificate,
Permanent_CorpseInfo.burriedDate,
Permanent_CorpseInfo.RecycleDate,
paymentInfo.fee,
paymentInfo.paymentid
FROM paymentInfo  
INNER JOIN OwnerInfo 
ON paymentInfo.generalGraveId = OwnerInfo.generalGraveId
LEFT JOIN Permanent_CorpseInfo
ON Permanent_CorpseInfo.permanentGraveId=OwnerInfo.generalGraveId 
;

-------------------------------------------------------------------------------------------------------------------------------------------------------------
---3.Deleting the row of permanent_CorpseInfo,ownerInfo and paymentInfo Table

BEGIN TRANSACTION
 DELETE FROM  
 Permanent_CorpseInfo 
 WHERE serialNo = 1009 AND permanentCorpseId=1002;
 DELETE FROM paymentInfo 
 WHERE   serialNo = 1009 ;
DELETE FROM    OwnerInfo 
WHERE   serialNo = 1009; 
COMMIT;

-------------------------------------------------------------------------------------------------------------------------------------------------------------
---4.Search by name

SELECT a.serialNo,
a.ownerId,
a.ownerName,
a.ownerPhoneNumber,
a.ownerNidOrBirthCertificate,
a.ownerAddress,
a.SellingStatus,
a.availablility,
b.permanentCorpseId,
b.permanentGraveId,
b.permanentGraveSize,
b.RelationWithOwner,
b.corpseName,
b.corpseGender,
b.corpseFatherName,
b.corpseMotherName,
b.corpseDateOfBirth,
b.corpseDateOfDeath,
b.corpseTimeOfDeath,
b.corpseCauseOfDeath,
b.corpseNidOrBirthCertificate,
b.burriedDate,
b.RecycleDate,
c.fee,
c.paymentid  
FROM OwnerInfo AS a  
INNER JOIN Permanent_CorpseInfo AS b  
ON b.permanentGraveId=a.generalGraveId 
AND corpseName LIKE '%Mahiha mahi%'
INNER JOIN paymentInfo AS c  
ON c.generalGraveId = a.generalGraveId
-------------------------------------------------------------------------------------------------------------------------------------------------------------
---5.search by permanentGraveId

 SELECT a.serialNo,
a.ownerId,
a.ownerName,
a.ownerPhoneNumber,
a.ownerNidOrBirthCertificate,
a.ownerAddress,
a.SellingStatus,
a.availablility,
b.permanentCorpseId,
b.permanentGraveId,
b.permanentGraveSize,
b.RelationWithOwner,
b.corpseName,
b.corpseGender,
b.corpseFatherName,
b.corpseMotherName,
b.corpseDateOfBirth,
b.corpseDateOfDeath,
b.corpseTimeOfDeath,
b.corpseCauseOfDeath,
b.corpseNidOrBirthCertificate,
b.burriedDate,
b.RecycleDate,
c.fee,
c.paymentid  
FROM OwnerInfo AS a  
INNER JOIN Permanent_CorpseInfo AS b  
ON b.permanentGraveId=a.generalGraveId 
AND permanentGraveId =21
INNER JOIN paymentInfo AS c  
ON c.generalGraveId = a.generalGraveId
-------------------------------------------------------------------------------------------------------------------------------------------------------------
---6.search by grave size

 SELECT a.serialNo,
a.ownerId,
a.ownerName,
a.ownerPhoneNumber,
a.ownerNidOrBirthCertificate,
a.ownerAddress,
a.SellingStatus,
a.availablility,
b.permanentCorpseId,
b.permanentGraveId,
b.permanentGraveSize,
b.RelationWithOwner,
b.corpseName,
b.corpseGender,
b.corpseFatherName,
b.corpseMotherName,
b.corpseDateOfBirth,
b.corpseDateOfDeath,
b.corpseTimeOfDeath,
b.corpseCauseOfDeath,
b.corpseNidOrBirthCertificate,
b.burriedDate,
b.RecycleDate,
c.fee,
c.paymentid  
FROM OwnerInfo AS a  
INNER JOIN Permanent_CorpseInfo AS b  
ON b.permanentGraveId=a.generalGraveId 
AND permanentGraveSize ='"+burried_date+"'
INNER JOIN paymentInfo AS c  
ON c.generalGraveId = a.generalGraveId
-------------------------------------------------------------------------------------------------------------------------------------------------------------
---7.search by Owner name

 SELECT a.serialNo,
a.ownerId,
a.ownerName,
a.ownerPhoneNumber,
a.ownerNidOrBirthCertificate,
a.ownerAddress,
a.SellingStatus,
a.availablility,
b.permanentCorpseId,
b.permanentGraveId,
b.permanentGraveSize,
b.RelationWithOwner,
b.corpseName,
b.corpseGender,
b.corpseFatherName,
b.corpseMotherName,
b.corpseDateOfBirth,
b.corpseDateOfDeath,
b.corpseTimeOfDeath,
b.corpseCauseOfDeath,
b.corpseNidOrBirthCertificate,
b.burriedDate,
b.RecycleDate,
c.fee,
c.paymentid  
FROM OwnerInfo AS a  
INNER JOIN Permanent_CorpseInfo AS b  
ON b.permanentGraveId=a.generalGraveId 
AND ownerName LIKE '%Mahiha mahi%'
INNER JOIN paymentInfo AS c  
ON c.generalGraveId = a.generalGraveId

-------------------------------------------------------------------------------------------------------------------------------------------------------------
---8.search by Corpse Gender

 SELECT a.serialNo,
a.ownerId,
a.ownerName,
a.ownerPhoneNumber,
a.ownerNidOrBirthCertificate,
a.ownerAddress,
a.SellingStatus,
a.availablility,
b.permanentCorpseId,
b.permanentGraveId,
b.permanentGraveSize,
b.RelationWithOwner,
b.corpseName,
b.corpseGender,
b.corpseFatherName,
b.corpseMotherName,
b.corpseDateOfBirth,
b.corpseDateOfDeath,
b.corpseTimeOfDeath,
b.corpseCauseOfDeath,
b.corpseNidOrBirthCertificate,
b.burriedDate,
b.RecycleDate,
c.fee,
c.paymentid  
FROM OwnerInfo AS a  
INNER JOIN Permanent_CorpseInfo AS b  
ON b.permanentGraveId=a.generalGraveId 
AND corpseGender ='"+burried_date+"'
INNER JOIN paymentInfo AS c  
ON c.generalGraveId = a.generalGraveId
-------------------------------------------------------------------------------------------------------------------------------------------------------------
---9.search by availability

   SELECT a.serialNo,
a.ownerId,
a.ownerName,
a.ownerPhoneNumber,
a.ownerNidOrBirthCertificate,
a.ownerAddress,
a.SellingStatus,
a.availablility,
b.permanentCorpseId,
b.permanentGraveId,
b.permanentGraveSize,
b.RelationWithOwner,
b.corpseName,
b.corpseGender,
b.corpseFatherName,
b.corpseMotherName,
b.corpseDateOfBirth,
b.corpseDateOfDeath,
b.corpseTimeOfDeath,
b.corpseCauseOfDeath,
b.corpseNidOrBirthCertificate,
b.burriedDate,
b.RecycleDate,
c.fee,
c.paymentid  
FROM OwnerInfo AS a  
INNER JOIN Permanent_CorpseInfo AS b  
ON b.permanentGraveId=a.generalGraveId 
AND a.availablility ='empty'
INNER JOIN paymentInfo AS c  
ON c.generalGraveId = a.generalGraveId

-------------------------------------------------------------------------------------------------------------------------------------------------------------
---10.search by relation

 SELECT a.serialNo,
a.ownerId,
a.ownerName,
a.ownerPhoneNumber,
a.ownerNidOrBirthCertificate,
a.ownerAddress,
a.SellingStatus,
a.availablility,
b.permanentCorpseId,
b.permanentGraveId,
b.permanentGraveSize,
b.RelationWithOwner,
b.corpseName,
b.corpseGender,
b.corpseFatherName,
b.corpseMotherName,
b.corpseDateOfBirth,
b.corpseDateOfDeath,
b.corpseTimeOfDeath,
b.corpseCauseOfDeath,
b.corpseNidOrBirthCertificate,
b.burriedDate,
b.RecycleDate,
c.fee,
c.paymentid  
FROM OwnerInfo AS a  
INNER JOIN Permanent_CorpseInfo AS b  
ON b.permanentGraveId=a.generalGraveId 
AND b.RelationWithOwner ='Father'
INNER JOIN paymentInfo AS c  
ON c.generalGraveId = a.generalGraveId
-------------------------------------------------------------------------------------------------------------------------------------------------------------
---11.search by Cause of Death

 SELECT a.serialNo,
a.ownerId,
a.ownerName,
a.ownerPhoneNumber,
a.ownerNidOrBirthCertificate,
a.ownerAddress,
a.SellingStatus,
a.availablility,
b.permanentCorpseId,
b.permanentGraveId,
b.permanentGraveSize,
b.RelationWithOwner,
b.corpseName,
b.corpseGender,
b.corpseFatherName,
b.corpseMotherName,
b.corpseDateOfBirth,
b.corpseDateOfDeath,
b.corpseTimeOfDeath,
b.corpseCauseOfDeath,
b.corpseNidOrBirthCertificate,
b.burriedDate,
b.RecycleDate,
c.fee,
c.paymentid  
FROM OwnerInfo AS a  
INNER JOIN Permanent_CorpseInfo AS b  
ON b.permanentGraveId=a.generalGraveId 
AND b.corpseCauseOfDeath ='Natural'
INNER JOIN paymentInfo AS c  
ON c.generalGraveId = a.generalGraveId
-------------------------------------------------------------------------------------------------------------------------------------------------------------
---12.search by owner Id

 SELECT a.serialNo,
a.ownerId,
a.ownerName,
a.ownerPhoneNumber,
a.ownerNidOrBirthCertificate,
a.ownerAddress,
a.SellingStatus,
a.availablility,
b.permanentCorpseId,
b.permanentGraveId,
b.permanentGraveSize,
b.RelationWithOwner,
b.corpseName,
b.corpseGender,
b.corpseFatherName,
b.corpseMotherName,
b.corpseDateOfBirth,
b.corpseDateOfDeath,
b.corpseTimeOfDeath,
b.corpseCauseOfDeath,
b.corpseNidOrBirthCertificate,
b.burriedDate,
b.RecycleDate,
c.fee,
c.paymentid  
FROM OwnerInfo AS a  
INNER JOIN Permanent_CorpseInfo AS b  
ON b.permanentGraveId=a.generalGraveId 
AND b.ownerId =1
INNER JOIN paymentInfo AS c  
ON c.generalGraveId = a.generalGraveId
-------------------------------------------------------------------------------------------------------------------------------------------------------------
---13.search by burried date

 SELECT a.serialNo,
a.ownerId,
a.ownerName,
a.ownerPhoneNumber,
a.ownerNidOrBirthCertificate,
a.ownerAddress,
a.SellingStatus,
a.availablility,
b.permanentCorpseId,
b.permanentGraveId,
b.permanentGraveSize,
b.RelationWithOwner,
b.corpseName,
b.corpseGender,
b.corpseFatherName,
b.corpseMotherName,
b.corpseDateOfBirth,
b.corpseDateOfDeath,
b.corpseTimeOfDeath,
b.corpseCauseOfDeath,
b.corpseNidOrBirthCertificate,
b.burriedDate,
b.RecycleDate,
c.fee,
c.paymentid  
FROM OwnerInfo AS a  
INNER JOIN Permanent_CorpseInfo AS b  
ON b.permanentGraveId=a.generalGraveId 
AND b.burriedDate ='19-05-2020'
INNER JOIN paymentInfo AS c  
ON c.generalGraveId = a.generalGraveId
-------------------------------------------------------------------------------------------------------------------------------------------------------------
---14.Number of people burring in one day 

select burried,sum(t.NumberOfPerson) as NumberOfPerson from  
 ((SELECT burriedDate as burried,COUNT(burriedDate) AS NumberOfPerson FROM Permanent_CorpseInfo GROUP BY BurriedDate)
 union all
(SELECT burriedDate as burried,COUNT(burriedDate) AS NumberOfPerson FROM GeneralGrave1 GROUP BY burriedDate)) t
 GROUP BY burried

-------------------------------------------------------------------------------------------------------------------------------------------------------------
---15.Number of people died in same cause

select cause,sum(t.NumberOfPerson) as NumberOfPerson from  
 ((SELECT corpseCauseOfDeath as cause ,COUNT (corpseCauseOfDeath) AS NumberOfPerson 
 FROM Permanent_CorpseInfo GROUP BY corpseCauseOfDeath )
 union all
(SELECT corpseCauseOfDeath as cause,COUNT (corpseCauseOfDeath) AS NumberOfPerson
 FROM GeneralGrave1 GROUP BY corpseCauseOfDeath)) t
 GROUP BY cause

-------------------------------------------------------------------------------------------------------------------------------------------------------------
---16.Number of people died in same gender

select gender,sum(t.NumberOfPerson) as NumberOfPerson from  
((SELECT corpseGender as gender,COUNT (corpseGender) AS NumberOfPerson FROM Permanent_CorpseInfo GROUP BY corpseGender)
 union all
(SELECT corpseGender as gender,COUNT (corpseGender) AS NumberOfPerson FROM GeneralGrave1 GROUP BY corpseGender)) t
 GROUP BY gender

-------------------------------------------------------------------------------------------------------------------------------------------------------------
---17.number of people burried in same grave size

select graveSize,sum(t.NumberOfPerson) as NumberOfPerson from  ((SELECT permanentGraveSize as graveSize ,COUNT (permanentGraveSize) AS NumberOfPerson
 FROM Permanent_CorpseInfo GROUP BY permanentGraveSize)
 union all
(SELECT generalGraveSize as graveSize ,COUNT (generalGraveSize) AS NumberOfPerson
 FROM GeneralGrave1 GROUP BY generalGraveSize )) t
 GROUP BY graveSize
-------------------------------------------------------------------------------------------------------------------------------------------------------------
---18.number of plot bought by one owner

SELECT ownerId,COUNT (ownerId) AS NumberOfPerson FROM OwnerInfo GROUP BY ownerId ORDER BY ownerId;
SELECT ownerId ,ownerName,COUNT (ownerId) AS NumberOfPerson FROM OwnerInfo GROUP BY ownerId,ownerName ORDER BY ownerId;

-------------------------------------------------------------------------------------------------------------------------------------------------------------
---19.Number of people burried in same plot

(SELECT permanentGraveId,COUNT (permanentGraveId) AS NumberOfPerson
  FROM Permanent_CorpseInfo GROUP BY permanentGraveId )
  UNION ALL
  (SELECT generalGraveId,COUNT (generalGraveId) AS NumberOfPerson
  FROM GeneralGrave1 GROUP BY generalGraveId)
 
-------------------------------------------------------------------------------------------------------------------------------------------------------------
---20.Number of people died in one year
 select Year,sum(t.NumberOfPerson) as NumberOfPerson from  
 ((SELECT DATEPART(YEAR,burriedDate) As Year ,COUNT(DATEPART(YEAR,burriedDate)) AS NumberOfPerson FROM Permanent_CorpseInfo GROUP BY DATEPART(YEAR,burriedDate))
 union all
(SELECT DATEPART(YEAR,burriedDate) As Year ,COUNT(DATEPART(YEAR,burriedDate)) AS NumberOfPerson FROM GeneralGrave1 GROUP BY DATEPART(YEAR,burriedDate))) t
 GROUP BY Year

-------------------------------------------------------------------------------------------------------------------------------------------------------------
---21.Showing the Whole Database
(select 
generalGraveId as plotNo,
generalGraveSize as plotSize,
generalCorpseId as corpseId,
corpseType as type,
corpseName as name,
corpseGender as gender,
corpseFatherName as father,
corpseMotherName as mother,
corpseDateOfBirth as dob,
corpseDateOfDeath as dod,
corpseTimeOfDeath as tod,
corpseCauseOfDeath as cod,
guardianContactNo as phn,
guardianRelation as relation,
guardianAddress as address,
guardianName as relativeName,
corpseNidNo as nid,
burriedDate as burried,
RecycleDate as recylce,
fee as fee
 from GeneralGrave1)
UNION all
(SELECT
Permanent_CorpseInfo.permanentGraveId as plotNo,
Permanent_CorpseInfo.permanentGraveSize as plotSize,
Permanent_CorpseInfo.permanentCorpseId as corpseId,
Permanent_CorpseInfo.corpseType as type,
Permanent_CorpseInfo.corpseName as name,
Permanent_CorpseInfo.corpseGender as gender,
Permanent_CorpseInfo.corpseFatherName as father,
Permanent_CorpseInfo.corpseMotherName as mother,
Permanent_CorpseInfo.corpseDateOfBirth as dob,
Permanent_CorpseInfo.corpseDateOfDeath as dod,
Permanent_CorpseInfo.corpseTimeOfDeath as tod,
Permanent_CorpseInfo.corpseCauseOfDeath as cod,
OwnerInfo.ownerPhoneNumber as phn,
Permanent_CorpseInfo.RelationWithOwner as relation,
OwnerInfo.ownerAddress as address,
OwnerInfo.ownerName relativeName,
Permanent_CorpseInfo.corpseNidOrBirthCertificate as nid,
Permanent_CorpseInfo.burriedDate as burried,
Permanent_CorpseInfo.RecycleDate as recylce,
paymentInfo.fee as fee
FROM OwnerInfo
INNER JOIN  Permanent_CorpseInfo
ON Permanent_CorpseInfo.permanentGraveId=OwnerInfo.generalGraveId
INNER JOIN paymentInfo
ON OwnerInfo.generalGraveId=paymentInfo.generalGraveId
 )


-------------------------------------------------------------------------------------------------------------------------------------------------------------
---22.Showing the owner info and payment info in the receipt

select  
o.serialNo,
o.ownerId, 
o.ownerName,
o.ownerPhoneNumber,
o.ownerNidOrBirthCertificate ,
o.ownerAddress ,
o.SellingStatus, 
o.generalGraveId ,
o.availablility, 
p.paymentid ,
p.fee 
from OwnerInfo o  inner join paymentInfo p
on o.serialNo=p.serialNo and o.ownerId=2;

-------------------------------------------------------------------------------------------------------------------------------------------------------------
---23.if the availability of permanent_corpseInfo is occupied then the availability of OwnerInfo will be Occupied

   UPDATE t1
   SET t1.availablility = t2.availablility
   FROM Permanent_CorpseInfo  t2
   INNER JOIN OwnerInfo t1 ON           
   t2.availablility = 'Occupied' AND t2.serialNo=t1.serialNo AND t2.RecycleDate >= CAST(CURRENT_TIMESTAMP AS DATE)
-------------------------------------------------------------------------------------------------------------------------------------------------------------
---24.the availability of ownerInfo table will be empty if the recycle date is over

UPDATE t1 SET t1.availablility ='empty' 
FROM Permanent_CorpseInfo  t2 
INNER JOIN OwnerInfo t1
 ON t2.RecycleDate <= CAST(CURRENT_TIMESTAMP AS DATE) AND t2.serialNo=t1.serialNo
-------------------------------------------------------------------------------------------------------------------------------------------------------------
---25.the availability of Permanent_corpseInfo table will be empty if recycle date is over  

UPDATE Permanent_CorpseInfo 
SET availablility ='empty' 
WHERE RecycleDate <= CAST(CURRENT_TIMESTAMP AS DATE);

-------------------------------------------------------------------------------------------------------------------------------------------------------------
---26.When update occurs the ownerId and availability will be change in permanent_CorpseInfo table

UPDATE t2 SET t2.ownerId = t1.ownerId,
 t2.availablility = t1.availablility
   FROM Permanent_CorpseInfo  t2
   INNER JOIN OwnerInfo t1 ON          
   t2.serialNo=t1.serialNo 


-------------------------------------------------------------------------------------------------------------------------------------------------------------
---27.When update occurs the Plot No will be changed in ownerInfo table

 UPDATE t2
  SET t2.generalGraveId = t1.permanentGraveId
   FROM OwnerInfo t2
   INNER JOIN  Permanent_CorpseInfo  t1 ON          
   t2.serialNo=t1.serialNo 
 
-------------------------------------------------------------------------------------------------------------------------------------------------------------
---28.When update occurs the the ownerId and PlotNo will be change in PaymentInfo table

 UPDATE t2 
 SET t2.ownerId = t1.ownerId,
 t2.generalGraveId = t1.generalGraveId 
 FROM paymentInfo t2 
 INNER JOIN OwnerInfo t1 ON t2.serialNo=t1.serialNo
 
 ------------------------------------------------------------------------------------------------------------------------------------------------------------- 
 ---29.To show no of available plots

 select SUM(t.available) from ((SELECT(2000-COUNT(DISTINCT generalGraveId)) as available 
 FROM GeneralGrave1 
 where RecycleDate>GETDATE()) 
 union all
 (SELECT(2000-COUNT(DISTINCT permanentCorpseId))
  as available FROM Permanent_CorpseInfo where RecycleDate>GETDATE())) t

  -------------------------------------------------------------------------------------------------------------------------------------------------------------
  ---30.To show occupied plots of GeneralGrave
  SELECT generalGraveId,Max(recycleDate)
   AS recycleDate FROM GeneralGrave1 
   where recycleDate>GETDATE() group by generalGraveId

-------------------------------------------------------------------------------------------------------------------------------------------------------------
---31.To no of show sold plots
   SELECT(2000-COUNT(DISTINCT generalGraveId)) as available FROM OwnerInfo

-------------------------------------------------------------------------------------------------------------------------------------------------------------
   ---32. To show usage frequency of plots
   Select permanentGraveId,COUNT (permanentGraveId) AS NumberOfPerson 
   FROM Permanent_CorpseInfo GROUP BY permanentGraveId) 
   UNION ALL (SELECT generalGraveId,COUNT (generalGraveId) 
   AS NumberOfPerson FROM GeneralGrave1 GROUP BY generalGraveId )


