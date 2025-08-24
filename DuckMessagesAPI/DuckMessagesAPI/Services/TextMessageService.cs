using DuckMessagesAPI.Data;
using DuckMessagesAPI.DTO;
using DuckMessagesAPI.Interfaces;
using DuckMessagesAPI.Repositories;

namespace DuckMessagesAPI.Services
{
    public class TextMessageService : ITextMessageService
    {
        private readonly TextMessageRepository _repository;
        public TextMessageService(TextMessageRepository repository)
        {
            _repository = repository;
        }

        public async Task<List<DTO_TextMessage_GET_Response>?> TextMessage_GET(DTO_TextMessage_GET_Request request)
        {
            // Call the repository's GET method and return the result
            return await _repository.TextMessage_GET(request);
        }

        public async Task<bool> TextMessage_POST(DTO_TextMessage_POST request)
        {
            // Call the repository's POST method and return the result
            return await _repository.TextMessage_POST(request);
        }

        public async Task<bool> TextMessage_PUT(DTO_TextMessage_PUT request)
        {
            // Call the repository's PUT method and return the result
            return await _repository.TextMessage_PUT(request);
        }

        public async Task<bool> TextMessage_MarkAsRead(DTO_TextMessage_MarkAsRead request)
        {
            // Call the repository's MarkAsRead method and return the result
            return await _repository.TextMessage_MarkAsRead(request);
        }
    }
}

