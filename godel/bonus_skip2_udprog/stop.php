<?php
    session_start();
    if(isset($_GET["time"]))
    {
        $file = fopen("/tmp/".$_SESSION["hackername"].".txt", "w");
        $time = file_get_contents("/tmp/".$_SESSION["hackername"].".txt");
        fwrite($file,intval($time) + intval($_GET["time"]));
        fclose($file);

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
        $filename = "/tmp/osszesitett.txt";
        $sum_file = fopen($filename,"w");
        fwrite($sum_file, intval($_GET["sumtime"]) + intval(file_get_contents($filename)));
        fclose($sum_file);
    }
    session_destroy();
?>

