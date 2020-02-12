<?php
   $con=mysqli_connect("localhost","root","","localdb1");

   if (mysqli_connect_errno($con)) {
      echo "Failed to connect to MySQL: " . mysqli_connect_error();
   }
else 
	echo "Connection Successfully Created.";



   $id = $_POST['id2'];
   $name = $POST['name2'];
   
  
$sql="INSERT INTO employee (id, name) VALUES ('$id','$name')";
   if (mysqli_query($con,$sql)) {
      echo "Values have been inserted successfully";
   }
mysqli_close($con);
?>
