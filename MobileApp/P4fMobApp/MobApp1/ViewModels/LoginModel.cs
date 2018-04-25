using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using MobApp1.ViewModels;
using System.Windows.Input;
using MobApp1.Services;
using System.Threading.Tasks;

namespace MobApp1.ViewModels
{
    class LoginModel
    {
        private ApiServices apiServices = new ApiServices();
            public string username { get; set; }

        public string password { get; set; }

        public ICommand LogicCommand
        {
            get
            {
                return new Command(async() =>
               {
                  await _apiServices.LogicAsync(username, password);
               });
            }
        }

    }
}
