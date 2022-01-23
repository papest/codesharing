<html lang="en">

<head>
    <title>Code</title>
    <style>pre {
            border: groove;
            background-color: lightgrey;
        }
        #load_date {
            color: green;
        }
        #views_restriction{
            text-decoration: underline;
        }
        #time_restriction{
            text-decoration: underline;
        }
    </style>
    <link rel="stylesheet"
          href="https://cdn.jsdelivr.net/gh/highlightjs/cdn-release@11.4.0/build/styles/dark.min.css">
    <script src="https://cdn.jsdelivr.net/gh/highlightjs/cdn-release@11.4.0/build/highlight.js"></script>
    <script>hljs.highlightAll();</script>
</head>
<body>

<#setting locale="en_US" >
<span id="load_date"> ${date} </span> <br>
<#if views ?? ><span id="views_restriction">${views}</span><span> more views allowed</span><br></#if>
<#if time ?? ><span>The code will be available for </span><span id="time_restriction">${time}</span><span> seconds</span><br>
</#if>
<pre id="code_snippet"><code>${code}</code></pre>


</body>
</html>