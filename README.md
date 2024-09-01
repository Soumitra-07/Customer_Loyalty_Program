# Customer Loyalty Program

## Overview

This project is a simple console-based application that simulates a Customer Loyalty Program for a retail business. It is developed using Core Java, MySQL, and JDBC. The application allows users to manage customers, rewards, and purchases in a loyalty program.

## Features

- *Customer Management*
  - Add a new customer
  - View customer details
  - Update customer information
  - Delete a customer

- *Reward Management*
  - Add a new reward
  - View reward details
  - Update reward information
  - Delete a reward

- *Purchase Management*
  - Add a new purchase
  - View purchase details
  - Update purchase information
  - Delete a purchase

## Technologies Used

- Java (Core Java)
- MySQL
- JDBC (Java Database Connectivity)

## Database Schema

The following database schema is used in this project:

### Customer Table
| Column        | Data Type | Constraints        |
|---------------|-----------|--------------------|
| customer_id   | INT       | PRIMARY KEY, AUTO_INCREMENT |
| name          | VARCHAR   | NOT NULL           |
| email         | VARCHAR   | NOT NULL           |
| phone         | VARCHAR   | NOT NULL           |
| total_points  | INT       | DEFAULT 0          |

### Reward Table
| Column        | Data Type | Constraints        |
|---------------|-----------|--------------------|
| reward_id     | INT       | PRIMARY KEY, AUTO_INCREMENT |
| name          | VARCHAR   | NOT NULL           |
| points_required | INT     | NOT NULL           |
| description   | VARCHAR   |                    |

### Purchase Table
| Column        | Data Type | Constraints        |
|---------------|-----------|--------------------|
| purchase_id   | INT       | PRIMARY KEY, AUTO_INCREMENT |
| customer_id   | INT       | FOREIGN KEY (references Customer.customer_id) |
| reward_id     | INT       | FOREIGN KEY (references Reward.reward_id) |
| purchase_date | DATE      |                    |
| points_earned | INT       | NOT NULL           |

## Setup and Installation

### Prerequisites

- Java Development Kit (JDK) installed
- MySQL installed and running
- A MySQL client or access to MySQL via the command line
- JDBC driver for MySQL (mysql-connector-java.jar)

### Steps

1. *Clone the Repository:*

    sh
    git clone https://github.com/your_username/loyalty-program.git
    cd loyalty-program
    

2. *Create the Database:*

    - Log in to MySQL and execute the following commands:

    sql
    CREATE DATABASE loyalty_program;
    USE loyalty_program;

    CREATE TABLE Customer (
        customer_id INT PRIMARY KEY AUTO_INCREMENT,
        name VARCHAR(100),
        email VARCHAR(100),
        phone VARCHAR(15),
        total_points INT DEFAULT 0
    );

    CREATE TABLE Reward (
        reward_id INT PRIMARY KEY AUTO_INCREMENT,
        name VARCHAR(100),
        points_required INT,
        description VARCHAR(255)
    );

    CREATE TABLE Purchase (
        purchase_id INT PRIMARY KEY AUTO_INCREMENT,
        customer_id INT,
        reward_id INT,
        purchase_date DATE,
        points_earned INT,
        FOREIGN KEY (customer_id) REFERENCES Customer(customer_id),
        FOREIGN KEY (reward_id) REFERENCES Reward(reward_id)
    );
    

3. *Update Database Configuration:*

    - In the DBConnection.java file, update the database connection details with your MySQL username and password.

    java
    private static final String URL = "jdbc:mysql://localhost:3306/loyalty_program";
    private static final String USER = "your_mysql_username";
    private static final String PASSWORD = "your_mysql_password";
    

4. *Compile the Code:*

    sh
    javac LoyaltyProgram.java
    

5. *Run the Application:*

    sh
    java LoyaltyProgram
    

## Usage

After running the application, you will be presented with a menu to manage customers, rewards, and purchases. You can navigate through the options by entering the corresponding number and pressing Enter.

## Contributing

Feel free to fork this repository, make your changes, and submit a pull request. Contributions are always welcome!



## Contact

For any inquiries or issues, please reach out to [soumitramukherjee254@gmail.com](mailto:soumitramukherjee254@gmail.com).