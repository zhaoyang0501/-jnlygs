package xwgl.core.dto;

public class Option {

    private String text;
    private String value;
    private Boolean selected;


    public Option(String text, String value) {
        super();
        this.text = text;
        this.value = value;
    }

    public Option(String text, String value, Boolean selected) {
        super();
        this.text = text;
        this.value = value;
        this.selected = selected;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Boolean getSelected() {
        return selected;
    }

    public void setSelected(Boolean selected) {
        this.selected = selected;
    }

}
