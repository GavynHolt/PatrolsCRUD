DROP TABLE IF EXISTS post_order CASCADE;
DROP TABLE IF EXISTS date_range CASCADE;
DROP TABLE IF EXISTS location CASCADE;
DROP TABLE IF EXISTS step CASCADE;
DROP TABLE IF EXISTS recurring CASCADE;
DROP TABLE IF EXISTS time_window CASCADE;
DROP TABLE IF EXISTS patrol_check CASCADE;
DROP TABLE IF EXISTS patrol_recurring CASCADE;
DROP TABLE IF EXISTS patrol_time_window CASCADE;
DROP TABLE IF EXISTS patrol_date_range CASCADE;
DROP TABLE IF EXISTS patrol CASCADE;

CREATE TABLE location(
    location_id INT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    name VARCHAR(255) NOT NULL,
    address1 VARCHAR(255) NOT NULL,
    address2 VARCHAR(255),
    city VARCHAR(255) NOT NULL,
    state VARCHAR(255) NOT NULL,
    postal_code VARCHAR(255) NOT NULL,
    country VARCHAR(255) NOT NULL
);

INSERT INTO location(name, address1, city, state, postal_code, country)
VALUES
    ('Heroku Test Location', '123 Fake Street', 'Toronto', 'ON', 'M6Z 1Z3', 'Canada'),
    ('Gavyns House (Heroku)', '42 Dayton Ave', 'Toronto', 'ON', 'M8Z 3L7', 'Canada'),
    ('My Heroku Backyard', '555 Test St.' , 'Toronto', 'ON', 'M8Z 3L9', 'Canada'),
    ('Another Heroku Location', 'Avenue Unknown', 'Barrie', 'ON', 'L4N 2P5', 'Canada');

CREATE TABLE date_range(
	date_range_id INT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
	start_date VARCHAR(255) NOT NULL,
	end_date VARCHAR(255) NOT NULL
);

INSERT INTO date_range(start_date, end_date)
VALUES
	('2022-04-01', '2022-04-10'),
	('2022-03-01', '2022-07-01'),
	('2022-06-15', '2022-07-12');

CREATE TABLE post_order(
    post_order_id INT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    name VARCHAR(255) NOT NULL,
	notes VARCHAR(255),
    location_id INT REFERENCES location(location_id),
	date_range_id INT REFERENCES date_range(date_range_id)
);

INSERT INTO post_order(name, notes, location_id, date_range_id)
VALUES
	('Test Post Order','Be sure to say hello to George', 2, 1),
	('Another Order', 'These are test notes', 2, 2),
	('Example Order', 'Please note that a note is not required.', 3, 3);

CREATE TABLE step(
	step_id INT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
	name VARCHAR(255) NOT NULL,
	photo_required BOOLEAN,
	escalation_contact VARCHAR(255),
	post_order_id INT REFERENCES post_order(post_order_id)
);

INSERT INTO step (name, photo_required, escalation_contact, post_order_id)
VALUES
	('Front Cash secure?', false, 'General Manager', 1),
	('Back Door locked?', true, 'Location Manager',  1),
	('No vandalism or damage?', true, 'Vice President', 2);

CREATE TABLE recurring(
	recurring_id INT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
	day VARCHAR(255) NOT NULL,
	post_order_id INT REFERENCES post_order(post_order_id)
);

INSERT INTO recurring (day, post_order_id)
VALUES
	('monday', 1),
	('tuesday', 1),
	('thursday', 1),
	('friday', 1),
	('saturday', 2),
	('sunday', 2),
	('friday', 3);

CREATE TABLE time_window(
	time_window_id INT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
	start_time VARCHAR(255) NOT NULL,
	end_time VARCHAR(255) NOT NULL
);

INSERT INTO time_window (start_time, end_time)
VALUES
	('11:00', '12:00'),
	('13:00', '23:59'),
	('08:00', '12:00');

CREATE TABLE patrol_check(
	patrol_check_id INT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
	amount int NOT NULL,
	post_order_id INT REFERENCES post_order(post_order_id),
	time_window_id INT REFERENCES time_window(time_window_id),
	notes VARCHAR(255)
);

INSERT INTO patrol_check (amount,  post_order_id, time_window_id, notes)
VALUES
	(1, 1, 1, 'This is a patrol check specific note.'),
	(3, 1, 2, 'For this patrol check only.'),
	(5, 3, 3, 'RE: this patrol check, John will be the manager on duty during this time.');

CREATE TABLE patrol_date_range(
	patrol_date_range_id INT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
	start_date VARCHAR(255) NOT NULL,
	end_date VARCHAR(255) NOT NULL
);

INSERT INTO patrol_date_range(start_date, end_date)
VALUES
	('2022-04-01', '2022-04-10');

CREATE TABLE patrol_time_window(
	patrol_time_window_id INT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
	start_time VARCHAR(255) NOT NULL,
	end_time VARCHAR(255) NOT NULL
);

INSERT INTO patrol_time_window (start_time, end_time)
VALUES
	('11:00', '12:00');


CREATE TABLE patrol(
	patrol_id INT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
	patrol_name VARCHAR(255) NOT NULL,
	amount int NOT NULL,
	notes VARCHAR(255),
	patrol_date_range_id INT REFERENCES patrol_date_range(patrol_date_range_id),
	patrol_time_window_id INT REFERENCES patrol_time_window(patrol_time_window_id)
);

INSERT INTO patrol(patrol_name, amount, patrol_date_range_id, patrol_time_window_id, notes)
VALUES ('Test patrol', 2, 1, 1, 'Notes go here');

CREATE TABLE patrol_recurring(
	recurring_id INT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
	day VARCHAR(255) NOT NULL,
	patrol_id INT REFERENCES patrol(patrol_id)
);

INSERT INTO patrol_recurring (day, patrol_id)
VALUES
	('monday', 1),
	('tuesday', 1),
	('thursday', 1),
	('friday', 1);
