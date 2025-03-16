
Real-Time Project Report
"Travel and Tourism Management System"





















Table of Contents
1.	Project Title
2.	Abstract
3.	Introduction
4.	Objectives
5.	Scope of the Project
6.	System Features
7.	System Architecture & Design
8.	Database Design
9.	System Implementation
10.	Testing & Evaluation
11.	Challenges Faced
12.	Future Scope
13.	Conclusion
14.	References
________________________________________






























2. Abstract
The Travel and Tourism Management System is an advanced, real-time application built using Java Swing, JDBC, and MySQL, designed to streamline the various aspects of customer management, tour management, and destination exploration for travel agencies or organizations.
The system enables user-friendly interaction with customers, manages the available tour packages, allows customer registration, and enables visualization of travel destinations via interactive slideshows. This modular application adheres to object-oriented principles like abstraction and encapsulation, integrates database management using JDBC, and ensures an intuitive graphical user interface for administrative tasks and real-time visualization.

















________________________________________
3. Introduction
3.1 Project Background
In the modern era, Travel Agencies need advanced and innovative software systems to manage customers, tours, and services. Existing systems often lack flexibility, visualization, and user interaction. The Travel and Tourism Management System aims to bridge this gap by offering a real-time system for managing customers, viewing destinations, and administrating packages.
This application leverages Java’s robust Swing toolkit and database management capabilities to deliver fast, user-friendly experiences.
________________________________________
3.2 Purpose of the Project
The Travel and Tourism Management System is designed to:
1.	Manage Customer Information: Input, view, and edit customer details securely.
2.	Facilitate Tour Packages: Create and manage tour packages for customers to select.
3.	Destination Slideshow: Provide interactive visualization of destinations through slideshows.
4.	Dynamic Database Connectivity: Use MySQL as a relational database to store dynamic user and tour information securely.
5.	Enhance User Interaction: Using Java Swing for a responsive and intuitive user interface.
________________________________________
3.3 Goals of the Project
1.	Create a fully functional user-friendly system using Java Swing and JDBC.
2.	Connect the system to a database to handle persistent user/customer/tour data.
3.	Incorporate features such as user authentication, customer management, tour management, and slideshow-based visualization.
4.	Ensure seamless database operations for CRUD processes.
________________________________________



4. Objectives
The main objectives of the system are:
1.	Customer Management: Allow administrators to add, edit, or search for customer records.
2.	Destination Slideshow: Implement an automatic image slideshow showcasing popular destinations.
3.	Database Integration: Utilize MySQL with JDBC to perform secure database transactions.
4.	Tour Package Management: Allow users to create and update custom tour packages.
5.	Improve Workflow Efficiency: Replace manual tourism record-keeping with this digital system.
6.	Ensure Ease of Use: Design a clean and attractive graphical user interface (GUI).















________________________________________
5. Scope of the Project
The scope of this project is defined as follows:
In Scope
•	Customer Authentication: Allow users to log in securely to view their respective information.
•	CRUD for Customers: Ability to perform create, read, update, and delete operations on customer data.
•	Tour Package CRUD: Enable administrators to add and manage tour packages.
•	Destination Viewer: Interactive slideshow with destination visuals.
•	MySQL database integration with Java (via JDBC): Ensure secure and fast database connectivity.
Out of Scope
•	Online booking features.
•	Real-time integration with external APIs.













________________________________________
6. System Features
The system implements the following features:
1.	Customer Registration & Management
Allows admins to add, edit, or view customer data like name, phone number, and address.
2.	Package Management
Admins can create new tour packages and update existing ones.
3.	Login Authentication
Authenticate admin users to ensure only authorized personnel can modify data.
4.	Destination Slideshow Viewer
A slideshow displaying destination images for user engagement.
5.	Database Integration with MySQL
Data is stored persistently for all actions using JDBC.
6.	Graphical User Interface
An intuitive and interactive user interface designed with Java Swing.














________________________________________
7. System Architecture & Design
The system is developed with a Layered Architecture combining GUI, database logic, and database layers.
Architecture Layers
1.	Presentation Layer
o	Built using Java Swing for user-friendly GUI interaction.
o	Interfaces include the login screen, destination viewer, and customer management.
2.	Business Logic Layer
o	Handles database interactions and user input processing.
3.	Data Access Layer
o	JDBC connection handles interaction with the database.
o	Query execution to read and write customer and tour data.
4.	Database Layer
o	MySQL serves as the database backend.
________________________________________
System Design
Diagram:










________________________________________
8. Database Design
The system uses MySQL as the database backend. The database stores user/customer information and other essential data.
Entity Relationship Diagram (ERD)
The database schema includes:
•	Customer Table: Contains customer data like username, address, phone number, email.
•	Package Table: Stores information about various travel packages.
SQL Schema(SYX)
CREATE DATABASE test;

