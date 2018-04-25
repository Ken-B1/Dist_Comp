using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using Plugin.RestClient;
using MobApp1.Models;

namespace MobApp1.Services
{
   public  class UserServices
    {
        public async Task<List<User>> GetUsersAsync()
        {

            RestClient<User> restClient = new RestClient<User>();

            var usersList = await restClient.GetAsync();

            return usersList;
        }
    }
}
