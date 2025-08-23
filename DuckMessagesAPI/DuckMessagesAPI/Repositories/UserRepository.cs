using Microsoft.EntityFrameworkCore;
using DuckMessagesAPI.DTO;
using DuckMessagesAPI.Interfaces;
using DuckMessagesAPI.Models;

namespace DuckMessagesAPI.Repositories
{
    public class UserRepository : IUserRepository
    {
        private readonly ApplicationDbContext _context;
        public UserRepository(ApplicationDbContext context)
        {
            _context = context;
        }

        public async Task<DTO_User_GET_Response?> GetUser(DTO_User_GET_Request request)
        {
            // unpack DTO
            var (PhoneNumber, Password) = request;

            // check if account exist
            // var account = await _context. Users.FirstOrDefaultAsync(i => i.PhoneNumber == PhoneNumber);

            return new DTO_User_GET_Response
            {
                DisplayName = "account.DisplayName",
                PhoneNumber = "account.PhoneNumber",
            };

            //// if not return null
            //if (account == null) return null;

            //// A PROBLEM IN ACCOUNT

            //// if exist return DTO
            //else
            //{
            //    return new DTO_User_GET_Response
            //    {
            //        DisplayName = "account.DisplayName",
            //        PhoneNumber = "account.PhoneNumber",
            //    };
            //}
        }

        public async Task<bool> PostUser(DTO_User_POST request)
        {
            // unpack DTO
            var (DisplayName, PhoneNumber, Password) = request;

            // check if phone number haven't been used
            var temp = await _context.Users.FirstOrDefaultAsync(i => i.PhoneNumber == PhoneNumber);
            if(temp != null) return false;

            // if phone number haven't been used then create user
            var newUser = new User
            {
                DisplayName = DisplayName,
                PhoneNumber = PhoneNumber,
            };

            await _context.Users.AddAsync(newUser);
            await _context.SaveChangesAsync();

            return true;
        }
    }
}
