using Newtonsoft.Json;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Net.Http;
using System.Text;
using System.Threading.Tasks;
using MobApp1.Models;
using MobApp1.Services;

namespace MobApp1.ApiServices
{
    public class ApiServices
    {
        public async Task <bool> RegisterAsync(string Fname, string Lname, string Username, string Gender, string Country, string Email, string Password, string ConfirmPassword)

        {
            var client = new HttpClient();

            var model = new RegisterModel
            {
                Fname = Fname,
                Lname = Lname,
                Username = Username,
                Gender = Gender,
                Country = Country,
                Email = Email,
                Password = Password,
                ConfirmPassword = ConfirmPassword,

            };
            var json = JsonConvert.SerializeObject(model);

            HttpContent content = new StringContent(json);
            //Content.Headers.ContentType = new MediaTypeHeaderValue("application/json")

            var response = await client.PostAsync("http://localhost:50666/api/Users", content);

            return response.IsSuccessStatusCode;

        }
        public async Task LoginAsync(string Username, string Password)
        {
            var keyvalues = new List<KeyValuePair<string, string>>
            {
                new KeyValuePair<string, string>("username", Username),
                new KeyValuePair<string, string>("password", Password),
                 new KeyValuePair<string, string>("grant_type", "Password"),
            };
        }
               

    }

}
