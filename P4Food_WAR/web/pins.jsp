<html>
    <head>
        <link rel="stylesheet" href="css/bootstrap.min.css">
        <link rel="stylesheet" href="css/fa-svg-with-js.css">
        <script src="js/bootstrap.bundle.min.js"></script>
        <script defer src="js/fontawesome-all.js"></script>

        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

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
                        <a class="nav-link" href="home.jsp"><i class="fas fa-home fa-sm"></i> Home</a>
                    </li>
                    <li class="nav-item active">
                        <a class="nav-link" href="boards.jsp"><i class="fab fa-flipboard fa-sm"></i> Boards</a>
                    </li>
                    <li class="nav-item active">
                        <a class="nav-link" href="pins.jsp"><i class="fas fa-thumbtack fa-sm"></i> Pins</a>
                    </li>
                </ul>
                <i class="fas fa-user-circle fa-2x"></i>
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

                <button type="button" class="btn btn-info btn-lg" data-toggle="modal" data-target="#myModal"><i class="fas fa-plus-circle fa-lg"></i> Create Board</button>

                    <!-- Modal -->
                    <div class="modal fade" id="myModal" role="dialog">
                        <div class="modal-dialog">
                        
                        <!-- Modal content-->
                        <div class="modal-content">
                            <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal"><i class="fas fa-times-circle fa-lg"></i></button>
                            </div>
                            <div class="modal-body">
                                <form action="# createboard.jsp">
                                    <div class="row">
                                        <div class="col">
                                        <input type="text" class="form-control" placeholder="Name of the Board">
                                        </div>                                       
                                    </div>
                                </form>
                            </div>
                            <div class="modal-footer">
                            <button type="submit" class="btn btn-default">Create</button>
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