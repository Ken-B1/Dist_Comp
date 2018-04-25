using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using Xamarin.Forms;
using Xamarin.Forms.Xaml;
using System.Threading.Tasks;
using UInterface.Views;
using UInterface.ViewModels;
using UInterface.Models;

namespace UInterface.Views
{
	[XamlCompilation(XamlCompilationOptions.Compile)]
	public partial class LoginPage : ContentPage
	{
        public LoginPage()
        {

            InitializeComponent();
        }
    
        private void InitializeComponent()
        {
            throw new NotImplementedException();
        }

        void Signinprocedure(object sender, EventArgs e)
        {
            User user = new User(Entry_Username.Text, Entry_Password.Text);
            if (user.CheckInformation())
            {
                DisplayAlert("Login", "Login success", "Ok");
            }
            else
            {
                DisplayAlert("Login", "Login failed, incorrect username or password", "Ok");
            }
        }

    }

    internal class Entry_Password
    {
        public static string Text { get; internal set; }
    }

    internal class Entry_Username
    {
        public static string Text { get; internal set; }
    }

}