package com.dw.servce.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dw.dao.impl.RoleDaoImpl;
import com.dw.model.CityCode;
import com.dw.model.InterFace;
import com.dw.model.Jur;
import com.dw.model.Menu;
import com.dw.model.Role;
import com.dw.model.RoleJur;
import com.dw.model.RootMenu;
import com.dw.servce.IRoleService;
@Service("roleService")
@Transactional
public class RoleServiceImpl implements IRoleService{

	@Autowired
	RoleDaoImpl roleDao;
	@Override
	public List<Role> getRoleList(Role role) {
		return roleDao.getRoleList(role);
	}

	@Override
	public boolean UpdateRole(Role role){
		return roleDao.UpdateRole(role);
	}

	@Override
	public boolean delRole(String ids) {
		return roleDao.delRole(ids);
	}

	@Override
	public List<RootMenu> getRoleList(String roleid,String menulevel,String menuParentId,String type) {
		return roleDao.getRoleList(roleid,menulevel,menuParentId,type);
	}
	
	 public Role addRole(Role role){
		 return roleDao.addRole(role);
	 }

	@Override
	public List<Jur> getJurBymenuId(String menu_id, String role_id,
			String jur_type, String jur_parent_id) {
		 
		List<Jur> resultlist = roleDao.getJurBymenuId(menu_id, role_id, jur_type, jur_parent_id);
		 
		for(Jur jur : resultlist){
			if(jur.getJur_id() == null ){
				jur.setJur_id("");
			}if(jur.getJur_key() == null){
				jur.setJur_key("");
			}if(jur.getJur_value() == null){
				jur.setJur_value("");
			}if(jur.getJur_desc() == null){
				jur.setJur_desc("");
			}if(jur.getJur_parent_id() == null){
				jur.setJur_parent_id("");
			}if(jur.getCreate_time() == null){
				jur.setCreate_time("");
			}if(jur.getUpdate_time() == null){
				jur.setUpdate_time("");
			}if(jur.getChild() == null){
				List list = new ArrayList();
				jur.setChild(list);
			}
		}
		
		 return resultlist;
	}

	@Override
	public Jur addJur(Jur jur){
 		return roleDao.addJur(jur);
	}
	
	public Boolean delJur(String jur_id){
		 return roleDao.delJur(jur_id);
	 }

	public Boolean UpdateJur(Jur fromjur){
		return roleDao.UpdateJur(fromjur);
	}

	
	/**
	 * 
	 * 
	 * 删除角色下的权限
	 */
	public Boolean  delRoleJur(String role_id,String menu_id){
		return roleDao.delRoleJur(role_id,menu_id);
	}
	
	/**
	 * 添加角色权限表
	 * 
	 */
	public Boolean addRoleJur(RoleJur rolejur){
		return roleDao.addRoleJur(rolejur);
	}
	
	/**
	 * 
	 * 添加接口
	 * 
	 */
	public Boolean addInterface(InterFace infa){
		return roleDao.addInterface(infa);
	}
	
	/**
	 * 通过菜单Id查找对应接口
	 * 
	 */
	
	public List<InterFace> getInterfaceAddressByMenuId (Menu me){
		return roleDao.getInterfaceAddressByMenuId(me);
	}
	
	/**
	 * 
	 * 判断级联下是否子菜单
	 * 
	 */
	
	public Boolean checkCascadeChild(String jurId){
		return roleDao.checkCascadeChild(jurId);
	}
	

	/**
	 * 
	 * 查询省分、地市
	 * 
	 */
	public List <Map> jurcitylist(CityCode cityCode){
		return roleDao.jurcitylist(cityCode);
	}
	
	public Boolean deleteInterFace(String mumeId){
		return roleDao.deleteInterFace(mumeId);
	}
	
	/**
	 * 
	 * 通过menu_id,role_id 获得权限
	 * 
	 */
	public List<Map> getJurDetailByMenuIdAndRoleId(String menu_id,String roleid,String jurtype,String jurParentId){
		return roleDao.getJurDetailByMenuIdAndRoleId(menu_id,roleid,jurtype,jurParentId);
	}
	
	 
}
