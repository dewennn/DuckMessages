namespace DuckMessagesAPI.DTO
{
    public class DTO_TextMessage_GET_Request
    {
        public Guid SenderId { get; set; }
        public Guid ReceiverId { get; set; }
        public void Deconstruct(out Guid SenderId, out Guid ReceiverId)
        {
            SenderId = this.SenderId;
            ReceiverId = this.ReceiverId;
        }
    }

    public class DTO_TextMessage_GET_Response
    {
        public long MessageId {  get; set; }
        public string Content { get; set; }
        public DateTime SentAt { get; set; }
        public bool IsRead { get; set; }
        public bool IsSender { get; set; }
    }

    public class DTO_TextMessage_POST
    {
        public Guid SenderId { get; set; }
        public Guid ReceiverId { get; set; }
        public string Content { get; set; }
        public void Deconstruct(out Guid SenderId, out Guid ReceiverId, out string Content)
        {
            SenderId = this.SenderId;
            ReceiverId = this.ReceiverId;
            Content = this.Content;
        }
    }

    public class DTO_TextMessage_PUT
    {
        public long MessageId {  get; set; }
        public string Content { get; set; }
        public void Deconstruct(out long MessageId, out string Content)
        {
            MessageId = this.MessageId;
            Content = this.Content;
        }
    }

    public class DTO_TextMessage_MarkAsRead
    {
        public long MessageId {  get; set; }

        public void Deconstruct(out long MessageId)
        {
            MessageId = this.MessageId;
        }
    }
}
