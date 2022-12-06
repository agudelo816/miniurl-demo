package com.miniurl.common.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DecomposedURL {
	private String fullURL;
	private String scheme;
	private String domain;
	private String topLevelDomain;
	private int portNumber;
	private String path;
	private String queryString;
	private boolean hasQueryString;
}
