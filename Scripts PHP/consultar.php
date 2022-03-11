<?php

include 'connection.php';

$id_agenda=$_GET['id_agenda'];

$consulta = "select * from tabla_agenda where id_agenda = '$id_agenda'";
$result = $connection -> query($consulta);

while($aux=$result -> fetch_array()){
	$datos[] = array_map('utf8_encode', $aux);
}

echo json_encode($datos);
$result -> close();

?>