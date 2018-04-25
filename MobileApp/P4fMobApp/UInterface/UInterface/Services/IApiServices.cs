using System.Threading.Tasks;

namespace UInterface.Services
{
    public interface IApiServices
    {
        Task LoginAsync(string Username, string Password);
        Task<bool> RegisterAsync(string Fname, string Lname, string Username, string Gender, string Country, string Email, string Password, string ConfirmPassword);
    }
}