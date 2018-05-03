﻿using System;
using Newtonsoft.Json;
using System.Linq;
using System.Net.Http;
using System.Collections.Generic;
using System.Text;
using UInterface.Services;
using UInterface.Models;
using System.Threading.Tasks;

namespace UInterface.Services
{
    public class ApiServices
    {
                       
            public async Task<bool> RegisterAsync(string Fname, string Lname, string Username, string Gender, string Country, string Email, string Password, string ConfirmPassword)

            {
                var client = new HttpClient();

                var model = new Models.RegisterModels
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
}