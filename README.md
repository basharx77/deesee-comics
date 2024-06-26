# Deesee Comics - Superhero Distribution Platform
This project is a backend solution designed to manage and distribute information about superheroes. The tasks focus on implementing string encryption, domain modeling, and creating a webservice using Spring Boot.

# String Encryption
The project includes a custom encryption algorithm, the "DeeSee Chiffre," which shifts each letter of a superhero's identity by a given key.

# Domain Modeling
A comprehensive domain model is developed to manage superheroes and their attributes, with a focus on specific superpowers such as strength, speed, flight, invulnerability, and healing.

# REST Endpoint
A RESTful API is implemented to handle the storage and retrieval of superheroes. The API integrates the domain model and encryption functionality, providing one endpoint to:

1- Retrieve all superheroes <br>
2- Retrieve superheroes with encrypted identities <br>
3- Retrieve superheroes by specified superpowers <br>
4- Retrieve superheroes by specified superpowers with encrypted identities <br>

Example
A valid URL to retrieve all superheroes with encrypted identities that have both flight and strength superpowers:

__APPLICATION_HOST:8080/superheroes?encryptedIdentities=true&superpowers=flight,strength__

# Tests
The project includes both unit tests and integration tests to ensure the correctness and reliability of the code.
 
