package rectEdit.handler;

import java.util.HashMap;
import java.util.Map;

public class HandlerRegistry {
	private final Map<String, ActionHandler> handlerMap = new HashMap<>();

	public void register(String key, ActionHandler handler) {
		handlerMap.put(key, handler);
	}

	public ActionHandler get(String key) {
		ActionHandler handler = handlerMap.get(key);
		if (handler == null) {
			throw new IllegalArgumentException("Handler not registered: " + key);
		}
		return handler;
	}
}
