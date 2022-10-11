-- Used to clear or scaffold the necessary tables -- 

if object_id ('CatchingStaff' , 'U') is not null
	drop table CatchingStaff;
go

if object_id ('Infielders' , 'U') is not null
	drop table Infielders;
go

if object_id ('Outfielders' , 'U') is not null
	drop table Outfielders;
go

if object_id ('PitchingStaff' , 'U') is not null
	drop table PitchingStaff;
go

if object_id ('Teams' , 'U') is not null
	drop table Teams;
go

create table CatchingStaff (
	Name nvarchar(MAX),
	Team nvarchar(MAX),
	GamesPlayed decimal(18,0),
	GamesStarted decimal(18,0),
	InningsPlayed decimal(18,0),
	TotalChances decimal(18,0),
	PutOuts decimal(18,0),
	Assists decimal(18,0),
	Errors decimal(18,0),
	DoublePlays decimal(18,0),
	IsInjured bit,
	Injury nvarchar(MAX),
	IsSuspended bit,
	Suspension nvarchar(MAX),
	Position nvarchar(MAX),
	Number nvarchar(MAX)
);

create table Infielders (
	Name nvarchar(MAX),
	Team nvarchar(MAX),
	GamesPlayed decimal(18,0),
	GamesStarted decimal(18,0),
	InningsPlayed decimal(18,0),
	TotalChances decimal(18,0),
	PutOuts decimal(18,0),
	Assists decimal(18,0),
	Errors decimal(18,0),
	DoublePlays decimal(18,0),
	IsInjured bit,
	Injury nvarchar(MAX),
	IsSuspended bit,
	Suspension nvarchar(MAX),
	Position nvarchar(MAX),
	Number nvarchar(MAX)
);

create table Outfielders (
	Name nvarchar(MAX),
	Team nvarchar(MAX),
	GamesPlayed decimal(18,0),
	GamesStarted decimal(18,0),
	InningsPlayed decimal(18,0),
	TotalChances decimal(18,0),
	PutOuts decimal(18,0),
	Assists decimal(18,0),
	Errors decimal(18,0),
	DoublePlays decimal(18,0),
	IsInjured bit,
	Injury nvarchar(MAX),
	IsSuspended bit,
	Suspension nvarchar(MAX),
	Position nvarchar(MAX),
	Number nvarchar(MAX)
);

create table PitchingStaff (
	Player nvarchar(MAX),
	Team nvarchar(MAX),
	InningsPitched decimal(18,0),
	Hits decimal(18,0),
	Runs decimal(18,0),
	EarnedRuns decimal(18,0),
	Walks decimal(18,0),
	StrikeOuts decimal(18,0),
	Homeruns decimal(18,0),
	Saves decimal(18,0),
	ERA decimal(18,0),
	WHIP decimal(18,0),
	IsInjured bit,
	Injury nvarchar(MAX),
	IsSuspended bit,
	Suspension nvarchar(MAX),
	Number nvarchar(MAX)
);

create table Teams (
	Name nvarchar(MAX),
	Rank decimal(18, 0)
)