package bookinghospital.common_module.userInfo;

import lombok.Getter;

@Getter
public class UserDetails {
	private final Long userId;
	private final String role;

	public UserDetails(Long id, String role) {
		this.userId = id;
		this.role = role;
	}
}
