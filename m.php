<?php
  $txt = $_REQUEST['content'] . "\n";
	$myfile = "content.txt";
	$f = fopen($myfile, 'a');
	fwrite($f, $txt);
	fclose($f);
  header("Location: index.html"); 
?>
