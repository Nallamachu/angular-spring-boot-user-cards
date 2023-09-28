package com.msts.solution.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApiResponse {
	private Number page;
	private Number per_page;
	private Number total;
	private Number total_pages;
	private List<User> data;
	private Support support;
}
