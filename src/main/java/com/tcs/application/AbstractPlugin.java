package com.tcs.application;

public abstract class AbstractPlugin implements Plugin {
	
	private String id;
	private String name;
	private String identifier;
	private String version;
	private String activatorMethod;
	private String deActivatorMethod;

	@Override
	public void setPlugId(String id) {
		this.id = id;
	}

	@Override
	public String getPlugId() {
		return id;
	}

	@Override
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}

	@Override
	public String getIdentifier() {
		return identifier;
	}

	@Override
	public String getVersion() {
		return version;
	}

	@Override
	public String getClassName() {
		return this.getClass().getName();
	}

	@Override
	public String getActivatorMethod() {
		return activatorMethod;
	}

	@Override
	public String getDeActivatorMethod() {
		return deActivatorMethod;
	}
	
	public void setVersion(String version) {
		this.version = version;
	}

	public void setActivatorMethod(String activatorMethod) {
		this.activatorMethod = activatorMethod;
	}

	public void setDeActivatorMethod(String deActivatorMethod) {
		this.deActivatorMethod = deActivatorMethod;
	}

}
