using DuckMessagesAPI.DTO;

namespace DuckMessagesAPI.Interfaces
{
    public interface IUserService
    {
        Task<DTO_User_GET_Response?> GetUser(DTO_User_GET_Request request);
        Task<bool> PostUser(DTO_User_POST request);
    }
}
