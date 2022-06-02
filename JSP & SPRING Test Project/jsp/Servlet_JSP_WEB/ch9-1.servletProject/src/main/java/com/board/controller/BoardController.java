package com.board.controller;

import javax.servlet.http.HttpServletRequest;

import com.main.controller.Controller;
public class BoardController implements Controller{
	@Override
	public String execute(HttpServletRequest request) throws Exception{
		System.out.println(getClass().getSimpleName()+".execute()");
		return null;
	}

}

