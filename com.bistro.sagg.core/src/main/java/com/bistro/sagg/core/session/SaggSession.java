package com.bistro.sagg.core.session;

import java.util.HashMap;
import java.util.Map;

public class SaggSession {

	private static SaggSession session;
	private Map<String, Object> sessionObjects = new HashMap<String, Object>();

	private SaggSession() {
		super();
	}

	public static synchronized SaggSession getCurrentSession() {
		if (session == null) {
			session = new SaggSession();
		}
		return session;
	}

	public void addSessionObject(String key, Object value) {
		this.sessionObjects.put(key, value);
	}

	public <T> T getSessionObject(String key) {
		if (this.sessionObjects.containsKey(key)) {
			return (T) this.sessionObjects.get(key);
		}
		return null;
	}

	public <T> T removeSessionObject(String key) {
		if (this.sessionObjects.containsKey(key)) {
			return (T) this.sessionObjects.remove(key);
		}
		return null;
	}
}
