CREATE TABLE IF NOT EXISTS statistics (
id BIGINT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY NOT NULL,
app VARCHAR NOT NULL,
uri VARCHAR NOT NULL,
ip VARCHAR NOT NULL,
time TIMESTAMP NOT NULL);