package com.demo.vlada.controllers;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.demo.vlada.compiler.DynamicCompiler;
import com.demo.vlada.compiler.InlineCompiler;
import com.demo.vlada.dto.FileEDto;
import com.demo.vlada.dto.TextAreaDto;
import com.demo.vlada.entities.FileE;
import com.demo.vlada.network.Response;
import com.demo.vlada.services.FileService;
import com.demo.vlada.util.UtilHelper;

@RestController
@RequestMapping(value="/rest")
public class IndexController {

	@RequestMapping(value="/executeTextArea", method=RequestMethod.POST, produces="application/json; charset=UTF-8")
	public void executeTextArea(@RequestBody TextAreaDto text) {
		System.out.println("-----------------------------Controller");
		try {
//			DynamicCompiler.activate(text.getCodeText());
			InlineCompiler.exec(text.getCodeText());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		return new ResponseEntity<Response>(new Response(UtilHelper.executeTextArea(text)), HttpStatus.OK);
	}
	
	
	
}
