<!doctype html>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Sign up</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-gH2yIJqKdNHPEq0n4Mqa/HGKIhSkIHeL5AyhkYV8i59U5AR6csBvApHHNl/vI1Bx" crossorigin="anonymous">
  </head>
  <body>
    <form class="mb-4" action="/process_signup" method="post" modelattribute="user">
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
    <div class="card mt-5" style="width: 20rem; margin: auto;">
        <div class="card-body text-center">
            <form class="mb-4">
                
                <h1 class="mb-4">Sign up</h1>
                <div class="form-group mt-2">
                    <label for="username" class="visually-hidden">username</label>
                    <input type="text" id="username" name="username" class="form-control" placeholder="Username" required autofocus>
                </div>
                <div class="form-group mt-2">
                    <label for="email" class="visually-hidden">email</label>
                    <input type="email" id="email" name="email" class="form-control" placeholder="Email" required>
                </div>
                <div class="form-group mt-2 mb-5">
                    <label for="password" class="visually-hidden">password</label>
                    <input type="password" id="password" name="password" class="form-control" placeholder="Password" required>
                </div>
                <div class="form-group d-grid gap-2">
                    <button class="btn btn-success w-100" onclick=document.location='/signin'>Sign in</button>
                    <button  class="btn btn-success w-100"><a href='/thankyou'></a>Sign Up</button>
                    <a href="/#">Return to homepage</a>

                </div>
             
           
            </form>
            
        </div>
    </div>

    <script src="https://kit.fontawesome.com/e19fcdf015.js" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/js/bootstrap.bundle.min.js" integrity="sha384-A3rJD856KowSb7dwlZdYEkO39Gagi7vIsF0jrRAoQmDKKtQBHUuLZ9AsSv4jD4Xa" crossorigin="anonymous"></script>
  </body>
</html>