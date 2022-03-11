<?php

include 'connection.php';

$id_agenda=$_POST['id_agenda'];
$fecha=$_POST['fecha'];
$asunto=$_POST['asunto'];
$actividad=$_POST['actividad'];

$insert = "insert into tabla_agenda values('".$id_agenda."','".$fecha."','".$asunto."','".$actividad."')";
mysqli_query($connection, $insert) or die (mysqli_error());
mysqli_close($connection);

?>