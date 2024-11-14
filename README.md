# 🛒 Shopping Cart Application

A Jakarta EE-based web application that manages products and users in a shopping cart system. This project enables full CRUD operations on both products and users, with a responsive, Bootstrap-styled interface for an enhanced user experience.

## Features
- **Built with Jakarta-EE**: Provides enterprise-level functionality and structure.
- **Add Products and Users**: Add new users and products with details like name, price, and quantity.
- **Update Details**: Modify product quantities, prices, and user information.
- **View Lists**: Access organized lists for products and users.
- **Remove Items**: Delete products and users from the database.
- **Database Integration**: Uses MySQL with JPA and Hibernate for persistent data storage.
- **Annotations with CDI**: Streamlines object management with CDI annotations.
- **Styled with Bootstrap**: Provides a polished, responsive UI.

## 📖 Usage
- **Product and User Forms** - Use the forms to add or update information.
- **View Lists** - Access organized lists for both products and users.
- **Database Sync** - Data is saved in the MySQL database, allowing persistent session storage.
- **Perform CRUD Operations** - Create, read, update, and delete entries with full database synchronization.

## 🛠️ Tech Stack
- **Java (Jakarta-EE)** - Core language and platform.
- **CDI Annotations** - For dependency injection.
- **JPA and Hibernate** - ORM for database management.
- **Maven** - Dependency management.
- **MySQL** - Database for storing data.
- **JSP** - For dynamic pages.
- **Bootstrap** - UI styling.

## 🚀 Installation
1. **Clone the Repository**:
   ```bash
   git clone https://github.com/Joselam97/Shopping-Cart.git

2. **Navigate to the Project Directory**:
   ```bash
   cd Shopping-Cart

3. **Set up the SQL Database**:
- Import the provided .sql tables located in the resources directory into MySQL.
- Update the database connection settings in the project configuration to match your MySQL credentials.

4. **Build the Project with Maven**:
   ```bash
   mvn clean install

6. **Deploy the Application**:
- Deploy the project on a Jakarta EE Tomcat server.

  
6. **Access the Application**:
- Open a web browser and access the application via your server's URL.
