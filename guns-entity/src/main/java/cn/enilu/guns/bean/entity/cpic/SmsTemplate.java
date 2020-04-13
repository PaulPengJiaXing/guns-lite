package cn.enilu.guns.bean.entity.cpic;

import javax.persistence.*;
import java.util.Objects;

/**
 * @Author: pengjx
 * @Date: 2020/4/11 8:16 下午
 */
@Entity
@Table(name = "sms_template", schema = "gunslite", catalog = "")
public class SmsTemplate {
    private int id;
    private String code;
    private String content;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "code")
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Basic
    @Column(name = "content")
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SmsTemplate that = (SmsTemplate) o;
        return id == that.id &&
                Objects.equals(code, that.code) &&
                Objects.equals(content, that.content);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, code, content);
    }
}
