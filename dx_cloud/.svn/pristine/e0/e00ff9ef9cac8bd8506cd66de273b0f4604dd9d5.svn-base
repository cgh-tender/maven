package com.dw.util;

import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;

/**
 * 将记录list转化为树形list 
 * 基于BaseTreeGid类的转换 
 * @author hutianlong
 *
 */
public class TreeUtils {
	public static <T extends BaseTreeGrid> List<T> formatTree(List<T> list, Boolean flag) {  
		  
        List<T> nodeList = new ArrayList<T>();    
        for(T node1 : list){    
            boolean mark = false;    
            for(T node2 : list){    
                if(node1.getParentId()!=null && node1.getParentId().equals(node2.getId())){   
                    node2.setLeaf(false);  
                    mark = true;    
                    if(node2.getChildren() == null) {  
                        node2.setChildren(new ArrayList<BaseTreeGrid>());    
                    }  
                    node2.getChildren().add(node1);   
                    if (flag) {  
                        //默认已经全部展开  
                    } else{  
                        node2.setExpanded(false);  
                    }  
                    break;    
                }    
            }    
            if(!mark){    
                nodeList.add(node1);     
                if (flag) {  
                    //默认已经全部展开  
                } else{  
                    node1.setExpanded(false);  
                }  
            }    
        }  
        return nodeList;  
    }  
	
