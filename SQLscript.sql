/* Clear database to demonstrate new build */
DROP TABLE Updates;
DROP TABLE WorksAt;
DROP TABLE StoredAt;
DROP TABLE Drives;
DROP TABLE Vehicle;
DROP TABLE Dispatches;
DROP TABLE HasDeliveryType;
DROP TABLE Parcel;
DROP TABLE Places;
DROP TABLE OrderStatus;
DROP TABLE PaysFor;
DROP TABLE Driver;
DROP TABLE Manager;
DROP TABLE Customer;
DROP TABLE ShippingUser;
DROP TABLE Servicing;
DROP TABLE Insurance;
DROP TABLE Branch;
DROP TABLE DeliveryType;
DROP TABLE ParcelOrder;
DROP TABLE Transaction;

/* Create Database Tables */
CREATE TABLE Transaction
(tid                     CHAR(11) NOT NULL,
 amount                   FLOAT NULL,
 PRIMARY KEY (tid));

GRANT SELECT on Transaction to PUBLIC;

CREATE TABLE ParcelOrder
(tracking_number         CHAR(20) NOT NULL,
 destination              CHAR(20) NULL,
 PRIMARY KEY (tracking_number));

GRANT SELECT on ParcelOrder to PUBLIC;

CREATE TABLE DeliveryType
(delivery_type           CHAR(11) NOT NULL,
 rate                     FLOAT NOT NULL,
 PRIMARY KEY (delivery_type),
 UNIQUE (rate));

GRANT SELECT on DeliveryType to PUBLIC;

CREATE TABLE Branch
(bid                  CHAR(11) NOT NULL,
 address               CHAR(20) NOT NULL,
 PRIMARY KEY (bid),
 UNIQUE (address));

GRANT SELECT on Branch to PUBLIC;

CREATE TABLE Insurance
(insurance_number CHAR(11) NOT NULL,
 insurance_expiry  DATE NULL,
 PRIMARY KEY (insurance_number));

GRANT SELECT on Insurance to PUBLIC;

CREATE TABLE Servicing
(last_servicing         DATE NOT NULL,
 next_servicing         DATE NULL,
 PRIMARY KEY (last_servicing));

GRANT SELECT on Servicing to PUBLIC;

CREATE TABLE ShippingUser
(username             CHAR(20) NOT NULL,
 user_password        CHAR(20) NULL,
 phone                INTEGER NULL,
 email                CHAR(20) NOT NULL,
 first_name           CHAR(11) NULL,
 last_name            CHAR(11) NULL,
 address              CHAR(100) NULL,
 PRIMARY KEY (username),
 UNIQUE (email));

GRANT SELECT on ShippingUser to PUBLIC;

CREATE TABLE Customer
(username            CHAR(20) NOT NULL,
 loyalty_member       INT NULL,
 PRIMARY KEY (username),
 FOREIGN KEY (username) REFERENCES ShippingUser ON DELETE CASCADE);

GRANT SELECT on Customer to PUBLIC;

CREATE TABLE Manager
(username            CHAR(20) NOT NULL,
 salary               FLOAT NULL,
 PRIMARY KEY (username),
 FOREIGN KEY (username) REFERENCES ShippingUser ON DELETE CASCADE);

GRANT SELECT on Manager to PUBLIC;

CREATE TABLE Driver
(username            CHAR(20) NOT NULL,
 licence_number       CHAR(7) NOT NULL,
 salary               FLOAT NULL,
 orders_delivered     INT,
 PRIMARY KEY (username),
 UNIQUE (licence_number),
 FOREIGN KEY (username) REFERENCES ShippingUser ON DELETE CASCADE);

GRANT SELECT on Driver to PUBLIC;

CREATE TABLE PaysFor
(tid                     CHAR(11) NOT NULL,
 date_paid                DATE NULL,
 username                 CHAR(20) NOT NULL,
 tracking_number          CHAR(20) NULL,
 PRIMARY KEY (tid),
 FOREIGN KEY (tracking_number) REFERENCES ParcelOrder ON DELETE CASCADE,
 FOREIGN KEY (username) REFERENCES Customer ON DELETE CASCADE,
 FOREIGN KEY (tid) REFERENCES Transaction ON DELETE CASCADE);

GRANT SELECT on PaysFor to PUBLIC;

CREATE TABLE OrderStatus
(status                 CHAR(11) NOT NULL,
 time_updated            DATE NULL,
 tracking_number         CHAR(20) NOT NULL,
 PRIMARY KEY (tracking_number, status),
 FOREIGN KEY (tracking_number) REFERENCES ParcelOrder ON DELETE CASCADE);

GRANT SELECT on OrderStatus to PUBLIC;

