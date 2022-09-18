package br.com.renato.lst_rest.controllers.exception;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StandardError implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Integer httpCode;
	private String msg;
	private String dh;
	
	public StandardError(Integer httpCode, String msg) {
		super();
		this.httpCode = httpCode;
		this.msg = msg;
		this.dh =  new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
	}

}
