using System.Net;

namespace SuperSalesServices.Global
{
    /// <summary>
    /// Provides a centralized way to manage HTTP status codes and their associated messages.
    /// This static class ensures consistency across API responses.
    /// </summary>
    
    public static class ApiResponse
    {
        // --- Success (2xx) ---
        // 200
        public static Dictionary<string, object> GetSuccess(dynamic data) =>
        new ()
        {
            {"status", (int) HttpStatusCode.OK },
            {"message", "Data sukses Ditarik" },
            {"data", data}
        };
        public static Dictionary<string, object> GetWithPaginationSuccess(dynamic data, int pageNum, int dataAmmount, int pageSize) =>
        new ()
        {
            {"status", (int) HttpStatusCode.OK },
            {"message", "Data sukses Ditarik" },
            {"curr_page", pageNum},
            {"total_page", (dataAmmount / pageSize) + 1},
            {"total_data", dataAmmount},
            {"data", data},
        };
        public static Dictionary<string, object> PostSuccess() =>
        new ()
        {
            {"status", (int) HttpStatusCode.OK },
            {"message", "Data sukses ditambahkan" },
        };
        public static Dictionary<string, object> PutSuccess() =>
        new ()
        {
            {"status", (int) HttpStatusCode.OK },
            {"message", "Data sukses diubah" },
        };
        public static Dictionary<string, object> DeleteSuccess() =>
        new ()
        {
            {"status", (int) HttpStatusCode.OK },
            {"message", "Data sukses dihapus" },
        };

        // 204
        public static Dictionary<string, object> NoContent() =>
        new ()
        {
            {"status", (int) HttpStatusCode.NoContent },
            {"message", "Tidak ada data untuk request ini."}
        };

        // --- Client Errors (4xx) ---
        // 400
        public static Dictionary<string, object> BadRequest(string? errorMsg = null) =>
        new ()
        {
            {"status", (int) HttpStatusCode.BadRequest },
            {"message", errorMsg ?? "Bad request. Please check your input." }
        };

        // 401
        public static Dictionary<string, object> Unauthorized() =>
        new ()
        {
            {"status", (int) HttpStatusCode.Unauthorized },
            {"message", "Authentication required or failed." }
        };

        // 403
        public static Dictionary<string, object> Forbidden() =>
        new ()
        {
            {"status", (int) HttpStatusCode.Forbidden },
            {"message", "Access to the resource is forbidden." }
        };

        // 404
        public static Dictionary<string, object> NotFound(string? errorMsg) =>
        new ()
        {
            {"status", (int) HttpStatusCode.NotFound },
            {"message", errorMsg ?? "The requested resource was not found." }
        };

        // --- Server Errors (5xx) ---
        // 500
        public static Dictionary<string, object> InternalServerError(Exception? ex) =>
        new ()
        {
            {"status", (int) HttpStatusCode.InternalServerError },
            {"message", ex != null ? ex.InnerException?.Message ?? ex.Message : "Internal Server Error"}
        };

        // 503
        public static Dictionary<string, object> ServiceUnavailable() =>
        new ()
        {
            {"status", (int) HttpStatusCode.ServiceUnavailable },
            {"message", "Service is temporarily unavailable." }
        };
    }
}
