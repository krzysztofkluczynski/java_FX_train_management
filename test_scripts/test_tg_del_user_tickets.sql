-- Test deletion of a user
DECLARE
v_users_cnt NUMBER;
    v_tickets_cnt NUMBER;
BEGIN
INSERT INTO users (login, PASSWORD, name, surname, email) VALUES ('kakkva', 'asd2', 'Test', 'user', 'testuser@email.com');
DELETE FROM users WHERE login = 'kakkva';
SELECT COUNT(*) INTO v_users_cnt FROM users WHERE login = 'kakkva';
SELECT COUNT(*) INTO v_tickets_cnt FROM tickets WHERE user_id = (SELECT user_id FROM users WHERE login = 'kakkva');
IF v_users_cnt = 0 AND v_tickets_cnt = 0 THEN
        dbms_output.put_line('Success: User and their tickets deleted');
ELSE
        dbms_output.put_line('Failure: User and/or their tickets not deleted');
END IF;
END;
/