package com.csye6225.fall2018.courseservice.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.csye6225.fall2018.courseservice.datamodel.InMemoryDatabase;
import com.csye6225.fall2018.courseservice.datamodel.Program;

public class ProgramService {

	static HashMap<Long,Program> programDB = InMemoryDatabase.getProgramDB();
	
	//Get all programs
	public List<Program> getAllProgram(){
		List<Program> list = new ArrayList<>();
		for(Program pg: programDB.values()) {
			list.add(pg);
		}
		return list;
	}
	
	//Add a program
	public Program addProgram(Program program) {
		long nextAvailableId = programDB.size() + 1;
		program.setProgramId(nextAvailableId);
		programDB.put(nextAvailableId, program);
		return programDB.get(nextAvailableId);
	}
	
	//Update a program
	public Program updateProgram(Long programId,Program prog) {
		Program old = programDB.get(programId);
		programId = old.getProgramId();
		prog.setProgramId(programId);
		programDB.put(programId, prog);
		return prog;
	}
	
	//Delete a program
	public Program deleteProgram(Long programId) {
		Program details = programDB.get(programId);
		programDB.remove(programId);
		System.out.println(programId);
		return details;
	}
}
