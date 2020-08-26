package com.venusj.secondkill.exception;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.venusj.secondkill.common.ApiResponse;
import com.venusj.secondkill.common.CodeMsg;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;


@ControllerAdvice
@ResponseBody
public class GlobalExceptionHandler {
	@ExceptionHandler(value=Exception.class)
	public ApiResponse<String> exceptionHandler(HttpServletRequest request, Exception e){
		e.printStackTrace();
		if(e instanceof GlobalException) {
			GlobalException ex = (GlobalException)e;
			return ApiResponse.error(ex.getCm());
		}else if(e instanceof BindException) {
			BindException ex = (BindException)e;
			List<ObjectError> errors = ex.getAllErrors();
			ObjectError error = errors.get(0);
			String msg = error.getDefaultMessage();
			return ApiResponse.error(CodeMsg.BIND_ERROR.fillArgs(msg));
		}else {
			return ApiResponse.error(CodeMsg.SERVER_ERROR);
		}
	}
}
