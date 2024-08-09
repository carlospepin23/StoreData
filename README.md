# eStore Final Project

This is the final project for the Ironhack course, an eStore application built with React for the frontend and Java Spring Boot for the backend.

## Table of Contents

- [Introduction](#introduction)
- [Features](#features)
- [Installation](#installation)
- [Backend Setup](#backend-setup)
- [Frontend Setup](#frontend-setup)
- [Database Setup](#database-setup)
- [Usage](#usage)
- [Technologies Used](#technologies-used)
- [License](#license)

## Introduction

The eStore application allows users to view and manage stores and their departments. It includes features such as adding, editing, and deleting stores, as well as viewing store details.

## Features

- View a list of stores
- Add a new store
- Edit store details (name, location & departments)
- Delete a store
- View store departments
- Add new departments
- Edit departments details (employees & products)
- Delete departments
- View departments' employees/sellers & products
- Add new employees & products
- Edit employees & products details
- Delete employees & products

## Installation

To run this project locally, follow these steps:

### Backend Setup

1. Clone the repository:
    ```sh
    git clone https://github.com/carlospepin23/estore-final-project.git
    ```
2. Navigate to the backend directory:
    ```sh
    cd estore-final-project/backend
    ```
3. Open the project in IntelliJ IDEA.
4. Install the dependencies and build the project.
5. Run the Spring Boot application.

### Frontend Setup

1. Navigate to the frontend directory:
    ```sh
    cd estore-final-project/frontend
    ```
2. Install the dependencies:
    ```sh
    npm install
    ```

### Database Setup

1. Install MySQL and DBeaver.
2. Create a new database in MySQL.
3. Update the database configuration in the `application.properties` file located in the `src/main/resources` directory of the backend project:
    ```properties
    spring.datasource.url=jdbc:mysql://localhost:3306/your-database-name
    spring.datasource.username=your-username
    spring.datasource.password=your-password
    ```

## Usage

To start the development server, run:

### Backend

In IntelliJ IDEA, run the Spring Boot application.

### Frontend

In the terminal, navigate to the frontend directory and run:

```npm run dev```

This will start the application on http://localhost:5173.

### Technologies Used

Frontend:

- React
- CSS
- JavaScript
- Visual Studio Code
  
Backend:

- Java
- Spring Boot
- IntelliJ IDEA

Database:

- MySQL
- DBeaver

### License
This project is licensed under the MIT License. See the LICENSE file for details.
