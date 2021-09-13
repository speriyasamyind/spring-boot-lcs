package com.example.lcs.service;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.lcs.dto.LcsInput;
import com.example.lcs.dto.LcsOutput;
import com.example.lcs.exception.AppException;

@SpringBootTest
public class LcsServiceTest {

	@Autowired
	private LcsService lcsService;

	@BeforeEach
	public void init() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	@DisplayName("Should return \"com\" for the given input")
	public void validate_lcs_with_input_1() {
		LcsInput input = new LcsInput();
		List<Map<String, String>> setOfStrings = new ArrayList<>();
		Map<String, String> map1 = Map.of("value", "comcast");
		setOfStrings.add(map1);
		Map<String, String> map2 = Map.of("value", "communicate");
		setOfStrings.add(map2);
		Map<String, String> map3 = Map.of("value", "commutation");
		setOfStrings.add(map3);
		input.setSetOfStrings(setOfStrings);

		LcsOutput outPut = lcsService.getLcsStrings(input);

		assertNotNull(outPut);
		assertNotNull(outPut.getLcs());
		assertTrue(outPut.getLcs().get(0).get("value").toString().equals("com"));
	}

	@Test
	@DisplayName("Should return \"abc\" , \"com\" for the given input")
	public void validate_lcs_with_input_2() {
		LcsInput input = new LcsInput();
		List<Map<String, String>> setOfStrings = new ArrayList<>();
		Map<String, String> map1 = Map.of("value", "comcastabc");
		setOfStrings.add(map1);
		Map<String, String> map2 = Map.of("value", "communicateabc");
		setOfStrings.add(map2);
		Map<String, String> map3 = Map.of("value", "commutabction");
		setOfStrings.add(map3);
		input.setSetOfStrings(setOfStrings);

		LcsOutput outPut = lcsService.getLcsStrings(input);

		assertNotNull(outPut);
		assertNotNull(outPut.getLcs());
		assertTrue(outPut.getLcs().get(0).get("value").toString().equals("abc"));
		assertTrue(outPut.getLcs().get(1).get("value").toString().equals("com"));
	}

	@Test
	@DisplayName("Should throw \"setOfStrings should not be Empty\" if input list is empty")
	public void validate_lcs_with_input_3() {
		LcsInput input = new LcsInput();
		List<Map<String, String>> setOfStrings = new ArrayList<>();
		input.setSetOfStrings(setOfStrings);
		assertThatThrownBy(() -> lcsService.getLcsStrings(input)).isInstanceOf(AppException.class)
				.hasMessage("setOfStrings should not be Empty");
	}

	@Test
	@DisplayName("Should throw \"All the strings are not unqiue in setOfStrings\" if input list has duplicate values")
	public void validate_lcs_with_input_4() {
		LcsInput input = new LcsInput();
		List<Map<String, String>> setOfStrings = new ArrayList<>();

		Map<String, String> map1 = Map.of("value", "comcast");
		setOfStrings.add(map1);
		Map<String, String> map2 = Map.of("value", "comcast");
		setOfStrings.add(map2);
		input.setSetOfStrings(setOfStrings);

		assertThatThrownBy(() -> lcsService.getLcsStrings(input)).isInstanceOf(AppException.class)
				.hasMessage("All the strings are not unqiue in setOfStrings");
	}
}
