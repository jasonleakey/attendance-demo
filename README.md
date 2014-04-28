Attendance System Demo
===============
Introduction
------------
This is a simple web application running on Google App Engine.
Please visit [http://attendance-demo.appspot.com](http://attendance-demo.appspot.com) for demo. 
Author: Yetian Huang

File structure
--------------
.
├── attendance_system-ear
│   ├── attendance_system-ear.iml
│   ├── pom.xml
│   └── src
│       └── main
│           └── application
│               └── META-INF
│                   ├── appengine-application.xml
│                   ├── application.xml
│                   └── MANIFEST.MF
├── attendance_system.iml
├── attendance_system-war
│   ├── attendance_system-war.iml
│   ├── pom.xml
│   └── src
│       ├── main
│       │   ├── java
│       │   │   └── edu
│       │   │       └── utd
│       │   │           └── ooad
│       │   │               ├── controller
│       │   │               │   └── DropDownBoxController.java
│       │   │               ├── domain
│       │   │               │   ├── AttendanceRecord.java
│       │   │               │   ├── ClassRoster.java
│       │   │               │   ├── Clazz.java
│       │   │               │   ├── PersistentStorageHandler.java
│       │   │               │   ├── Person.java
│       │   │               │   ├── Professor.java
│       │   │               │   └── Student.java
│       │   │               ├── model
│       │   │               │   ├── SearchResult.java
│       │   │               │   └── Selection.java
│       │   │               └── validator
│       │   │                   └── SelectionValidator.java
│       │   ├── resources
│       │   │   ├── data
│       │   │   │   ├── attendRec001-2014_02_11.csv
│       │   │   │   ├── attendRec001-2014_04_16.csv
│       │   │   │   ├── attendRec002-2014_03_26.csv
│       │   │   │   ├── attendRec002-2014_05_12.csv
│       │   │   │   ├── ClassRoster-001.csv
│       │   │   │   └── ClassRoster-002.csv
│       │   │   └── message.properties
│       │   └── webapp
│       │       ├── attendance_icon.ico
│       │       ├── index.jsp
│       │       ├── selection_success.jsp
│       │       ├── stylesheets
│       │       │   ├── checkbox.css
│       │       │   └── main.css
│       │       └── WEB-INF
│       │           ├── appengine-web.xml
│       │           ├── logging.properties
│       │           ├── mvc-dispatcher-servlet.xml
│       │           └── web.xml
│       └── test
│           └── java
├── pom.xml
└── README.md

23 directories, 37 files


