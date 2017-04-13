package xwgl.common.dto.json;

import java.util.List;

public class PageResponse<T> implements Response {
	private String code;
	private String msg;
	private Long count;
	private List<T> datas;
	

    public PageResponse(List<T> datas,Long count) {
        this.code = "1";
        msg = "success";
        this.datas = datas;
        this.count=count;
    }

    public PageResponse(String code, String msg, List<T> datas) {
        this.code = code;
        this.msg = msg;
        this.datas = datas;
    }
    
    public List<T> getDatas() {
        return datas;
    }

	public Long getCount() {
		return count;
	}

	public void setCount(Long count) {
		this.count = count;
	}

	@Override
	public String getCode() {
		return code;
	}

	@Override
	public String getMsg() {
		return msg;
	}
}
