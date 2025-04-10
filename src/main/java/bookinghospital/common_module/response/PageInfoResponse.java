package bookinghospital.common_module.response;

import lombok.Getter;

@Getter
public class PageInfoResponse {
	private int page;
	private int size;
	private int totalElements;
	private int totalPage;
}
