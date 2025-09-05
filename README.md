# Fit Zone 💪

## Description
Fit Zone is an application designed to help users manage a gym. Built with Java, JDBC, and Maven, it follows a clean architecture with modular layers to ensure scalability and maintainability.

## Architecture
The project is structured in the following layers:
- **Application**: Contains the main entry point and application setup
- **Config**: Manages application and database configuration settings
- **Connection**: Manages database connectivity
- **Domain**: Defines domain entities and models
- **Repository**: Implements Data Access Objects (DAO) for database operations

## Technologies Used
- Java
- JDBC (Java Database Connectivity)
- Maven
- MySQL (Database)

## Prerequisites
- JDK 8 or higher
- Maven 3.6.x or higher
- MySQL Server

## Installation

1. Clone the repository:
```bash
git clone https://github.com/your-username/fit-zone.git
```

2. Navigate to the project directory:
```bash
cd fit-zone
```

3. Build the project with Maven:
```bash
mvn clean install
```

## Database Configuration
1. Create a MySQL database
2. Configure the database credentials in the configuration file (resources/config/.env)

## Execution
To run the application:
```bash
mvn exec:java -Dexec.mainClass="com.fitzone.Main"
```

## Project Structure
```
src/
├── main/
│   ├── java/
│   │   ├── application/   # Main application entry point
│   │   ├── config/        # Configuration classes
│   │   ├── connection/    # Database configuration
│   │   ├── domain/        # Entities and models
│   │   └── repository/    # Data Access Objects (DAO)
│   └── resources/         
│       └── config/        # Configuration files (.env)
└── test/
    └── java/              # Unit and integration tests
```

## Contributing
To contribute to the project:
1. Fork the repository
2. Create a feature branch (`git checkout -b feature/new-feature`)
3. Commit your changes (`git commit -am 'Add new feature'`)
4. Push to the branch (`git push origin feature/new-feature`)
5. Create a Pull Request

## License
This project is licensed under the MIT License. See the `LICENSE` file for details.
