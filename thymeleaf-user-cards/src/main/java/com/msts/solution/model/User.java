package com.msts.solution.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
	private Number id;
	private String first_name;
	private String last_name;
	private String email;
	private String avatar;
}
