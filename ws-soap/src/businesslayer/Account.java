package businesslayer;

import javax.xml.bind.annotation.*;
import java.util.Date;

@XmlRootElement(name = "account")
@XmlAccessorType(XmlAccessType.FIELD) //Put annotation jaxB on attribute (by default on getters and setters)
public class Account {
    @XmlAttribute
    private Integer code;
    @XmlElement
    private Double balance;
    @XmlTransient //Not serializable
    private Date creationDate;

    public Account(Integer code, Double balance, Date creationDate) {
        this.code = code;
        this.balance = balance;
        this.creationDate = creationDate;
    }

    public Account() {
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }
}
