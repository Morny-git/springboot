package com.mx.conf;



import com.mx.domain.ArgumentInvalidResult;
import com.mx.util.ResultEntity;
import com.mx.util.StateCode;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Created by Administrator on 2017/4/17.
 */
@ControllerAdvice
@ResponseBody
public class GlobalExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResultEntity bindExceptionHandler(MethodArgumentNotValidException e) throws Exception{
        ResultEntity result = new ResultEntity();
        //按需重新封装需要返回的错误信息
        List<ArgumentInvalidResult> invalidArguments = new ArrayList<>();

        for(FieldError error: e.getBindingResult().getFieldErrors()){
            ArgumentInvalidResult argumentInvalidResult = new ArgumentInvalidResult();
            argumentInvalidResult.setDefaultMessage(error.getDefaultMessage());
            argumentInvalidResult.setField(error.getField());
            argumentInvalidResult.setRejectedValue(error.getRejectedValue());
            invalidArguments.add(argumentInvalidResult);
        }
        result.setSuccess(false);
        result.setErrorCode(StateCode.PARAM_ERROR.getStateCode());
        result.setMsg(e.getMessage());
        result.setData(invalidArguments);
        return result;
    }
    @ExceptionHandler(value = { ConstraintViolationException.class })
    @ResponseBody
    public ResultEntity handleResourceNotFoundException(ConstraintViolationException e) {
        ResultEntity result = new ResultEntity();
        Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
        //按需重新封装需要返回的错误信息
        List<ArgumentInvalidResult> invalidArguments = new ArrayList<>();
        for (ConstraintViolation<?> violation : violations ) {
            ArgumentInvalidResult argumentInvalidResult = new ArgumentInvalidResult();
            argumentInvalidResult.setDefaultMessage(violation.getMessage());
            argumentInvalidResult.setField(violation.getPropertyPath().toString());
            argumentInvalidResult.setRejectedValue(violation.getInvalidValue());
            invalidArguments.add(argumentInvalidResult);
        }
        result.setSuccess(false);
        result.setErrorCode(StateCode.PARAM_ERROR.getStateCode());
        result.setMsg(e.toString());
        result.setData(invalidArguments);
        return result;
    }
    @ExceptionHandler(Exception.class)
    public ResultEntity baseExceptionHandler(Exception e) throws Exception{
        ResultEntity result = new ResultEntity();
        result.setSuccess(false);
        result.setErrorCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
        //result.setMsg(e.getCause().getClass().+":"+e.getCause().getMessage());
        result.setMsg(e.getClass().getName()+":"+e.getMessage());
        return result;
    }

}
