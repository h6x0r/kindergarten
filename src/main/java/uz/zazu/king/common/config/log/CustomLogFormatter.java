package uz.zazu.king.common.config.log;

import io.micrometer.common.util.StringUtils;
import org.zalando.logbook.*;
import org.zalando.logbook.HttpRequest;
import org.zalando.logbook.HttpResponse;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class CustomLogFormatter implements HttpLogFormatter {

    private static final int MAX_BODY_LENGTH = 2048;

    @Override
    public String format(Precorrelation precorrelation, HttpRequest request) throws IOException {
        LocalDateTime startTime = LocalDateTime.now();

        String requestBody = new String(request.getBody(), StandardCharsets.UTF_8);
        String requestBodyTrimmed = requestBody.isBlank()
                ? "No Content"
                : requestBody.substring(0, Math.min(requestBody.length(), MAX_BODY_LENGTH));

        return "************* Request ************* | " +
                "Request URI: " + request.getRequestUri() + " | " +
                "Request Method: " + request.getMethod() + " | " +
                "Request Headers: " + request.getHeaders() + " | " +
                "Request Content Length: " + requestBody.length() + " | " +
                "Request Body: " + requestBodyTrimmed;
    }

    @Override
    public String format(Correlation correlation, HttpResponse response) throws IOException {
        byte[] responseBodyBytes = response.withBody().getBody();
        String responseBody = new String(responseBodyBytes, StandardCharsets.UTF_8);
        String responseBodyTrimmed = responseBody.isBlank()
                ? "No Content"
                : responseBody.substring(0, Math.min(responseBody.length(), MAX_BODY_LENGTH));

        return "************* Response ************* | " +
                "Response Content Length: " + responseBodyBytes.length + " | " +
                "Response Body: " + responseBodyTrimmed + " | " +
                "Response Status Code: " + response.getStatus() + " | " +
                "Response Status Message: " + response.getReasonPhrase();
    }
}
