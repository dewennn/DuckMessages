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
        public async Task<IActionResult> GetUser([FromBody] DTO_User_GET_Request request)
        {
            try
            {
                var result = await _service.GetUser(request);

                if (result != null) return StatusCode(200, ApiResponse.GetSuccess(result));
                else return StatusCode(400, ApiResponse.BadRequest());
            }
            catch (Exception ex)
            {
                return StatusCode(500, ApiResponse.InternalServerError(ex));
            }
        }
        [HttpPost("register")]
        public async Task<IActionResult> CreateUser(DTO_User_POST request)
        {
            try
            {
                var result = await _service.PostUser(request);
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
