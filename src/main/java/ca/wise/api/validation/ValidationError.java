package ca.wise.api.validation;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import com.google.common.base.Strings;

import lombok.Getter;

public class ValidationError {
	@Getter
	private final String propertyName;
	
	@Getter
	private final Integer index;
	
	@Getter
	private final String message;
	
	@Getter
	private final Object object;
	
	private final List<ValidationError> children = new ArrayList<>();
	
	public List<ValidationError> getChildren() {
		return Collections.unmodifiableList(children);
	}
	
	public ValidationError(String propertyName, String message, Object object) {
		this.propertyName = propertyName;
		this.index = null;
		this.message = message;
		this.object = object;
	}
	
	public ValidationError(int index, String message, Object object) {
		this.propertyName = null;
		this.index = index;
		this.message = message;
		this.object = object;
	}
	
	public ValidationError addChild(ValidationError child) {
		this.children.add(child);
		return this;
	}
	
	public Object getValue() {
		if (!Strings.isNullOrEmpty(this.propertyName)) {
			try {
				Field[] fields = this.object.getClass().getDeclaredFields();
				Optional<Field> field = Arrays.stream(fields).filter(x -> x.getName().equals(propertyName)).findFirst();
				if (field.isPresent()) {
					field.get().setAccessible(true);
					return field.get().get(object);
				}
				else {
					Method method = this.object.getClass().getMethod("get" + propertyName.substring(0, 1).toUpperCase() + propertyName.substring(1));
					if (method != null) {
						method.setAccessible(true);
						return method.invoke(object);
					}
				}
			}
			catch (SecurityException|IllegalAccessException|NoSuchMethodException|InvocationTargetException ex) { }
		}
		else if (this.index != null) {
			if (this.object instanceof Collection) {
				Collection<?> coll = (Collection<?>)this.object;
				int i = 0;
				for (Object obj : coll) {
					if (i == this.index)
						return obj;
					i++;
				}
			}
		}
		return this;
	}
}
