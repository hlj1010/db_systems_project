CREATE TABLE `Patient` (
	`Patient_ID`	NUMERIC NOT NULL,
	`Patient_Name`	TEXT NOT NULL,
	`Phone`	NUMERIC,
	`Address`	TEXT,
	`Birthday`	NUMERIC,
	`Gender`	TEXT,
	`Allergies_History`	TEXT,
	`Doctor_ID`	NUMERIC NOT NULL,
	`Illness_ID`	NUMERIC,
	PRIMARY KEY(`Patient_ID`)
);
CREATE TABLE IF NOT EXISTS "Doctor" (
	`Doctor_ID`	NUMERIC NOT NULL UNIQUE,
	`Doctor_Name`	TEXT NOT NULL,
	`Phone`	NUMERIC,
	`Address`	TEXT,
	`Birthday`	NUMERIC,
	`Experiences_Year`	INTEGER,
	`Department`	TEXT,
	PRIMARY KEY(`Doctor_ID`)
);
CREATE TABLE `Illness` (
	`Illness_ID`	NUMERIC NOT NULL UNIQUE,
	`Illness_Name`	TEXT NOT NULL UNIQUE,
	`Department`	TEXT,
	`Symptom`	TEXT,
	`Emergency_Level`	INTEGER,
	PRIMARY KEY(`Illness_ID`)
);
