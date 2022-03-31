# Health Management System: inSalute

Assignment for [Software Development Process (blended)](https://elearning.unimib.it/pluginfile.php/827378/mod_resource/content/6/Syllabus_Processo_e_Sviluppo_v1.pdf) college course. <br>
The application is an implementation of the [previous assignment](https://gitlab.com/DavideMammarella/2020_assignment2_insalute) in which the requirements were elicited.

## Application

The application is a telemedicine software backend solution for medical reports management that improves the life of the patient and the doctor by connecting them digitally.<br>
The mission is to provide a concrete aid to the management of one's health through a medical reports exchange platform with doctors for a simple, safe and effective experience.

The application present a **backend** structure that includes data persistence via **Spring Data JPA** and has also been made extensible to a possible **frontend** by adding endpoints via **Spring Web**.
In order to verify the correct functioning of **CRUD** (Create, Read, Update, Delete) and **search** operations, 
**integration tests** for each entity are available. The same tests can be done directly by querying the application via **HTTP methods**.<br>

In the current state of the application we can:
- _Create, read, update, delete_ reports, blood tests and covid tests (and their associated entities),
- _Create, read, update, delete_ doctors (and their associated entities) and also _search_ for doctors associated with a patient,
- _Create, read, update, delete_ patients (and their associated entities),
- _Create, read, update, delete_ health facilities and medical laboratories, including those internal to facilities (and their associated entities).

Java documentation detailing the code is made available [here](https://davidemammarella.gitlab.io/insalute_backend_doc).

## Instructions for installing and running the application

### Prerequisites
**Java**, **Docker** and **Git** must be installed on your machine.

### Download
You can download the compressed file from this page and extract 
it to a folder of your choice, alternatively you can install it directly 
from a terminal using one of the following _Clone_ commands:

Clone with HTTPS:
```
git clone https://github.com/DavideMammarella/Health-Management-System.git
```
Clone with SSH:
```
git clone git@github.com:DavideMammarella/Health-Management-System.git
```

### Install
Access the application folder:
```
cd Health-Management-System/
```
"Install" the application:
```
mvn package -Dmaven.test.skip=true
```

### Run
Start the application (and the PostgreSQL Database):
```
docker-compose up -d --build
```
The application is accessible at the following address (see Control Layer section for more information):
```
http://localhost:8080
```

## Additional features

### Database customization
The **schema** and the **data** of the database are **loaded automatically** via SQL files. <br>
You can disable this features (individually or both) by editing the `docker-compose.yml` file as follows:
- **Table creation**: comment line _33_
  - You must also uncomment line _17_ in order for the application to work properly (this way the tables will be generated directly from the data access layer of the application)
- **Data insertion**: comment line _35_

Further documentation on how to work independently on the database can be found in the _README_ file present in `src/main/resources/db`

### Continuous Development
The application uses a continuous development pipeline to perform **style checking** via checkstyle and **integration testing** using an in-memory database (H2).<br>
In this way, the quality of the code and the correct functioning of the data access layer of the application and therefore of all the entities and their CRUD operations (plus search) is ensured.

### Control Layer
Even though the application is backend, it has a **service** and **control** layer, facilitating a possible extension to a complete spring application (it's possible to apply a view at will).<br>
The control layer makes available endpoints listed below, which during development can be used to test the application with the production database.<br>
In this way there is the possibility to test both the service and control layers (with the production database), leading to a complete testing of the application since the model and repository layers are tested by test classes (with the in-memory database).

The endpoints that can currently be used are:
| **Method** | **Endpoint** | **Description** |
| --- | --- | --- |
| `POST`, `GET`, `DELETE` | /api/doctors | You can perform CRUD operations on the doctor entity |
| `POST`, `GET`, `DELETE` | /api/patients |  You can perform CRUD operations on the patient entity |
| `GET` | /api/patients/doctors_patient/{id} |  You can search for doctors associated with a patient by entering the patient ID |
| `POST`, `GET`, `DELETE` | /api/health_facilities | You can perform CRUD operations on the health facility entity |
| `POST`, `GET`, `DELETE` | /api/medical_reports | You can perform CRUD operations on the medical report entity |
| `POST`, `GET`, `DELETE` | /api/medical_reports/covid_tests | You can perform CRUD operations on the covid test entity |
| `POST`, `GET`, `DELETE` | /api/medical_reports/blood_tests |  You can perform CRUD operations on the blood test entity |