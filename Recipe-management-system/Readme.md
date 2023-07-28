# Recipe Management System API


## Features

- Create, Read, Update, and Delete recipes
- Add comments to recipes
- Add likes to recipes
- Search for recipes by recipeId and Owner
- Validation using annotation-based validation
- Ownership verification to allow only the post owner to update recipes other user only like and comments on recipe post


## Technologies Used(Language and Framework)

- Java
- Maven
- Spring Boot
- Spring Data JPA
- MySQL
- Swagger (for API documentation)
- lombok
- validation
- AWS service for deployment publicly


## IDE
- IntelliJ IDEA COMMUNITY EDITION 2023.1.2


## MVC Model-View-Controller (MVC) Architecture(DATAFLOW)
- Project Structure (MVC)
- The project follows the Model-View-Controller (MVC) architecture to organize the codebase and separate responsibilities:


1. Model - Entities
- dto - signInInput - SignUpOutPut
- Enum - Gender
- AuthenticationToken
- Comment
- Like
- User
- Recipe
2. Controller Classes - handle all the api request and response to endpoint:this connect with service classes
- UserController
- RecipeController
- LikeController
- CommentController

3. Service Classes - In these classes we provide all the Business Logic of all methods:this connect with repository classes
- HasingUtility-PasswordEncrupter:
- UserService
- RecipeService
- LikeService
- CommentService
- AuthenticationsService

4. Repository Classes - these classes deals with database
- IAuthenticationRepo
- IUserRepo
- IRecipeRepo
- ILikeRepo
- ICommentRepo

## DataStructure used
- List

## DataBase Design
- mySql

## Setup and Installation

1. Clone the repository:


git clone https://github.com/ahmedraza6377/MCT_Recipe_Managment_System.git
cd MCT_Recipe_Managment_System

2. Set up MySQL database:

- Create a MySQL database named `recipe_management` (you can use a different name if desired).
- Update the database connection properties in the `application.properties` file to match your MySQL configuration.

3. Build and run the application:



The application will be accessible at `http://43.204.114.169:8080/swagger-ui/index.html#/`.

## API Endpoints

The API provides the following endpoints:

- `GET /api/recipes`: Get a list of all recipes.
- `GET /api/recipes/{id}`: Get a specific recipe by ID.
- `GET /api/recipes/owner/{ownerId}`: Get all recipes of a specific owner by ownerId.
- `POST /api/recipes`: Create a new recipe.
- `PUT /api/recipes/{id}`: Update an existing recipe (only allowed by the post owner).
- `DELETE /api/recipes/{id}`: Delete a recipe (only allowed by the post owner).
- `GET /api/recipes/{recipeId}/comments`: Get all comments for a specific recipe.
- `POST /api/recipes/{recipeId}/comments`: Add a new comment to a recipe.

## API Documentation

The API documentation is available at `[/swagger-ui.html](http://43.204.114.169:8080/swagger-ui/index.html#/)` when the application is running. It provides detailed information about all the available endpoints, request and response models, and allows testing the API directly from the documentation.

## Authors

- [Ahmedraza6277](https://github.com/ahmedraza6377)

## Summary
- The Recipe Management System API is a Java-based Spring Boot application that allows users to store and manage recipes. Users can create, read, update, and delete recipes, along with adding comments and likes to recipes. The application uses a MySQL database for data storage and supports CRUD operations on recipes and comments.

