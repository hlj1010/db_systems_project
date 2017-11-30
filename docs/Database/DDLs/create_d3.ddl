CREATE TABLE `Doctor` (
	`Doctor_ID`	NUMERIC NOT NULL UNIQUE,
	`Doctor_Name`	TEXT NOT NULL,
	`Phone`	NUMERIC,
	`Address`	TEXT,
	`Birthday`	NUMERIC,
	`Experiences_Years`	INTEGER,
	`Department`	TEXT,
	PRIMARY KEY(`Doctor_ID`)
);
CREATE TABLE `Illness` (
	`Illness_ID`	NUMERIC NOT NULL UNIQUE,
	`Illness_Name`	TEXT NOT NULL,
	`Department`	TEXT,
	`Symptom`	TEXT,
	`Emergency_Level`	INTEGER,
	PRIMARY KEY(`Illness_ID`)
);
CREATE TABLE IF NOT EXISTS "Patient Personal Info" (
	`Patient_Name`	TEXT NOT NULL,
	`Phone`	NUMERIC,
	`Gender`	TEXT,
	`Birthday`	NUMERIC,
	`Allergies_History`	TEXT,
	`Doctor_ID`	NUMERIC,
	`Illness_ID`	NUMERIC
);
CREATE TABLE IF NOT EXISTS "Patient Contact Info" (
	`Patient_Name`	TEXT NOT NULL,
	`Phone`	NUMERIC,
	`Address`	TEXT,
	`Patient_ID`	INTEGER NOT NULL UNIQUE,
	PRIMARY KEY(`Patient_ID`)
);
