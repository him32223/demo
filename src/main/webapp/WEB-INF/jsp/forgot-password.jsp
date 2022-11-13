<!doctype html>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Forgot Password</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-gH2yIJqKdNHPEq0n4Mqa/HGKIhSkIHeL5AyhkYV8i59U5AR6csBvApHHNl/vI1Bx" crossorigin="anonymous">
  </head>
  <body>
  
    <form class="mt-5" action="/forgot-password" method="post">
    <input type="hidden" name="${_csrf.parameterName}"
						value="${_csrf.token}" />
    
        <div class="card" style="width: 20rem; margin: auto;">
            <div class="card-body text-center">
                <h4 class="mb-4">Forgot Password</h4>
                <p>Please enter your registered email.<p>
                <div class="form-group mt-2 mb-4">
                    <label for="email" class="visually-hidden">email</label>
                    <input type="email" name="email" id="email" class="form-control" placeholder="Email" required autofocus>
                </div>
                
                <div class="form-group d-grid gap-2">
                    <button type="submit" class="btn btn-primary w-100"> Send</button>
                    
                    <c:if test="${error_success != null}">
                    <div class="alert alert-success" role="alert">
  						${error_success}
					</div>
					</c:if>
					<c:if test="${error_warning != null}">
                    <div class="alert alert-warning" role="alert">
  						${error_warning}
					</div>
					</c:if>
                </div>
     
                 <i class="fa-brands fa-github"></i>
                <a href="https://github.com/him32223/demo">demo</a>
                </footer>
            </div>
        </div>
    </form>

    <script src="https://kit.fontawesome.com/e19fcdf015.js" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/js/bootstrap.bundle.min.js" integrity="sha384-A3rJD856KowSb7dwlZdYEkO39Gagi7vIsF0jrRAoQmDKKtQBHUuLZ9AsSv4jD4Xa" crossorigin="anonymous"></script>
  </body>
</html>