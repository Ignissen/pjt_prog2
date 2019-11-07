<?php
session_start();
if(isset($_POST["submit"]) && isset($_POST["username"]) && isset($_POST["password"]))
{
    function init_counter()
    {
        
        $_SESSION["hackerin"] = true;
        $_SESSION["hackername"] = $_POST["username"];

        if(!file_exists("/tmp/".$_POST["username"].".txt"))
        {
            $file = fopen("/tmp/".strval($_POST["username"]).".txt", "w");
            fwrite($file, 0);
            $_SESSION["localhackertime"] = 0;
            fclose($file);
        }
        else
        {
            $time = intval(file_get_contents("/tmp/".$_POST["username"].".txt"));
            $_SESSION["localhackertime"] = $time;
            fclose($file);
        }

        if(file_exists("/tmp/num_of_hackers.txt"))
        {
            $content = intval(file_get_contents("/tmp/num_of_hackers.txt"));
            file_put_contents("/tmp/num_of_hackers.txt",$content + 1);
        }
        else
        {
            file_put_contents("/tmp/num_of_hackers.txt", 1);
        }
        if(file_exists("/tmp/osszesitett.txt"))
        {
            $file = fopen("/tmp/osszesitett.txt","r");
            $content = fread($file, filesize("/tmp/osszesitett.txt"));
            $_SESSION["hackertime"] = intval($content);
            fclose($file);
        }
        else
        {
            $file = fopen("/tmp/osszesitett.txt","w");
            fwrite($file,0);
            $_SESSION["hackertime"] = 0;
            fclose($file);
        }
        header("Location: 100.php");
    }

    if($_POST["username"] == "MatyiBatfai" && $_POST["password"] == "teszt")
    {
        init_counter();
    }
    else if($_POST["username"] == "NorbertBatfai" && $_POST["password"] == "teszt")
    {
        init_counter();
    }
    else if($_POST["username"] == "BotondLovasz" && $_POST["password"] == "teszt")
    {
        init_counter();
    }
    else if($_POST["username"] == "LajosNagy" && $_POST["password"] == "teszt")
    {
        init_counter();
    }
    else if($_POST["username"] == "RobiDekany" && $_POST["password"] == "teszt")
    {
        init_counter();
    }
    else
    {
        echo '<script>alert("Bejelentkezés sikertelen");</script>';
    }
    

    
}

?>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8" />
        <title>Bejelentkezés</title>
        <style type="text/css">
            body{
                text-align: center;
            }
            form{
                display: inline-block;
                margin: auto;
            }
            #submit{
                text-align: center;
            }
            img{
                width: 10cm;
                height: auto;
            }
        </style>
        <link rel="shortcut icon" type="image/png" href="deac_logo.png"/>
    </head>
    <body>
        <img src="deac_logo.png" /> <br />
        <form method="POST">
            <table>
                <tr>
                    <td>
                        Felhasználónév:
                    </td>
                    <td>
                        <input type="text" name="username" />
                    </td>
                </tr>
                <tr>
                    <td>
                        Jelszó:
                    </td>
                    <td>
                        <input type="password" name="password" />
                    </td>
                </tr>
                <tr>
                    <td colspan="2" id="submit">
                        <input type="submit" name="submit" value="Bejelentkezés"  />
                    </td>
                </tr>
            </table>
        </form>
    </body>
</html>