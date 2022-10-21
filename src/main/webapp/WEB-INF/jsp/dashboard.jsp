<nav class="navbar navbar-expand-sm navbar-light bg-light fixed-top">
    <div class="container">
        <span class="navbar-brand mt-1 h1">Linked<span class="text-primary">Me</span>In</span>

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
                  <a href="#" class="nav-link">Find</a>
                </li>
            </ul>
        </div>

        <button type="button" class="btn btn-outline-dark me-2" style="border: none;">
            @<c:out value="${pageContext.request.remoteUser}" />
        </button>
        
        <form action="/logout" method="post">
        <input type="hidden" name="${_csrf.parameterName}"
                    value="${_csrf.token}" />
            <button type="submit" class="btn btn-danger">Logout</button>
    </form>
    </div>

</nav>