using DuckMessagesAPI.DTO;
using DuckMessagesAPI.Interfaces;

namespace DuckMessagesAPI.Services
{
    public class UserService : IUserService
    {
        private readonly IUserRepository _repository;
        public UserService(IUserRepository repository)
        {
            _repository = repository;
        }

        public async Task<DTO_User_Auth_Response?> Login(DTO_User_Login request)
        {
            return await _repository.Login(request);
        }

        public async Task<DTO_User_Auth_Response?> Register(DTO_User_Register request)
        {
            return await _repository.Register(request);
        }

        public async Task<List<DTO_User_GetFriends_Response>> GetFriends(Guid userId)
        {
            return await _repository.GetFriends(userId);
        }

        public async Task<bool> AddFriend(DTO_User_AddFriend request)
        {
            return await _repository.AddFriend(request);
        }
    }
}
