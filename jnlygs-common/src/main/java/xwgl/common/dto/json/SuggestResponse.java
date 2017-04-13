package xwgl.common.dto.json;

import java.util.List;

public class SuggestResponse<T> implements Response {
	private String code;
	private String msg;
    private List<T> vaule;

    public SuggestResponse(List<T> vaule) {
        this.code = "1";
        msg = "success";
        this.vaule = vaule;
    }

    public SuggestResponse(String code, String msg, List<T> vaule) {
        this.code = code;
        this.msg = msg;
        this.vaule = vaule;
    }
    
    public List<T> getVaule() {
        return vaule;
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
