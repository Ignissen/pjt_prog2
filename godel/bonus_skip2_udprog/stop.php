<?php
    session_start();
    if(isset($_GET["time"]))
    {
        $file = fopen("/tmp/osszesitett.txt", "w");
        fwrite($file, $_GET["time"]);
        fclose($file);
        $file = fopen("/tmp/".$_SESSION["hackername"].".txt", "w");
        
        $n_of_hackers = intval(file_get_contents("/tmp/num_of_hackers.txt")) - 1;
        
        if($n_of_hackers > 0)
        {
            file_put_contents("/tmp/num_of_hackers.txt",$n_of_hackers);
        }
        else
        {
            unlink("/tmp/num_of_hackers.txt");
        }
        header("location: login.php");
    }
    if(isset($_GET["sumtime"]))
    {
        $filename = "/tmp/" . $_SESSION["hackername"] . ".txt";
        $sum_file = fopen($filename,"w");
        fwrite($sum_file, $_GET["sumtime"] + intval(file_get_contents($filename)));
        fclose($sum_file);
    }
    session_destroy();
?>