	public static void main(String[] args) {
		String a="[{'大数据事业部':{'数据经理':[{'org_name':'数据经理','id':1,'user_name':'11','phone':'18600241929','role_id':'1508832395901','pass_show':'dsdod','email':'hutianlong@dwsoft.com.cn','organization_id':'1509431777204','login_name':'admin','create_time':'2017-09-11 19:46:01','user_id':'1505130419462','role_name':'测试2'},{'org_name':'数据经理','id':9,'user_name':'11','phone':'18600241929','role_id':'1508832395901','pass_show':'1','email':'hutianlong@dwsoft.com.cn','organization_id':'1509431777204','login_name':'htl','create_time':'2017-09-11 19:46:01','user_id':'1505130419461','role_name':'测试2'},{'org_name':'数据经理','id':16,'user_name':'test','phone':'12345678111','role_id':'1508832395901','pass_show':'1','email':'21212@qq.com','organization_id':'1509431777204','login_name':'test','create_time':'2017-10-24 17:24:14','user_id':'1508837054418','role_name':'测试2'},{'org_name':'数据经理','id':19,'user_name':'建坤','phone':'13661230296','role_id':'1','pass_show':'a12345678','email':'3275@qq.com','organization_id':'1509431777204','login_name':'test20171031','create_time':'2017-10-31 16:37:25','user_id':'1509439045857'}]}}][{'研发测试组':{'前端开发':[{'org_name':'前端开发','id':14,'user_name':'bgg','phone':'18600241929','role_id':'1508832395901','pass_show':'1','email':'hutianlong@dwsoft.com.cn','organization_id':'1509431803125','login_name':'bgg','create_time':'2017-09-11 17:59:50','user_id':'1508830508172','role_name':'测试2'}]},'大数据事业部':{'数据经理':[{'org_name':'数据经理','id':1,'user_name':'11','phone':'18600241929','role_id':'1508832395901','pass_show':'dsdod','email':'hutianlong@dwsoft.com.cn','organization_id':'1509431777204','login_name':'admin','create_time':'2017-09-11 19:46:01','user_id':'1505130419462','role_name':'测试2'},{'org_name':'数据经理','id':9,'user_name':'11','phone':'18600241929','role_id':'1508832395901','pass_show':'1','email':'hutianlong@dwsoft.com.cn','organization_id':'1509431777204','login_name':'htl','create_time':'2017-09-11 19:46:01','user_id':'1505130419461','role_name':'测试2'},{'org_name':'数据经理','id':16,'user_name':'test','phone':'12345678111','role_id':'1508832395901','pass_show':'1','email':'21212@qq.com','organization_id':'1509431777204','login_name':'test','create_time':'2017-10-24 17:24:14','user_id':'1508837054418','role_name':'测试2'},{'org_name':'数据经理','id':19,'user_name':'建坤','phone':'13661230296','role_id':'1','pass_show':'a12345678','email':'3275@qq.com','organization_id':'1509431777204','login_name':'test20171031','create_time':'2017-10-31 16:37:25','user_id':'1509439045857'}]}},{'研发测试组':{'前端开发':[{'org_name':'前端开发','id':14,'user_name':'bgg','phone':'18600241929','role_id':'1508832395901','pass_show':'1','email':'hutianlong@dwsoft.com.cn','organization_id':'1509431803125','login_name':'bgg','create_time':'2017-09-11 17:59:50','user_id':'1508830508172','role_name':'测试2'}]},'大数据事业部':{'数据经理':[{'org_name':'数据经理','id':1,'user_name':'11','phone':'18600241929','role_id':'1508832395901','pass_show':'dsdod','email':'hutianlong@dwsoft.com.cn','organization_id':'1509431777204','login_name':'admin','create_time':'2017-09-11 19:46:01','user_id':'1505130419462','role_name':'测试2'},{'org_name':'数据经理','id':9,'user_name':'11','phone':'18600241929','role_id':'1508832395901','pass_show':'1','email':'hutianlong@dwsoft.com.cn','organization_id':'1509431777204','login_name':'htl','create_time':'2017-09-11 19:46:01','user_id':'1505130419461','role_name':'测试2'},{'org_name':'数据经理','id':16,'user_name':'test','phone':'12345678111','role_id':'1508832395901','pass_show':'1','email':'21212@qq.com','organization_id':'1509431777204','login_name':'test','create_time':'2017-10-24 17:24:14','user_id':'1508837054418','role_name':'测试2'},{'org_name':'数据经理','id':19,'user_name':'建坤','phone':'13661230296','role_id':'1','pass_show':'a12345678','email':'3275@qq.com','organization_id':'1509431777204','login_name':'test20171031','create_time':'2017-10-31 16:37:25','user_id':'1509439045857'}]}}][{'其他组':{},'研发测试组':{'前端开发':[{'org_name':'前端开发','id':14,'user_name':'bgg','phone':'18600241929','role_id':'1508832395901','pass_show':'1','email':'hutianlong@dwsoft.com.cn','organization_id':'1509431803125','login_name':'bgg','create_time':'2017-09-11 17:59:50','user_id':'1508830508172','role_name':'测试2'}]},'大数据事业部':{'数据经理':[{'org_name':'数据经理','id':1,'user_name':'11','phone':'18600241929','role_id':'1508832395901','pass_show':'dsdod','email':'hutianlong@dwsoft.com.cn','organization_id':'1509431777204','login_name':'admin','create_time':'2017-09-11 19:46:01','user_id':'1505130419462','role_name':'测试2'},{'org_name':'数据经理','id':9,'user_name':'11','phone':'18600241929','role_id':'1508832395901','pass_show':'1','email':'hutianlong@dwsoft.com.cn','organization_id':'1509431777204','login_name':'htl','create_time':'2017-09-11 19:46:01','user_id':'1505130419461','role_name':'测试2'},{'org_name':'数据经理','id':16,'user_name':'test','phone':'12345678111','role_id':'1508832395901','pass_show':'1','email':'21212@qq.com','organization_id':'1509431777204','login_name':'test','create_time':'2017-10-24 17:24:14','user_id':'1508837054418','role_name':'测试2'},{'org_name':'数据经理','id':19,'user_name':'建坤','phone':'13661230296','role_id':'1','pass_show':'a12345678','email':'3275@qq.com','organization_id':'1509431777204','login_name':'test20171031','create_time':'2017-10-31 16:37:25','user_id':'1509439045857'}]}},{'其他组':{},'研发测试组':{'前端开发':[{'org_name':'前端开发','id':14,'user_name':'bgg','phone':'18600241929','role_id':'1508832395901','pass_show':'1','email':'hutianlong@dwsoft.com.cn','organization_id':'1509431803125','login_name':'bgg','create_time':'2017-09-11 17:59:50','user_id':'1508830508172','role_name':'测试2'}]},'大数据事业部':{'数据经理':[{'org_name':'数据经理','id':1,'user_name':'11','phone':'18600241929','role_id':'1508832395901','pass_show':'dsdod','email':'hutianlong@dwsoft.com.cn','organization_id':'1509431777204','login_name':'admin','create_time':'2017-09-11 19:46:01','user_id':'1505130419462','role_name':'测试2'},{'org_name':'数据经理','id':9,'user_name':'11','phone':'18600241929','role_id':'1508832395901','pass_show':'1','email':'hutianlong@dwsoft.com.cn','organization_id':'1509431777204','login_name':'htl','create_time':'2017-09-11 19:46:01','user_id':'1505130419461','role_name':'测试2'},{'org_name':'数据经理','id':16,'user_name':'test','phone':'12345678111','role_id':'1508832395901','pass_show':'1','email':'21212@qq.com','organization_id':'1509431777204','login_name':'test','create_time':'2017-10-24 17:24:14','user_id':'1508837054418','role_name':'测试2'},{'org_name':'数据经理','id':19,'user_name':'建坤','phone':'13661230296','role_id':'1','pass_show':'a12345678','email':'3275@qq.com','organization_id':'1509431777204','login_name':'test20171031','create_time':'2017-10-31 16:37:25','user_id':'1509439045857'}]}},{'其他组':{},'研发测试组':{'前端开发':[{'org_name':'前端开发','id':14,'user_name':'bgg','phone':'18600241929','role_id':'1508832395901','pass_show':'1','email':'hutianlong@dwsoft.com.cn','organization_id':'1509431803125','login_name':'bgg','create_time':'2017-09-11 17:59:50','user_id':'1508830508172','role_name':'测试2'}]},'大数据事业部':{'数据经理':[{'org_name':'数据经理','id':1,'user_name':'11','phone':'18600241929','role_id':'1508832395901','pass_show':'dsdod','email':'hutianlong@dwsoft.com.cn','organization_id':'1509431777204','login_name':'admin','create_time':'2017-09-11 19:46:01','user_id':'1505130419462','role_name':'测试2'},{'org_name':'数据经理','id':9,'user_name':'11','phone':'18600241929','role_id':'1508832395901','pass_show':'1','email':'hutianlong@dwsoft.com.cn','organization_id':'1509431777204','login_name':'htl','create_time':'2017-09-11 19:46:01','user_id':'1505130419461','role_name':'测试2'},{'org_name':'数据经理','id':16,'user_name':'test','phone':'12345678111','role_id':'1508832395901','pass_show':'1','email':'21212@qq.com','organization_id':'1509431777204','login_name':'test','create_time':'2017-10-24 17:24:14','user_id':'1508837054418','role_name':'测试2'},{'org_name':'数据经理','id':19,'user_name':'建坤','phone':'13661230296','role_id':'1','pass_show':'a12345678','email':'3275@qq.com','organization_id':'1509431777204','login_name':'test20171031','create_time':'2017-10-31 16:37:25','user_id':'1509439045857'}]}}]";
		JSONArray array_test = new JSONArray();
		array_test.add(a);
		for (Object object : array_test) {
			System.out.println(object);
		}
		System.out.println(array_test);
	}
}
