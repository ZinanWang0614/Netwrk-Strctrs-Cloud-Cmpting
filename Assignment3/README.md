# Netwrk-Strctrs-Cloud-Cmpting
# Assignment 3

#### 1. Create Student

|     Url   | Input  |Example|
| ------| ------ |------ |
| POST:/students | Json |/students|

For test, make sure the email Id in the input could be access. Confirm email will be sent to the email address. <br>
Sample Input<br>
```json
{
    "department": "Csye",
    "emailId": "wang.zin@husky.neu.edu", // change the email address
    "firstName": "stu4",
    "joiningDate": "1995-11-19",
    "lastName": "li",
    "registerCourses": [
        "Info5100"
    ]
}
```
*Student Id is generated as "firstname + lastname", for uniqueness, if some students have same first & lastname, a number will be added at the end of studentId, like stu1.wang and stu1.wang2*

#### 2. Register The Student to a Course

|     Url   | Input  |Example|
| ------| ------ |------ |
| POST:/student/{studentId}/register | Json |/student/stu4.li/register|

**The input should be the detail of the course you want to register** <br>
Sample Input<br>
```json
{
        "boardId": "2",
        "courseId": "Csye6200",
        "department": "Csye",
        "id": "00d85316-422d-4bdf-83ea-8751771913e5",
        "notificationTopic": "arn:aws:sns:us-west-2:732704573169:CSYE6200",
        "profId": "wen.sun",
        "roster": [
            "stu1.wang",
            "stu2.zhao"
        ],
        "taId": "stu1.wang"
}
```

*A confirm email will be sent to you after registeration. Please confirm subscribe in the email to access the topic.*

#### 3. Post a new announcement

|     Url   | Input  |Example|
| ------| ------ |------ |
| POST:/announcements/{boardId} | Json |/announcements/2|

Sample Input<br>
```json
{
    "announcementText": "Welcome!",
    "courseId": "Csye6200"
}
```
#### 4. Check your email. A notification shall be sent to you email.

