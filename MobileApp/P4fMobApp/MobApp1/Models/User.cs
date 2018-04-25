using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace MobApp1.Models
{
    public class User
    {
        public int Id { get; set; }
        public string Fname { get; set; }
        public string Lname { get; set; }
        public string Username { get; set; }
        public string Gender { get; set; }
        public string Email { get; set; }
        public string Country { get; set; }
        public string Password { get; set; }
        public string ConfirmPassword { get; set; }
    }
}
