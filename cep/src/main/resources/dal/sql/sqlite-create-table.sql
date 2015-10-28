create table SOURCE (
    ID         INTEGER  PRIMARY KEY AUTOINCREMENT,
    NAME       TEXT
);

create table SOURCE_VIEW (
    FROMID     INTEGER              NOT NULL,
    TOID       INTEGER              NOT NULL,
    SPACEID    INTEGER              NOT NULL,
    EDGE       BOOLEAN              NOT NULL,
    constraint pk_SOURCE_VIEW PRIMARY KEY (FROMID, TOID, SPACEID)
);

create table VIEWSPACE (
    ID         INTEGER  PRIMARY KEY AUTOINCREMENT,
    NAME       TEXT
);