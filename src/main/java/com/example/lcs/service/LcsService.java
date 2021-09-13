package com.example.lcs.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.example.lcs.dto.LcsInput;
import com.example.lcs.dto.LcsOutput;
import com.example.lcs.exception.AppException;

@Service
public class LcsService {

	public LcsOutput getLcsStrings(@Valid LcsInput lcsInput) {
		LcsOutput lcsOuput = new LcsOutput();
		List<Map<String, String>> li = lcsInput.getSetOfStrings();

		validateInput(li);

		String[] arr = new String[li.size()];
		int j = 0;

		for (Map<String, String> l : li) {
			arr[j++] = l.get("value");
		}

		List<String> matchList = getLongComSubStringList(arr);

		Collections.sort(matchList);
		List<Map<String, String>> lc = new ArrayList<>();

		matchList.stream().forEach(mat -> {
			Map<String, String> m = new HashMap<>();
			m.put("value", mat);
			lc.add(m);
		});

		lcsOuput.setLcs(lc);

		return lcsOuput;
	}

	private void validateInput(List<Map<String, String>> li) {
		if (CollectionUtils.isEmpty(li)) {
			throw new AppException(HttpStatus.BAD_REQUEST, "setOfStrings should not be Empty");
		}
		
		 Set<String> lSet = new HashSet<String>();
		 for (Map<String, String> l : li) {
			 lSet.add(l.get("value"));
			}
		 if(li.size() != lSet.size()) {
			 throw new AppException(HttpStatus.BAD_REQUEST, "All the strings are not unqiue in setOfStrings");
		 }
	}

	private List<String> getLongComSubStringList(String[] arr) {
		List<String> matchList = commonSubstring(arr[0], arr[1]);
		for (int i = 2; i < arr.length; i++) {
			matchList = validateMatch(matchList, arr[i]);
		}
		return matchList;
	}

	private List<String> commonSubstring(String S1, String S2) {
		Integer match[][] = new Integer[S1.length()][S2.length()];

		int len1 = S1.length();
		int len2 = S2.length();
		int max = Integer.MIN_VALUE;
		ArrayList<String> result = null;

		for (int i = 0; i < len1; i++) {
			for (int j = 0; j < len2; j++) {
				if (S1.charAt(i) == S2.charAt(j)) {
					if (i == 0 || j == 0)
						match[i][j] = 1;

					else
						match[i][j] = match[i - 1][j - 1] + 1;

					if (match[i][j] > max) {
						max = match[i][j];
						result = new ArrayList<String>();
						result.add(S1.substring(i - max + 1, i + 1));
					} else if (match[i][j] == max) {
						result.add(S1.substring(i - max + 1, i + 1));
					}
				} else
					match[i][j] = 0;
			}
		}
		return result;
	}

	private List<String> validateMatch(List<String> lcsList, String str) {
		List<String> matchedList = new ArrayList<>();

		lcsList.stream().forEach(lcs -> {
			if (str.contains(lcs))
				matchedList.add(lcs);
		});
		return matchedList;
	}
}
