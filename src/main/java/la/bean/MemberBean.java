package la.bean;

import java.io.Serializable;
import java.sql.Date;

public class MemberBean implements Serializable{
	private int member_id;
    private String member_name;
    private String member_address;
    private String member_tel;
    private String member_email;
    private String member_pass;
    private Date admission_date;
    private Date update_date;
    private Date secession_date;
    private boolean is_seller;

    public MemberBean(int member_id, String member_name, String member_address, String member_tel,
            String member_email) {
        this.member_id = member_id;
        this.member_name = member_name;
        this.member_address = member_address;
        this.member_tel = member_tel;
        this.member_email = member_email;
    }
    
    public MemberBean(String member_name, String member_address, String member_tel,
            String member_email, String member_pass) {
        this.member_name = member_name;
        this.member_address = member_address;
        this.member_tel = member_tel;
        this.member_email = member_email;
        this.member_pass = member_pass;
    }
    
    public MemberBean(int member_id, String member_name, String member_address, String member_tel,
            String member_email, String member_pass, Date admission_date, Date update_date, Date secession_date, boolean is_seller) {
    	this.member_id = member_id;
        this.member_name = member_name;
        this.member_address = member_address;
        this.member_tel = member_tel;
        this.member_email = member_email;
        this.member_pass = member_pass;
        this.admission_date = admission_date;
        this.update_date = update_date;
        this.secession_date = secession_date;
        this.is_seller = is_seller;
    }

    public MemberBean() {

    }

	public int getMember_id() {
		return member_id;
	}

	public void setMember_id(int member_id) {
		this.member_id = member_id;
	}

	public String getMember_name() {
		return member_name;
	}

	public void setMember_name(String member_name) {
		this.member_name = member_name;
	}

	public String getMember_address() {
		return member_address;
	}

	public void setMember_address(String member_address) {
		this.member_address = member_address;
	}

	public String getMember_tel() {
		return member_tel;
	}

	public void setMember_tel(String member_tel) {
		this.member_tel = member_tel;
	}

	public String getMember_email() {
		return member_email;
	}

	public void setMember_email(String member_email) {
		this.member_email = member_email;
	}

	public String getMember_pass() {
		return member_pass;
	}

	public void setMember_pass(String member_pass) {
		this.member_pass = member_pass;
	}

	public Date getAdmission_date() {
		return admission_date;
	}

	public void setAdmission_date(Date admission_date) {
		this.admission_date = admission_date;
	}

	public Date getUpdate_date() {
		return update_date;
	}

	public void setUpdate_date(Date update_date) {
		this.update_date = update_date;
	}

	public Date getSecession_date() {
		return secession_date;
	}

	public void setSecession_date(Date secession_date) {
		this.secession_date = secession_date;
	}

	public boolean isIs_seller() {
		return is_seller;
	}

	public void setIs_seller(boolean is_seller) {
		this.is_seller = is_seller;
	}
}
