--------------------------------------------------------
--  File created - poniedzia�ek-stycznia-30-2023   
--------------------------------------------------------
--------------------------------------------------------
--  DDL for Trigger TG_DEL_STOPS_AND_TICKETS
--------------------------------------------------------

  CREATE OR REPLACE EDITIONABLE TRIGGER "Z13"."TG_DEL_STOPS_AND_TICKETS" 
BEFORE DELETE ON connections
FOR EACH ROW
BEGIN
    DELETE FROM stops WHERE connection_id = :old.connection_id;
    DELETE FROM tickets WHERE connection_id = :old.connection_id;
END;

/
ALTER TRIGGER "Z13"."TG_DEL_STOPS_AND_TICKETS" ENABLE;
