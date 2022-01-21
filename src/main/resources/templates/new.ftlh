
<html lang="en">
<head>

<title>Create</title>
    </head>
<body>
<form>
    <script>
        function send() {
       let object = {
        "code": document.getElementById("code_snippet").value
         };

        let json = JSON.stringify(object);

        let xhr = new XMLHttpRequest();
        xhr.open("POST", '/api/code/new', false);
        xhr.setRequestHeader('Content-type', 'application/json; charset=utf-8');
        xhr.send(json);

       if (xhr.status == 200) {
        alert("Success!");
            }
        }
        </script>
    <p><textarea id="code_snippet">//write your code here</textarea></p>
    <p><button id="send_snippet" type="submit" onclick="send()">Submit</button></p>
    </form>
</body>
</html>