DROP TABLE IF EXISTS post_order;
DROP TABLE IF EXISTS date_range;
DROP TABLE IF EXISTS location;
DROP TABLE IF EXISTS step;
DROP TABLE IF EXISTS recurring;
DROP TABLE IF EXISTS time_window;
DROP TABLE IF EXISTS patrol_check;
DROP TABLE IF EXISTS patrol_recurring;
DROP TABLE IF EXISTS patrol_time_window;
DROP TABLE IF EXISTS patrol_date_range;
DROP TABLE IF EXISTS patrol;

CREATE TABLE location(
    location_id INT IDENTITY(1,1) PRIMARY KEY NOT NULL,
    name VARCHAR(150) NOT NULL,
    address1 VARCHAR(150) NOT NULL,
    address2 VARCHAR(150),
    city VARCHAR(150) NOT NULL,
    state VARCHAR(150) NOT NULL,
    postal_code VARCHAR(150) NOT NULL,
    country VARCHAR(150) NOT NULL,
);

INSERT INTO location(name, address1, city, state, postal_code, country)
VALUES
    ('Test Location', '123 Fake Street', 'Toronto', 'ON', 'M6Z 1Z3', 'Canada'),
    ('Gavyns House', '42 Dayton Ave', 'Toronto', 'ON', 'M8Z 3L7', 'Canada'),
    ('My Backyard', '555 Test St.' , 'Toronto', 'ON', 'M8Z 3L9', 'Canada'),
    ('Another Location', 'Avenue Unknown', 'Barrie', 'ON', 'L4N 2P5', 'Canada');

CREATE TABLE date_range(
	date_range_id INT IDENTITY(1,1) PRIMARY KEY NOT NULL,
	start_date VARCHAR(150) NOT NULL,
	end_date VARCHAR(150) NOT NULL,
);

INSERT INTO date_range(start_date, end_date)
VALUES 
	('2022-04-01', '2022-04-10'),
	('2022-03-01', '2022-07-01'),
	('2022-06-15', '2022-07-12');

CREATE TABLE post_order(
    post_order_id INT IDENTITY(1,1) PRIMARY KEY NOT NULL,
    name VARCHAR(150) NOT NULL,
	notes VARCHAR(150),
    location_id int FOREIGN KEY REFERENCES location(location_id),
	date_range_id int FOREIGN KEY REFERENCES date_range(date_range_id)
);

INSERT INTO post_order(name, notes, location_id, date_range_id)
VALUES
	('Test Post Order','Be sure to say hello to George', 2, 1),
	('Another Order', 'These are test notes', 2, 2),
	('Example Order', 'Please note that a note is not required.', 3, 3);

CREATE TABLE step(
	step_id INT IDENTITY(1,1) PRIMARY KEY NOT NULL,
	name VARCHAR(150) NOT NULL,
	post_order_id int FOREIGN KEY REFERENCES post_order(post_order_id)
);

INSERT INTO step (name, post_order_id)
VALUES
	('Walk the dog', 1),
	('Say Hello', 1),
	('Run away', 2);

CREATE TABLE recurring(
	recurring_id INT IDENTITY(1,1) PRIMARY KEY NOT NULL,
	day VARCHAR(150) NOT NULL,
	post_order_id int FOREIGN KEY REFERENCES post_order(post_order_id)
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
	time_window_id INT IDENTITY(1,1) PRIMARY KEY NOT NULL,
	start_time VARCHAR(150) NOT NULL,
	end_time VARCHAR(150) NOT NULL,
);	

INSERT INTO time_window (start_time, end_time)
VALUES
	('11:00', '12:00'),
	('13:00', '23:59'),
	('08:00', '12:00');

CREATE TABLE patrol_check(
	patrol_check_id INT IDENTITY(1,1) PRIMARY KEY NOT NULL,
	amount int NOT NULL,
	post_order_id int FOREIGN KEY REFERENCES post_order(post_order_id),
	time_window_id int FOREIGN KEY REFERENCES time_window(time_window_id)
);

INSERT INTO patrol_check (amount, post_order_id, time_window_id) 
VALUES
	(1, 1, 1),
	(3, 1, 2),
	(5, 3, 3);

CREATE TABLE patrol_date_range(
	patrol_date_range_id INT IDENTITY(1,1) PRIMARY KEY NOT NULL,
	start_date VARCHAR(150) NOT NULL,
	end_date VARCHAR(150) NOT NULL,
);

INSERT INTO patrol_date_range(start_date, end_date)
VALUES 
	('2022-04-01', '2022-04-10');

CREATE TABLE patrol_time_window(
	patrol_time_window_id INT IDENTITY(1,1) PRIMARY KEY NOT NULL,
	start_time VARCHAR(150) NOT NULL,
	end_time VARCHAR(150) NOT NULL,
);

INSERT INTO patrol_time_window (start_time, end_time)
VALUES
	('11:00', '12:00');


CREATE TABLE patrol(
	patrol_id INT IDENTITY(1,1) PRIMARY KEY NOT NULL,
	patrol_name VARCHAR(150) NOT NULL,
	amount int NOT NULL,
	notes VARCHAR(150),
	patrol_date_range_id int FOREIGN KEY REFERENCES patrol_date_range(patrol_date_range_id),
	patrol_time_window_id int FOREIGN KEY REFERENCES patrol_time_window(patrol_time_window_id)
);

INSERT INTO patrol(patrol_name, amount, patrol_date_range_id, patrol_time_window_id, notes)
VALUES ('Test patrol', 2, 1, 1, 'Notes go here');

CREATE TABLE patrol_recurring(
	recurring_id INT IDENTITY(1,1) PRIMARY KEY NOT NULL,
	day VARCHAR(150) NOT NULL,
	patrol_id int FOREIGN KEY REFERENCES patrol(patrol_id)
);

INSERT INTO patrol_recurring (day, patrol_id)
VALUES
	('monday', 1),
	('tuesday', 1),
	('thursday', 1),
	('friday', 1);
