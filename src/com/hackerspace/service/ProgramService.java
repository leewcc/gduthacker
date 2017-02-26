package com.hackerspace.service;

import java.util.ArrayList;
import java.util.List;

import com.hackerspace.dao.LinkDao;
import com.hackerspace.dao.ProgramDao;
import com.hackerspace.model.HaskerSystem;
import com.hackerspace.model.Link;
import com.hackerspace.model.Program;

public class ProgramService {

	public ArrayList<Program> getProgram() {
		ProgramDao pd=new ProgramDao();
		ArrayList<Program> al=pd.getProgram(0);
		for(Program p:al) {
			p.setPrograms(pd.getProgram(p.getId()));
		}
		return al;
	}
	
	public ArrayList<Program> getManagerProgram() {
		ProgramDao pd=new ProgramDao();
		ArrayList<Program> al=pd.getProgram(0);
		for(Program p:al) {
			p.setPrograms(pd.getManagerProgram(p.getId()));
		}
		return  al;
	}

	public boolean saveProgram(Program p) {
		ProgramDao pd=new ProgramDao();
	
		return pd.saveProgram(p);
		
	}

	public HaskerSystem getSystemName() {
		ProgramDao pd=new ProgramDao();
		return pd.getSystemName();
	}

	public boolean saveSystemName(HaskerSystem hs) {
		ProgramDao pd=new ProgramDao();
		return pd.updateSystemName(hs);
	}

	public List<Link> getLists() {
		LinkDao ld=new LinkDao();
		return ld.getLists();
	}
	
	public boolean updateList(List<Link> la,List<Link> lb) {
		LinkDao ld=new LinkDao();
		return ld.updateLists(la)&&ld.saveLists(lb);
	}

	public boolean deleteLink(int id) {
		LinkDao ld=new LinkDao();
		return ld.deleteLists(id);
	}

	public boolean moveProgram(String hid, String fid) {
		ProgramDao ld=new ProgramDao();
		int ihid=Integer.parseInt(hid);
		int ifid=Integer.parseInt(fid);
		if(ihid==ifid) return true;
		return ld.moveProgram(ihid,ifid);
	}
	
	public boolean operationProgram (int id,int status) {
		ProgramDao pd=new ProgramDao();
		return pd.operationProgram(id, status);
	}


}
