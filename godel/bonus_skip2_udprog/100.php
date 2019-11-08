<?php
session_start();
if (!(isset($_SESSION["hackerin"]) && $_SESSION["hackerin"] == true)) {
    header("location: login.php");
    exit;
}
$hackername = $_SESSION["hackername"];
$hackertime = $_SESSION["hackertime"];

if(isset($_GET["time"]))
{
    $hackerfile = "/tmp/".$_SESSION["hackername"].".txt";
    $time = intval(file_get_contents($hackerfile));
    file_put_contents($hackerfile, $time + intval($_GET["time"]));

}
if(isset($_GET["sumtime"]))
{
    $hackerfile = "/tmp/osszesitett.txt";
    $time = intval(file_get_contents($hackerfile));
    file_put_contents($hackerfile, $time + intval($_GET["sumtime"]));
    $hackertime = $_SESSION["hackertime"] = intval($_GET["sumtime"]);
}

function swap(&$a, &$b)
{
    $tmp = $a;
    $a = $b;
    $b = $tmp;
}

function bubble(&$sort_by, &$other)
{
    for($i = 0; $i < count($sort_by); $i++ )
    {
        for($j = count($sort_by) - 1; $j > $i; $j--)
        {
            if($sort_by[$j] > $sort_by[$j - 1])
            {
                swap($sort_by[$j], $sort_by[$j - 1]);
                swap($other[$j], $other[$j - 1]);
            }
        }
    }
}


?>
<!DOCTYPE HTML>
<html>

<head>
    <meta charset="UTF-8">
    <style>
        p {
            margin-top: 0px;
            text-align: center;
            font-size: 55px;
            font-variant: small-caps;
        }

        img {
            display: block;
            margin-left: auto;
            margin-right: auto;
        }
    </style>
    <script type="text/javascript" src="jquery-3.4.1.min.js" ></script>
    <link rel="shortcut icon" type="image/png" href="deac_logo.png"/>
</head>

<body>
    <p id="ido">Streameljünk 100 óra DEAC játékot!</p>
    <img src="http://deac.hu/upload/5b376e152b0b5.png" width="300">
    <p id="hanyan" class="smallcap">Hány DEAC-Hackers streamel?</p>
    <div align="center">
        Hajrá, <?php echo $hackername; ?>!
    </div>
    <div align="center">
        <button onclick="refresh100()">FRISSÍT</button>
        <button onclick="stop100()">LEÁLLÍT</button>
    </div>
    <div align="center">
        <?php
        $hackers = array("MatyiBatfai", "NorbertBatfai","BotondLovasz","LajosNagy","RobiDekany");
        $hackertimes = array(0, 0, 0, 0, 0);
        for($i = 0; $i < count($hackers); $i++)
        {
            $hackerfile = "/tmp/" . $hackers[$i].".txt";
            $time = 0;
            if (file_exists($hackerfile))
                $time = intval(file_get_contents($hackerfile));
            $hackertimes[$i] = $time;
            
        }
        bubble($hackertimes, $hackers);
        for($i = 0; $i < count($hackers); $i++)
        {
            $h = $hackers[$i];
            $time = $hackertimes[$i];
            $hours = (int) ($time / (1000 * 60 * 60));
            $minutes = (int) (($time - $hours * 1000 * 60 * 60) / (1000 * 60));
            $seconds = (int) (($time - $hours * 1000 * 60 * 60 - $minutes * 1000 * 60) / 1000);
            echo "Hacker: " . $h . ", idő: " . $hours . " óra " . $minutes . " perc " . $seconds . " mp";
            echo "<br>";
        }
        ?>
    </div>
    <h1 style="color: red; text-align: center;">Please leave this website via the Leállít button!</h1>
    <h1 style="color: red; text-align: center;">Kérem a Leállít gombot használva hagyja el az oldalt!</h1>
    <h1 style="color: red; text-align: center;">Más esetben nem fogja jól menteni az adatokat!</h1>
    <script>
        var limit = 1000 * 60 * 60 * 100;
        var limit = 1000 * 60 * 60 * 100;
        var refreshlimit = 1000 * 60 * 5;
        var nofhackers = 1;
        var time = <?php echo $hackertime; ?>;
        //console.log(time);
        var localtime = 0;
        var solotime = <?php $_SESSION["localhackertime"]; ?>
        setInterval(function() {

            $.get("get_n_of_hackers.php", {"getnum" : "1"}, function(data){
                nofhackers = parseInt(data);
            });

            time = time + nofhackers * 1000;
            solotime = solotime + 1000;
            localtime = localtime + 1000;
            var hours = Math.floor(time / (1000 * 60 * 60));
            var minutes = Math.floor((time - hours * 1000 * 60 * 60) / (1000 * 60));
            var seconds = Math.floor((time - hours * 1000 * 60 * 60 - minutes * 1000 * 60) / 1000);
            document.getElementById("ido").innerHTML = hours + " óra " +
                minutes + " perc " + seconds + " mp ";
            if (nofhackers == 1)
                document.getElementById("hanyan").innerHTML = nofhackers + " hacker streamel";
            else
                document.getElementById("hanyan").innerHTML = nofhackers + " hacker streamel párhuzamosan";
            if (time >= limit) {
                document.getElementById("ido").innerHTML = "A 100 óra stream teljesítve!";
            }
            if (localtime >= refreshlimit) {
                localtime = 0;
                refresh100();
            }
        }, 1000);

        function stop100() {
            window.location.href = "stop.php?time=" + solotime + "&sumtime="+ time;
        }
        
        function refresh100() {
            window.location.href = "100.php?time=" + solotime + "&sumtime="+ time;
        }

    </script>
</body>

</html>