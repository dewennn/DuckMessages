using DuckMessagesAPI.Data;
using DuckMessagesAPI.Data.Models;
using DuckMessagesAPI.DTO;
using DuckMessagesAPI.Interfaces;
using Microsoft.EntityFrameworkCore;

namespace DuckMessagesAPI.Repositories
{
    public class TextMessageRepository : ITextMessageRepository
    {
        private readonly DuckMessagesContext _context;
        public TextMessageRepository(DuckMessagesContext context)
        {
            _context = context;
        }

        public async Task<List<DTO_TextMessage_GET_Response>?> TextMessage_GET(DTO_TextMessage_GET_Request request)
        {
            var (SenderId, ReceiverId) = request;

            return await _context.TextMessages
                .Where(i =>
                    (i.SenderId.Equals(SenderId) && i.ReceiverId.Equals(ReceiverId)) ||
                    (i.SenderId.Equals(ReceiverId) && i.ReceiverId.Equals(SenderId))
                )
                .Select(i => new DTO_TextMessage_GET_Response
                {
                    MessageId = i.MessageId,
                    Content = i.Content,
                    IsSender = i.SenderId.Equals(SenderId),
                    SentAt = i.SentAt,
                    IsRead = i.IsRead,
                })
                .OrderByDescending(i => i.SentAt)
                .ToListAsync();
        }

        public async Task<bool> TextMessage_POST(DTO_TextMessage_POST request)
        {
            var (SenderId, ReceiverId, Content) = request;

            // Create a new TextMessage entity from the DTO
            var newMessage = new TextMessage
            {
                SenderId = SenderId,
                ReceiverId = ReceiverId,
                Content = Content,
                SentAt = DateTime.Now,
                IsRead = false // Default to not read
            };

            // Add the new message to the context and save
            _context.TextMessages.Add(newMessage);
            var saved = await _context.SaveChangesAsync();

            // Return true if at least one record was changed
            return saved > 0;
        }

        public async Task<bool> TextMessage_PUT(DTO_TextMessage_PUT request)
        {
            var (MessageId, Content) = request;

            // Find the existing message by its ID
            var message = await _context.TextMessages.FindAsync(MessageId);

            // If the message doesn't exist, return false
            if (message == null)
            {
                return false;
            }

            // Update the content and save changes
            message.Content = Content;
            var saved = await _context.SaveChangesAsync();

            return saved > 0;
        }

        public async Task<bool> TextMessage_MarkAsRead(DTO_TextMessage_MarkAsRead request)
        {
            var MessageId = request;

            // Find the existing message by its ID
            var message = await _context.TextMessages.FindAsync(MessageId);

            // If the message doesn't exist, return false
            if (message == null)
            {
                return false;
            }

            // Mark the message as read and save changes
            message.IsRead = true;
            var saved = await _context.SaveChangesAsync();

            return saved > 0;
        }
    }

}
