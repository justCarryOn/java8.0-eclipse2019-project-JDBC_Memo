-- drop table
drop table useraccount;
drop table usermemo;
drop sequence memoseq;
drop sequence messeageseq;

-- sequence
create sequence memoseq;
create sequence messageseq;

-- user
create table useraccount (
	name varchar2(20) not null,						
	id varchar2(20) primary key,					
	password varchar2(20) not null					 
);

-- memo
create table usermemo (
	memoseq varchar2(60) primary key,
    title varchar2(60) not null, 					
    content varchar2(2000) not null,				
    indate date default sysdate,    				
    id varchar2(20) references useraccount(id)		
);

-- message
create table usermessage (
	messageseq varchar2(60) primary key,
    title varchar2(60) not null, 					
    content varchar2(2000) not null,				
    indate date default sysdate,   					
    toid varchar2(20) references useraccount(id),	
    fromid varchar2(20) references useraccount(id)		 
);

-- commit
commit;
