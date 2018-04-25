using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using UInterface.Views;
using Xamarin.Forms;



using static System.Net.Mime.MediaTypeNames;

namespace MobApp
   
{
	public partial class App : Application
	{
		public App ()

		{ 
			InitializeComponent();

			MainPage = new LoginPage();
		}

		protected override void OnStart ()
		{
			// Handle when your app starts
		}

		protected override void OnSleep ()
		{
			// Handle when your app sleeps
		}

		protected override void OnResume ()
		{
			// Handle when your app resumes
		}

        public override bool Equals(object obj)
        {
            return base.Equals(obj);
        }

        public override int GetHashCode()
        {
            return base.GetHashCode();
        }

        public override string ToString()
        {
            return base.ToString();
        }
    }
}
