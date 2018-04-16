using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using p4foodapp1.Models;
using p4foodapp1.Services;
using p4foodapp1.ViewModels;

namespace p4foodapp1.ViewModels
{
    public class MainViewModel : INotifyPropertyChanged
    {            
            private List<User> userlist;
            // public List<User> UserList { get; set; }
            public List<User> UserList
            {
                get { return userlist; }
                set
                {
                    userlist = value;
                    OnPropertyChanged();
                }
            }

            public MainViewModel()
            {
                //var userServices = new ClientServices();
                //UserList = clientServices.GetClients();

                var userServices = new UserServices();
                UserList = await userServices.GetUsersAsync()();

            }private Task InitializeDataAsync()


            public event PropertyChangedEventHandler PropertyChanged;

            protected virtual void OnPropertyChanged([System.Runtime.CompilerServices.CallerMemberName] string propertyName = null)
            {
                PropertyChanged?.Invoke(this, new PropertyChangedEventArgs(propertyName));
            }
        }
    }



