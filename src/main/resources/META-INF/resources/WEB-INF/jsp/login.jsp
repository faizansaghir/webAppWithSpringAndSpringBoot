<html>
    <head>
        <link href="webjars/bootstrap/5.1.3/css/bootstrap.min.css" rel="stylesheet" >
        <title>Login Page</title>
    </head>
    <body>
        <div class="container">
            <h1>Login</h2>
            <pre>${errorMessage}</pre>
            <form method="post">
                Username: <input type="text" name="username"/>
                Password: <input type="password" name="password"/>
                <input type="submit"/>
            </form>
        </div>
        <script src="webjars/bootstrap/5.1.3/js/bootstrap.min.js"></script>
        <script src="webjars/jquery/3.6.0/jquery.min.js"></script>
    </body>
</html>