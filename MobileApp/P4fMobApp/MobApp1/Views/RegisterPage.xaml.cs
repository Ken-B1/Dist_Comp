using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using MobApp1.Views;
using Xamarin.Forms;
using Xamarin.Forms.Xaml;

namespace MobApp1.Views
{
    [XamlCompilation(XamlCompilationOptions.Compile)]

    public partial class RegisterPage : ContentPage 

    {
        public RegisterPage()
        {
            InitializeComponent();
        }
        private async void Button_onClicked(Object Sender, EventArgs e)
        {
            await Navigation.pushAsync(new LoginPage());

        }

        private class XamlCompilationOptions
        {
        }
    }
}