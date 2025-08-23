using System.ComponentModel.DataAnnotations;

namespace DuckMessagesAPI.DTO
{
    public class DTO_User_GET_Request
    {
        public string PhoneNumber { get; set; }
        public string Password { get; set; }
        public void Deconstruct(out string PhoneNumber, out string Password)
        {
            PhoneNumber = this.PhoneNumber;
            Password = this.Password;
        }
    }
    public class DTO_User_GET_Response
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

    public class DTO_User_POST
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
}
