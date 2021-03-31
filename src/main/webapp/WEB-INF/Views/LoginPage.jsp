<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
 pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<style>
body, html {
  height: 100%;
  font-family: Arial, Helvetica, sans-serif;
}

* {
  box-sizing: border-box;
}

.bg-img {
  /* The image used */
  background-image: url("Images/Education.jpg");

  min-height: 712px;

  /* Center and scale the image nicely */
  background-position: center;
  background-repeat: no-repeat;
  background-size: cover;
  position: relative;
}

/* Add styles to the form container */
.container {
     position: absolute;
    right: 251px;
    margin: 42px;
    max-width: 413px;
    height: 794px;
    padding: 23px;
    background-color: rgba(50, 115, 220, 0.3);
    margin-right: 187px;

}

/* Full-width input fields */
input[type=text], input[type=password] ,input[type=email] {
  width: 100%;
  padding: 15px;
  margin: 5px 0 22px 0;
  border: none;
  background-color: rgba(231, 173, 4, 0.5);
}

input[type=text]:focus, input[type=password]:focus, input[type=email]:focus {
  background-color: #ddd;
  outline: none;
}

/* Set a style for the submit button */
.btn {
  background-color: rgb(134, 115, 56);
    color: #101010;
    padding: 16px 20px;
    border: none;
    cursor: pointer;
    width: 100%;
    opacity: 92.1;
    font-size: 19px;
}

.btn:hover {
  opacity: 1;
}
</style>


</head>
<body >
 <c:url var="appointment" value="getAppointmentDetails"></c:url>
  <form:form action="${appointment}" class="container"  method="post">
    <h1>Doctors Appointment</h1>
    <label for="name"><b>Name</b></label>
    <input type="text" placeholder="Enter Name" name="name" id="name" required>
    <label for="age"><b>Age</b></label>
    <input type="text" placeholder="Enter Age" name="age" id="age" required>
    <label for="dob"><b>DOB</b></label>
    <input type="date" placeholder="Enter DOB" name="dob" id="dob" required style="width: 100%; padding: 15px;margin: 5px 0 22px 0; border: none;background-color: rgba(231, 173, 4, 0.5);">
    <label for="bloodgroup"><b>Blood Group</b></label>
    <input type="text" placeholder="Enter Blood Group" name="bloodgroup" id="bloodgroup" required>
    <label for="address"><b>Address</b></label>
    <input type="text" placeholder="Enter Address" name="address" id="address" required>
    <label for="mobilenumber"><b>Mobile Number</b></label>
    <input type="text" placeholder="Enter Mobile Number" name="mobilenumber" id="mobilenumber" required>
    <label for="email"><b>Email</b></label>
    <input type="email" placeholder="Enter Email" name="email" id="email" required>
    
    <button type="submit" class="btn">Login</button>
  </form:form> 
</body>
</html>