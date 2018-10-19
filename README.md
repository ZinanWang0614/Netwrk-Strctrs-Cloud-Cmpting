# Netwrk-Strctrs-Cloud-Cmpting
## Assignment 1
**Using POSTMAN to test flowing urls.** <br>

**Some data was hard-coded into memory for test. Here's details:** <br>
```json
[
    {
        "courseList": [
            {
                "board": [
                    {
                        "content": "This is our first assignment,welcome everyone",
                        "id": 1,
                        "subject": "Welcome"
                    }
                ],
                "courseId": 1,
                "courseName": "Info5100",
                "enrolledStu": [
                    {
                        "enrolledCourses": [
                            "Info5100"
                        ],
                        "image": "none",
                        "programName": "msis",
                        "studentId": 1,
                        "studentName": "zinan"
                    }
                ],
                "lectures": [
                    {
                        "associatedMaterials": "Not now",
                        "lectureId": 1,
                        "lectureName": "week 1",
                        "notes": "noets"
                    }
                ],
                "professor": {
                    "department": "Amazon",
                    "firstName": "Ami",
                    "joiningDate": "2018-10-17T20:56:23.34",
                    "professorId": 1
                },
                "roster": [
                    {
                        "name": "zinan"
                    }
                ],
                "ta": {
                    "enrolledCourses": [
                        "Info5100"
                    ],
                    "image": "none",
                    "programName": "msis",
                    "studentId": 1,
                    "studentName": "zinan"
                }
            }
        ],
        "programId": 1,
        "programName": "MSIS"
    }
]
```


## URLs - the root url is localhost:8080/courseservice/webapi
### Program
#### • Get all the programs in the system <br>  
- GET: /programs <br>
#### • Add a program into the system. The input should be json format.
- *POST: /programs* <br>
- Input needs to be JSON format. Sample input:<br>
```json
{
	"programName" : "CSYE"
}
```
#### • Update the program info
-  *PUT: /programs/{programid}.*  <br>
- **For example:    /programs/1** <br>
-  Input needs to be JSON format. Sample input:<br>
```json
{
	"programName" : "CSYE"
}
```
#### •   Delete the program by program id from the system <br>
- *DELETE: /programs/{programid}*<br>  
- **For example:    /programs/1**<br>


### Course - basic
**Course is sub-resource of program** <br>
**There is no PUT method in course part, because all the parts of a course can be edit by some other urls next, <br>
no need for a indepentent PUT method.**

#### • Get all courses of the program based on program id.
- *GET: /programs/{programid}/classes* <br> 
- **For example:    /programs/1/classes** <br>  

#### • Add course into the program. <br>  
- *POST: /programs/{programid}/classes*<br>
- **For example:    /programs/1/classes** <br>
- Input needs to be JSON format. Sample input:<br>
```json
{
	"courseName" : "6250"
}
```

#### • Get a course by id. <br>
- GET: /programs/{programid}/classes/{courseid}
- **For example:    /programs/1/classes/1**

### Course - Enrolled Students
#### • Get all the enrolled students of the course. 
- GET: /programs/{programid}/classes/{courseid}/students <br>
- **For example:    /programs/1/classes/1/students**

#### • Add a student to enrolled student list of the course
- POST: /programs/{programid}/classes/{courseid}/students/{studentid} <br>
- **For example:    /programs/1/classes/1/students/1**<br>
- No json/any other input is required, since the url add a student into the enrolled from the student DB in the system, only need correct {studentid}
#### • Delete a student from the enrolled list
- DELETE: /programs/{programid}/classes/{courseid}/students/{studentid} <br>
- **For example:    /programs/1/classes/1/students/1**<br>
- Only delete from the enrolled list, not the system. <br>


### Course - Lectures
**Lecture is a sub-resource of course** <br>
#### • Get all lectures of the course
- GET: /programs/{programid}/classes/{courseid}/lectures <br>
- **For example:    /programs/1/classes/1/lectures**<br>

#### • Add a lecture to the course
- POST: /programs/{programid}/classes/{courseid}/lectures <br>
- **For example:    /programs/1/classes/1/lectures**<br>
- Input needs to be JSON format. Sample input:<br>
```json
{
        "associatedMaterials": "Not now",
        "lectureName": "week 1",
        "notes": "noets"
}
```
#### • Update a lecture
- PUT: /programs/{programid}/classes/{courseid}/lectures/{lectureid}<br>
- **For example:    /programs/1/classes/1/lectures**<br>
- Input needs to be JSON format. Sample input:<br>
```json
{
        "associatedMaterials": "Not now",
        "lectureName": "week 1",
        "notes": "noets"
}
```

