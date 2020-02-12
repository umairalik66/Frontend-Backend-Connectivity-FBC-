<?php
   $con=mysqli_connect("localhost","root","","localdb1");

   

   
   $id2 = $_POST['id2'];
  
   $result = mysqli_query($con,"SELECT name FROM employee where id='$id2'");
   $row = mysqli_fetch_array($result);
   $data = $row[0];

   if($data){
      echo $data;
   }
   mysqli_close($con);

?>
