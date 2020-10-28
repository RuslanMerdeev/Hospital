CREATE TABLE doctors (
        id BIGINT IDENTITY PRIMARY KEY,
        firstname VARCHAR(20) NOT NULL,
        middlename VARCHAR(20) NOT NULL,
        lastname VARCHAR(20) NOT NULL,
        speciality VARCHAR(20)
        );
INSERT INTO doctors (firstname, middlename, lastname, speciality)
        values
        ('Vasiliy', 'Petrovich', 'Kemkin', 'onko'),
        ('Semen', 'Viktorovich', 'Sudakov', 'neural'),
        ('Vlad', 'Ivanovich','Shevchenko', 'surgerer')
        ;

CREATE TABLE patients (
        id BIGINT IDENTITY PRIMARY KEY,
        firstname VARCHAR(20) NOT NULL,
        middlename VARCHAR(20) NOT NULL,
        lastname VARCHAR(20) NOT NULL,
        phone VARCHAR(20)
        );
INSERT INTO patients (firstname, middlename, lastname, phone)
        values
        ('Ruslan', 'Zamirovich', 'Merdeev', '+71111111111'),
        ('Alexey', 'D.', 'Nikolayev', '+72222222222'),
        ('Maxim', 'Igorevich','Kornev', '+73333333333')
        ;

CREATE TABLE receipts (
        id BIGINT IDENTITY PRIMARY KEY,
        description VARCHAR(100),
        doctorID BIGINT NOT NULL,
        patientID BIGINT NOT NULL,
        creationDate DATE NOT NULL,
        validity INTEGER NOT NULL,
        priority INTEGER,
        FOREIGN KEY (doctorID) REFERENCES doctors(id) ON DELETE CASCADE,
        FOREIGN KEY (patientID) REFERENCES patients(id) ON DELETE CASCADE
        );
INSERT INTO receipts (description, doctorID, patientID, creationDate, validity, priority)
        values
        ('use that very useful drug', 0, 2, DATE '2020-10-23', 2, 0),
        ('it is cool to use that drug', 0, 1, DATE '2020-10-25', 4, 1),
        ('drug drug drug', 1, 0, DATE '2020-10-27', 8, 2)
        ;