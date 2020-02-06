package kr.co.rci.esign.admin.domain;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Locale;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import kr.co.rci.esign.admin.constant.AppConstants;


/**
 * 최상위 컴포넌트
 * @author Think-Tree Inc.
 * @version 1.0.0
 */
public abstract class CoTopComponent {
	/** 로그인 추적 로그 */
	protected static final Logger login_log = LoggerFactory.getLogger("LOGIN-LOG");
	protected static final Logger log = LoggerFactory.getLogger("SYSTEM_LOG");

	/** The application context. */
	protected static WebApplicationContext applicationContext;

	/**
	 * Sets the web application context.
	 *
	 * @param applicationContext the new web application context
	 */
	@SuppressWarnings("static-access")
	@Autowired
	public void setWebApplicationContext(WebApplicationContext applicationContext) {
		this.applicationContext = applicationContext;
	}

	/** The message source. */
	protected static MessageSource messageSource;

	/**
	 * Sets the message source.
	 *
	 * @param messageSource the new message source
	 */
	@SuppressWarnings("static-access")
	@Autowired
	public void setMessageSource(MessageSource messageSource) {
		this.messageSource = messageSource;
	}

	/** The env. */
	protected static Environment appEnv;

	/**
	 * Sets the environment.
	 *
	 * @param env the new environment
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	@SuppressWarnings("static-access")
	@Resource
	public void setEnvironment(Environment env) throws IOException {
		this.appEnv = env;
	}

    protected static String loginUserName() {
    	try{
    	return SecurityContextHolder.getContext().getAuthentication().getName();
    	}catch(Exception e) {return "";}
	}

	/**
	 * 현재 설정중인 locale정보를 반환.
	 *
	 * @return the locale
	 */
	protected static Locale getLocale() {
		return LocaleContextHolder.getLocale();
	}

	/**
	 * Gets the message.
	 *
	 * @param code the code
	 * @return the message
	 */
	protected static String getMessage(String code) {
		return messageSource.getMessage(code, null, getLocale());
	}

	/**
	 * Gets the message.
	 *
	 * @param code the code
	 * @param args the args
	 * @return the message
	 */
	protected static String getMessage(String code, Object[] args) {
		return messageSource.getMessage(code, args, getLocale());
	}



	/**
	 * Put session object.
	 *
	 * @param key the key
	 * @param obj the obj
	 * @return true, if successful
	 */
	protected static boolean putSessionObject(String key, Object obj) {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		if(request != null) {
			HttpSession session = request.getSession();
			if(session != null) {
				session.setAttribute(key, obj);
				return true;
			}
		}
		return false;
	}


	/**
	 * Gets the servlet request.
	 *
	 * @return the servlet request
	 */
	protected static HttpServletRequest getServletRequest() {
		return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
	}

	/**
	 * Gets the session object.
	 *
	 * @param key the key
	 * @return the session object
	 */
	protected static Object getSessionObject(String key) {
		return getSessionObject(key, false);
	}

	/**
	 * Gets the session object.
	 *
	 * @param key the key
	 * @param oneTimeFlag the one time flag
	 * @return the session object
	 */
	protected static Object getSessionObject(String key, boolean oneTimeFlag) {
		HttpServletRequest request = getServletRequest();
		if(request != null) {

			HttpSession session = request.getSession();
			if(session != null) {
				Object sessionObj = session.getAttribute(key);
				if(oneTimeFlag) {
					try {
						session.removeAttribute(key);
					} catch (Exception e) {
						log.error(e.getMessage(), e);
					}
				}
				return sessionObj;
			}
		}
		return null;
	}

	/**
	 * Delete session.
	 *
	 * @param key the key
	 */
	protected static void deleteSession(String key) {

		try {
			HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
			if(request != null) {

				HttpSession session = request.getSession();
				if(session != null) {
					session.removeAttribute(key);
				}
			}
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
	}


	/**
	 * Response Entity 생성
	 * @param json
	 * @return
	 */
	protected ResponseEntity<Object> makeResponseEntity(JsonObject json) {
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.set("Content-Type", MediaType.APPLICATION_JSON_UTF8_VALUE);
		return new ResponseEntity<Object>(toJson(json), responseHeaders, HttpStatus.OK);
	}

	protected ResponseEntity<Object> makeResponseEntity(Map<String, Object> json) {
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.set("Content-Type", MediaType.APPLICATION_JSON_UTF8_VALUE);
		return new ResponseEntity<Object>(toJson(json), responseHeaders, HttpStatus.OK);
	}

	protected static ResponseEntity<Object> makeJsonResponse(Object obj) {
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.set("Content-Type", MediaType.APPLICATION_JSON_UTF8_VALUE);
		return new ResponseEntity<Object>(toJson(obj), responseHeaders, HttpStatus.OK);
	}

	/**
	 * 객체(obj)를 Json형태의 String으로 변환합니다.
	 * @param obj
	 * @return
	 */
	protected static String toJson(Object obj) {
		return new GsonBuilder().create().toJson(obj);
	}

	/**
	 * 응답({@link HttpServletResponse}) 전송
	 * @param res
	 * @param jsonObject
	 * @throws IOException
	 */
	protected void writeResponse(HttpServletResponse res, JsonObject jsonObject ) throws IOException {
		writeResponse(res,jsonObject.toString());
	}

	/**
	 * 응답({@link HttpServletResponse}) 전송
	 * @param res
	 * @param message
	 * @throws IOException
	 */
	protected void writeResponse(HttpServletResponse res, String message ) throws IOException {
		res.setHeader("Content-Type", MediaType.APPLICATION_JSON_UTF8_VALUE);

		PrintWriter pw = res.getWriter();
		pw.write(message);
		pw.close();
	}

	   /**
     * Checks if is empty.
     *
     * @param s the s
     * @return the boolean
     */
    protected Boolean isEmpty(String s) {
		return StringUtils.isEmpty(s);
	}

    /**
     * Avoid null.
     *
     * @param s the s
     * @return the string
     */
    protected String avoidNull(String s) {
    	return StringUtils.defaultString(s);
    }

    /**
     * Avoid null.
     *
     * @param s0 the s 0
     * @param s1 the s 1
     * @return the string
     */
    protected String avoidNull(String s0, String s1) {
    	return StringUtils.defaultIfEmpty(s0, s1);
    }

    protected String emptyToNull(String s) {
    	return isEmpty(s) ? null : s;
    }

    /**
     * Gets the code string.<br>
     * e-best(MariaDB) 관리자에서 사용하는 코드값의 표시명을 반환
     *
     * @param codeClass the code class
     * @param codeVal the code val
     * @return the code string
     */
    protected String getCodeString(String codeClass, String codeVal) {
    	if(isEmpty(codeClass) || isEmpty(codeVal)) {
    		return "";
    	}
    	String convertKey = codeClass + "-" + codeVal;
		if(AppConstants.CODE_NAME_MAP.containsKey(convertKey)) {
			return AppConstants.CODE_NAME_MAP.get(convertKey);
		}
    	return codeVal;
	}
}
