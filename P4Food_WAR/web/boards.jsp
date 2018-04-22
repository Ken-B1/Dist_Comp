<html>
    <head>
        <link rel="stylesheet" href="css/bootstrap.min.css">
        <link rel="stylesheet" href="css/fa-svg-with-js.css">
        <script src="js/bootstrap.bundle.min.js"></script>
        <script defer src="js/fontawesome-all.js"></script>
    </head>

    <body>
    <nav class="navbar navbar-expand-lg navbar-light bg-light">
            <a class="navbar-brand" href="#">
                <img src="images\icons\logo.png" width="30" height="30" class="d-inline-block align-top" alt="">
                Pin4FooD
            </a>   
            <div class="collapse navbar-collapse" id="navbarSupportedContent"> 
                <ul class="navbar-nav mr-auto">
                    <li class="nav-item active">
                        <a class="nav-link" href="pinboard"><i class="fas fa-home fa-sm"></i> Home</a>
                    </li>
                    <li class="nav-item active">
                            <a class="nav-link" href="boards.jsp"><i class="fab fa-flipboard fa-sm"></i> Boards</a>
                        </li>
                </ul>
                <c:if test="${isAdmin}"><a class="nav-item active" href="admin">Admin</a></c:if>
                <a class="nav-item" href="settings"><i class="fas fa-user-circle fa-2x"></i></a>
                <a class="nav-item" href="statistics">Statistics</a>
                <button class="btn btn-outline-success my-2 my-sm-0" >Login</button>
            </div> 
        </nav>
        <div class="container">
           <!-- Image and text -->
            

            <div class="jumbotron">
                <div class="container">
                    <h1 class="display-4">Pin 4 Food</h1>
                    <p class="lead">Add Your board and Share your pins</p>
                </div>

                <p>
                    <a class="btn btn-primary" data-toggle="collapse" href="#collapseExample" role="button" aria-expanded="false" aria-controls="collapseExample">
                        <i class="fas fa-plus-circle fa-lg"></i> Add to Board
                    </a>
                </p>
                <div class="collapse" id="collapseExample">
                    <div class="input-group mb-3">
                            <div class="input-group-prepend">
                                <label class="input-group-text" for="inputGroupSelect01">Choose the Category</label>
                            </div>
                            <select class="custom-select" id="inputGroupSelect01">
                                <option selected>Choose...</option>
                                <option value="1">African</option>
                                <option value="2">Belgian</option>
                                <option value="3">French</option>
                                <option value="4">Indian</option>
                                <option value="5">Spanish</option>
                            </select>
                            </div>
                    <div class="card card-body">
                        <div class="input-group mb-3">
                            <div class="custom-file">
                              <input type="file" class="custom-file-input" id="inputGroupFile02">
                              <label class="custom-file-label" for="inputGroupFile02">Choose file</label>
                            </div>
                            <div class="input-group-append">
                                    <button class="btn btn-outline-secondary" type="button">Upload</button>
                            </div>
                          </div>
                    </div>
                </div>
            </div>

            <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
            <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.0/umd/popper.min.js" integrity="sha384-cs/chFZiN24E4KMATLdqdvsezGxaGsi4hLGOzlXwp5UZB1LY//20VyM2taTB4QvJ" crossorigin="anonymous"></script>
            <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.0/js/bootstrap.min.js" integrity="sha384-uefMccjFJAIv6A+rW+L4AHf99KvxDjWSu1z9VI8SKNVmz4sk7buKt/6v9KI65qnm" crossorigin="anonymous"></script>
    </body>
</html>