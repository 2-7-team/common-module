package bookinghospital.common_module.userInfo;

import lombok.Getter;

@Getter
public class UserDetails {
	private final String userId;
	private final String role;

	public UserDetails(String id, String role) {
		this.userId = id;
		this.role = role;
	}
}
