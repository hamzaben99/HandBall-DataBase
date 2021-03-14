# SGBD Project: Fédération sportive de Hand-ball

This is the depository of the SGBD Project. Welcome !

This project consists of creating a database to manage handball matches between clubs in a federation and it involves the creation of a relational database and the implementation of a number of operations (consultations, statistics, updates, etc.).

***

## Team members

- Elomari Alaoui Ismail
- Boullit Mohamed Fayçal 
- Benmendil Hamza 
- El hammadi Mouhcine

## Project description

*This deposit contains four folders :*

- sqlScripts : contains SQL files.

- repport : contains the repport of the project (pdf file).

- src : contains JAVA source files used for:

	- JDBC connection with the database.

	- Interface implementation.

- build : (created dynamically with "make") : contains all the files with the extention .class it is generated after the commande make.

- HTML : (created dynamically) after using consultation or statistic option in the principal menu, it contains all file with extension .html.

## Requirement

Using the machine Oracle present at ENSEIRB-MATMECA, The project can be builded and executed with no changes.

Using an other machine, the following instructions are essential:

- Installing The Oracle Database 11g.

- Creating data and tables using SQLPLUS.

- Updating The username/password/URL in Main.java present in src folder.

## Build/Run

- The command "make" compiles the source files.

- The command "make run" execute the Main, and allows an access to the principle Menu.

- The command "make clean" cleans the deposit from any executables, and deletes the HTML/build folders.