#### • Delete a lecture
- DELETE: /programs/{programid}/classes/{courseid}/lectures/{lectureid}<br>
- **For example:    /programs/1/classes/1/lectures/1**<br>

### Course - Board
#### • Get all announcements on board
- GET: /programs/{programid}/classes/{courseid}/board <br>
- **For example:    /programs/1/classes/1/board**<br>

#### • Add an announcement on board
- POST: /programs/{programid}/classes/{courseid}/board <br>
- **For example:    /programs/1/classes/1/board** <br>
- Input needs to be JSON format. Sample input:<br>
```json
{
        "content": "This is our first assignment,welcome everyone",
        "subject": "Welcome"
}
```
#### • Update an announcement on board
- PUT: /programs/{programid}/classes/{courseid}/board/{anid}
- **For example:    /programs/1/classes/1/board/1** <br>
- Input needs to be JSON format. Sample input:<br>
```json
{
        "content": "This is our first assignment,welcome everyone",
        "subject": "Welcome"
}
```

#### • Delete an announcement on board
- DELETE: /programs/{programid}/classes/{courseid}/board/{anid}
- **For example:    /programs/1/classes/1/board/1**

### Course - Professor
#### • Get the professor of the course
- GET: /programs/{programid}/classes/{courseid}/professor
- **For example:    /programs/1/classes/1/professor**
#### • Add/change a professor
- POST: /programs/{programid}/classes/{courseid}/professor/{profid}<br>
- **For example:    /programs/1/classes/1/professor/1** <br>
- No json input is needed, will add professor in the system based on professor id in the url.

#### • Delete the professor
- DELETE: /programs/{programid}/classes/{courseid}/professor/{profid}
- **For example:    /programs/1/classes/1/professor/1**

### Course - TA
#### • Get the Ta
- GET: /programs/{programid}/classes/{courseid}/ta
- **For example:    /programs/1/classes/1/ta/1**
#### • Add/change the ta
- POST: /programs/{programid}/classes/{courseid}/ta/{studnetid}<br>
- **For example:    /programs/1/classes/1/ta/1** <br>
- No json input is needed, will add professor in the system based on professor id in the url.
#### • Delete the Ta
- DELETE: /programs/{programid}/classes/{courseid}/ta/{studentid} <br>
- **For example:    /programs/1/classes/1/ta/1** <br>

### Course - Roster
**Roster is generated by enrolled student list, so only GET method implemented.**<br>
#### • Get the roster
- GET: /programs/{programid}/classes/{courseid}/roster <br>
- **For example:    /programs/1/classes/1/roster** <br>

### Professor
#### • Get all professors in the system
- GET: /professors <br>
- **For example:    /professors** <br>
#### • Get professor by id
- GET: /professors/{profid} <br>
- **For example:    /professors/1** <br>
#### • Add a professor in the system
- POST: /professors
- **For example:    /professors** <br>
- Input needs to be JSON format. Sample input:<br>
```
{
    "department": "Amazon",
    "firstName": "Ami",
    "joiningDate": "2018-10-18T18:33:02.973"
}
```

#### • Delete a professor
- DELETE: /professors/{profid}
- **For example:    /professors/1** <br>

#### • Update a professor
- PUT: /professors/{profid}
- **For example:    /professors/1** <br>
- Input needs to be JSON format. Sample input:<br>
```
{
    "department": "Amazon",
    "firstName": "Ami",
    "joiningDate": "2018-10-18T18:33:02.973"
}
```

### Student
#### • Get all students
- GET: /students
- **For example:    /students** <br>
#### • Add a student
- POST: /students
- **For example:    /students** <br>
- Input needs to be JSON format. Sample input:<br>
```json
{
    "enrolledCourses": [
        "Info5100"
    ],
    "image": "none",
    "programName": "msis",
    "studentName": "zinan"
}
```
#### • Delete a student
- Delete: /students/{studentid}
- **For example:    /students/1** <br>
#### • Update a student
- PUT: /students/{studentid}
- Input needs to be JSON format. Sample input:<br>
```json
{
    "enrolledCourses": [
        "Info5100"
    ],
    "image": "none",
    "programName": "msis",
    "studentName": "zinan"
}
```
