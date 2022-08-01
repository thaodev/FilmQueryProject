# FilmQueryProject
#Description
This App to facilitate searching information about films, including but not limit to inventory for rental, duration, actors, language, rating, and so forth. Information is extracted from multiple tables on database.

#How to use this program
The user is presented with three options:
(1) Look up a certain film by film id.
(2) Look up  a film by search a key word (can be found in either title or description).
(3) Quit program.
For either first two options, if the program found the searched film, it will print out title, year, rating, language, list of actors and description.
When a film is displayed, user is presented a submenu to print all remaining details, including but not limit to category, inventory and their condition.
The program will prompt the user for menu selections until the user chose to quit, which is the third option.


#Technologies Used
- My SQL
- Java
- Driver for DB Connection, POM file for setup Driver using dependency in Maven
- MAMP for local server

#Lessons Learned
- My SQL syntax to select, filter data from table or multiple tables
- Using "%" to look up key word
- Mapping Objects to corresponding tables
- Reading ERDs to navigating the connection between tables
