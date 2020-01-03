package com.example.demo.common.vo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

public class Result<T> {

	private String resultCode;
	private String resultDesc;
    @JsonInclude(JsonInclude.Include.NON_NULL)
	private T resultData;
	
	@JsonIgnore
	public boolean isSuccess() {
		return ResultCode.RESULT_SUCCESS.equals(resultCode);
	}

	@JsonIgnore
	public boolean isError() {
		return !isSuccess();
	}

	public String getResultCode() {
		return resultCode;
	}

	public void setResultCode(String resultCode) {
		this.resultCode = resultCode;
	}

	public String getResultDesc() {
		return resultDesc;
	}

	public void setResultDesc(String resultDesc) {
		this.resultDesc = resultDesc;
	}

	public T getResultData() {
		return resultData;
	}

	public void setResultData(T resultData) {
		this.resultData = resultData;
	}
	
    public static <E> Result<E> successResult() {
        return result(ResultCode.RESULT_SUCCESS);
    }

    public static <E> Result<E> successResult(E data) {
        Result<E> result = successResult();
        result.setResultData(data);
        return result;
    }

	public static <E> Result<E> result(String resultCode) {
		return result(resultCode, ResultCode.getValueByKey(resultCode));
	}

	public static <E> Result<E> result(String resultCode, String desc) {
		Result<E> result = new Result<E>();
		result.setResultCode(resultCode);
		result.setResultDesc(desc);
		return result;
	}
	
}
