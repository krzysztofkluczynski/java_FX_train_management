BEGIN
INSERT INTO connections (connection_id, DEPARTURE_STATION, ARRIVAL_STATION)
VALUES (77463, 328, 327);
INSERT INTO stops (connection_id, station_id, departure_hour, arrival_hour)
VALUES (77463, 328, '2023-02-01 10:00:00', '2023-02-01 11:00:00');
INSERT INTO stops (connection_id, station_id, departure_hour, arrival_hour)
VALUES (77463, 327, '2023-02-01 11:00:00', '2023-02-01 12:00:00');
END;
/

-- case 1: valid data (120)
DECLARE
travel_time NUMBER;
BEGIN
SELECT "Z13"."TRAVEL_TIME"(77463, 328, 327) INTO travel_time FROM dual;
DBMS_OUTPUT.PUT_LINE(travel_time);
END;

-- case 2: no connection found
DECLARE
travel_time NUMBER;
BEGIN
BEGIN
SELECT Z13.TRAVEL_TIME(432, 1, 2) INTO travel_time FROM dual;
EXCEPTION
        WHEN OTHERS THEN
            DBMS_OUTPUT.PUT_LINE('Error: ' || SQLERRM);
END;
END;

-- case 3: (Invalid station order)
DECLARE
travel_time NUMBER;
BEGIN
BEGIN
SELECT Z13.TRAVEL_TIME(77463, 327, 328) INTO travel_time FROM dual;
EXCEPTION
        WHEN OTHERS THEN
            DBMS_OUTPUT.PUT_LINE('Error: ' || SQLERRM);
END;
END;

-- deletion of test data
DELETE
FROM stops
WHERE connection_id = 77463;
DELETE
FROM connections
WHERE connection_id = 77463;