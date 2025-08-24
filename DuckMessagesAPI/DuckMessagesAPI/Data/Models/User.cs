using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;
using Microsoft.EntityFrameworkCore;

namespace DuckMessagesAPI.Data.Models
{
    [Index("PhoneNumber", Name = "UQ__Users__85FB4E388CC4A515", IsUnique = true)]
    public partial class User
    {
        public User()
        {
            TextMessageReceivers = new HashSet<TextMessage>();
            TextMessageSenders = new HashSet<TextMessage>();
            UserRelationshipUserId1Navigations = new HashSet<UserRelationship>();
            UserRelationshipUserId2Navigations = new HashSet<UserRelationship>();
        }

        [Key]
        [Column("UserID")]
        public Guid UserId { get; set; }
        [StringLength(20)]
        public string DisplayName { get; set; } = null!;
        [StringLength(20)]
        public string PhoneNumber { get; set; } = null!;
        [MaxLength(64)]
        public byte[] PasswordHash { get; set; } = null!;
        [MaxLength(32)]
        public byte[] PasswordSalt { get; set; } = null!;
        public DateTime CreatedAt { get; set; }

        [InverseProperty("Receiver")]
        public virtual ICollection<TextMessage> TextMessageReceivers { get; set; }
        [InverseProperty("Sender")]
        public virtual ICollection<TextMessage> TextMessageSenders { get; set; }
        [InverseProperty("UserId1Navigation")]
        public virtual ICollection<UserRelationship> UserRelationshipUserId1Navigations { get; set; }
        [InverseProperty("UserId2Navigation")]
        public virtual ICollection<UserRelationship> UserRelationshipUserId2Navigations { get; set; }
    }
}
