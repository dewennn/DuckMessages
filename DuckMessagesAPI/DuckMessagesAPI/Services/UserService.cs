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

        public async Task<DTO_User_GET_Response?> GetUser(DTO_User_GET_Request request)
        {
            return await _repository.GetUser(request);
        }

        public async Task<bool> PostUser(DTO_User_POST request)
        {
            return await _repository.PostUser(request);
        }
    }
}
