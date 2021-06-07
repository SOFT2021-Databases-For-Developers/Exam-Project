# Database For Developers 2021 Exam Project
- Jonatan Bakke https://github.com/JonatanMagnusBakke
- Jonas Hein - https://github.com/Zenzus
- Thomas Ebsen - https://github.com/Srax 
- Assignment - [assignment.pdf](/documents/assignment.pdf)
- Rapport - [rapport.pdf](/documents/dbd-exam-pdf.pdf)

## How to run
**Requirements:** Docker and a working linux kernel  
1. Open a CMD in the root of the project.
2. Run command:  **<code>docker compose build</code>** to build the images
3. Run command:  **<code>docker compose up</code>** to setup and run the project.

The setup may take some time to complete due to the large amount of cars we insert into postgresql, so make sure it has finished loading before querying. A sure way to know that has finished setting up (assuming docker didn’t crash) is to click on exam-project-postgres-api_1 to see the console.

It’s finished setting up once you see the “Alright we good” message in the console as pictured above.