CREATE TABLE Places
(username            CHAR(20) NOT NULL,
 tracking_number      CHAR(20) NOT NULL,
 PRIMARY KEY (tracking_number),
 FOREIGN KEY (tracking_number) REFERENCES ParcelOrder ON DELETE CASCADE,
 FOREIGN KEY (username) REFERENCES Customer ON DELETE CASCADE);

GRANT SELECT on Places to PUBLIC;

CREATE TABLE Parcel
(pid                    CHAR(11) NOT NULL,
 parcel_size             INT NULL,
 tracking_number         CHAR(20) NULL,
 weight                  FLOAT NULL,
 delivery_type           CHAR(11) NOT NULL,
 PRIMARY KEY (pid),
 FOREIGN KEY (tracking_number) REFERENCES ParcelOrder ON DELETE CASCADE,
 FOREIGN KEY (delivery_type) REFERENCES DeliveryType ON DELETE CASCADE);

GRANT SELECT on Parcel to PUBLIC;

CREATE TABLE HasDeliveryType
(delivery_type        CHAR(11) NOT NULL,
 tracking_number       CHAR(20) NOT NULL,
 PRIMARY KEY (tracking_number),
 FOREIGN KEY (tracking_number) REFERENCES ParcelOrder ON DELETE CASCADE,
 FOREIGN KEY (delivery_type) REFERENCES DeliveryType ON DELETE CASCADE);

GRANT SELECT on HasDeliveryType to PUBLIC;

CREATE TABLE Dispatches
(username              CHAR(20) NULL,
 tracking_number        CHAR(20) NOT NULL,
 bid                    CHAR(11) NULL,
 PRIMARY KEY (tracking_number),
 FOREIGN KEY (tracking_number) REFERENCES ParcelOrder ON DELETE CASCADE,
 FOREIGN KEY (username) REFERENCES Manager ON DELETE CASCADE,
 FOREIGN KEY (bid) REFERENCES Branch ON DELETE CASCADE);

GRANT SELECT on Dispatches to PUBLIC;

CREATE TABLE Vehicle
(vin                   CHAR(11) NOT NULL,
 insurance_number       CHAR(11) NULL,
 last_servicing         DATE NULL,
 PRIMARY KEY (vin),
 FOREIGN KEY (insurance_number) REFERENCES Insurance ON DELETE CASCADE,
 FOREIGN KEY (last_servicing) REFERENCES Servicing ON DELETE CASCADE);

GRANT SELECT on Vehicle to PUBLIC;

CREATE TABLE Drives
(vin                   CHAR(11) NOT NULL,
 username               CHAR(20) NOT NULL,
 PRIMARY KEY (vin),
 UNIQUE (username),
 FOREIGN KEY (vin) REFERENCES Vehicle ON DELETE CASCADE,
 FOREIGN KEY (username) REFERENCES Driver ON DELETE CASCADE);

GRANT SELECT on Drives to PUBLIC;

CREATE TABLE StoredAt
(bid                  CHAR(11) NULL,
 vin                   CHAR(11) NOT NULL,
 PRIMARY KEY (vin),
 FOREIGN KEY (vin) REFERENCES Vehicle ON DELETE CASCADE,
 FOREIGN KEY (bid) REFERENCES Branch ON DELETE CASCADE);

GRANT SELECT on StoredAt to PUBLIC;

CREATE TABLE WorksAt
(username              CHAR(20) NOT NULL,
 start_date             DATE NOT NULL,
 bid                    CHAR(11) NOT NULL,
 PRIMARY KEY (username, start_date),
 FOREIGN KEY (username) REFERENCES ShippingUser ON DELETE CASCADE,
 FOREIGN KEY (bid) REFERENCES Branch ON DELETE CASCADE);

GRANT SELECT on WorksAt to PUBLIC;

CREATE TABLE Updates
(username          CHAR(20) NOT NULL,
 tracking_number    CHAR(20) NULL,
 status             CHAR(11) NOT NULL,
 PRIMARY KEY (username, status),
 FOREIGN KEY (username) REFERENCES Driver ON DELETE CASCADE,
 FOREIGN KEY (status, tracking_number) REFERENCES OrderStatus ON DELETE CASCADE,
 FOREIGN KEY (tracking_number) REFERENCES ParcelOrder ON DELETE CASCADE);

GRANT SELECT on Updates to PUBLIC;


/* Populate Database with sample data
   Add users
 */

