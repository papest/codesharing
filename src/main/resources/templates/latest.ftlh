<html lang="en">

<head>
    <title>Latest</title>
    <style>pre {
            border: groove;
            background-color: lightgrey;
        }

        span {
            color: green;
        } </style>
    <link rel="stylesheet"
          href="https://cdn.jsdelivr.net/gh/highlightjs/cdn-release@11.4.0/build/styles/agate.min.css">
    <script src="https://cdn.jsdelivr.net/gh/highlightjs/cdn-release@11.4.0/build/highlight.js"></script>
    <script>hljs.highlightAll();</script>
</head>
<body>
<#setting locale="en_US" >
<#setting date_format="EEE MMM dd HH:mm:ss zzz yyyy">
<#list fragments as fragment >
    <span id="load_date"> ${fragment.date?datetime?string("EEE MMM dd HH:mm:ss zzz yyyy")} </span>
    <pre id="code_snippet"><code>${fragment.code}</code></pre>
</#list>
</body>
</html>