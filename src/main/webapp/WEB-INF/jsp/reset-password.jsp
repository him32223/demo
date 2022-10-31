<!doctype html>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Reset Password</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-gH2yIJqKdNHPEq0n4Mqa/HGKIhSkIHeL5AyhkYV8i59U5AR6csBvApHHNl/vI1Bx" crossorigin="anonymous">
  </head>
  <body>
  
    <form class="mt-5" action="/reset-password" method="post">
    <input type="hidden" name="${_csrf.parameterName}"
						value="${_csrf.token}" />
    
        <div class="card" style="width: 20rem; margin: auto;">
            <div class="card-body text-center">
                <span class="navbar-brand mt-1 h1">Linked<span class="text-primary">Me</span>In</span>
                <h4 class="mb-4">Reset Password</h4>
                <p modelattribute="email">Your email (${email}) is verified. Please enter your new password.<p>
                <input type="hidden" name="email" value="${email}">
                <div class="form-group mt-2 mb-2">
                    <label for="password" class="visually-hidden">password</label>
                    <input type="password" name="password" id="password" class="form-control" placeholder="password" required autofocus>
                </div>
                <div class="form-group mb-4">
                    <label for="cpassword" class="visually-hidden">confirmed password</label>
                    <input type="password" name="cpassword" id="cpassword" class="form-control" placeholder="confirm password" required autofocus>
                </div>
                
                <div class="form-group d-grid gap-2">
                    <button type="submit" class="btn btn-primary w-100"> Send</button>
                    
                    <c:if test="${error_warning != null}">
                    <div class="alert alert-warning" role="alert">
  						${error_warning}
					</div>
					</c:if>
                </div>
     
                <footer class="mt-5 text-muted">&copy; 2022. <a href="/">linkedMeIn</a> portal.<br/>
                    Made with <i class="fa-solid fa-heart"></i> in Penang.<br/>
                    <i class="fa-brands fa-github"></i>
                    <a href="https://github.com/hadrihl/linkedmein"> linkedmein</a>
                </footer>
            </div>
        </div>
    </form>

    <script src="https://kit.fontawesome.com/e19fcdf015.js" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/js/bootstrap.bundle.min.js" integrity="sha384-A3rJD856KowSb7dwlZdYEkO39Gagi7vIsF0jrRAoQmDKKtQBHUuLZ9AsSv4jD4Xa" crossorigin="anonymous"></script>
  </body>
</html>