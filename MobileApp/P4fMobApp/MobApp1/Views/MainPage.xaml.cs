using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using Xamarin.Forms;
using MobApp1.Views;

namespace MobApp1
{
	public partial class MainPage : ContentPage
	{
		public MainPage()
		{
			InitializeComponent();

        }
        private void Button _OnClicked(Object Sender, EventArgs e)
        {
            Navigation.pushAsync();
        }
    }

}
