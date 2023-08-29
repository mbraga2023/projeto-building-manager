# projeto-building-manager

Spring Security Project: Product Sales Blog
This project focuses on implementing Spring Security methods for a Product Sales Blog. It includes features like database login authentication, authenticated routes, an administrative page for managing users and posts (CRUD operations), hashed passwords, and user functionality to post articles with pictures.

Features
Database Login Authentication: Users can log in using their credentials, which are authenticated against the database.

Authenticated Routes: Different routes are accessible based on user roles. Users are redirected to appropriate pages based on their roles after login.

Administrative Page: Admin users can manage user information and posts. CRUD operations are supported for both users and posts.

Hashed Passwords: User passwords are securely hashed before storing them in the database.

User Functionality: Authenticated users can create and post articles. They can also include pictures along with their articles.

Project Structure
The project consists of multiple controllers, models, services, and security configuration files. Below is an overview of the main components:

Controllers
LoginController: Handles login, registration, and redirection based on user roles.
ProductController: Manages products, including deletion and editing.
UploadController: Handles uploading product information along with images.
UserController: Manages user-related routes and post details.
Models
ProductModel: Represents product information, including title, author, contact, description, price, image, date, and audit information.
Security Configuration
SecurityConfig: Configures Spring Security settings, including password encoding, authentication success handling, and access control based on roles.

Getting Started

Clone the repository to your local machine.
Set up your database configurations in application.properties file.
Run the application and navigate to the appropriate routes based on user roles.
Usage
Register an account or log in with existing credentials.
Authenticated users can access and manage products.
Admin users can also manage users and perform CRUD operations on products.
Authenticated users can create and publish articles with pictures.
Contributors
Michel Braga  - (https://github.com/mbraga2023/)
License
This project is licensed under the MIT License.

Acknowledgments
Spring Boot
Spring Security
