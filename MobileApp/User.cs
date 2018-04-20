using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Webp4foodApp1.Models
{
    public class User
    {
        public int Id { get; set; }
        public string fName { get; set; }
        public string lName { get; set; }
        public string gender { get; set; }
        public string email { get; set; }
        public string country { get; set; }
        public string password { get; set; }
        public string confirm_password { get; set; }


    }
}