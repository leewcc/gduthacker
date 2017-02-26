//package com.hackerspace.action.view.user;
//
//import java.util.Date;
//import java.util.List;
//import java.util.Map;
//
//import javax.servlet.http.HttpServletRequest;
//
//import org.apache.struts2.ServletActionContext;
//import org.slf4j.Logger;
//
//import jdk.nashorn.internal.ir.RuntimeNode.Request;
//
//import com.hackerspace.model.Classroom;
//import com.hackerspace.model.ClassroomApply;
//import com.hackerspace.service.ClassroomApplyService;
//import com.hackerspace.service.ClassroomService;
//import com.hackerspace.util.Log;
//import com.opensymphony.xwork2.ActionContext;
//import com.opensymphony.xwork2.ActionSupport;
//import com.opensymphony.xwork2.ModelDriven;
//
//public class ClassroomAction extends ActionSupport implements ModelDriven<ClassroomApply>{
//	private ClassroomApply ca;
//	
//	private static Logger log = Log.get();
//	
//	public ClassroomApply getCa() {
//		return ca;
//	}
//
//	public void setCa(ClassroomApply ca) {
//		this.ca = ca;
//	}
//
//	public String selectEmptyClassroom(){
//		try{
//			//第一步：	创建课室服务、课室申请服务
//			ClassroomService cs = new ClassroomService();
//			ClassroomApplyService cas = new ClassroomApplyService();
//			
//			//第二步：	获取选中时间的被申请的课室、所有课室
//			List<Classroom> classrooms = cs.selectClassroom();
//			
//			Log.info(log, "selectEmptyClassroom()教室查询完毕;共{}个教室", classrooms.size());
//			List<ClassroomApply> classroomApplies = cas.selectApply(ca.getDate(), ca.getStatus());
//			
//			Log.info(log, "selectEmptyClassroom()申请的教室查询完毕,共{}申请", classroomApplies.size());
//			
//			Log.info(log, "selectEmptyClassroom()");
//			//TODO 1. 没有课室的情况; 
//			//     2. 申请的课室被删除的情况
//			
//			//第三步：	移除已被申请的课室
//			for(ClassroomApply c : classroomApplies) {
//				classrooms.remove(c.getClassroom());
//			}
//			
//			//第四步：	将空闲课室存进请求
//			ActionContext ac = ActionContext.getContext();
//			Map req = (Map)ac.get("request");
//			HttpServletRequest hsq = ServletActionContext.getRequest();
//			hsq.setAttribute("classrooms", classrooms);
//			hsq.setAttribute("status", ca.getStatus());
//			hsq.setAttribute("date", ca.getDate().toString());
//			
//			return SUCCESS;
//			
//		}catch(Exception e){
//			e.printStackTrace();
//			return ERROR;
//		}
//	}
//
//	private int cid;
//	
//	
//	public int getCid() {
//		return cid;
//	}
//
//	public void setCid(int cid) {
//		this.cid = cid;
//	}
//
//	public String applyClassroom(){
//		try{
//			//第一步：	创建课室服务、课室申请服务
//			ClassroomService cs = new ClassroomService();
//			ClassroomApplyService cas = new ClassroomApplyService();
//			
//			//第二步：	获取课室
//			Classroom classroom = cs.find(cid);
//			ca.setClassroom(classroom);
//			
//			//第三步：	执行提交申请的方法
//			cas.create(ca);
//			
//			return SUCCESS;
//			
//		}catch(Exception e){
//			e.printStackTrace();
//			return ERROR;
//		}
//	}
//	
//	@Override
//	public ClassroomApply getModel() {
//		if(ca == null)
//			ca = new ClassroomApply(); 
//		
//		return ca;
//	}
//	
//	
//}
