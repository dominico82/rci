package kr.co.rci.esign.admin.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import kr.co.rci.esign.admin.domain.CoTopComponent;

/**
 * Json Response Entity Builder
 * @author Eddie
 * @since 1.0.0
 */
public class JsonResponseBuilder extends CoTopComponent {
	private Logger log = LoggerFactory.getLogger(JsonResponseBuilder.class);

	private HttpHeaders headers;
	private JsonObject body = new JsonObject();
	private HttpStatus httpStatus;

	public JsonResponseBuilder() {
		// 생성 시첨에서 요청시간을 넣어둡니다.
		this.body.addProperty("reqDt", DateUtils.currentDateStr());
	}
	/**
	 * Set the given, single header value under the given name.
	 * @param headerName the header name
	 * @param headerValue the header value
	 */
	public JsonResponseBuilder setHeader(String headerName, String headerValue) {
		if("Content-Type".equals(headerName)) {
			log.warn("header 'Conten-Type' cannot be modified manually. Only 'application/json' is available.");
			return this;
		}

		if(this.headers == null)
			this.headers = new HttpHeaders();
		this.headers.set(headerName, headerValue);
		return this;
	}
	/**
	 * Convenience method to add a primitive member. The specified value is converted to a JsonPrimitive of String.
	 * @param property name of the member.
	 * @param value the string value associated with the member.
	 */
	public JsonResponseBuilder addProperty(String property, String value) {
		body.addProperty(property, value);
		return this;
	}
	/**
	 * Convenience method to add a boolean member. The specified value is converted to a JsonPrimitive of Boolean.
	 * @param property name of the member.
	 * @param value the number value associated with the member.
	 */
	public JsonResponseBuilder addProperty(String property, boolean value) {
		body.addProperty(property, value);
		return this;
	}
	/**
	 * Convenience method to add a primitive member. The specified value is converted to a JsonPrimitive of Number.
	 * @param property name of the member.
	 * @param value the number value associated with the member.
	 */
	public JsonResponseBuilder addProperty(String property, Number value) {
		body.addProperty(property, value);
		return this;
	}
	/**
	 * Adds a member, which is a name-value pair, to self. The name must be a String, but the value can be an arbitrary
	 * <p>JsonElement, thereby allowing you to build a full tree of JsonElements rooted at this node.
	 * @param property name of the member.
	 * @param value the member object.
	 */
	public JsonResponseBuilder add(String property, JsonElement value) {
		body.add(property, value);
		return this;
	}

	/**
	 * 응답코드 설정
	 * <pre>
	 * 	<h3>주요 사용 코드 예제</h3>
	 * 200: {@code HttpStatus.OK} ( 성공 : default )
	 * 401: {@code HttpStatus.UNAUTHORIZED} ( 로그인 필요 )
	 * 403: {@code HttpStatus.FORBIDDEN} ( 권한 없음 )
	 * 404: {@code HttpStatus.NOT_FOUND} ( 찾을 수 없음 )
	 * 505: {@code HttpStatus.INTERNAL_SERVER_ERROR} ( 서버 에러 )
	 * </pre>
	 * @param status
	 * @return
	 */
	public JsonResponseBuilder httpStatus(HttpStatus status) {
		this.httpStatus = status;
		return this;
	}

	/**
	 * ResponseEntity 객체 생성
	 * @return
	 */
	public ResponseEntity<?> build() {
		if( this.headers == null ) {
			this.headers = new HttpHeaders();
		}
		this.headers.set("Content-Type", MediaType.APPLICATION_JSON_UTF8_VALUE);

		if( this.httpStatus == null ) {
			log.debug("response 'httpStatus' cannot be null. This will be set to '200' as default.");
			this.httpStatus = HttpStatus.OK;
		}

		ResponseEntity<?> response = new ResponseEntity<>(toJson(body), this.headers, this.httpStatus);
		return response;
	}
}
