using DuckMessagesAPI.DTO;

namespace DuckMessagesAPI.Interfaces
{
    public interface ITextMessageRepository
    {
        Task<List<DTO_TextMessage_GET_Response>?> TextMessage_GET(DTO_TextMessage_GET_Request request);
        Task<bool> TextMessage_POST(DTO_TextMessage_POST request);
        Task<bool> TextMessage_PUT(DTO_TextMessage_PUT request);
        Task<bool> TextMessage_MarkAsRead(DTO_TextMessage_MarkAsRead request);
    }
}
