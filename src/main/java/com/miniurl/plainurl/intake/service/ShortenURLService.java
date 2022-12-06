package com.miniurl.plainurl.intake.service;

import java.security.MessageDigest;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.miniurl.plainurl.intake.model.HashedURL;
import com.miniurl.plainurl.intake.model.PlainURL;
import com.miniurl.plainurl.intake.model.PreprocessedPlainURL;
import com.miniurl.plainurl.intake.util.URLIntakeUtility;

@Component
public class ShortenURLService {
	// <k:v>
	// hashedurl: originalurl
	private final HashMap<String, String> urlMap = new HashMap<>();


	@Autowired
	private URLIntakeUtility urlIntakeUtility;

	public HashedURL processPlainURL(final PlainURL plainURL) {


		// map url
		/*
		 * MAP URL parse incoming url: protocol, domain (top, extension), path,
		 * querystring separator, fragment
		 */

		// handle errors: catch, throw?

		// build response

		HashedURL hashedURL = new HashedURL();
		try {
			// preprocess
			final PreprocessedPlainURL preprocessedPlainURL = this.urlIntakeUtility.preprocessPlainURL(plainURL);

			// process aka hash
			byte[] predigestedURL = preprocessedPlainURL.getFullURL().getBytes();
			MessageDigest md = MessageDigest.getInstance("MD5");

			// finish process and postprocess aka store data
			hashedURL = this.urlIntakeUtility.buildHashedURL(md.digest(predigestedURL));

			//			hashedURL.setPath(rawpostdigestURL.toString());
			this.urlMap.put(plainURL.getFullURL(), hashedURL.getPath());

		} catch (final Exception exc) {
		} finally {

		}

		return hashedURL;
	}


}
