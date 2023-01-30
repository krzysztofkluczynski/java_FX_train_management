DECLARE
v_station_id NUMBER := 1; -- change to whatever station you need info about
BEGIN
    "Z13"."PRINT_STATION_CONNECTIONS"(v_station_id);
END;
/