USE test;

CREATE TABLE customer (
    username VARCHAR(50) NOT NULL PRIMARY KEY,
    id_type VARCHAR(20),
    number VARCHAR(15),
    name VARCHAR(100),
    gender VARCHAR(20),
    country VARCHAR(50),
    address VARCHAR(255),
    phone VARCHAR(15),
    email VARCHAR(100)
);

CREATE TABLE package (
    packageId VARCHAR(50) NOT NULL PRIMARY KEY,
    packageName VARCHAR(100),
    date DATE,
    cost DECIMAL(10,2)
);

MY SQL Queries:


create database tourismmanagementsystem;
 
use tourismmanagementsystem;
 
create table account(username varchar(20),name varchar(20), password varchar(100), answer varchar(50));

ALTER TABLE account ADD question VARCHAR(100);

CREATE TABLE customer (
    id INT AUTO_INCREMENT PRIMARY KEY,    -- Auto-incremented customer ID
    username VARCHAR(255) NOT NULL,        -- Username (from login)
    id_type VARCHAR(50),                   -- ID Type (Passport, Aadhar Card, etc.)
    number VARCHAR(255),                   -- ID Number
    name VARCHAR(255),                     -- Customer Name
    gender VARCHAR(10),                    -- Gender (Male/Female)
    country VARCHAR(255),                  -- Country
    address VARCHAR(255),                  -- Address
    phone VARCHAR(20),                     -- Phone Number
    email VARCHAR(255),                    -- Email Address
    FOREIGN KEY (username) REFERENCES users(username)  -- Assuming there's a users table with username as PK
);

CREATE TABLE users (
    username VARCHAR(255) PRIMARY KEY,
    password VARCHAR(255) NOT NULL,  -- Example for storing user password
    email VARCHAR(255),
    phone VARCHAR(20)
);

ALTER TABLE customer ADD COLUMN id_type VARCHAR(50);
ALTER TABLE customer ADD COLUMN number VARCHAR(255);
ALTER TABLE customer ADD COLUMN gender VARCHAR(10);
ALTER TABLE customer ADD COLUMN country VARCHAR(255);
ALTER TABLE customer ADD COLUMN username VARCHAR(255) NOT NULL;

UPDATE customer
SET id_type = ?, number = ?, name = ?, gender = ?, country = ?, address = ?, phone = ?, email = ?
WHERE username = ?;

CREATE TABLE bookPackage (
    id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(255),
    package VARCHAR(255),
    persons INT NOT NULL,
    totalPrice VARCHAR(20) NOT NULL,
    bookingDate TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);


CREATE TABLE bookHotel (
    username VARCHAR(50) NOT NULL,
    name VARCHAR(100),
    numPersons INT,
    numDays INT,
    acNonAc VARCHAR(10),
    foodIncluded VARCHAR(10),
    id VARCHAR(20),
    number VARCHAR(20),
    phone VARCHAR(15),
    cost DECIMAL(10, 2),
    PRIMARY KEY (username)
);







________________________________________
9. System Implementation
The system integrates Java Swing with MySQL through JDBC.
1.	User Login: Validates users before allowing access to features.
2.	Customer Data Management: Admins can edit or add customer information dynamically.
3.	Package Creation: Tour packages can be added and updated.
4.	Slideshow Feature: Displays a smooth slideshow of popular destinations.


















________________________________________
10. Testing & Evaluation
The system underwent multiple rounds of testing:
Unit Testing
Tested each feature like login, customer CRUD, slideshow, and database query execution independently.
Integration Testing
Ensured that database connections and user input handling worked as intended.
Performance Testing
Checked database response time, GUI responsiveness, and database read/write times.















________________________________________
11. Challenges Faced
1.	JDBC Integration with MySQL: Managed database errors and connection issues.
2.	GUI Responsiveness: Optimized Java Swing for seamless interactions.
3.	Secure Query Execution: Addressed SQL Injection through PreparedStatements.




















________________________________________
12. Future Scope
1.	Introduce role-based access control for admins and users.
2.	Implement data analytics reporting for management insights.
3.	Enable real-time payment gateway integration.




















________________________________________
13. Conclusion
The Travel and Tourism Management System effectively solves customer and tour data management challenges with real-time features and user interactivity. It adheres to object-oriented principles and demonstrates the potential for future enhancements.




















________________________________________
References
1.	JDBC Documentation - Oracle Docs
2.	MySQL Documentation - MySQL Docs
3.	Java Swing Tutorial - Oracle Swing Guide
________________________________________

