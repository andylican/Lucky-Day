<?php
if ($_SERVER["REQUEST_METHOD"] == "GET") {
  $conn=mysqli_connect("localhost","root","","myapp");

  if (mysqli_connect_errno($conn)) {
     echo "Failed to connect to MySQL: " . mysqli_connect_error();
  }
  $sql = "SELECT name, score FROM leaderboard ORDER BY score DESC";
  $result = $conn->query($sql);
  $cnt=0;
  $arr=array();
  while(($row = $result->fetch_assoc()) and ($cnt < 10)) {
    $str = $row["name"].": ".$row["score"];
    $arr[]=array($str);
    $cnt++;


  }
  echo json_encode($arr);

     mysqli_close($conn);
}
?>
