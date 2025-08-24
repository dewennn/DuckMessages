using DuckMessagesAPI.Data.Models;
using DuckMessagesAPI.DTO;
using DuckMessagesAPI.Interfaces;
using Microsoft.AspNetCore.Mvc;
using Microsoft.EntityFrameworkCore;
using SuperSalesServices.Global;

namespace DuckMessagesAPI.Controllers
{
    [Route("api/v1/text-message")]
    [ApiController]
    public class TextMessageController : Controller
    {
        private readonly ITextMessageService _service;

        public TextMessageController(ITextMessageService service)
        {
            _service = service;
        }

        [HttpGet("")]
        public async Task<IActionResult> TextMessage_GET([FromQuery] DTO_TextMessage_GET_Request request)
        {
            try
            {
                var result = await _service.TextMessage_GET(request);

                // Check if the result is null, which might indicate an issue or no messages
                if (result == null)
                {
                    return NotFound(ApiResponse.NotFound("No messages found between the specified users."));
                }

                // Return the list of messages with a 200 OK status
                return Ok(ApiResponse.GetSuccess(result));
            }
            catch (Exception ex)
            {
                return StatusCode(500, ApiResponse.InternalServerError(ex));
            }
        }

        [HttpPost("")]
        public async Task<IActionResult> TextMessage_POST([FromBody] DTO_TextMessage_POST request)
        {
            try
            {
                var result = await _service.TextMessage_POST(request);

                if (result)
                    return StatusCode(200, ApiResponse.PostSuccess());
                else
                    return StatusCode(400, ApiResponse.BadRequest("Failed to send message."));
            }
            catch (Exception ex)
            {
                return StatusCode(500, ApiResponse.InternalServerError(ex));
            }
        }

        [HttpPut("")]
        public async Task<IActionResult> TextMessage_PUT([FromBody] DTO_TextMessage_PUT request)
        {
            try
            {
                var result = await _service.TextMessage_PUT(request);

                if (result)
                    return Ok(ApiResponse.PutSuccess());
                else
                    return NotFound(ApiResponse.NotFound($"Message with ID {request.MessageId} not found."));
            }
            catch (Exception ex)
            {
                return StatusCode(500, ApiResponse.InternalServerError(ex));
            }
        }

        [HttpPost("mark-as-read")]
        public async Task<IActionResult> TextMessage_MarkAsRead([FromBody] DTO_TextMessage_MarkAsRead request)
        {
            try
            {
                var result = await _service.TextMessage_MarkAsRead(request);

                if (result)
                    return Ok(ApiResponse.PostSuccess());
                else
                    return NotFound(ApiResponse.NotFound($"Message with ID {request.MessageId} not found."));
            }
            catch (Exception ex)
            {
                return StatusCode(500, ApiResponse.InternalServerError(ex));
            }
        }
    }
}
