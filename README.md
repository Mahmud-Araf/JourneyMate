# JourneyMate: Tour Management System
---
<p align="center">
  <img src="src/img/cover.jpeg" alt="Project Image">
</p>
---

## Table of Contents
- [Current Version](#current-version)
- [Outline](#outline)
- [In Details](#in-details)
- [How To Install & Run](#how-to-install--run)
  - [Download & Installation](#download--installation)
  - [Project SetUp](#project-setup)
  - [Build & Run](#build--run)
- [Platform, Library & Tools](#platform-library--tools)
- [Limitations](#limitations)
- [Reference](#reference)
- [Author Info](#author-info)
- [YouTube Link](#youtube-link)

## Current Version
1

---

## Outline
This is an academic project of the course CSE-2112 for the 2nd year 1st semester in the Department of Computer Science and Engineering at the University of Dhaka. This project is developed with JavaFX and MySQL database.

---

## In Details
The idea behind tour management software is to help tourism companies that organize guided tours manage customer information, tour information, and back-office activities. This type of software helps tour operators keep track of organizational aspects of tours such as scheduling, itinerary, meals, and more. Additionally, it tracks tourists and attendance, as well as payments and expenses. Some tour management software also provides features such as online booking, marketing, Customer Relationship Management (CRM), inventory management, and reporting. The main benefits of using tour management software are to save time, increase revenue, improve customer service, and streamline operations.

---

## How To Install & Run
---
### Download & Installation
---
- Install VSCode: [VSCode](https://code.visualstudio.com/download)
- Install JDK-20, JavaFX, and MySQL using these command line arguments for Linux.
~~~
sudo apt-get update
sudo apt-get install openjdk-20-jdk 
sudo apt-get install mysql-server
sudo mysql_secure_installation
~~~
- Download JavaFX SDK 20 from here: [JavaFX SDK](https://openjfx.io)
---

### Project SetUp
---
1. Clone the git repository in any folder.
```
git clone https://github.com/Mahmud-Araf/JourneyMate.git
```

2. Open the cloned folder in VSCode.

3. In the `.vsode` folder, go to the `settings.json` file and update the following paths with the absolute path of the JavaFX `.jar` files.
```
"java.classPath": [
    "lib/**/*.jar",
    "path/to/javafx/lib/javafx.base.jar",
    "path/to/javafx/lib/javafx.controls.jar",
    "path/to/javafx/lib/javafx.fxml.jar",
    "path/to/javafx/lib/javafx.graphics.jar",
    "path/to/javafx/lib/javafx.media.jar",
    "path/to/javafx/lib/javafx.swing.jar",
    "path/to/javafx/lib/javafx.web.jar",
    "path/to/javafx/lib/javafx-swt.jar"
    ],
```
```
"java.project.referencedLibraries": [
    "lib/**/*.jar",
    "path/to/javafx/lib/javafx.base.jar",
    "path/to/javafx/lib/javafx.controls.jar",
    "path/to/javafx/lib/javafx.fxml.jar",
    "path/to/javafx/lib/javafx.graphics.jar",
    "path/to/javafx/lib/javafx.media.jar",
    "path/to/javafx/lib/javafx.swing.jar",
    "path/to/javafx/lib/javafx.web.jar",
    "path/to/javafx/lib/javafx-swt.jar"
    ]
```

4. Install the [MySQL](https://marketplace.visualstudio.com/items?itemName=formulahendry.vscode-mysql) extension and set up a localhost connection ([Link](https://youtu.be/wzdCpJY6Y4c)) using it. Then, right-click the localhost and click `New Query`. Paste the MySQL commands from `src/MySQL Database/MySQLQuery.sql` and run them. It will create a local database named **JourneyMate** in your local database.

---

### Build & Run
---
- Go to `src/Main.java` and run the file by clicking the Run option over the `main()` function.
- You will see an error: "Error: JavaFX runtime components are missing, and are required to run this application."
- Now, go to the `launch.json` file and paste this snippet where the `"mainClass": "Main"` code is:
```
"vmArgs": "--module-path /usr/share/javafx20/lib --add-modules javafx.controls,javafx.graphics,javafx.fxml -cp /home/araf/Projects/JourneyMate/lib/mysql-connector-j-8.0.32.jar",
```
Like this:
```
    {
        "type": "java",
        "name": "Main",
        "request": "launch",
        "mainClass": "Main",
        "vmArgs": "--module-path /usr/share/javafx20/lib --add-modules javafx.controls,javafx.graphics,javafx.fxml -cp /home/araf/Projects/JourneyMate/lib/mysql-connector-j-8.0.32.jar",
        "projectName": "JourneyMate_818d93e8"
    },
```
- Again, go to `src/Main.java` file and run it by clicking the Run option over the `main()` function.

---

## Platform, Library & Tools
---
#### Programming Languages

- Java

#### Libraries

- JavaFX

#### Database

- MySQL

#### UI Designing Tool

- CSS, FXML, Scene Builder

#### Extra Tools

- Git/GitHub

---

## Limitations
---
- UI design took a lot of time because we built design libraries from scratch, and we used little to no pre-built object libraries.
- Most of the work was done using JavaFX. We used only available images or other design elements for our Java project. The color we chose for our Java project was very dull.
- The table we use to show our client/package/spot status is not editable on the spot. We have to use an on-spot edit feature to edit our data. Data orientation was dull.
- We don't go through the depth of one feature. The feature of showing the info of the client has a bug. After pressing the search button, we can see the data table.
- User information is not updatable.

---

## Reference
---
- [JavaFx](https://openjfx.io/)

---

## Author Info
---
- Abdullah Al Mahmud - [@Mahmud-Araf](https://github.com/Mahmud-Araf)
- Rafi Al Sad Rifat - [@Rafi-Rifat](https://github.com/Rafi-Rifat)
- Md. Shawn - [@Shawn-33](https://github.com/Shawn-33)

---

## YouTube Link
---
[https://www.youtube.com/watch?v=VTvyJwNl16c](https://www.youtube.com/watch?v=VTvyJwNl16c)

---
