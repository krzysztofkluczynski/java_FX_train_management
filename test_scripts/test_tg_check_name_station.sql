-- Test insertion of a new station
DECLARE
v_stations_cnt NUMBER;
BEGIN
INSERT INTO stations (station_id, name) VALUES (default, 'Test Station');
SELECT COUNT(*) INTO v_stations_cnt FROM stations WHERE name = 'Test Station';
IF v_stations_cnt = 1 THEN
        dbms_output.put_line('Success: New station inserted');
ELSE
        dbms_output.put_line('Failure: New station not inserted');
END IF;
END;
/

-- Test insertion of a station with the same name
DECLARE
v_error_code NUMBER;
    v_error_message VARCHAR2(2000);
BEGIN
INSERT INTO stations (name) VALUES ('Test Station');
EXCEPTION
    WHEN OTHERS THEN
        v_error_code := SQLCODE;
        v_error_message := SUBSTR(SQLERRM, 1, 2000);
IF v_error_code = -20001 THEN
  dbms_output.put_line('Success: Error raised');
ELSE
  dbms_output.put_line('Failure: Error not raised');
END IF;
END;
/