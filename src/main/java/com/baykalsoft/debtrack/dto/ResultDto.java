package com.baykalsoft.debtrack.dto;


import com.baykalsoft.debtrack.enums.ResponseCode;
import java.util.LinkedHashMap;
import java.util.Map;

public class ResultDto {

  private Map<String, Object> data = new LinkedHashMap<>();

  public static ResultDto BUILDER() {
    return new ResultDto();
  }

  public ResultDto response(ResponseCode codeEnum) {
    this.data.put("code", codeEnum.getValue());
    this.data.put("message", codeEnum.toString());
    return this;
  }

  public ResultDto add(String key, Object data) {
    this.data.put(key, data);
    return this;
  }

  public Map<String, Object> build() {
    return data;
  }

  public Map<String, Object> getData() {
    return data;
  }

  public void setData(Map<String, Object> data) {
    this.data = data;
  }
}