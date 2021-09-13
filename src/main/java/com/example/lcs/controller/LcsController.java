package com.example.lcs.controller;

import java.lang.invoke.MethodHandles;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.lcs.dto.ExceptionDetail;
import com.example.lcs.dto.LcsInput;
import com.example.lcs.dto.LcsOutput;
import com.example.lcs.service.LcsService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/lcs")
@Api(value = "Longest Common SubStrings", tags = { "Longest Common SubStrings" })
public class LcsController {

	private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

	@Autowired
	private LcsService lcsService;

	@PostMapping
	@ApiOperation(value = "Get Longest Common SubStrings")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Ok", response = LcsOutput.class),
			@ApiResponse(code = 400, message = "Bad Request", response = ExceptionDetail.class), })
	public ResponseEntity<LcsOutput> getLongestCommonString(@Valid @RequestBody LcsInput lcsInput) {
		logger.info("Attepting to get the Longest common substring : {}", lcsInput.toString());
		LcsOutput lcsOutput = lcsService.getLcsStrings(lcsInput);
		return new ResponseEntity<>(lcsOutput, HttpStatus.OK);
	}

}
