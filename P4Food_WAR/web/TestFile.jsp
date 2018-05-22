<!DOCTYPE html>
<html>
    <head>
        <title>Google SignIn</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
        <meta name="google-signin-client_id" content="785046240241-r17p1kpcru97io4goo32863tdso6hd8k.apps.googleusercontent.com">
        <script src="https://apis.google.com/js/platform.js" async defer></script>
        </script>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
        
        <script>
		function onSignIn(googleUser)
		{
			var profile = googleUser.getBasicProfile();
			$(".g-signin2").css("display","none");
			$(".data").css("display","block");
			$("#pic").attr('src',profile.getImageUrl());
			$("#email").text(profile.getEmail());
                        
                                            console.log('ID: ' + profile.getId()); // Do not send to your backend! Use an ID token instead.
                    console.log('Name: ' + profile.getName());
                    console.log('Image URL: ' + profile.getImageUrl());
                    console.log('Email: ' + profile.getEmail()); // This is null if the 'email' scope is not present.
                    
		}
		function signOut()
		{
			var auth2 = gapi.auth2.getAuthInstance();
			auth2.signOut().then(function(){
				alert("You have successfully signed out");
				$(".g-signin2").css("display","block");
				$(".data").css("display","none");
				
			});
		}

		</script>
        <style>
            .g-signin2{
                margin-left: 500px;
                margin-top:200px;
            }
            .data{
                display:none;
            }
        </style>
    </head>
    <body>
        <div class="g-signin2" data-onsuccess="onSignIn"></div>
        <div class="data">
            <p>Profile Details</p>
            <img id="pic" class="img-circle" width="100" height="100"/>
            <p> Email Address</p>
            <p id="email" class="alert alert-danger"></p>
            <button onclick= "signOut()" class="btn btn-danger">SignOut</button>
        </div>
    </body>
</html>