package com.example.lcs.dto;

import java.util.List;
import java.util.Map;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class LcsOutput {
	
	@ApiModelProperty(example = "[ {\"value\": \"cast\"}]")
	List<Map<String, String>> lcs;
	
}
