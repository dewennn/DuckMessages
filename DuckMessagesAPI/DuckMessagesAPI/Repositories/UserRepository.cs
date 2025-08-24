using Microsoft.EntityFrameworkCore;
using DuckMessagesAPI.DTO;
using DuckMessagesAPI.Interfaces;
using DuckMessagesAPI.Data;
using DuckMessagesAPI.Helper;
using System.Security.Principal;

namespace DuckMessagesAPI.Repositories
{
    public class UserRepository : IUserRepository
    {
        private readonly DuckMessagesContext _context;
        public UserRepository(DuckMessagesContext context)
        {
            _context = context;
        }

        public async Task<DTO_User_Auth_Response?> Login(DTO_User_Login request)
        {
            // unpack DTO
            var (phoneNumber, password) = request;

            // check if account exist
            var account = await _context.Users.FirstOrDefaultAsync(i => i.PhoneNumber == phoneNumber);
            if (account == null) return null;

            // check if password is valid
            bool isPasswordValid = PasswordHelper.VerifyPasswordHash(password, account.PasswordHash, account.PasswordSalt);
            if (!isPasswordValid) return null;

            // if all is checked then return response
            return new DTO_User_Auth_Response
            {
                DisplayName = account.DisplayName,
                PhoneNumber = account.PhoneNumber,
                // TODO generate and include a JWT token here as well.
            };
        }

        public async Task<DTO_User_Auth_Response?> Register(DTO_User_Register request)
        {
            // unpack DTO
            var (DisplayName, PhoneNumber, Password) = request;

            // check if phone number haven't been used
            var temp = await _context.Users.FirstOrDefaultAsync(i => i.PhoneNumber == PhoneNumber);
            if(temp != null) return null;

            PasswordHelper.CreatePasswordHash(Password, out byte[] passwordHash, out byte[] passwordSalt);

            // if phone number haven't been used then create user
            var newUser = new Data.Models.User
            {
                DisplayName = DisplayName,
                PhoneNumber = PhoneNumber,
                PasswordHash = passwordHash,
                PasswordSalt = passwordSalt
            };

            await _context.Users.AddAsync(newUser);
            await _context.SaveChangesAsync();

            return new DTO_User_Auth_Response
            {
                DisplayName = DisplayName,
                PhoneNumber = PhoneNumber,
            };
        }

        public async Task<List<DTO_User_GetFriends_Response>> GetFriends(Guid userId)
        {
            var friendList = await _context.UserRelationships
                .Where(i => i.UserId1 == userId || i.UserId2 == userId)
                .Select(i => i.UserId1 == userId ? i.UserId2 : i.UserId1)
                .ToListAsync();

            return await _context.Users
                .Where(i => friendList.Contains(i.UserId))
                .Select(i => new DTO_User_GetFriends_Response
                {
                    FriendId = i.UserId,
                    DisplayName = i.DisplayName,
                    MessagePreview = "...",
                    Timestamp = null
                })
                .ToListAsync();
        }

        public async Task<bool> AddFriend(DTO_User_AddFriend request)
        {
            var (SenderId, ReceiverId) = request;

            var newRelation = new Data.Models.UserRelationship
            {
                UserId1 = SenderId,
                UserId2 = ReceiverId,
                CreatedAt = DateTime.UtcNow,
            };

            await _context.UserRelationships.AddAsync(newRelation);
            await _context.SaveChangesAsync();

            return true;
        }
    }
}
