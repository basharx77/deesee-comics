
# Deesee comics


This is a Spring Boot application that can be executed by calling the static __main__ method in the  __DeeseeComicsApplication__ class, which starts an embedded web server running by default on __8080 port__.

This application exposes only one REST endpoint with the __/superheroes__ suffix, which can be used to retrieve the stored superheroes in the system.


This endpoint can accept up to two query parameters named __encryptedIdentities__ and __superpowers__.

 __encryptedIdentities__ : A query parameter of type boolean used to define whether the identities of the retrieved superheroes should be encrypted.
 
__superpowers__ : A query parameter of type EnumSet used to define the superpowers that the retrieved superheroes should have. All possible superpower values can be found in __com.example.deeseecomics.api.dto.Superpower__.

Here is a valid URL that can be used to retrieve all superheroes with the flight superpower in encrypted mode:

__APPLICATION_HOST:8080/superheroes?encryptedIdentities=true&superpowers=flight__
