package com.me.fall2018.assignment3.resource;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.me.fall2018.assignment3.datamodel.Board;
import com.me.fall2018.assignment3.service.BoardService;

@Path("boards")
public class BoardResource {
	
	BoardService boardService = new BoardService();
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Board> getAllBoards(){
		return boardService.getAllBoards();
	}
	
	@GET
	@Path("/{boardId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Board getBoard(@PathParam("boardId") String boardId) {
		return boardService.getBoard(boardId);
	}
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Board addBoard(Board board) {
		return boardService.addBoard(board);
	}
	
	@PUT
	@Path("/{boardId}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Board update(@PathParam("boardId") String boardId, Board board) {
		return boardService.updateBoard(boardId, board);
	}
	
	@DELETE
	@Path("/{boardId}")
	public String deleteBoard(@PathParam("boardId") String boardId) {		
		return boardService.deleteBoard(boardId);
	}
}
