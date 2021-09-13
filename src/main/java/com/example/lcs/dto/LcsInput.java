package com.example.lcs.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class LcsInput {

	@ApiModelProperty(example = "[{\"value\": \"comcast\"}, {\"value\": \"communicate\"},{\"value\": \"commutation\"}]")
	List<Map<String, String>> setOfStrings = new ArrayList<>();

}