INSERT INTO ShippingUser
VALUES ('mattma', '1234', 2349020000, 'matt@gmail.com', 'Mattie', 'Lin', '1010 UBC Boulevard, Richmond');
INSERT INTO ShippingUser
VALUES ('olivek', '4321', 1020102222, 'olive@gmail.com', 'Ollie', 'Kiyono', '3829 Banff Boulevard, Calgary');
INSERT INTO ShippingUser
VALUES ('greenc', '2345', 1112223333, 'green@gmail.com', 'Green', 'Chun', '9876 Curry Boulevard, West Vancouver');
INSERT INTO ShippingUser
VALUES ('colleenr', '0482', 3942014726, 'colleen@gmail.com', 'Colleen', 'Rideout', '2366 Main Mall, North Vancouver');
INSERT INTO ShippingUser
VALUES ('waltert', '6739', 5603951093, 'walter@gmail.com', 'Khal', 'Tek', '5960 Exchange Union, Vancouver');
INSERT INTO ShippingUser
VALUES ('gamma', '7588', 5637851093, 'gamma@gmail.com', 'Steve', 'Velonic', '2345 Kitsilano St, Vancouver');
INSERT INTO ShippingUser
VALUES ('fairview', '6238', 6123765093, 'fairview@gmail.com', 'Jeremy', 'Guerin', '9576 Safe Way, Surrey');
INSERT INTO ShippingUser
VALUES ('emma', '2634', 0498273647, 'emma@gmail.com', 'Emma', 'Cheng', '5960 Professor Boulevard, Coquitlam');
INSERT INTO ShippingUser
VALUES ('vanessa', '2848', 8376251938, 'vanessa@gmail.com', 'Vanessa', 'Riley', '3948 Salt Lake, Coquitlam');

/* Add Customers */
INSERT INTO Customer
VALUES ('mattma', 0);
INSERT INTO Customer
VALUES ('olivek', 1);
INSERT INTO Customer
VALUES ('greenc', 0);
INSERT INTO Customer
VALUES ('colleenr', 1);
INSERT INTO Customer
VALUES ('waltert', 1);

/* Add Drivers */
INSERT INTO Driver
VALUES ('mattma', 'ABC1234', 30000.00, 100);
INSERT INTO Driver
VALUES ('waltert', 'BNG0987', 12000.00, 12);
INSERT INTO Driver
VALUES ('gamma', 'MDK2391', 20000.50, 50);
INSERT INTO Driver
VALUES ('fairview', 'WOE1001', 15000.00, 20);
INSERT INTO Driver
VALUES ('olivek', 'TDS2841', 35000.50, 150);

/* Add Managers */
INSERT INTO Manager
VALUES ('greenc', 35000.50);
INSERT INTO Manager
VALUES ('colleenr', 32000.00);
INSERT INTO Manager
VALUES ('emma', 25000.00);
INSERT INTO Manager
VALUES ('vanessa', 30000.00);

/* Add DeliveryTypes */
INSERT INTO DeliveryType
VALUES ('Regular', 20.00);
INSERT INTO DeliveryType
VALUES ('Overnight', 40.00);
INSERT INTO DeliveryType
VALUES ('Expedited', 15.00);

/* Add ParcelOrders */
INSERT INTO ParcelOrder
VALUES('TN1234', 'Vancouver');
INSERT INTO ParcelOrder
VALUES('TN6789', 'Richmond');
INSERT INTO ParcelOrder
VALUES('TN4321', 'Surrey');
INSERT INTO ParcelOrder
VALUES('TN9876', 'Richmond');
INSERT INTO ParcelOrder
VALUES('TN1111', 'Vancouver');

/* Add Parcels */
INSERT INTO Parcel
VALUES('PNAC56F2', 12, 'TN1234', 2.0, 'Regular');
INSERT INTO Parcel
VALUES('PNOB7869', 6, 'TN1234', 1.0, 'Regular');
INSERT INTO Parcel
VALUES('PNPP1111', 10, 'TN6789', 3.5, 'Expedited');


/* Add Orders */

INSERT INTO ParcelOrder VALUES ('1234567890', 'New York');
INSERT INTO Places (username, tracking_number) VALUES ('mattma', '1234567890');
INSERT INTO HasDeliveryType (delivery_type, tracking_number) VALUES ('Regular', '1234567890');
INSERT INTO Parcel VALUES ('9876543210', 3, '1234567890', 1.5, 'Regular');

INSERT INTO ParcelOrder VALUES ('0123456789', 'New York');
INSERT INTO Places VALUES ('mattma', '0123456789');
INSERT INTO HasDeliveryType VALUES ('Overnight', '0123456789');
INSERT INTO Parcel  VALUES ('38382919299', 3, '0123456789', 1.5, 'Overnight');


INSERT INTO ParcelOrder VALUES ('1111111111', 'New York');
INSERT INTO Places (username, tracking_number) VALUES ('mattma', '1111111111');
INSERT INTO HasDeliveryType (delivery_type, tracking_number) VALUES ('Expedited', '1111111111');
INSERT INTO Parcel  VALUES ('2222222222', 3, '1111111111', 1.5, 'Expedited');
