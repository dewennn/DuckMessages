using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;
using Microsoft.EntityFrameworkCore;

namespace DuckMessagesAPI.Data.Models
{
    [Index("UserId1", "UserId2", Name = "UQ_UserRelationshipPair", IsUnique = true)]
    public partial class UserRelationship
    {
        [Key]
        [Column("RelationshipID")]
        public long RelationshipId { get; set; }
        [Column("UserID1")]
        public Guid UserId1 { get; set; }
        [Column("UserID2")]
        public Guid UserId2 { get; set; }
        public DateTime CreatedAt { get; set; }

        [ForeignKey("UserId1")]
        [InverseProperty("UserRelationshipUserId1Navigations")]
        public virtual User UserId1Navigation { get; set; } = null!;
        [ForeignKey("UserId2")]
        [InverseProperty("UserRelationshipUserId2Navigations")]
        public virtual User UserId2Navigation { get; set; } = null!;
    }
}
