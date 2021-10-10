# CSV Reading and Database storage program
A CLI application that reads records from a CSV, cleans them and then stores the clean records in a MYSQL database. Also allows user retrieval of a record.

---

### Architecture
This was developed in an MVC pattern, with use of the Data Access Object pattern for the finding employee record method.

---
### Features available

* Can read in any number of records from specified CSV files into a MYSQL database.
* Uses multithreading to speed up initial data Insertion (uses 50 threads currently to give 1.38 seconds insertion of 65499 records)
* Checks for duplicate records
* Checks for records with invalid fields and removes them from the clean files
* Reports number of unique, invalid, duplicate and clean records.
* Reports time taken to complete the insertion function.

---
### Future improvements

* Allowing the user to edit a user record
* Allow for deletion of a user record
* Allow for getting multiple user records

---
### Known bugs

No steps have been taken to preserve data integrity so unexpected unproduceable issues may pop up because of this.
