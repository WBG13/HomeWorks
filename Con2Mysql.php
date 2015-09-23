<?php
include "connect.php"
?>
<?php
function clearData($data, $type = 'i')
{
    switch ($type) {
        case 'i':
            return $data * 1;
            break;
        case 's':
            return trim(strip_tags($data));
            break;
    }
}

$output = "";
if ($_SERVER['REQUEST_METHOD'] == 'POST') {
    $number1 = clearData($_POST['number1']);
    $number2 = clearData($_POST['number2']);
    if ($_POST['1']) {
        $nameD = $_POST["number1"];
        $telD = $_POST["number2"];
        $query = "INSERT INTO `NoteBook` (`nameData`, `telData`)
		VALUES('$nameD', '$telD');";
        $result = mysql_query($query);
        $output = 'Comlete';
    } else if ($_POST['2']) {
        $nameD = $_POST["number1"];
        $telD = $_POST["number2"];
        $query = "SELECT * FROM NoteBook where nameData='$nameD';";
        $result = mysql_query($query);
        output($result);
    } else if ($_POST['3']) {
        $query = "SELECT * FROM NoteBook";
        $result = mysql_query($query);
        output($result);
    }
}
?>

<form action="<?= $_SERVER['PHP_SELF'] ?>" method="POST">
    Name:
    <input type="text" name="number1">
    Tel:
    <input type="text" name="number2"><br/>
    <input type="submit" name="1" value="Insert">
    <input type="submit" name="2" value="Find">
    <input type="submit" name="3" value="Show">
</form>

<?php
if ($output) {
    echo $output;
}
?>
<?php
function output($result)
{
    while ($nameData = mysql_fetch_array($result)) {
        echo "<h3>" . $nameData["nameData"] . "</h3>";
        echo "<p>" . $nameData["telData"] . "</p>";
    }
}

?>
