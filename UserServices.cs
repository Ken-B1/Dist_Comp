using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using Mobile.Services;
using Plugin.RestClient;
using Mobile.Models;

namespace Mobile.Services
{
    public class UserServices
    {
        public async Task<List<User>> GetUsersAsync()
        {

            RestClient<User> restClient = new RestClient<User>();
            var userList = await restClient.GetAsync();
            return userList;
            
        }
    }
}


/*var list = new List<User>
           {
               new User
               {
                   fName = "Alice",
                   lName = "NEM",
                   gender ="female",
                   email = "lllllk@gmail.com",
                   country ="italy",
                   password ="string",
                   confirm_password = "",
               },
              new User
               {
                   fName = "Peter",
                   lName = "MEN",
                   gender ="male",
                   email = "kmmok@gmail.com",
                   country ="france",
                   password ="string",
                   confirm_password ="",
               },
           };

           return list;*/
