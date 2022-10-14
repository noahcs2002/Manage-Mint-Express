-- Used to clear or scaffold the necessary tables -- 

IF object_id ('CatchingStaff' , 'U') IS NOT NULL
	DROP TABLE CatchingStaff;

IF object_id ('Infielders' , 'U') IS NOT NULL
	DROP TABLE Infielders;

IF object_id ('Outfielders' , 'U') IS NOT NULL
	DROP TABLE Outfielders;

IF object_id ('PitchingStaff' , 'U') IS NOT NULL
	DROP TABLE PitchingStaff;

IF object_id ('Teams' , 'U') IS NOT NULL
	DROP TABLE Teams;

CREATE TABLE CatchingStaff (
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

CREATE TABLE Infielders (
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

CREATE TABLE Outfielders (
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

CREATE TABLE PitchingStaff (
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

CREATE TABLE Teams (
	Name nvarchar(MAX),
	Rank decimal(18, 0)
);