<html lang="en">
<head>

    <title>Create</title>
</head>
<body>
<form>
    <script>
        function send() {
            let object = {
                "code": document.getElementById("code_snippet").value,
                "time": document.getElementById("time_restriction").value,
                "views": document.getElementById("views_restriction").value
            };

            let json = JSON.stringify(object);

            let xhr = new XMLHttpRequest();
            xhr.open("POST", '/api/code/new', false);
            xhr.setRequestHeader('Content-type', 'application/json; charset=utf-8');
            xhr.send(json);
            console.log(xhr.status);

            if (xhr.status == 200) {
                alert("Success!");
            }
        }
    </script>
    <p><textarea id="code_snippet">//write your code here</textarea></p> <br>
    <span>time_restriction</span><input id="time_restriction" type="text" pattern="^[ 0-9]+$" value=0><br>
    <span>views_restriction</span><input id="views_restriction" type="text" pattern="^[ 0-9]+$" value=0><br>
    <p>
        <button id="send_snippet" type="submit" onclick="send()">Submit</button>
    </p>
</form>
</body>
</html>