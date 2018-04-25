using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using UInterface.Views;

using Xamarin.Forms;
using Xamarin.Forms.Xaml;

namespace UInterface.Views
{
    [XamlCompilation(XamlCompilationOptions.Compile)]
    public partial class RegisterPage : ContentPage
    {
        public RegisterPage()
        {
            InitializeComponent();
        }

        private async void RegisterCommand(object sender, EventArgs e)
        {
            await Navigation.PushAsync(new LoginPage());

        }

        private class XamlCompilationOptions
        {
            
        }
        
    }
}