package nz.net.osnz.dto;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author Kefeng Deng (deng@51any.com) - Created on 9/03/15.
 */
@Component
@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class UserDto implements Serializable {

	public Map<Long, Integer> shoppingCart;

	public String username;

	public void putItem(Long itemId, Integer quantity) {
		if (shoppingCart == null) {
			this.shoppingCart = new LinkedHashMap<>();
		}
		shoppingCart.put(itemId, quantity);
	}

	@Override
	public String toString() {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("User [").append(this.username).append(" ]:");
		if (shoppingCart == null || shoppingCart.isEmpty()) {
			stringBuilder.append("empty");
		} else {
			shoppingCart.forEach((k, v) -> {
				stringBuilder.append("[").append(k).append(":").append(v).append("]");
			});
		}
		return stringBuilder.toString();
	}

}
