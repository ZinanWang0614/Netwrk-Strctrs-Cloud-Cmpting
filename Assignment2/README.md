# Netwrk-Strctrs-Cloud-Cmpting
# Assignment 2 -- RESTful APIs

## Professors

#### Get All Professors
|     Url   | Input  |Example|
| ------| ------ |------ |
| GET:/professors | * |/professors |

#### Get Professor By Professor Id
|     Url   | Input  |Example|
| ------| ------ |------ |
| GET:/professors/{professorId} | * |/professors/zinan1 |

#### Add a Professor
|     Url   | Input  |Example|
| ------| ------ |------ |
| POST:/professors | Json |/professors|

Sample Input<br>
```json
{
        "department": "CS",
        "firstName": "zhang",
        "joiningDate": "2018-01-01"
}
```
*professor Id is generated as "firstname + number" for uniqueness*

#### Update a Professor
|     Url   | Input  |Example|
| ------| ------ |------ |
| PUT:/professors/{professorId} | Json |/professors/zinan1|

Sample Input<br>
```json
{
        "department": "Music",
        "firstName": "wang",
        "joiningDate": "1999-12-12"
}
```

*Professor Id will not be changed with other attributes*

#### Delete a Professor
|     Url   | Input  |Example|
| ------| ------ |------ |
| DELETE:/professors/{professorId} | * |/professors/zinan1|

## Students

#### Get All Students
|     Url   | Input  |Example|
| ------| ------ |------ |
| GET:/students| * |/students|

#### Get Student By Student Id
|     Url   | Input  |Example|
| ------| ------ |------ |
| GET:/students/{studentId} | * |/students/stu1.wang |

#### Add a Student
|     Url   | Input  |Example|
| ------| ------ |------ |
| POST:/students | Json |/students|

Sample Input<br>
```json
{
    "department": "Csye",
    "firstName": "stu4",
    "joiningDate": "1995-11-19",
    "lastName": "li",
    "registerCourses": [
        "Info5100",
        "Csye6200"
    ]
}
```
*Student Id is generated as "firstname + lastname", for uniqueness, if some students have same first & lastname, a number will be added at the end of studentId, like stu1.wang and stu1.wang2*

#### Update a Student
|     Url   | Input  |Example|
| ------| ------ |------ |
| PUT:/students/{studentId} | Json |/students/stu1.wang|

Sample Input<br>
```json
{
    "department": "updated",
    "firstName": "stu1",
    "joiningDate": "1995-11-19",
    "lastName": "wang",
    "registerCourses": [
        "updated",
        "updated"
    ]
}
```

*Student Id will not be changed with other attributes*

#### Delete a Student
|     Url   | Input  |Example|
| ------| ------ |------ |
| DELETE:/students/{studentId} | * |/students/stu3.zhao|

## Courses

#### Get All Courses
|     Url   | Input  |Example|
| ------| ------ |------ |
| GET:/courses| * |/courses|

#### Get Course By Course Id
|     Url   | Input  |Example|
| ------| ------ |------ |
| GET:/courses/{courseId} | * |/courses/CS6200 |

#### Add a Course
|     Url   | Input  |Example|
| ------| ------ |------ |
| POST:/courses | Json |/courses|

Sample Input<br>
```json
{
    "boardId": "4",
    "courseId": "CS7390",
    "department": "CS",
    "profId": "wen.sun",
    "roster": [
        "stu1.wang",
        "stu2.zhao"
    ],
    "taId": "stu1.wang"
}
```
#### Update a Course
|     Url   | Input  |Example|
| ------| ------ |------ |
| PUT:/courses/{courseId} | Json |/courses/CS6200|

Sample Input<br>
```json
{
    "boardId": "4",
    "courseId": "CS6200",
    "department": "updated",
    "profId": "updated",
    "roster": [
        "stu1.wang",
        "stu2.zhao"
    ],
    "taId": "stu2.he"
}
```
#### Delete a Course
|     Url   | Input  |Example|
| ------| ------ |------ |
| DELETE:/courses/{courseId} | * |/courses/CS6200|

## Board

#### Get All Boards
|     Url   | Input  |Example|
| ------| ------ |------ |
| GET:/boards| * |/boards|

#### Get Board By Board Id
|     Url   | Input  |Example|
| ------| ------ |------ |
| GET:/boards/{boardId} | * |/boards/1 |

#### Add a Board
|     Url   | Input  |Example|
| ------| ------ |------ |
| POST:/boards | Json |/boards|

Sample Input<br>
```json
{
    "courseId": "Info6200"
}
```
*Board Id is generated as number*

#### Update a Board
|     Url   | Input  |Example|
| ------| ------ |------ |
| PUT:/boards/{boardId} | Json |/boards/1|

Sample Input<br>
```json
{
    "courseId": "updated"
}
```
*Board Id will not changed with attributes*

#### Delete a Course
|     Url   | Input  |Example|
| ------| ------ |------ |
| DELETE:/boards/{boardId} | * |/boards/2|

## Announcement

#### Get All Announcements
|     Url   | Input  |Example|
| ------| ------ |------ |
| GET:/announcements| * |/announcements|

#### Get Announcements By Board Id
|     Url   | Input  |Example|
| ------| ------ |------ |
| GET:/announcements/{announcementId} | * |/announcements/1 |

*This url returns all the announcements in the board(based on board Id).*

#### Get Announcement By Board Id & Announcement Id
|     Url   | Input  |Example|
| ------| ------ |------ |
| GET:/announcements/{boardId}_{announcementId} | * |/announcements/1_1 |

*This url returns the specific announcement based on board Id & announcement Id.*

#### Add an announcement
|     Url   | Input  |Example|
| ------| ------ |------ |
| POST:/announcements/{boardId} | Json |/announcements/2|

Sample Input<br>
```json
{
    "announcementText": "Welcome!"
}
```
#### Update a Announcement
|     Url   | Input  |Example|
| ------| ------ |------ |
| PUT:/announcements/{boardId}_{announcementId} | Json |/announcements/1_1|

Sample Input<br>
```json
{
    "announcementText": "Updated"
}
```
#### Delete a Course
|     Url   | Input  |Example|
| ------| ------ |------ |
| DELETE:/announcements/{boardId}_{announcementId} | * |/announcements/2_1|
