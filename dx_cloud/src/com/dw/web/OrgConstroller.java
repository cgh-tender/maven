package com.dw.web;

import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dw.model.Organization;
import com.dw.model.User;
import com.dw.servce.IOrgService;
import com.dw.servce.IUserService;
import com.dw.util.BaseTreeGrid;
import com.google.gson.Gson;

@Controller 
public class OrgConstroller {

	@Autowired
	IOrgService orgService;
	@Autowired
	IUserService userSer;
	/**
	 * 组织机构列表
	 * @param request
	 * @param response
	 */
	@ResponseBody
    @RequestMapping(method = RequestMethod.GET,value = "/orgmanagement/orglist",produces="text/html;charset=UTF-8")
    public void getOrgList(HttpServletRequest request,HttpServletResponse response,@RequestParam("orgidparent") String orgidparent,@RequestParam("orglevel") String orglevel)   {
    	 Gson json=new Gson(); 
    	 boolean jsonP = false;
    	 String cb = request.getParameter("audit");
    	 if (cb != null) {
    	     jsonP = true;
    	     response.setContentType("text/javascript");
    	 } else {
    	     response.setContentType("application/x-json");
    	 }
    	 PrintWriter out;
		try {
			out = response.getWriter();
			if (jsonP) {
	    	     out.write(cb + "(");
	    	 }
			 Organization org = new Organization();
			 org.setOrgIdParent(orgidparent);
			 org.setOrgLevel(orglevel);
			 List<Map<String,String>> rlist = orgService.getOrgList(org);
    		 out.print(json.toJson(rlist));
	    	 if (jsonP) {
	    	     out.write(");");
	    	 }
		}  catch (Exception e) {
			e.printStackTrace();
		}
    }
	/**
	 * 组织机构树形列表
	 * @param request
	 * @param response
	 */
	@ResponseBody
    @RequestMapping(method = RequestMethod.GET,value = "/orgmanagement/orgtreelist",produces="text/html;charset=UTF-8")
    public void getOrgTreeList(HttpServletRequest request,HttpServletResponse response,@RequestParam("orgid") String orgid,@RequestParam("orglevel") String orglevel)   {
    	 Gson json=new Gson(); 
    	 boolean jsonP = false;
    	 String cb = request.getParameter("audit");
    	 if (cb != null) {
    	     jsonP = true;
    	     response.setContentType("text/javascript");
    	 } else {
    	     response.setContentType("application/x-json");
    	 }
    	 PrintWriter out;
		try {
			out = response.getWriter();
			if (jsonP) {
	    	     out.write(cb + "(");
	    	 }
			 Organization org = new Organization();
			 org.setOrgId(orgid);
			 org.setOrgLevel(orglevel);
			 List<Map<String,String>> rlists=orgService.getOrgList(org);
			 BaseTreeGrid bb;
		     List<BaseTreeGrid> rblists=new ArrayList<BaseTreeGrid>();
			 if (rlists != null && rlists.size() > 0) {
				for (Map<String, String> map : rlists) {
					bb=new BaseTreeGrid();
					bb.setId(map.get("org_id"));
					bb.setName(map.get("org_name"));
					bb.setNoede_desc(map.get("org_desc"));
					bb.setLevel(map.get("org_level"));
					bb.setParentId(map.get("org_id_parent"));
					org=new Organization();
					org.setOrgId(map.get("org_id"));
					//添加子节点
					List<BaseTreeGrid> lbs;
				    List<Map<String,String>> rlist = orgService.getOrgTreeList(org);
					 BaseTreeGrid bb1;
					 lbs=new ArrayList<BaseTreeGrid>();
				    for (Map<String, String> map2 : rlist) {
				    	bb1=new BaseTreeGrid(); 
						bb1.setId(map2.get("org_id"));
						bb1.setName(map2.get("org_name"));
						bb1.setNoede_desc(map2.get("org_desc"));
						bb1.setLevel(map2.get("org_level"));
						bb1.setParentId(map2.get("org_id_parent"));
						lbs.add(bb1);
					}
				    bb.setChildren(lbs);
					  rblists.add(bb);
				}
			}
    		 out.print(json.toJson(rblists));
	    	 if (jsonP) {
	    	     out.write(");");
	    	 }
		}  catch (Exception e) {
			e.printStackTrace();
		}
    }
	/**
	 * 新增/修改
	 * @param request
	 * @param response
	 * @param orgEntity
	 */
	@ResponseBody
    @RequestMapping(method = RequestMethod.GET,value = "/orgmanagement/orgUpOrSave",produces="text/html;charset=UTF-8")
    public void saveOrUpdateOrg(HttpServletRequest request,HttpServletResponse response,@RequestParam("orgEntity") String orgEntity)   {
    	 Gson json=new Gson(); 
		 Organization org = new Organization();
		 Map<String,String> map = new HashMap<String,String>();
		 SimpleDateFormat sdf =   new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String orgId =  Calendar.getInstance().getTimeInMillis()+"";
    	 boolean jsonP = false;
    	 String cb = request.getParameter("audit");
    	 if (cb != null) {
    	     jsonP = true;
    	     response.setContentType("text/javascript");
    	 } else {
    	     response.setContentType("application/x-json");
    	 }
    	 PrintWriter out;
		try {
			out = response.getWriter();
			if (jsonP) {
	    	     out.write(cb + "(");
	    	 }
			try {
				if (!"".equals(orgEntity+"")) {
					JSONObject jsons = new JSONObject(orgEntity);
					//String orgName=new String(jsons.getString("org_name").getBytes("iso8859-1"),"UTF-8");
					String orgName= jsons.getString("org_name") ;
					System.out.println(orgName);
					/*String orgDesc= new String(jsons.getString("org_desc").getBytes("iso8859-1"),"UTF-8");
					String org_id_parent=new String(jsons.getString("org_id_parent").getBytes("iso8859-1"),"UTF-8"); 
					String org_level=new String(jsons.getString("org_level").getBytes("iso8859-1"),"UTF-8"); 
					String org_id = new String(jsons.getString("id").getBytes("iso8859-1"),"UTF-8");*/
					
					String orgDesc=  jsons.getString("org_desc") ;
					String org_id_parent= jsons.getString("org_id_parent"); 
					String org_level= jsons.getString("org_level"); 
					String org_id =  jsons.getString("id");
					
					org.setOrgName(orgName);
					org.setOrgDesc(orgDesc);
					org.setOrgIdParent(org_id_parent);
					org.setOrgLevel(org_level);
					 if ("0".equals(org_id)) {
						 org.setOrgId(orgId);
						 //判断组织机构名称是否存在
						 Organization org1 = new Organization();
						 org1.setOrgName(orgName);
						 List<Map<String,String>> orglist =orgService.getOrgList(org1);
						 if (orglist != null && orglist.size() > 0) {
								map.put("msg", "添加失败！组织机构名称已存在！");
							    map.put("code", "0");
							    out.print(json.toJson(map));
							   return;
							}
						   map.put("msg", "添加成功！");
						   org.setCreateTime(sdf.format(new Date()));
					 }else {
						//修改
						
						map.put("msg", "修改成功！"); 
						Organization org1 = new Organization();
						org1.setOrgId(org_id);
						List<Map<String,String>> olist =orgService.getOrgList(org1);
						if (olist == null || olist.size() <= 0 ) {
							map.put("msg", "修改失败！该组织机构不存在！");
						    map.put("code", "0");
						    out.print(json.toJson(map));
						   return;
						}
						org.setUpdateTime(sdf.format(new Date()));
						org.setCreateTime(olist.get(0).get("create_time")+"");
						org.setOrgId(org_id);
						orgService.delOrgs(org_id);
					}
				}
				boolean flage= orgService.saveOrUpOrg(org);
				if(flage){
					 map.put("org_id", orgId);
	        		 map.put("code", "1");
				}else {
					 map.put("code", "0");
					 map.put("msg", "修改失败！该组织机构不存在！"); 
				}
				
			} catch (Exception e) {
				map.put("code", "0");
			    map.put("msg", "操作失败！"); 
			}
    		 out.print(json.toJson(map));
	    	 if (jsonP) {
	    	     out.write(");");
	    	 }
		}  catch (Exception e) {
			e.printStackTrace();
		}
    }
	/**
	 * 组织机构删除
	 */
	@ResponseBody
    @RequestMapping(method = RequestMethod.GET,value = "/orgmanagement/delOrgs",produces="text/html;charset=UTF-8")
    public void delOrgs(HttpServletRequest request,HttpServletResponse response,@RequestParam("ids") String ids)   {
    	 Gson json=new Gson(); 
    	 boolean jsonP = false;
    	 String cb = request.getParameter("audit");
    	 if (cb != null) {
    	     jsonP = true;
    	     response.setContentType("text/javascript");
    	 } else {
    	     response.setContentType("application/x-json");
    	 }
    	 PrintWriter out;
		try {
			out = response.getWriter();
			if (jsonP) {
	    	     out.write(cb + "(");
	    	 }
			Map<String,String> map = new HashMap<String,String>();
			 if (!"".equals(ids) && ids.length() > 0) {
				try {
					String[] strids = ids.split(",");
					boolean flag = false;
					for (int i = 0; i < strids.length; i++) {
						Organization org = new Organization();
						org.setOrgIdParent(strids[i]);
						List<Map<String,String>> orglist = orgService.getOrgList(org);
						User uu = new User();
						uu.setOrganizationId(strids[i]);
						List<Map<String,String>> ulist = userSer.getUserList(uu);
						if ((orglist != null && orglist.size() > 0)  || (ulist != null && ulist.size() > 0 )) {
							flag=true;
							break;
						}
					}
					//ids需要以逗号分割
					if (!flag) {
						orgService.delOrgs(ids);
						 map.put("code", "1");
						 map.put("msg", "删除成功！"); 
					}else {
						 map.put("code", "2");
						 map.put("msg", "删除失败，该组织机构下有子机构或用户！"); 
					}
					
				} catch (Exception e) {
					 map.put("code", "0");
					 map.put("msg", "删除失败！"); 
				}
			}else {
				 map.put("code", "0");
			}
    		 out.print(json.toJson(map));
	    	 if (jsonP) {
	    	     out.write(");");
	    	 }
		}  catch (Exception e) {
			e.printStackTrace();
		}
    }
}
