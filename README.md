**Project Name: ExamenUF3 (Final Exam - UF3 Programació)**

**Description:**
This project served as the final exam for the UF3 Programació (Programming) course. It is a Java application designed to manage classroom information. It allows users to create, delete, and display details of classrooms. The application provides functionalities such as manual creation of classroom data, automatic generation of demo data, displaying data of individual classrooms, displaying data of all classrooms, loading specific data files, and deleting data.

**File Structure:**
```
ExamenUF3/
│
├── data/             // Directory containing data files
│   ├── 2023-05-17/
│   │   ├── 11:56:21.csv
│   │   ├── 16:09:01.csv
│   │   └── 23:41:09.csv
│   ├── 2023-05-21/
│   │   └── 19:29:59.csv
│   ├── 2023-05-22/
│   │   └── 10:13:43.csv
│   └── 2023-05-23/
│       └── 19:52:46.csv
│
├── ExamenUF3.iml      // IntelliJ IDEA project file
│
└── src/               // Source code directory
    └── net/
        └── xeill/
            └── elpuig/
                ├── Aula.java         // Class for representing a classroom
                ├── Controlador.java  // Class for managing classrooms
                └── Main.java         // Main class containing the application logic
```

**Features:**
1. **Manual Creation of Classroom Data:** Users can manually input data such as code, name, capacity, and number of computers for a classroom.
2. **Automatic Generation of Demo Data:** Demo data is automatically generated for testing purposes.
3. **Display Data of Individual Classroom:** Users can view details of a specific classroom by providing its ID.
4. **Display Data of All Classrooms:** Details of all classrooms can be displayed.
5. **Load Specific Data Files:** Users can load data from specific files.
6. **Delete Data:** Users can delete specific data files or clear all data.

**Instructions:**
1. **Manual Creation:** Select option 1 from the menu and provide the required details.
2. **Automatic Generation:** Select option 2 to generate demo data automatically.
3. **Display Data:** Choose options 3 or 4 to display data of individual classrooms or all classrooms, respectively.
4. **Load Data:** Select option 5 to load data from specific files.
5. **Delete Data:** Choose option 6 to delete specific data files or clear all data.

**Note:** Ensure that data files are located in the `data` directory with the appropriate structure (YYYY-MM-DD/hh:mm:ss.csv) for the application to load them correctly.
