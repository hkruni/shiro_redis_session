package com.cmdi.yjs.model;

import lombok.Getter;
import lombok.Setter;

public class User {
	
	@Setter @Getter private String username;
	@Setter @Getter private String password;
	@Setter @Getter private String locked;
}
