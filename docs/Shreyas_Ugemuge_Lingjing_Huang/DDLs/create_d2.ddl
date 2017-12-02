CREATE TABLE IF NOT EXISTS "Doctor Personal Info" (
	`Doctor_ID`	NUMERIC NOT NULL UNIQUE,
	`Doctor_Name`	TEXT NOT NULL,
	`Phone`	NUMERIC,
	`Department`	TEXT,
	`Birthday`	INTEGER,
	PRIMARY KEY(`Doctor_ID`)
);
CREATE TABLE IF NOT EXISTS "Doctor Contact Info" (
	`Doctor_Name`	TEXT NOT NULL,
	`Phone`	NUMERIC,
	`Address`	TEXT,
	`Department`	TEXT
);
CREATE TABLE IF NOT EXISTS "Illness" (
	`Illness_ID`	NUMERIC NOT NULL UNIQUE,
	`Illness_Name`	TEXT NOT NULL,
	`Department`	TEXT,
	`Symptom`	TEXT,
	`Emergency_Level`	INTEGER,
	PRIMARY KEY(`Illness_ID`)
);
CREATE TABLE IF NOT EXISTS "Patient" (
	`Patient_ID`	NUMERIC NOT NULL UNIQUE,
	`Patient_Name`	TEXT NOT NULL,
	`Phone`	NUMERIC,
	`Address`	TEXT,
	`Birthday`	NUMERIC,
	`Gender`	TEXT,
	`Allergies_History`	TEXT,
	`Doctor_ID`	NUMERIC,
	`Illness_ID`	NUMERIC,
	PRIMARY KEY(`Patient_ID`)
);
