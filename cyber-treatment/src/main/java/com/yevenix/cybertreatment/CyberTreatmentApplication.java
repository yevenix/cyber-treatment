package com.yevenix.cybertreatment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 启动类
 * @author yevenix
 */
@SpringBootApplication
public class CyberTreatmentApplication {
	public static void main(String[] args) {
		// 启动项目
		SpringApplication.run(CyberTreatmentApplication.class, args);
		System.out.println("启动成功！");
	}

}
