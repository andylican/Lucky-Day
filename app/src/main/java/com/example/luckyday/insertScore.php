<?php
   $con=mysqli_connect("127.0.0.1:3306","Andy Li","vQ@TC*h3&02#","leaderboard");

   if (mysqli_connect_errno($con)) {
      echo "Failed to connect to MySQL: " . mysqli_connect_error();
   }
   $sql = "INSERT INTO leaderboard (Name,Score) VALUES ('lmao',2)"

   if($conn->query($sql) == TRUE) {
     echo "New record created successfully";

   }else {
  echo "Error: " . $sql . "<br>" . $conn->error;
}

   mysqli_close($con);
?>
