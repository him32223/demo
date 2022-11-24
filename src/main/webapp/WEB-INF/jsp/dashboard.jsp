<!doctype html>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Profile</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-gH2yIJqKdNHPEq0n4Mqa/HGKIhSkIHeL5AyhkYV8i59U5AR6csBvApHHNl/vI1Bx" crossorigin="anonymous">
  </head>
  <body>
    <nav class="navbar navbar-expand-sm navbar-light bg-light fixed-top">
        <div class="container">
            <span class="navbar-brand mt-1 h1">de<span class="text-primary">Mo</span></span>

            <button type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav" class="navbar-toggler" 
            aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle Navigation"><span class="navbar-toggler-icon"></span></button>
    
            <div class="collapse navbar-collapse" id="navbarNav">
                <ul class="navbar-nav">
                    <li class="nav-item active">
                        <a href="#" class="nav-link">Home</a>
                    </li>
                    <li class="nav-item active">
                        <a href="/about" class="nav-link">About</a>
                    </li>
                    <li class="nav-item active">
                      <a href="/contact" class="nav-link">Contact</a>
                    </li>
                    <li class="nav-item active">
                      <a href="/jobs" class="nav-link">Jobs</a>
                    </li>
                    <li class="nav-item active">
                      <a href="#" class="nav-link">Find</a>
                    </li>
                </ul>
            </div>
            <section>
              <div class="container mt-3">
              <c:url var="search_url" value="/dashboard" />
              <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
                        <form:form role="search" action="${search_url}" method="post">
                            <div class="input-group">
                                  <input class="form-control" name="keyword" type="search" placeholder="Search" aria-label="Search">
                                  <button class="btn btn-success me-2" type="submit">Search</button>
                            </div>
                        </form:form>
                </div>
            </section>

            <ul class="navbar-nav me-2 my-2 my-lg-0 navbar-nav-scroll" modelattribute="person" style="--bs-scroll-height: 100px;">
              <li class="nav-item dropdown">
                <a class="nav-link dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                  @<c:out value="${pageContext.request.remoteUser}" />
                </a>
                <ul class="dropdown-menu dropdown-menu-lg-end" >
                  <li><a class="dropdown-item" href="/profile?id=${person.id}">Profile</a></li>
                  <li><hr class="dropdown-divider"></li>
                  <li><a class="dropdown-item" href="/settings">Settings</a></li>
                  <li><a class="dropdown-item" href="/dashboard">Dashboard</a></li>
                </ul>
              </li>
      </ul>
      <form action="/logout" method="post">
        <input type="hidden" name="${_csrf.parameterName}"
        value="${_csrf.token}" />
          <button type="submit" class="btn btn-danger">Logout</button>
        </form>
        </div>

    </nav>

    <section class="container" style="margin-top: 100px;">
      <style>
        table {
          font-family: arial, sans-serif;
          border-collapse: collapse;
          width: 100%;
        }

        td, th {
          border: 1px solid #dddddd;
          text-align: left;
          padding: 8px;
        }

        tr:nth-child(even) {
          background-color: #dddddd;
        }
      </style>

      <table class="table card-body text-center">
        <thead>
          <tr>
            <th scope="col">#</th>
            <th scope="col">Username</th>
            <th scope="col">Email</th>
            <th scope="col">Password</th>
            <th scope="col">Country</th>
          </tr>
        </thead>
        <tbody>
          <c:if test="${not empty users}">
          <c:forEach var="user" items="${users}">
          <tr>
            <th scope="row">${user.id}</th>
            <td>${user.username}</td>
            <td>${user.email}</td>
            <td>${user.password}</td>
            <td>${user.country}</td>
          </tr>
          </c:forEach>
          </c:if>
        </tbody>
      </table>
    </div>
    </section>

    <section>
      <div class="container" style="margin-top: 425px;"></div>
    </section>

    <footer class="py-5 bg-light text-muted">
      <div class="container">
        <div class="row">
          <div class="col-8 d-flex justify-content-start">
            <span>&copy; 2022.
              <i class="fa-brands fa-github"></i>
              <a href="https://github.com/him32223/demo">demo</a>
            </span>
          </div>

          <div class="col-4 d-flex justify-content-end">
            <a href="#" class="me-2">Sitemap</a>
            <a href="#">Policy</a>
          </div>
        </div>
      </div>
    </footer>

    <script src="https://kit.fontawesome.com/e19fcdf015.js" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/js/bootstrap.bundle.min.js" integrity="sha384-A3rJD856KowSb7dwlZdYEkO39Gagi7vIsF0jrRAoQmDKKtQBHUuLZ9AsSv4jD4Xa" crossorigin="anonymous"></script>
  </body>
</html>