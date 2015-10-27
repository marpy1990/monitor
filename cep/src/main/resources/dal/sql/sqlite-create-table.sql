create table USER (
    ID         INTEGER  PRIMARY KEY AUTOINCREMENT,
    NAME       TEXT,
    PASSWORD   TEXT
);

create table SOURCE_NODE_STATE (
	SOURCEID  INTEGER               NOT NULL,
	SPACEID   INTEGER               NOT NULL,
	USERID    INTEGER               NOT NULL,
	TREEID    TEXT                  NOT NULL,
	ISOPEN    BOOLEAN               NOT NULL,
	constraint pk_SOURCE_NODE_STATE PRIMARY KEY (SOURCEID, SPACEID, USERID, TREEID)
);

create table SOURCE_TREE_STATE (
	USERID             INTEGER               NOT NULL,
	TREEID             TEXT                  NOT NULL,
	CURRENT_SPACEID    INTEGER               NOT NULL,
	constraint pk_SOURCE_TREE_STATE PRIMARY KEY (USERID, TREEID)
);

create table SOURCE_ICON(
	SOURCEID     INTEGER  			   NOT NULL,
	FEATURE      INTEGER               NOT NULL,
	URL          TEXT                  NOT NULL,
	constraint pk_SOURCE_TYPE_ICON PRIMARY KEY (SOURCEID, FEATURE)
);