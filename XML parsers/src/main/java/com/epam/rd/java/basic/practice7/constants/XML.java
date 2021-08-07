package com.epam.rd.java.basic.practice7.constants;

public enum XML {
	CATALOG("catalog"),
	SONG("song"),
	TITLE("title"),
	MUSICIAN("musician"),
	COUNTRY("country"),
	YEAR("year"),
	RATING("rating");


	private String value;

	XML(String value) {
		this.value = value;
	}

	public boolean equalsTo(String name) {
		return value.equals(name);
	}

	public String value() {
		return value;
	}
}
