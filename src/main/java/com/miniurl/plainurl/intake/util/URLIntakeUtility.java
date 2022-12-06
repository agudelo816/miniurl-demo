package com.miniurl.plainurl.intake.util;

import org.springframework.stereotype.Component;

import com.miniurl.common.model.DecomposedURL;
import com.miniurl.plainurl.intake.model.HashedURL;
import com.miniurl.plainurl.intake.model.PlainURL;
import com.miniurl.plainurl.intake.model.PreprocessedPlainURL;

@Component
public class URLIntakeUtility {

	/**
	 * Handles operations needed to be performed ahead of hashing such as
	 * safening...
	 *
	 * @param rawURL
	 * @return
	 */

	private static final String shortenedURLDomain = "localhost";
	private static final String shortenedURLScheme = "http";
	private static final String shortenedURLTopLevelDomain = "";
	private static final String shortenedURLContextPath = "/api";
	private static final String backslash = "/";
	private static final String colon = ":";

	private static final int shortenedURLPort = 8080;

	//	pri
	public PreprocessedPlainURL preprocessPlainURL(PlainURL plainURL) {
		DecomposedURL decomposedURL = this.decomposeURL(plainURL);
		PreprocessedPlainURL preprocessedPlainURL = new PreprocessedPlainURL();
		preprocessedPlainURL.setFullURL(decomposedURL.getFullURL());
		return preprocessedPlainURL;
	}

	private DecomposedURL decomposeURL(PlainURL plainURL) {
		DecomposedURL decomposedURL = new DecomposedURL();
		decomposedURL.setFullURL(plainURL.getFullURL());
		return decomposedURL;
	}

	public HashedURL buildHashedURL(byte[] postdigestURL) {
		HashedURL hashedURL = new HashedURL();
		hashedURL.setDomain(shortenedURLDomain);
		hashedURL.setPath(shortenedURLContextPath + backslash + postdigestURL.toString());
		hashedURL.setPortNumber(shortenedURLPort);
		hashedURL.setScheme(shortenedURLScheme);
		hashedURL.setFullURL(hashedURL.getScheme() + colon + backslash + backslash + hashedURL.getDomain() + colon
				+ Integer.toString(shortenedURLPort)
				+ hashedURL.getPath());

		return hashedURL;
	}
}
