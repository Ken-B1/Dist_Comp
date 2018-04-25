using System;
using System.Collections.Generic;
using System.Text;
using UInterface.Views;
using UInterface.ViewModels;
using System.Windows.Input;
using Xamarin.Forms;
using UInterface.Services;

namespace UInterface.ViewModels
{
    public class RegisterViewModel
    {

        ApiServices apiServices = new ApiServices();


        public string Fname { get; set; }

        public string Lname { get; set; }

        public string Username { get; set; }

        public string Email { get; set; }

        public string Gender { get; set; }

        public string Country { get; set; }

        public string Password { get; set; }

        public string ConfirmPassword { get; set; }

        public string Message { get; set; }

        public ICommand RegisterCommand => new Command(async () =>
                                                         {
                                                             var isSuccess = await ApiServices.RegisterAsync(Fname, Lname, Username, Gender, Country, Email, Password, ConfirmPassword);

                                                             if (isSuccess)
                                                                 Message = "Registered successifully!";
                                                             else
                                                             {
                                                                 Message = "Not Registered, try again Later!";
                                                             }
                                                         });
    }
}