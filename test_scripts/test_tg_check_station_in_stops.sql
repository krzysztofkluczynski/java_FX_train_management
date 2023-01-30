-- Test deletion of a station without stops
DECLARE
v_stations_cnt NUMBER;
BEGIN
INSERT INTO stations (name) VALUES ('Test Station2');
DELETE FROM stations WHERE name = 'Test Station2';
SELECT COUNT(*) INTO v_stations_cnt FROM stations WHERE name = 'Test Station2';
IF v_stations_cnt = 0 THEN
        dbms_output.put_line('Success: Station deleted');
ELSE
        dbms_output.put_line('Failure: Station not deleted');
END IF;
END;
/

-- Test deletion of a station with stops
DECLARE
v_error_code NUMBER;
    v_error_message VARCHAR2(2000);
BEGIN
INSERT INTO stations (STATION_ID, name) VALUES (default, 'Test Station2');
INSERT INTO stops (CONNECTION_ID, STATION_ID) VALUES (1, (SELECT station_id FROM stations WHERE name = 'Test Station2'));
DELETE FROM stations WHERE name = 'Test Station2';
EXCEPTION
    WHEN OTHERS THEN
        v_error_code := SQLCODE;
        v_error_message := SUBSTR(SQLERRM, 1, 2000);
IF v_error_code = -20001 THEN
  dbms_output.put_line('Success: Error raised');
ELSE
  dbms_output.put_line('Error not raised');
END IF;
DELETE FROM stops WHERE STATION_ID = (SELECT station_id FROM stations WHERE name = 'Test Station2');
DELETE FROM stations WHERE name = 'Test Station2';
END;