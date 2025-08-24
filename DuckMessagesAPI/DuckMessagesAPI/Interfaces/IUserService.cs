using System.Threading.Tasks;
using DuckMessagesAPI.DTO;
using Microsoft.AspNetCore.Mvc;

namespace DuckMessagesAPI.Interfaces
{
    public interface IUserService
    {
        Task<DTO_User_Auth_Response?> Login(DTO_User_Login request);
        Task<DTO_User_Auth_Response?> Register(DTO_User_Register request);
        Task<List<DTO_User_GetFriends_Response>> GetFriends(Guid userId);
        Task<bool> AddFriend(DTO_User_AddFriend request);
    }
}
