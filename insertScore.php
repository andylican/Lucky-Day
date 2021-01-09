<?php
if ($_SERVER["REQUEST_METHOD"] == "POST") {
echo $_POST['name'];
$score = (int)$_POST['score'];
echo "WADDAWDAW";
   $conn=mysqli_connect("localhost","root","","myapp");

   if (mysqli_connect_errno($conn)) {
      echo "Failed to connect to MySQL: " . mysqli_connect_error();
   }

  $sql = "INSERT INTO leaderboard (Name,Score) VALUES ('".$_POST['name']."','".$score."')";

   if($conn->query($sql) == TRUE) {
     echo "New record created successfully";

   }else {
  echo "Error: " . $sql . "<br>" . $conn->error;
}

   mysqli_close($conn);
 }
?>
