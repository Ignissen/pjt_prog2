<?php
    if(isset($_GET["getnum"]))
    {
        echo intval(file_get_contents("/tmp/num_of_hackers.txt"));
    }
    else{
        echo "Menj innen!<br> <h1>Error 405</h1>Method not allowed.";
    }
?>