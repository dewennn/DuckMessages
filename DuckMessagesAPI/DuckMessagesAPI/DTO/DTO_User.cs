using System.ComponentModel.DataAnnotations;

namespace DuckMessagesAPI.DTO
{
    public class DTO_User_Login
    {
        public string PhoneNumber { get; set; }
        public string Password { get; set; }
        public void Deconstruct(out string PhoneNumber, out string Password)
        {
            PhoneNumber = this.PhoneNumber;
            Password = this.Password;
        }
    }

    public class DTO_User_Register
    {
        public string DisplayName { get; set; }
        public string PhoneNumber { get; set; }
        public string Password { get; set; }
        public void Deconstruct(out string DisplayName, out string PhoneNumber, out string Password)
        {
            DisplayName = this.DisplayName;
            PhoneNumber = this.PhoneNumber;
            Password = this.Password;
        }
    }

    public class DTO_User_Auth_Response
    {
        public Guid UserId { get; set; }
        public string DisplayName { get; set; }
        public string PhoneNumber { get; set; }
        public void Deconstruct(out Guid UserId, out string DisplayName, out string PhoneNumber)
        {
            UserId = this.UserId;
            DisplayName = this.DisplayName;
            PhoneNumber = this.PhoneNumber;
        }
    }

    public class DTO_User_GetFriends_Response
    {
        public Guid FriendId { get; set; }
        public string DisplayName { get; set; }
        public string? MessagePreview { get; set; }
        public string? Timestamp { get; set; }
        public void Deconstruct(out Guid FriendId, out string DisplayName, out string? MessagePreview, out string? Timestamp)
        {
            FriendId = this.FriendId;
            DisplayName = this.DisplayName;
            MessagePreview = this.MessagePreview;
            Timestamp = this.Timestamp;
        }
    }

    public class DTO_User_AddFriend
    {
        public Guid SenderId { get; set; }
        public string ReceiverPhoneNumber { get; set; }
        public void Deconstruct(out Guid SenderId, out string ReceiverPhoneNumber)
        {
            SenderId = this.SenderId;
            ReceiverPhoneNumber = this.ReceiverPhoneNumber;
        }
    }
}
