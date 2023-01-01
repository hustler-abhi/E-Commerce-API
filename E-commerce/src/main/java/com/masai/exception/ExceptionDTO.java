package com.masai.exception;

import java.time.LocalDateTime;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@NoArgsConstructor
@Getter
@Setter
public class ExceptionDTO {
	
	private LocalDateTime  dateAndTime;
	private String message;
	private String desc;
 
	

}
