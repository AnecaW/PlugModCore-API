package org.wannes.plugModCore.api;

import java.util.Map;

/**
 * Optional web API contract for modules.
 * PlugModCore forwards /api/modules/{id}/... requests to modules implementing this interface.
 */
public interface ModuleWebApi {

    ApiResponse handleApiRequest(String method, String path, Map<String, String> params);

    final class ApiResponse {
        private final int statusCode;
        private final String contentType;
        private final String body;

        public ApiResponse(int statusCode, String contentType, String body) {
            this.statusCode = statusCode;
            this.contentType = contentType;
            this.body = body;
        }

        public int getStatusCode() {
            return statusCode;
        }

        public String getContentType() {
            return contentType;
        }

        public String getBody() {
            return body;
        }

        public static ApiResponse okJson(String body) {
            return new ApiResponse(200, "application/json", body);
        }

        public static ApiResponse badRequest(String body) {
            return new ApiResponse(400, "text/plain", body);
        }

        public static ApiResponse notFound(String body) {
            return new ApiResponse(404, "text/plain", body);
        }

        public static ApiResponse error(String body) {
            return new ApiResponse(500, "text/plain", body);
        }
    }
}
