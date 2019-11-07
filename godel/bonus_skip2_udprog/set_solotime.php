<?php
    session_start();
    if(isset($_POST["addtime"]))
    {
        $_SESSION["localhackertime"] = $_SESSION["localhackertime"] + $_POST["settime"];
        $file = fopen("/tmp/". $_SESSION["hackername"] .".txt", "w");
        $time = intval(file_get_contents("/tmp/". $_SESSION["hackername"] .".txt"));

        fwrite("/tmp/".$_SESSION["hackername"].".txt",$time + $_POST["settime"]);
        fclose($file);
    }
    $time = intval(file_get_contents("/tmp/". $_SESSION["hackername"].".txt"));
?>