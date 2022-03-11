<?php

$hostname='localhost';
$database='agenda';
$usuario='root';
$contrasena='';

$connection=new mysqli($hostname,$usuario,$contrasena,$database);
if($connection->connect_errno){
	echo "Se ha producido un error";
}

?>