create table USER (
    ID         INTEGER  PRIMARY KEY AUTOINCREMENT,
    NAME       TEXT,
    PASSWORD   TEXT
);

create table SOURCE_TREE_STATE (
	SOURCEID  INTEGER               NOT NULL,
	USERID    INTEGER               NOT NULL,
	TREEID    TEXT                  NOT NULL,
	ISOPEN    BOOLEAN               NOT NULL,
	constraint pk_SOURCE_TREE_STATE PRIMARY KEY (SOURCEID, USERID, TREEID)
);

create table SOURCE_ICON(
	SOURCEID     INTEGER  			   NOT NULL,
	FEATURE      INTEGER               NOT NULL,
	URL          TEXT                  NOT NULL,
	constraint pk_SOURCE_TYPE_ICON PRIMARY KEY (SOURCEID, FEATURE)
);