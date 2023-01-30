-- Test deletion of a connection
DECLARE
v_connections_cnt NUMBER;
    v_stops_cnt NUMBER;
    v_tickets_cnt NUMBER;
BEGIN
INSERT INTO connections (CONNECTION_ID, DEPARTURE_STATION, ARRIVAL_STATION)
VALUES (992923, 1, 2);
DELETE FROM connections WHERE CONNECTION_ID = 992923;
SELECT COUNT(*) INTO v_connections_cnt FROM connections
WHERE CONNECTION_ID = 992923;
SELECT COUNT(*) INTO v_stops_cnt FROM stops WHERE connection_id = 992923;
SELECT COUNT(*) INTO v_tickets_cnt FROM tickets WHERE connection_id = 992923;
IF v_connections_cnt = 0 AND v_stops_cnt = 0 AND v_tickets_cnt = 0 THEN
        dbms_output.put_line('Success: Connection, stops and tickets deleted');
ELSE
        dbms_output.put_line('Failure: Connection, stops and tickets not deleted');
END IF;
END;
/
