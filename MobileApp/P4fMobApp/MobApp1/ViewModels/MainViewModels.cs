using System.Collections.Generic;
using System.ComponentModel;
using System.Runtime.CompilerServices;
using System.Threading.Tasks;
using PropertyChanged;
using MobApp1.Models;
using MobApp1.Services;


namespace MobApp1.ViewModels
{
    public class MainViewModels : INotifyPropertyChanged
    {
        private List<User> _userlist;

        public List<User> UserList
        {
            get { return _userlist; }
            set
            {
                _userlist = value;
                OnPropertyChanged();
            }
        }

        public MainViewModels()
        {
            //var clientServices = new ClientServices();
            //ClientList = clientServices.GetClients();

            InitializeDatAsync();
        }
            private async Task InitializeDatAsync()
        {
                var userServices = new UserServices();
                UserList = await userServices.GetUsersAsync();
        }
               
        public event PropertyChangedEventHandler PropertyChanged;

        protected virtual void OnPropertyChanged([CallerMemberName] string propertyName = null)
        {
            PropertyChanged?.Invoke(this, new PropertyChangedEventArgs(propertyName));
        }
    }
}

