package org.iweb.sys.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.iweb.sys.AbstractDAO;
import org.iweb.sys.ToolsUtil;
import org.iweb.sys.domain.User;

public class UserDAO extends AbstractDAO {

	public void add(Object parameters) {
		super.add("sys_addUser", parameters);
	}

	public Object get(Object uuid) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("uuid", uuid);
		return super.get("sys_getUser", map);
	}

	public void save(Object parameters) {
		super.save("sys_mdyUser", parameters);
	}
	
	public void savep(Object parameters) {
		super.save("sys_mdyUserp", parameters);
	}
	
	public void savedeptcode(Object parameters) {
		super.save("sys_mdyUserdept_code", parameters);
	}
	public void savedeptcode2(Object parameters) {
		super.save("sys_mdyUserdept_code2", parameters);
	}
	
	public void saveDept(Object parameters) {
		super.save("sys_mdyUserMDept", parameters);
	}

	public void delete(Object parameters) {
		super.delete("sys_delUser", parameters);
	}

	public List list(Map<String, Object> map) {
		return super.list("sys_getUser", map);
	}
	

	public List listEmail(Map<String, Object> map) {
		return super.list("sys_getUserEmail", map);
	}
	
	public List listBypro(Map<String, Object> map) {
		return super.list("sys_getUserByPos", map);
	}

	public List listCheck(Map<String, Object> map) {
		return super.list("sys_checkUserList", map);
	}

	public void changePWD(Object parameters) {
		super.save("sys_changePWD", parameters);
	}

	public void isave(Object parameters) {
		super.save("sys_imdyUser", parameters);
	}
	
	public void isaveSign(Object parameters) {
		super.save("sys_imdyUserSign", parameters);
	}

	public int checkMember(User user) {
		if (ToolsUtil.isEmpty(user.getTitle()) && ToolsUtil.isEmpty(user.getMobile()) && ToolsUtil.isEmpty(user.getEmail())) {
			return -1;
		} else {
			return (int) super.get("sys_checkMember", user);
		}
	}

	public int getResultCount() {
		return super.getResultCount();
	}
}
