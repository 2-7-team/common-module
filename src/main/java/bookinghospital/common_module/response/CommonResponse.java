package bookinghospital.common_module.response;

import lombok.Builder;
import lombok.Getter;

@Getter
public class CommonResponse<T> {
	private String message;
	private T data;

	@Builder
	private CommonResponse(String message, T data) {
		this.message = message;
		this.data = data;
	}


	public static <T> CommonResponse<T> success(T data, String message) {
		return CommonResponse.<T>builder()
			.message(message)
			.data(data)
			.build();
	}

	public static <T> CommonResponse<T> fail(String message) {
		return CommonResponse.<T>builder()
			.message(message)
			.data(null)
			.build();
	}
}
