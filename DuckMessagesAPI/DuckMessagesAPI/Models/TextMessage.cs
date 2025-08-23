using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;
using Microsoft.EntityFrameworkCore;

namespace DuckMessagesAPI.Models
{
    public partial class TextMessage
    {
        [Key]
        [Column("MessageID")]
        public long MessageId { get; set; }
        [Column("SenderID")]
        public Guid SenderId { get; set; }
        [Column("ReceiverID")]
        public Guid ReceiverId { get; set; }
        public string Content { get; set; } = null!;
        public DateTime SentAt { get; set; }
        public bool IsRead { get; set; }

        [ForeignKey("ReceiverId")]
        [InverseProperty("TextMessageReceivers")]
        public virtual User Receiver { get; set; } = null!;
        [ForeignKey("SenderId")]
        [InverseProperty("TextMessageSenders")]
        public virtual User Sender { get; set; } = null!;
    }
}
