<html lang="en">

    <head>
        <title>Latest</title>
            <style>pre{ border:groove; background-color:lightgrey; } span{ color:green;} </style>
    </head>
    <body>
    <#setting locale="en_US" >
    <#setting date_format="EEE MMM dd HH:mm:ss zzz yyyy">
        <#list fragments as fragment >
            <span id="load_date"> ${fragment.date?datetime?string("EEE MMM dd HH:mm:ss zzz yyyy")} </span>
            <pre id="code_snippet">${fragment.code}</pre>
        </#list>
    </body>
</html>