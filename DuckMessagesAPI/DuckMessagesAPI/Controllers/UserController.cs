using Microsoft.AspNetCore.Mvc;
using SuperSalesServices.Global;
using DuckMessagesAPI.DTO;
using DuckMessagesAPI.Interfaces;

namespace DuckMessagesAPI.Controllers
{
    [Route("api/v1/user")]
    [ApiController]
    public class UserController : Controller
    {
        private readonly IUserService _service;
        public UserController(IUserService service)
        {
            _service = service;
        }

        [HttpPost("login")]
        public async Task<IActionResult> Login([FromBody] DTO_User_Login request)
        {
            try
            {
                var result = await _service.Login(request);

                if (result != null) return StatusCode(200, ApiResponse.GetSuccess(result));
                else return StatusCode(400, ApiResponse.BadRequest());
            }
            catch (Exception ex)
            {
                return StatusCode(500, ApiResponse.InternalServerError(ex));
            }
        }
        [HttpPost("register")]
        public async Task<IActionResult> Register(DTO_User_Register request)
        {
            try
            {
                var result = await _service.Register(request);

                if (result != null) return StatusCode(200, ApiResponse.GetSuccess(result));
                else return StatusCode(400, ApiResponse.BadRequest());
            }
            catch (Exception ex)
            {
                return StatusCode(500, ApiResponse.InternalServerError(ex));
            }
        }
        [HttpGet("friend")]
        public async Task<IActionResult> GetFriends([FromQuery] Guid userId)
        {
            try
            {
                var result = await _service.GetFriends(userId);

                if (result != null) return StatusCode(200, ApiResponse.GetSuccess(result));
                else return StatusCode(400, ApiResponse.BadRequest());
            }
            catch (Exception ex)
            {
                return StatusCode(500, ApiResponse.InternalServerError(ex));
            }
        }

        [HttpPost("friend")]
        public async Task<IActionResult> AddFriend([FromBody] DTO_User_AddFriend request)
        {
            try
            {
                var result = await _service.AddFriend(request);

                if (result) return StatusCode(200, ApiResponse.PostSuccess());
                else return StatusCode(400, ApiResponse.BadRequest());
            }
            catch (Exception ex)
            {
                return StatusCode(500, ApiResponse.InternalServerError(ex));
            }
        }
    }
}
